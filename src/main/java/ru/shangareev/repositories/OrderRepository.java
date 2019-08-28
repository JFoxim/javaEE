package ru.shangareev.repositories;

import ru.shangareev.entities.Order;
import ru.shangareev.entities.OrderStatus;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    @Transactional
    public void merge(OrderStatus orerStatus){
        entityManager.merge(orerStatus);
    }

    @Transactional
    public Order findById(int id) throws SQLException {
        return entityManager.find(Order.class, id);
    }

    @Transactional
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        orders = entityManager.createQuery(
                "select order from Order as order")
                .getResultList();
        return orders;
    }

}
