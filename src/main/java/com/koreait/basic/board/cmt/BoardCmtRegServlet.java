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

@WebServlet("/board/cmt/reg")
public class BoardCmtRegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //댓글 insert
        // "/board/detail 화면으로
        int iboard = Utils.getParamaterInt(req,"iboard");
        String ctnt = req.getParameter("ctnt");
        int writer = Utils.getLoginUserPk(req);

        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(iboard);
        param.setCtnt(ctnt);
        param.setWriter(writer);

        int result = BoardCmtDAO.insBoardCmt(param);


        res.sendRedirect("/board/detail?nohits=1&iboard=" + iboard);
        //댓글달때마다 조회수 안올라가도록 하기위해 사용. nohits
    }
}
