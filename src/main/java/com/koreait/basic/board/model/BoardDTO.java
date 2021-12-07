package com.koreait.basic.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//에노테이션? 이라고함.
public class BoardDTO {
    private int iboard;
    private int page; //페이지
    private int startIdx; // 시작인덱스
    private int rowCnt; //몇줄표시

    public int getIboard() {
        return iboard;
    }

    public void setIboard(int iboard) {
        this.iboard = iboard;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.startIdx=(page-1)*rowCnt;
    }

    public int getStartIdx() {
        return startIdx;

    }
    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public int getRowCnt() {
        return rowCnt;
    }
    public void setRowCnt(int rowCnt) {
        this.rowCnt = rowCnt;

    }
}
