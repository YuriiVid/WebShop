package ua.nung.edu.pz.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nung.edu.pz.dao.entity.Price;

public class PriceRepository {
	public Price getPriceForGood(long good_id) {
		DataSource dataSource = new DataSource();
		Price price = new Price();
		String sql = "SELECT * FROM prices WHERE good_id = ?";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, good_id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				price.setId(resultSet.getLong("id"));
				price.setGood_id(resultSet.getLong("good_id"));
				price.setFrom_supplier(resultSet.getDouble("from_supplier"));
				price.setFor_client(resultSet.getDouble("for_client"));
				price.setCreated_at(resultSet.getString("created_at"));
				price.setDeleted_at(resultSet.getString("deleted_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
}
