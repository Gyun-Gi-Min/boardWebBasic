package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardDAO;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        BoardDTO param = new BoardDTO();
        int page = Utils.getParamaterInt(req,"page",1);
        param.setRowCnt(5); //몇줄씩 볼껀가?
        param.setPage(page);

        int maxPageNum = BoardDAO.getMaxPageNum(param);
        req.setAttribute("page",page);

        req.setAttribute("maxPageNum",maxPageNum);
        req.setAttribute("list",BoardDAO.selBoardList(param));

        Utils.displayView("게시판","board/list",req,res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
