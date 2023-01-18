package com.codestates.hobby.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    // UNAUTHORIZED
    UNAUTHORIZED(401,"Unauthorized"),

    // FORBIDDEN
    NO_PERMISSION_TO_EDIT(403, ""),
    NO_PERMISSION_TO_CREATE(403, ""),
    NO_PERMISSION_TO_DELETE(403, ""),
    WITHDRAWAL_MEMBER(403,"Member Is Withdrawn"),
    NOT_MATCH_MEMBER(500, "Doesn't Belong This Member"),

    // NOT FOUND
    NOT_FOUND_POST(404, "Post Not Found"),
    NOT_FOUND_MEMBER(404, "Member Not Found"),
    NOT_FOUND_SERIES(404,"Series Not Found"),
    NOT_FOUND_COMMENT(404,"Comment Not Found"),
    NOT_FOUND_CATEGORY(404, "Category Not Found"),
    NOT_FOUND_SHOWCASE(404,"Showcase Not Found"),

    // Conflict
    EXISTS_EMAIL(409,"Email Is Exists"),
    EXISTS_NICKNAME(409,"NickName Is Exists");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}