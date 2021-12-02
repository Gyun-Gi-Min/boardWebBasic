package com.koreait.basic;

import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.user.model.UserEntity;
import com.mysql.cj.protocol.Resultset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class Utils {
    public static void displayView(String title, String page,
                                   HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    req.setAttribute("title",title);
    req.setAttribute("page",page);
    disForward(req,res,"layout");
    }

    public static void disForward( HttpServletRequest req, HttpServletResponse res,String jsp) throws ServletException, IOException{
        req.getRequestDispatcher("/WEB-INF/view/"+jsp+".jsp").forward(req,res);
    }

    public static int getParamaterInt(HttpServletRequest req, String key){
        String val = req.getParameter(key);
        return parseStringToInt(val,0);
    }
    public static int parseStringToInt(String val,int defVal){
        try{
            return Integer.parseInt(val);

        }catch(Exception e){
            e.printStackTrace();
        }
        return defVal;
    }

    public static UserEntity getLoginUser(HttpServletRequest req){
        HttpSession hs =req.getSession();
        return (UserEntity) hs.getAttribute("loginUser");
    }

    public static int getLoginUserPk(HttpServletRequest req){
        UserEntity loginUser = getLoginUser(req);
        if(loginUser==null){
            return 0;
        }
        return loginUser.getIuser();
    }




}
