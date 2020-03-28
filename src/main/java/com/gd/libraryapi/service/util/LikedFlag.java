package com.gd.libraryapi.service.util;

/**
 * This enum class for detected that user liked book or disliked
 */
public enum LikedFlag {
    LIKED("like"),
    DISLIKED("dislike");

    public final String like;

    LikedFlag(String like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return like;
    }
}

