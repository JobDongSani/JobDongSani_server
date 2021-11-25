package com.odds_and_ends.backendv1.exceptions;

import com.odds_and_ends.backendv1.config.exception.GlobalException;

public class TrashShareBoardCanNotUpdateException extends GlobalException {
    public TrashShareBoardCanNotUpdateException(){
        super("Can not update this trash-share-post.", 403);
    }
}
