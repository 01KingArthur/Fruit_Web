package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.Season;

public class SeasonDao {
    private Connection connection;

    public SeasonDao(Connection connection) {
        this.connection = connection;
    }

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();

        String query = "SELECT DISTINCT season FROM products";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String seasonName = resultSet.getString("season");

                Season season = new Season(seasonName);
                seasons.add(season);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }
}