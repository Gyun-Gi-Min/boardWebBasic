package com.koreait.basic.user;

import com.koreait.basic.FileUtils;
import com.koreait.basic.Utils;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.UserEntity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/user/profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int loginUserPk = Utils.getLoginUserPk(req);
        UserEntity entity = new UserEntity();
        entity.setIuser(loginUserPk);
        req.setAttribute("data",UserDAO.selUser(entity));

        String title = "프로필";
        req.setAttribute("subPage","user/profile");//열어야 될 jsp 파일명
        Utils.displayView(title,"user/myPage",req,res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = Utils.getLoginUserPk(req);
        int maxSize = 10_485_760; //1024 * 1024 * 10 | 10Mb

        ServletContext application = req.getServletContext();
        //mkdirs? 로 폴더생성했음
        String targetPath = application.getRealPath("/res/img/profile/" + loginUserPk) ;
        //"" 빈칸을 주면 톰캣에서 돌아가는 프로젝트 root경로값 문자열을 준다.
        File targetFolder = new File(targetPath);
        if(targetFolder.exists()){
            FileUtils.delFolderFiles(targetPath,false);
        }else {
            targetFolder.mkdirs(); //폴더 만드는거임.  s 들어간거 쓰면 무적수쥰.
        }
        System.out.println("targetPath : " + targetPath);

        MultipartRequest mr
                = new MultipartRequest(req,targetPath, maxSize ,"UTF-8",new DefaultFileRenamePolicy());

        String changefileNm = mr.getFilesystemName("profileImg");
        //profile.jsp의 name을 적어야 한다~

        UserEntity entity = new UserEntity();
        entity.setIuser(loginUserPk);
        entity.setProfileImg(changefileNm);
        //알아야할 정보는 어떤 iuser의 img를 바꿀것인가?이므로 두개만 entity에 담으면됨.

        int result = UserDAO.updUser(entity);
        if(result == 1){
            UserEntity loginUser = Utils.getLoginUser(req);
            loginUser.setProfileImg(changefileNm);
        }

        //doGet(req,res); >> 이렇게 하고 업로드한 후 새로고침하면 post 실행됨.
        res.sendRedirect("/user/profile"); //그래서 마지막에 get방식이 되도록 바꿈.


    }
}
