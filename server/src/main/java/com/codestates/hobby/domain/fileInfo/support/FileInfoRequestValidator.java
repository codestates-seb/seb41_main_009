package com.codestates.hobby.domain.fileInfo.support;

import static org.springframework.util.StringUtils.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;

public class FileInfoRequestValidator implements ConstraintValidator<FileInfoRequest, FileRequestDto> {
	private static final Pattern pattern = Pattern.compile("([\\w\\-.]+)");
	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

	@Override
	public void initialize(FileInfoRequest constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(FileRequestDto dto, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		return hasText(dto.getFileURL())
			? validateIsUnchanged(dto, context)
			: validateIsNew(dto, context);
	}

	private boolean validateIsNew(FileRequestDto dto, ConstraintValidatorContext context) {
		dto.setNew(true);
		return isSizeInRange(dto, context) && isContentTypePresent(dto, context);
	}

	private boolean validateIsUnchanged(FileRequestDto dto, ConstraintValidatorContext context) {
		Matcher matcher = pattern.matcher(dto.getFileURL());
		if (!matcher.find()) {
			return addConstraintViolation(context, "Invalid URL (url: " + dto.getFileURL() + ")");
		}

		dto.setNew(false);
		return true;
	}

	private boolean isSizeInRange(FileRequestDto dto, ConstraintValidatorContext context) {
		if (0 < dto.getSize() && dto.getSize() <= MAX_FILE_SIZE)
			return true;

		return addConstraintViolation(context, "Size must be between 0 and 10MB.");
	}

	private boolean isContentTypePresent(FileRequestDto dto, ConstraintValidatorContext context) {
		if (dto.getContentType() != null)
			return true;

		return addConstraintViolation(context, "Content type must not be empty.");
	}

	private boolean addConstraintViolation(ConstraintValidatorContext context, String msg) {
		context.buildConstraintViolationWithTemplate(msg)
			.addConstraintViolation();
		return false;
	}
}
