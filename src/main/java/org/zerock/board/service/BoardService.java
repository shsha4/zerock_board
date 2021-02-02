package org.zerock.board.service;

import org.springframework.data.domain.PageRequest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto); //생성
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO); //페이지 목록
    BoardDTO get(Long bno); //조회
    void removeWithReplies(Long bno); //삭제
    void modify(BoardDTO boardDTO); //수정

    //DTO -> Entity 변환
    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
    }

    //Entity -> DTO 변환
    default BoardDTO entityToDTO(Board board, Member member, Long replycount) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replycount.intValue()) //long으로 나오므로 int로 변환
                .build();
    }
}
