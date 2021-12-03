package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.board.model.BoardVO;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardEntity entity) {//title, ctnt,writer
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_board(title,ctnt,writer) VALUES (?,?,?)";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static BoardVO selDetail(BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.ctnt, A.writer, A.hit, B.nm AS WriterNm, A.rdt " +
                " FROM t_board A " +
                " INNER JOIN t_user B " +
                " ON A.writer = B.iuser " +
                " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if (rs.next()) {
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                String ctnt = rs.getString("ctnt");
                int hit = rs.getInt("hit");
                int writer = rs.getInt("writer");
                String rdt = rs.getString("rdt");
                String WriterNm = rs.getString("WriterNm");
                BoardVO vo = BoardVO.builder()
                        .iboard(iboard)
                        .title(title)
                        .ctnt(ctnt)
                        .hit(hit)
                        .rdt(rdt)
                        .writer(writer)
                        .WriterNm(WriterNm)
                        .build();

                return vo;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }

        return null;
    }


    public static List<BoardVO> selBoardList() {
        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT " +
                "A.iboard, A.title, A.writer, A.hit, A.rdt, B.nm AS WriterNm" +
                " FROM t_board A" +
                " INNER JOIN t_user B " +
                " ON A.writer = B.iuser " +
                " ORDER BY A.iboard DESC ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                int writer = rs.getInt("writer");
                int hit = rs.getInt("hit");
                String rdt = rs.getString("rdt");
                String WriterNm = rs.getString("WriterNm");
                BoardVO vo = BoardVO.builder()
                        .iboard(iboard)
                        .title(title)
                        .writer(writer)
                        .hit(hit)
                        .rdt(rdt)
                        .WriterNm(WriterNm)
                        .build();
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static void updBoardHitUp(BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_board SET hit = hit + 1 " +
                " WHERE iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }

    }
        public static int delBoard(BoardEntity entity){
        Connection con =null;
        PreparedStatement ps = null;
        String sql ="DELETE FROM t_board WHERE iboard=? AND writer=? ";

        try{
            con=DbUtils.getCon();
            ps=con.prepareStatement(sql);
            ps.setInt(1,entity.getIboard());
            ps.setInt(2,entity.getWriter());
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;
        }


}






