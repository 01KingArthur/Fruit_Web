package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.Origin;

public class OriginDao {
    private Connection connection;

    public OriginDao(Connection connection) {
        this.connection = connection;
    }

    public List<Origin> getAllOrigins() {
        List<Origin> origins = new ArrayList<>();

        String query = "SELECT DISTINCT origin FROM products";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String originName = resultSet.getString("origin");

                Origin origin = new Origin(originName);
                origins.add(origin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return origins;
    }
}
