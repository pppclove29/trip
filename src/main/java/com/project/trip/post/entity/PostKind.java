package com.project.trip.post.entity;

public enum PostKind {
    NORMAL,NOTICE;

    public static PostKind convertFromString(String kind) {
        //TODO kind가 없는 종류일때 에러 처리
        return PostKind.valueOf(kind.toUpperCase());
    }
}
