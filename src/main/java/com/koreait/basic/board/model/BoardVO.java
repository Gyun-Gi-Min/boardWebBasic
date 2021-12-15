package com.koreait.basic.board.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//VO는 세터가 없다. 게터만... ㅓ
@Getter
@Builder

public class BoardVO {
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;

    //-----추가목록-----
    private String WriterNm;
    private int cnt;
    private String profileImg;
}
