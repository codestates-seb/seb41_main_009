package com.codestates.hobby.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    CATEGORY_NOT_FOUND(404, "Category Not Found"),
    POST_NOT_FOUND(404, "Post Not Found"),
    COMMENT_NOT_FOUND(404,"Comment Not Found"),
    SERIES_NOT_FOUND(404,"Series Not Found"),
    SHOWCASE_NOT_FOUND(404,"Showcase Not Found"),

    WITHDRAWAL_MEMBER(500,"Member Is Withdrawn"),
    EXISTS_EMAIL(500,"Email Is Exists"),
    EXISTS_NICKNAME(500,"NickName Is Exists"),
    NOT_MATCH_MEMBER(500, "Doesn't Belong This Member");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}