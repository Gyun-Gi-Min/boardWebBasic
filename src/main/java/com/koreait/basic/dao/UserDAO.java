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
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }



    // t_user 테이블에서 iuser or uid 유저 정보 가져올 수 있는 메소드
    public static UserEntity selUser1(UserEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iuser, uid, upw, nm, gender, rdt, profileImg FROM t_user WHERE ";

        if(entity.getIuser()>0){
            sql += "iuser = " + entity.getIuser();
        }else{
            sql += "uid = '" + entity.getUid() + "'";
        }

        try {
            con=DbUtils.getCon();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();

            if(rs.next()){
                UserEntity vo = new UserEntity();
                vo.setIuser(rs.getInt("iuser"));
                vo.setUid(rs.getString("uid"));
                vo.setUpw(rs.getString("upw"));
                vo.setNm(rs.getString("nm"));
                vo.setGender(rs.getInt("gender"));
                vo.setRdt(rs.getString("rdt"));
                vo.setProfileImg(rs.getString("profileImg"));
                return vo;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }return null;



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

        String sql = "SELECT iuser, upw, nm, gender, profileImg FROM t_user WHERE uid = ?";
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
                        loginUser.setProfileImg(rs.getString("profileImg"));

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


    public static UserEntity selUser(UserEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT uid, upw, nm, gender, rdt, profileImg FROM t_user WHERE iuser = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,entity.getIuser());
            rs = ps.executeQuery();
            if(rs.next()){
                UserEntity vo = new UserEntity();
                vo.setUid(rs.getString("uid"));
                vo.setUpw(rs.getString("upw"));
                vo.setNm(rs.getString("nm"));
                vo.setGender(rs.getInt("gender"));
                vo.setRdt(rs.getString("rdt"));
                vo.setProfileImg(rs.getString("profileImg"));
                return vo;
            }
        }catch (Exception e){e.printStackTrace();}
        finally {DbUtils.close(con,ps,rs);}
        return null;
    }


    //비밀번호 > iuser,upw (암호화가 된) 넘어옴
    // 프로필 이미지 > iuser , profileImg 넘어옴
    public static int updUser(UserEntity entity){
        Connection con = null;
        PreparedStatement ps= null;
        String sql = " UPDATE t_user SET ";
        String val = null;

        if(entity.getUpw() != null && !"".equals(entity.getUpw()) ){
            sql += " upw = ? ";
            val = entity.getUpw();
        }else if(entity.getProfileImg() != null && !"".equals(entity.getProfileImg())){
            sql += " profileImg = ? ";
            val = entity.getProfileImg();
        }

        sql += " WHERE iuser = ? ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,val);
            ps.setInt(2,entity.getIuser());
            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;


    }



}
