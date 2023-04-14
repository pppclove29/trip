package com.project.trip.post.entity;

public enum PostKind {
    NORMAL, NOTICE;

    public static PostKind convertToEnum(String value) {
        value = value.toUpperCase();
        System.out.println(value);
        try {
            return PostKind.valueOf(value); // 문자열을 Enum으로 변환
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
