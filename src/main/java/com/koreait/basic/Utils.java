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
import java.io.*;
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
        return getParamaterInt(req,key,0);
    }
    public static int getParamaterInt(HttpServletRequest req, String key,int defVal){
        String val = req.getParameter(key);
        return parseStringToInt(val,defVal);
    }

    public static int parseStringToInt(String val,int defVal){
        try{
            return Integer.parseInt(val);

        }catch(Exception e){

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


    public static String replaceStr(String str){


        return str.replace("가","123").replace("나","45552");
    }
    public static String replaceAll(String str1){
        return replaceStr(str1);
    }

    //cmtservlet에 쓰려고 가져옴.
    public static String getJson(HttpServletRequest request) throws IOException {
        String reqStr = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        reqStr = stringBuilder.toString();
        return reqStr;
    }



}
