package ru.shangareev.repositories;

import ru.shangareev.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private final Connection conn;

    public CategoryRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
    }

    public void insert(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into categories(title) values (?);")) {
            stmt.setString(1, category.getTitle());
            stmt.execute();
        }
    }

    public void save(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update categories set title = ? where id = ?;")) {
            stmt.setString(1, category.getTitle());
            stmt.execute();
        }
    }

    public Category findByTitle(String title) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title from categories where title = ?")) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        }
        return new Category(-1, "");
    }

    public Category findById(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title from categories where id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        }
        return null;
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, title from categories");

            while (rs.next()) {
                res.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        }
        return res;
    }

    public void delete(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from categories where id = ?;")) {
            stmt.setInt(1, category.getId());
            stmt.execute();
        }
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE categories (\n" +
                    "  id\t INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  title  VARCHAR(255) NOT NULL,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ") ENGINE = InnoDB DEFAULT CHARSET = utf8;\n");
        }
    }
}
