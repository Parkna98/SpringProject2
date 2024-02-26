package com.sist.web;
import java.util.*;
import java.sql.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connection();
	}
	public static void connection() {
		String url="jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
		String driver="com.mysql.cj.jdbc.Driver"; // username, password => root
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,"root","root");
			String sql="SELECT no,goods_name,goods_price FROM goods_all "
					+"ORDER BY no ASC "
					+ "LIMIT 0,20";  // 앞에 숫자부터 뒤에숫자만큼 0부터 20개 25,20 => 25번부터 20개
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"."+rs.getString(2)+" "+rs.getString(3));
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
