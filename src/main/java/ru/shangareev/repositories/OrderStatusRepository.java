package ru.shangareev.repositories;

import ru.shangareev.entities.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusRepository {
    private final Connection conn;

    public OrderStatusRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
    }

    public void insert(OrderStatus orderStatus) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into orders_statuses(title) values (?);")) {
            stmt.setString(1, orderStatus.getTitle());
            stmt.execute();
        }
    }

    public void save(OrderStatus orderStatus) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update orders_statuses set title = ? where id = ?;")) {
            stmt.setString(1, orderStatus.getTitle());
            stmt.execute();
        }
    }

    public OrderStatus findByTitle(String title) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title from orders_statuses where title = ?")) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new OrderStatus(rs.getInt(1), rs.getString(2));
            }
        }
        return new OrderStatus(-1, "");
    }

    public OrderStatus findById(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title from orders_statuses where id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new OrderStatus(rs.getInt(1), rs.getString(2));
            }
        }
        return null;
    }

    public List<OrderStatus> getAllOrderStatuses() throws SQLException {
        List<OrderStatus> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, title from orders_statuses");

            while (rs.next()) {
                res.add(new OrderStatus(rs.getInt(1), rs.getString(2)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE javaee_test_db.orders_statuses (\n" +
                    "  id                    INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  title                 VARCHAR(50) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ") ENGINE = InnoDB DEFAULT CHARSET = utf8;");
        }
    }
}
