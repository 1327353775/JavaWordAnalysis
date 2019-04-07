package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bean.UserBean;
import com.util.DBUtil;

/**
 * @author Cheyanlie
 * 2019年4月5日
 * TODO分别从数据库与网页上获取与category有关的内容封装到对象CompareBean
 */
public class CompareSelectDao {
	/**
	 * @param category
	 * @return
	 * CompareBean
	 */
	public UserBean SelectContent(String name) {
		
		UserBean userBean=new UserBean();
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			//test
			//System.out.println(category);
			String sql="select * from word where name='"+name+"'";
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				userBean.setName(resultSet.getString("name"));
				userBean.setContent(resultSet.getString("content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userBean;
		
	}
}
