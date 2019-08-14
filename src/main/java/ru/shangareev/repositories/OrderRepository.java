package ru.shangareev.repositories;

import ru.shangareev.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final Connection conn;

    public OrderRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
    }

    public void insert(Order order) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into orders(user_id, price, phone_number, status_id, create_at, update_at) values (?, ?, ?, ?, ?, ?);")) {
            stmt.setInt(1, order.getUserId());
            stmt.setDouble(2, order.getPrice());
            stmt.setString(3, order.getPhoneNumber());
            stmt.setInt(4, order.getStatusId());
            stmt.setTimestamp(5, Timestamp.valueOf(order.getCreateAt()));
            stmt.setTimestamp(6, Timestamp.valueOf(order.getUpdateAt()));
            stmt.execute();
        }
    }

    public void save(Order order) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update orders set price = ?, phone_number = ?, status_id= ?, create_at= ?, update_at= ?  where id = ?;")) {
            stmt.setDouble(1, order.getPrice());
            stmt.setString(2, order.getPhoneNumber());
            stmt.setInt(3, order.getStatusId());
            stmt.setTimestamp(5, Timestamp.valueOf(order.getCreateAt()));
            stmt.setTimestamp(6, Timestamp.valueOf(order.getUpdateAt()));
            stmt.execute();
        }
    }


    public Order findById(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, user_id, price, phone_number, status_id, create_at, update_at from orders where id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Order(rs.getInt(1), rs.getInt(2), rs.getDouble(3),
                        rs.getString(4), rs.getInt(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getTimestamp(7).toLocalDateTime());
            }
        }
        return null;
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, user_id, price, phone_number, status_id, " +
                    "create_at, update_at from orders");
            while (rs.next()) {
                res.add(new Order(rs.getInt(1), rs.getInt(2), rs.getDouble(3),
                        rs.getString(4), rs.getInt(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getTimestamp(7).toLocalDateTime()));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE javaee_test_db.orders (\n" +
                    "  id\t                INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  user_id               INT(11) NOT NULL,\n" +
                    "  price                 DECIMAL(8,2) NOT NULL,\n" +
                    "  phone_number          VARCHAR(20) NOT NULL,\n" +
                    "  status_id             INT(11) NOT NULL,\n" +
                    "  create_at             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  update_at             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)\n" +
                    "  REFERENCES users (id),\n" +
                    "  CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id)\n" +
                    "  REFERENCES orders_statuses (id)\n" +
                    ") ENGINE = InnoDB DEFAULT CHARSET = utf8;");
        }
    }
}
