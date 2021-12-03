package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParamaterInt(req,"iboard");
        int loginUserPk = Utils.getLoginUserPk(req);

        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        entity.setWriter(loginUserPk);

        int result = BoardDAO.delBoard(entity);

        res.sendRedirect("/board/list");

        }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
