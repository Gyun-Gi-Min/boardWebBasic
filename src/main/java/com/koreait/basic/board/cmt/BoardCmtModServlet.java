package com.koreait.basic.board.cmt;

import com.koreait.basic.Utils;
import com.koreait.basic.board.cmt.model.BoardCmtEntity;
import com.koreait.basic.dao.BoardCmtDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/mod")
public class BoardCmtModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String ctnt = req.getParameter("ctnt");
        int icmt = Utils.getParamaterInt(req,"icmt");
        int writer = Utils.getLoginUserPk(req);
        int iboard =Utils.getParamaterInt(req,"iboard");


        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        entity.setCtnt(ctnt);
        entity.setIcmt(icmt);
        entity.setWriter(writer);

        int result = BoardCmtDAO.updBoardCmt(entity);

        res.sendRedirect("/board/detail?iboard=" + iboard);



    }
}
