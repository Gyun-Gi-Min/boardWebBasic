package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardHeartEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardHeartDAO {
    //좋아요 처리
    public static int insBoardHeart(BoardHeartEntity entity){
        Connection con =null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_board_heart(iboard,iuser) VALUES (? , ?) ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,entity.getIboard());
            ps.setInt(2,entity.getIuser());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }

    //좋아요 취소 처리
    public static int delBoardHeart(BoardHeartEntity entity){

        Connection con =null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_board_heart WHERE iboard=? and iuser=?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,entity.getIboard());
            ps.setInt(2,entity.getIuser());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;

    }


    //return 1: 좋아요  return 2: 좋아요 안햇음. 0: 에러
    public static int selIsheart(BoardHeartEntity entity){

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iuser " +
                " FROM t_board_heart " +
                " WHERE iuser = ? " +
                " AND iboard = ?" ;

        try{
            con =DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,entity.getIuser());
            ps.setInt(2,entity.getIboard());
            rs = ps.executeQuery();
            if(rs.next()){
                return 1; // 값이 있다? 좋아요했다. >>>1
            } return 2; // 좋아요 안했다.

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            DbUtils.close(con,ps,rs);
        }
        return 0;  //에러.
    }

}
