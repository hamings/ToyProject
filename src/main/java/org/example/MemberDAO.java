package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private Connection conn = null;

    private PreparedStatement stmt = null;

    private ResultSet rs = null;

    private static final String MEMBER_SELECT_ALL = "select * from member";

    private static final String MEMBER_SELECT = "select * from member where id = ?";

    private static final String MEMBER_INSERT = "insert into member values (?,?,?,?)";

    private static final String MEMBER_UPDATE = "update member set phone = ? where id = ?";

    private static final String MEMBER_DELETE = "delete member where id = ?";



    public List<Member> selectALL(){ // 멤버 전체 조회
        List<Member> memberList = new ArrayList<>();
        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(MEMBER_SELECT_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nId = rs.getString("id");
                String nName = rs.getString("name");
                String nEmail = rs.getString("email");
                String nPhone = rs.getString("phone");

                memberList.add(new Member(nId, nName, nEmail, nPhone));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCMgr.close(rs,stmt,conn);
        }return memberList;
    }

    public Member select(String id){ //멤버 id로 조회
        Member member = null;
        try {
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement((MEMBER_SELECT));
            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String nId = rs.getString("id");
                String nName = rs.getString("name");
                String nEmail = rs.getString("email");
                String nPhone = rs.getString("phone");
                member = new Member(nId, nName, nEmail, nPhone);
            }
        } catch(SQLException e){
                e.printStackTrace();
        } finally {
            JDBCMgr.close(rs,stmt,conn);
        }return member;
    }

    public int insert(Member member){ //멤버 추가
        int res = 0;
        try{
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(MEMBER_INSERT);
            stmt.setString(1, member.getId());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getPhone());
            res = stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCMgr.close(stmt,conn);
        }return res;
    }

    public int update(Member member){ //멤버 정보 변경
        int res = 0;
        try{
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(MEMBER_UPDATE);
            stmt.setString(1, member.getPhone());
            stmt.setString(2, member.getId());
            res = stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCMgr.close(stmt,conn);
        }return res;
    }

    public int delete(Member member){ //멤버 삭제
        int res = 0;
        try{
            conn = JDBCMgr.getConnection();
            stmt = conn.prepareStatement(MEMBER_DELETE);
            stmt.setString(1, member.getId());
            res = stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCMgr.close(stmt,conn);
        }return res;
    }


}
