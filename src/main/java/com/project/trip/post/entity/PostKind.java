package com.project.trip.post.entity;

public enum PostKind {
    NORMAL,NOTICE;

    public static PostKind convertFromString(String kind) throws IllegalArgumentException{
        return PostKind.valueOf(kind.toUpperCase());
    }
}
