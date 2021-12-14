package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.cmt.model.BoardHearEntity;
import com.koreait.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/heart")
public class BoardHeartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        String proc = req.getParameter("proc");
        int iboard = Utils.getParamaterInt(req,"iboard");
        int loginUserPk = Utils.getLoginUserPk(req);

        BoardHearEntity entity = new BoardHearEntity();
        entity.setIboard(iboard);
        entity.setIuser(loginUserPk);

        switch (proc){
            case "1":
                BoardHeartDAO.insBoardHeart(entity);
                break;
            case "2":
                BoardHeartDAO.delBoardHeart(entity);
                break;
        }
        res.sendRedirect("/board/detail?iboard=" + iboard);
    }
}
