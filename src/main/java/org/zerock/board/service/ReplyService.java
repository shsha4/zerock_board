package org.zerock.board.service;

import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    List<ReplyDTO> getList(Long bno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);

    //ReplyDTO -> Reply 변환
    default Reply dtoToEntity(ReplyDTO replyDTO) {
        Board board = Board.builder().bno(replyDTO.getBno()).build();

       return Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

    }

    //Reply -> ReplyDTO 변환
    default ReplyDTO entityToDTO(Reply reply) {
        return ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
    }

}
