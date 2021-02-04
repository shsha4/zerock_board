package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.ReplyDTO;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void TestGetList() {
        List<ReplyDTO> replyDTOList = replyService.getList(100L);
        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }

}
