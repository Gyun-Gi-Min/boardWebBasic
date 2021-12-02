package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.user.model.LoginResult;
import com.koreait.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static int join(UserEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_user (uid,upw,nm,gender) VALUES (?,?,?,?) ";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,param.getUid());
            ps.setString(2,param.getUpw());
            ps.setString(3,param.getNm());
            ps.setInt(4,param.getGender());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }

    //무조건 LoginResult 객체 주소값 리턴
    // result 값 0:실패 1:로그인 성공 2:아이디x 3:비밀버호 틀림
    //result 가 1 이었을때 loginUser에 로그인 한 유저의 id nm gender 값 저장한 객체 담기
    public static LoginResult login(UserEntity entity){
        int result = 0;
        UserEntity loginUser = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT iuser, upw, nm, gender FROM t_user WHERE uid = ?";
        try{

                con =DbUtils.getCon();
                ps = con.prepareStatement(sql);
                ps.setString(1, entity.getUid());
                rs=ps.executeQuery();

                //entity 객체화 시켜라?? >> loginUser로 했는데 왜 햇음????
                if(rs.next()){
                    String dbPw=rs.getString("upw");
                    if(BCrypt.checkpw(entity.getUpw(), dbPw)){
                        result = 1;  //로그인 성공
                        loginUser = new UserEntity();
                        loginUser.setIuser(rs.getInt("iuser"));
                        loginUser.setUid(entity.getUid());
                        loginUser.setNm(rs.getString("nm"));
                        loginUser.setGender(rs.getInt("gender"));

                    }else{
                        result = 3; //비밀번호 틀림
                    }
                }else result=2; //아이디없음
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return new LoginResult(result, loginUser);

    }

}
