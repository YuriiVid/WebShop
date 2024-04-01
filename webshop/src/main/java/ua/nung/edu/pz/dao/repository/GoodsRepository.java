package ua.nung.edu.pz.dao.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ua.nung.edu.pz.dao.entity.Good;

public class GoodsRepository {
	public ArrayList<Good> getAllGoods() {
		DataSource dataSource = new DataSource();
		ArrayList<Good> goods = new ArrayList<>();
		String sql = "SELECT * FROM goods";

		try (
				Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {
			while (resultSet.next()) {
				goods.add(new Good(resultSet.getLong("id"), resultSet.getString("name"),
						resultSet.getString("description"),
						resultSet.getString("brand"), new String[] { resultSet.getString("photo") },
						resultSet.getInt("likes")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}
}
