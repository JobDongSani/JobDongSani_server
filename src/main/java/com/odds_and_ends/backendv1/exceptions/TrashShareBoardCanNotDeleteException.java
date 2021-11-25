package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class TrashShareBoardCanNotDeleteException extends GlobalException {
    public TrashShareBoardCanNotDeleteException(){
        super("쓰래기 나눔 게시글을 삭제할 수 없습니다.", 403);
    }
}
