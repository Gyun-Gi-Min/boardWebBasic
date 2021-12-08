package com.koreait.basic.board.cmt.model;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class BoardCmtVO {
    private int icmt;
    private String ctnt;
    private int writer;
    private String rdt;
    //---------추가목록--------
    private String writerNm;
}
