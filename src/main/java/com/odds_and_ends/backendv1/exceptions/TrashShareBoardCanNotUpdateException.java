package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class TrashShareBoardCanNotUpdateException extends GlobalException {
    public TrashShareBoardCanNotUpdateException(){
        super("쓰래기 나눔 게시글을 수정할 수 없습니다.", 403);
    }
}
