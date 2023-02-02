package com.codestates.hobby.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    // BAD REQUEST
    CERTIFICATION_NOT_MATCH(400, "Invalid code."),
    CODE_EXPIRATION(400, "This code has expired."),

    // UNAUTHORIZED
    UNAUTHORIZED(401,"Unauthorized"),
    NOT_CERTIFIED(401,"Certification required."),

    // FORBIDDEN
    NO_PERMISSION_TO_EDIT(403, ""),
    NO_PERMISSION_TO_CREATE(403, ""),
    NO_PERMISSION_TO_DELETE(403, ""),
    WITHDRAWAL_MEMBER(403,"Member Is Withdrawn"),
    NOT_MATCH_MEMBER(403, "Doesn't Belong This Member"),

    // NOT FOUND
    NOT_CERTIFICATION(404, "It is not certified"),
    NOT_FOUND_EMAIL(404, "Email not found"),
    NOT_FOUND_POST(404, "Post Not Found"),
    NOT_FOUND_MEMBER(404, "Member Not Found"),
    NOT_FOUND_SERIES(404,"Series Not Found"),
    NOT_FOUND_COMMENT(404,"Comment Not Found"),
    NOT_FOUND_CATEGORY(404, "Category Not Found"),
    NOT_FOUND_SHOWCASE(404,"Showcase Not Found"),

    // Conflict
    EXISTS_EMAIL(409,"Email Is Exists"),
    EXISTS_NICKNAME(409,"NickName Is Exists"),
    EXISTS_POST(409,"Post Is Exists");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}