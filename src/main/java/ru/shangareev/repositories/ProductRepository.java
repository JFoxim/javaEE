package ru.shangareev.repositories;

import ru.shangareev.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final Connection conn;

    public ProductRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
    }

    public void insert(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into products(name, description, price) values (?, ?, ?);")) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.execute();
        }
    }

    public void save(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update products set name = ?, description = ?, price= ? where id = ?;")) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getId());
            stmt.execute();
        }
    }

    public Product findByName(String name) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, name, description, price  from products where name = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3),  rs.getDouble(4));
            }
        }
        return new Product(-1, "", "", 0);
    }

    public Product findById(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, name, description, price from products where id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4));
            }
        }
        return null;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, name, description, price from products");

            while (rs.next()) {
                res.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4)));
            }
        }
        return res;
    }

    public void delete(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from products where id = ?;")) {
            stmt.setInt(1, product.getId());
            stmt.execute();
        }
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists products (\n" +
                    "\tid int auto_increment primary key,\n" +
                    "    name varchar(255),\n" +
                    "    description varchar(5000),\n" +
                    "    price decimal(8,2));");
        }
    }
}
