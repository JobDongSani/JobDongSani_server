package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class TrashShareBoardCanNotDeleteException extends GlobalException {
    public TrashShareBoardCanNotDeleteException(){
        super("Can not delete this trash-share-post", 403);
    }
}
