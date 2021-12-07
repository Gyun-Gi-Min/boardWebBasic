package com.koreait.basic.user;

import com.koreait.basic.Utils;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.LoginResult;
import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Utils.displayView("로그인","user/login",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        entity.setUpw(upw);

        System.out.println(entity);
        LoginResult lr = UserDAO.login(entity);
        String err = null;

        switch (lr.getResult()){
            case 1:
                //세션에 loginUser값 등록
                HttpSession hs = req.getSession();
                hs.setAttribute("loginUser",lr.getLoginUser());
                //어디론가 이동~
                res.sendRedirect("/board/list");
                return; //break주니 에러떴음.
            default:
                switch (lr.getResult()){
                    //세션에 loginUSer값들록
                }
                    case 0:
                        err="로그인을 실패";
                        break;
                    case 2:
                        err="아이디를 확인해 주세요";
                        break;

                    case 3:
                        err="비민번호를 확인해 주세여";
                        break;

        }
        req.setAttribute("err",err);
        doGet(req,res);




    }
}
