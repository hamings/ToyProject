package org.example;

import java.sql.*;

public class JDBCMgr {

    private JDBCMgr(){ }

    public static Connection getConnection(){
        Connection conn = null;
        try{
            DriverManager.registerDriver(new org.h2.Driver());

            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
        }catch (SQLException e){
            e.printStackTrace();
        }return conn;
    }

    public static void close(PreparedStatement stmt, Connection conn){
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn){
        try{
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        close(stmt,conn);
    }

}
