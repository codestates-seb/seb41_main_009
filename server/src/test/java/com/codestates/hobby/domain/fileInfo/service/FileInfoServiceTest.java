package com.codestates.hobby.domain.fileInfo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static org.mockito.BDDMockito.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileInfoServiceTest {
	FileInfoRepository repository;
	FileInfoService service;
	Storage storage;

	String fileURL, bucketName, domain;
	int duration;

	@BeforeAll
	void beforeAll() {
		repository = mock(FileInfoRepository.class);
		storage = mock(Storage.class);
		service = new GCSFileInfoService(repository, storage);

		duration = 15;
		bucketName = "intorest-images";
		domain = "https://storage.cloud.google.com";
		fileURL = String.join("/", domain, bucketName, "basepath/filename");

		ReflectionTestUtils.setField(service, "domain", domain);
		ReflectionTestUtils.setField(service, "bucketName", bucketName);
		ReflectionTestUtils.setField(service, "duration", duration);
	}

	@Nested
	@DisplayName("Create Entity")
	class EntityTest {
		@Test
		void Should_Success_Create_Entity() {
			FileInfo info = FileInfo.createShowcaseImage(null, fileURL);

			assertEquals(bucketName, info.getBucket());
			assertEquals("basepath", info.getBasePath());
			assertEquals("filename", info.getFilename());
			assertEquals(String.join("/", "basepath", "filename"), info.getPath());
		}

		@Test
		void Should_Fail_For_Invalid_FileURL() {
			String url = String.join("/", domain, "basepath/fileName");
			assertThrows(IllegalArgumentException.class, () -> FileInfo.createShowcaseImage(null, url));
		}
	}

	@TestFactory
	Stream<DynamicTest> generateSignedURL() throws MalformedURLException {
		URL url = new URL("http", domain, "/");

		given(repository.existsByFileURL(anyString())).willReturn(false);
		given(storage.signUrl(any(), anyLong(), any(), ArgumentMatchers.<Storage.SignUrlOption>any())).willReturn(url);

		return Stream.of(ImageType.values())
			.map(type -> dynamicTest(type.name(), () -> {
				// when
				SignedURL signedURL = service.generateSignedURL(type, BasePath.SHOWCASES);

				// then
				assertTrue(signedURL.getFileURL().startsWith(domain));
				assertEquals(url.toString(), signedURL.getSignedURL());
				assertEquals(type, signedURL.getType());
			}));
	}

	@Test
	void generateSignedURLs() throws MalformedURLException {
		// given
		List<ImageType> types = List.of(ImageType.values());
		URL url = new URL("http", domain, "/");

		given(repository.existsByFileURL(anyString())).willReturn(false);
		given(storage.signUrl(any(), anyLong(), any(), ArgumentMatchers.<Storage.SignUrlOption>any())).willReturn(url);

		// when
		List<SignedURL> signedURLs = service.generateSignedURLs(types, BasePath.SHOWCASES);

		// then
		assertIterableEquals(types, signedURLs.stream().map(SignedURL::getType).collect(Collectors.toList()));
		signedURLs.forEach(signedURL -> assertEquals(url.toString(), signedURL.getSignedURL()));
		signedURLs.forEach(signedURL -> assertTrue(signedURL.getFileURL().startsWith(domain)));
	}

	@Test
	void delete() {
		given(storage.delete(any(BlobId.class))).willReturn(true);
		willDoNothing().given(repository).delete(any(FileInfo.class));

		service.delete(FileInfo.createShowcaseImage(null, fileURL));
	}

	@Test
	void deleteAll() {
		given(storage.delete(anyList())).willReturn(List.of(true));
		willDoNothing().given(repository).deleteAll(anyList());

		service.delete(List.of(FileInfo.createShowcaseImage(null, fileURL)));
	}

	@Test
	void generateRandomFilename() {
		Pattern pattern = Pattern.compile("([\\-\\w]+)/([\\-\\w]+)\\.([a-z]+)");
		BasePath basePath = BasePath.SHOWCASES;
		ImageType type = ImageType.PNG;

		String res = service.generateRandomFilename(type, basePath);
		Matcher matcher = pattern.matcher(res);

		assertTrue(matcher.find());
		assertEquals(basePath.toString(), matcher.group(1));
		assertEquals(type.getExtension(), matcher.group(3));
	}
}