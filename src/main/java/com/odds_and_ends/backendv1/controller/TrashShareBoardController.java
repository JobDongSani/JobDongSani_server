package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.dto.trash_share_board.TrashShareBoardDto;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.TrashShareBoardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trash-share-board")
public class TrashShareBoardController {

    private final TrashShareBoardService trashShareBoardService;

    @PostMapping
    private CommonResponse<Long> save(@RequestBody TrashShareBoardDto trashShareBoardDto){
        return trashShareBoardService.save(trashShareBoardDto);
    }

    @GetMapping("/{id}")
    private CommonResponse<TrashShareBoardDto> findById(@PathVariable("id") long id){
        return trashShareBoardService.findById(id);
    }

    @GetMapping()
    private CommonResponse<List<TrashShareBoardDto>> findAll(){
        return trashShareBoardService.findAll();
    }

    @PutMapping("/{id}")
    private CommonResponse<Long> update(@PathVariable long id, @RequestBody TrashShareBoardDto trashShareBoardDto){
        return trashShareBoardService.update(id, trashShareBoardDto);
    }

    @DeleteMapping("{id}")
    private CommonResponse<Void> delete(@PathVariable("id") long id){
        return trashShareBoardService.delete(id);
    }
}
