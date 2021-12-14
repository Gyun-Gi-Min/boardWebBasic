package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.cmt.model.BoardHearEntity;
import com.koreait.basic.board.model.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardHeartDAO {

    public static int insBoardHeart(BoardHearEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql =" INSERT INTO t_board_heart (iuser, iboard) " +
                " VALUES (?,?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIuser());
            ps.setInt(2,param.getIboard());
            return ps.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }



    //좋아요 했다면 1, 아니면 0 ||| 글번호 iboard , 로그인한 사람의 loginUserPK
    public static int selIsHeart(BoardHearEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT iuser " +
                " FROM t_board_heart " +
                " WHERE iuser = ? " +
                " AND iboard = ? " ;

        try{
            con=DbUtils.getCon();
            ps=con.prepareStatement(sql);
            ps.setInt(1,param.getIuser());
            ps.setInt(2,param.getIboard());
            rs=ps.executeQuery();
            if(rs.next()){
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return 0;
    }


    public static int delBoardHeart(BoardHearEntity param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql =" DELETE FROM t_board_heart " +
                " where iuser=? and iboard=? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIuser());
            ps.setInt(2,param.getIboard());
            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }




}
