package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class TrashNotFoundException extends GlobalException {
    public TrashNotFoundException(){
        super("해당 쓰래기 나눔 게시글을 찾을 수 없습니다.", 404);
    }
}
