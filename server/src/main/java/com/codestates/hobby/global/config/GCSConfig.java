package com.codestates.hobby.global.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Profile("prod & gcs")
@Configuration
public class GCSConfig {
	@Bean
	public Storage storage() throws IOException {
		GoogleCredentials credentials =
			GoogleCredentials.fromStream(getClass().getResourceAsStream("/key.json"));

		return StorageOptions.newBuilder()
			.setCredentials(credentials)
			.build().getService();
	}
}
