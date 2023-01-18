package com.codestates.hobby.domain.fileInfo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;
import com.codestates.hobby.domain.stub.FileInfoStub;
import com.codestates.hobby.domain.stub.ShowcaseStub;
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
			FileInfo info = new FileInfo(ShowcaseStub.createShowcase(), fileURL, 0);

			assertEquals(bucketName, info.getToken(FileInfo.TOKEN.BUCKET));
			assertEquals("basepath", info.getToken(FileInfo.TOKEN.BASEPATH));
			assertEquals("filename", info.getToken(FileInfo.TOKEN.FILENAME));
			assertEquals(String.join("/", "basepath", "filename"), info.getToken(FileInfo.TOKEN.PATH));
		}

		@Test
		void Should_Fail_For_Invalid_FileURL() {
			String url = String.join("/", domain, "basepath/fileName");
			assertThrows(IllegalArgumentException.class, () -> new FileInfo(ShowcaseStub.createShowcase(), url, 0));
		}
	}

	@Test
	void generateSignedURL() throws MalformedURLException {
		// given
		FileRequestDto request = FileInfoStub.createRequest(0);
		URL url = new URL("http", domain, "/");

		given(repository.existsByFileURL_FileUrl(anyString())).willReturn(false);
		given(storage.signUrl(any(), anyLong(), any(), ArgumentMatchers.<Storage.SignUrlOption>any())).willReturn(url);

		// when
		FileInfo fileInfo = service.generateSignedURL(request, BasePath.SHOWCASES);

		// then
		assertEquals(request.getIndex(), fileInfo.getIndex());
		assertEquals(request.getContentType(), fileInfo.getImageType());
		assertTrue(fileInfo.getFileURL().endsWith(request.getContentType().getExtension()));
	}

	@Test
	void generateSignedURLs() throws MalformedURLException {
		// given
		List<FileRequestDto> requests = FileInfoStub.createRequests(0, 5);
		URL url = new URL("http", domain, "/");

		given(repository.existsByFileURL_FileUrl(anyString())).willReturn(false);
		given(storage.signUrl(any(), anyLong(), any(), ArgumentMatchers.<Storage.SignUrlOption>any())).willReturn(url);

		// when
		List<FileInfo> response = service.generateSignedURLs(requests, BasePath.SHOWCASES);
		requests.sort(Comparator.comparingInt(FileRequestDto::getIndex));
		response.sort(Comparator.comparingInt(FileInfo::getIndex));

		// then
		assertEquals(requests.size(), response.size());
		IntStream.range(0, requests.size())
			.forEach(idx -> {
				assertEquals(requests.get(idx).getIndex(), response.get(idx).getIndex());
				assertEquals(requests.get(idx).getContentType(), response.get(idx).getImageType());
				assertTrue(response.get(idx).getFileURL().endsWith(requests.get(idx).getContentType().getExtension()));
			});
	}

	@Test
	void delete() {
		given(storage.delete(any(BlobId.class))).willReturn(true);
		willDoNothing().given(repository).delete(any(FileInfo.class));

		service.delete(new FileInfo(ShowcaseStub.createShowcase(), fileURL, 0));
	}

	@Test
	void deleteAll() {
		given(storage.delete(anyList())).willReturn(List.of(true));
		willDoNothing().given(repository).deleteAll(anyList());

		service.delete(List.of(new FileInfo(ShowcaseStub.createShowcase(), fileURL, 0)));
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