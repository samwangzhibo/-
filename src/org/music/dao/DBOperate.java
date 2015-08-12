package org.music.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import cn.jpush.api.examples.PushExample;

public class DBOperate {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date=new Date();
		System.out.println(date);
		System.out.println("注册  "+Register("wzb","fasdge","008","130888888"));
		//System.out.println("登录 "+Login("sadfbhbth","fdseg"));
		//System.out.println("我的信息"+GetMyInfo("大赛3"));
		//System.out.println("更新home_pass  "+UpdateHomePass("wzb", "123456789"));
		//System.out.println("删除Task表按id "+DeleteTaskAtId("1"));
		//System.out.println("更新task  "+UpdateTaskFromId("'2'","'2'"));
		
		//System.out.println(ShowHomePeople("userid","username","phone","user","001"));
		//System.out.println(ShowHomePeople("unlock_id","unlock_time","username","unlock","001"));
		//AddHome("003");
		//System.out.println(GetUnlockMessage("wzb"));
		//System.out.println(UnLockByPass("wzb","252","2014-12-4"));
		//System.out.println(InsertUnlockRecord("201203214","wzb"))-
	}
	public static String Login(String username,String password){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs;
		String sql="select username,password from user";
		try {
			con=ConnFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery(sql);
			System.out.println(sql);
			System.out.println("读取成功");
			while(rs.next()){
				if(username.equals(rs.getString("username"))&&password.equals(rs.getString("password"))){
					return "success";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		ConnFactory.closers(rs);
		ConnFactory.closeps(ps);
		ConnFactory.closeConn(con);		
		return "lose";
	}

	public static String Register(String username,String password,String age,String phone){
		Connection con=null;
		PreparedStatement ps=null;
		String sql="insert into user values(null,?,?,?,?)";
		try {
			if(GetMyInfo(username) != "nouser"){
				System.out.println(GetMyInfo(username));
				return "username is used";
			}
			
			con=ConnFactory.getConnection();
			System.out.println("注册连接成功");
			ps=con.prepareStatement(sql);
			/*String name = new String(username.getBytes("GBK"),"UTF-8");
			System.out.println("转换后的username"+name);*/
			ps.setString(1,username);
			ps.setString(2, password);
			ps.setString(3, age);
			ps.setString(4,phone);
			//ps.setString(5,home_pass);
			System.out.println(sql);
			ps.executeUpdate();
			//添加家庭锁
			//AddHome(home_id);
			System.out.println("成功");
			return "success";
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "lose";
		}
		/*ConnFactory.closeps(ps);
		ConnFactory.closeConn(con);
		return "lose";*/
	}
	public static String GetMyInfo(String username){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs;
		String sql="select * from user where username=?";
		try{
			con=ConnFactory.getConnection();
			System.out.println("GetMyInfo连接成功");
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			JSONArray array=new JSONArray();
			if(!rs.next()){
				return "nouser";
			}
			rs.beforeFirst();
			while(rs.next()){
				JSONObject json=new JSONObject();
				
				json.put("id", rs.getString("id"));
				String name=new String(rs.getString("username").getBytes(),"GBK");
				json.put("username", name);
				json.put("age", rs.getString("age"));
				json.put("phone", rs.getString("phone"));
				array.add(json);
			}
			System.out.println("GetMyInfo成功");
			return array.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return "lose";
		}
	}
	
	public static JSONArray ParseToJsonArray(ResultSet rs,String couln1,String couln2,String couln3) throws SQLException{
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject json=new JSONObject();
			json.put(couln1, rs.getString(couln1));
			json.put(couln2,rs.getString(couln2));
			json.put(couln3, rs.getString(couln3));
			array.add(json);
		}
		return array;
	}
	
}
