package com.codestates.hobby.domain.auth.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.auth.dto.CertificationCreatedEvent;
import com.codestates.hobby.domain.auth.dto.CertificationPatchRequest;
import com.codestates.hobby.domain.auth.entity.Certification;
import com.codestates.hobby.domain.auth.repository.CertificationRepository;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CertificationService {
	private final CertificationRepository certificationRepository;
	private final MemberRepository memberRepository;

	private final ApplicationEventPublisher eventPublisher;

	public void attempt(String email) {
		verifyNotExistEmail(email);

		Certification cert = certificationRepository.findByEmail(email)
			.orElseGet(() -> certificationRepository.save(new Certification(email)));

		cert.initCode();

		eventPublisher.publishEvent(new CertificationCreatedEvent(email, cert.getCode()));
	}

	public void certify(CertificationPatchRequest request) {
		Certification cert = findCertification(request.getEmail(), ExceptionCode.NOT_FOUND_EMAIL);

		if (cert.isCertified()) return;

		cert.validate();

		if (!cert.matches(request.getCode()))
			throw new BusinessLogicException(ExceptionCode.CERTIFICATION_NOT_MATCH);
	}

	public void verifyEmail(String email) {
		Certification cert = findCertification(email, ExceptionCode.NOT_CERTIFICATION);

		if (!cert.isCertified())
			throw new BusinessLogicException(ExceptionCode.NOT_CERTIFIED);

		certificationRepository.delete(cert);
	}

	private Certification findCertification(String email, ExceptionCode code) {
		return certificationRepository.findByEmail(email)
			.orElseThrow(() -> new BusinessLogicException(code));
	}

	private void verifyNotExistEmail(String email) {
		if (memberRepository.existsByEmail(email))
			throw new BusinessLogicException(ExceptionCode.EXISTS_EMAIL);
	}
}
