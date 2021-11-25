package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.dto.trash_share_board.TrashShareBoardDto;
import com.odds_and_ends.backendv1.entity.trash_share_board.TrashShareBoard;
import com.odds_and_ends.backendv1.entity.trash_share_board.TrashShareBoardRepository;
import com.odds_and_ends.backendv1.exceptions.TrashNotFoundException;
import com.odds_and_ends.backendv1.exceptions.TrashShareBoardCanNotDeleteException;
import com.odds_and_ends.backendv1.facade.UserFacade;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TrashShareBoardService {

    private final TrashShareBoardRepository trashShareBoardRepository;
    private final UserFacade userFacade;

    public CommonResponse<Long> save(TrashShareBoardDto trashShareBoardDto){
        TrashShareBoard savedTrashShareBoard =
                trashShareBoardRepository.save(trashShareBoardDto.toEntity(userFacade.getCurrentUser()));
        return new CommonResponse<>(201, "쓰래기 나눔 게시글이 저장되었습니다.", savedTrashShareBoard.getId());
    }

    public CommonResponse<TrashShareBoardDto> findById(long id){
        TrashShareBoard trashShareBoard = trashShareBoardRepository.findById(id)
                .orElseThrow(TrashNotFoundException::new);
        return new CommonResponse<>(200, "쓰래기 나눔 게시글 조회가 성공했습니다.", TrashShareBoardDto.of(trashShareBoard));
    }

    public CommonResponse<List<TrashShareBoardDto>> findAll(){
        List<TrashShareBoardDto> findAllTrashShareBoard = trashShareBoardRepository.findAll(Sort.by(Sort.Direction.DESC))
                .stream()
                .map(TrashShareBoardDto::of)
                .collect(Collectors.toList());
        return new CommonResponse<>(200, "쓰래기 나눔 전체 조회가 성공했습니다.", findAllTrashShareBoard);
    }

    @Transactional
    public CommonResponse<Long> update(long id, TrashShareBoardDto trashShareBoardDto){
        TrashShareBoard trashShareBoard = trashShareBoardRepository.findById(id)
                .orElseThrow(TrashNotFoundException::new);
        trashShareBoard.update(
                trashShareBoardDto.getTitle(),
                trashShareBoardDto.getContents(),
                trashShareBoardDto.getContact(),
                trashShareBoardDto.getImagePath()
        );

        return new CommonResponse<>(200, "쓰래기 나눔 게시글이 수정되었습니다", trashShareBoard.getId());
    }

    public CommonResponse<Void> delete(long id){
        TrashShareBoard trashShareBoard = trashShareBoardRepository.findByIdAndUser(id, userFacade.getCurrentUser())
                .orElseThrow(TrashShareBoardCanNotDeleteException::new);
        return new CommonResponse<>(200, "해당 쓰래기 나눔 게시글을 삭제 했습니다.", null);
    }

}
