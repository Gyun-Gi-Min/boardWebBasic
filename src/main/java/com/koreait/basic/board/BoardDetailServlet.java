package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.cmt.model.BoardCmtDTO;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardCmtDAO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int iboard = Utils.getParamaterInt(req,"iboard");
        int nohits = Utils.getParamaterInt(req,"nohits");
        BoardDTO dto =new BoardDTO();
        dto.setIboard(iboard);

        BoardVO data = BoardDAO.selDetail(dto);
        //로그인 한 사람이 pk값과 data에 들어있는 writer값이 다르거나
        //로그인 안되어있으면 hit값 올리기.
        req.setAttribute("aaa",data);


        BoardCmtDTO cmtParam = new BoardCmtDTO();
        cmtParam.setIboard(iboard);
        req.setAttribute("cmtList", BoardCmtDAO.selBoardCmtList(cmtParam));

        int loginUserPk = Utils.getLoginUserPk(req);
        if(data.getWriter() != loginUserPk && nohits != 1){//로그인 안되어 있으면 0, 되어있으면  pk
            BoardDAO.updBoardHitUp(dto);
        }

        Utils.displayView("상세정보","board/detail",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
