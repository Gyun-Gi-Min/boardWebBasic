package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardRankDAO {

    //조회수
    public static List<BoardVO> selBoardHitsRankList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        //조회수가 내림차순으로 10개.
        //hits값이 0인건 제외.
        String sql = " SELECT A.iboard, A.title, A.writer, A.hit , A.rdt, B.nm AS WriterNm" +
                " FROM t_board A " +
                " INNER JOIN t_user B " +
                " ON A.writer = B.iuser " +
                " WHERE hit>0 " +
                " ORDER BY A.hit DESC, A.iboard DESC " +
                " LIMIT 10 ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                int writer = rs.getInt("writer");
                int cnt = rs.getInt("hit"); //여기선 hit컬럼에서 가져오는거.
                //cnt 라고 적혀있지만 select에선 hit가져오므로 hit에 넣을 내용. 좋아요,댓글 때문에 이름 바꿈.
                String rdt = rs.getString("rdt");
                String WriterNm = rs.getString("WriterNm");
                BoardVO vo = BoardVO.builder()
                        .iboard(iboard)
                        .title(title)
                        .writer(writer)
                        .cnt(cnt)
                        .rdt(rdt)
                        .WriterNm(WriterNm)
                        .build();
                list.add(vo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return list;
    }
    //댓글수 10
    public static List<BoardVO> selBoardCmtRankList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        String sql = " SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm " +
                ", C.cnt " +
                " FROM t_board A " +
                " INNER JOIN t_user B" +
                " ON A.writer = B.iuser" +
                " INNER JOIN" +
                " (" +
                " SELECT iboard, COUNT(icmt) AS cnt " +
                " FROM t_board_cmt " +
                " GROUP BY iboard " +
                " ) C " +
                " ON A.iboard = C.iboard " +
                " ORDER BY C.cnt DESC " +
                " LIMIT 10 ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                int writer = rs.getInt("writer");
                String rdt = rs.getString("rdt");
                int cnt = rs.getInt("cnt");
                String WriterNm = rs.getString("WriterNm");
                BoardVO vo = BoardVO.builder()
                        .iboard(iboard)
                        .title(title)
                        .writer(writer)
                        .cnt(cnt)
                        .rdt(rdt)
                        .WriterNm(WriterNm)
                        .build();
                list.add(vo);
            }
        }catch (Exception e){
        e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return list;
    }
    //좋아요 10
    public static List<BoardVO> selBoardHeartRankList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;

        String sql = " SELECT A.iboard, A.title, A.writer, A.rdt, B.nm AS writerNm " +
                ", C.cnt " +
                " FROM t_board A " +
                " INNER JOIN t_user B" +
                " ON A.writer = B.iuser" +
                " INNER JOIN" +
                " (" +
                " SELECT iboard, COUNT(iuser) AS cnt " +
                " FROM t_board_heart " +
                " GROUP BY iboard " +
                " )C " +
                " ON A.iboard = C.iboard " +
                " ORDER BY C.cnt DESC " +
                " LIMIT 10 ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                int writer = rs.getInt("writer");
                String rdt = rs.getString("rdt");
                int cnt = rs.getInt("cnt");
                String WriterNm = rs.getString("WriterNm");
                BoardVO vo = BoardVO.builder()
                        .iboard(iboard)
                        .title(title)
                        .writer(writer)
                        .cnt(cnt)
                        .rdt(rdt)
                        .WriterNm(WriterNm)
                        .build();
                list.add(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }


        return list;
    }
}
