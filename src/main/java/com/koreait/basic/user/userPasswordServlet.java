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

@WebServlet("/user/password")
public class userPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = "비밀번호 변경";
        req.setAttribute("subPage","user/password");
        Utils.displayView(title,"user/myPage",req,res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //현재 비밀번호가 다르다면  에러메시지 띄운다.> doGet(req,res)
        //띄우는 것을 활용해서 에러 메시지
        //현재 비밀번호가 맞다면 changedUpw 값으로 변경 > 로그아웃 > 로그인 화면

        //upw 받아오고. changeupw도 받아와야하고  둘이 비교 해야하고.
        //

        String upw = req.getParameter("upw");
        String changedUpw = req.getParameter("changedUpw");
        int loginUserPk = Utils.getLoginUserPk(req);
        UserEntity entity =new UserEntity();
        entity.setIuser(loginUserPk);

        String err = null;

        UserEntity myInfo = UserDAO.selUser1(entity);

        if(BCrypt.checkpw(upw,myInfo.getUpw())){
            String hashUpw = BCrypt.hashpw(changedUpw,BCrypt.gensalt());
            entity.setUpw(hashUpw);
            int result = UserDAO.updUser(entity);
            res.sendRedirect("/user/logout");
            return;
        }
            req.setAttribute("err","비밀번호가 다릅니다.");
            doGet(req,res);










    }
}
