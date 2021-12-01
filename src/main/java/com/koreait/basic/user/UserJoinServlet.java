package com.koreait.basic.user;

import com.koreait.basic.Utils;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/join")
public class UserJoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Utils.displayView("회원가입","user/join",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");
        String nm = req.getParameter("nm");
        int gender = Utils.getParamaterInt(req,"gender");

        String hashPw= BCrypt.hashpw(upw,BCrypt.gensalt());
        //BCrypt를 ㅅㅏ용하여 힙밥.
        System.out.println("upw : "+ upw);
        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        entity.setUpw(hashPw);
        entity.setNm(nm);
        entity.setGender(gender);

        System.out.println(entity);

        int result = UserDAO.join(entity);

        res.sendRedirect("/user/login");






    }
}