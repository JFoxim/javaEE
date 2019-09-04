package ru.shangareev.repositories;

import ru.shangareev.entities.OrderStatus;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusRepository {

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    @Transactional
    public void merge(OrderStatus orerStatus){
        entityManager.merge(orerStatus);
    }

    @Transactional
    public OrderStatus findByTitle(String title) throws SQLException {
        OrderStatus orderStatus = (OrderStatus) entityManager.createQuery(
                "select orderStatus from OrderStatus as orderStatus where orderStatus.title = ?1")
                .setParameter(1, title)
                .getSingleResult();
        return orderStatus;
    }

    @Transactional
    public OrderStatus findById(int id) throws SQLException {
        return entityManager.find(OrderStatus.class, id);
    }

    @Transactional
    public List<OrderStatus> getAllOrderStatuses() throws SQLException {
        List<OrderStatus> orderStatusList = new ArrayList<>();
        orderStatusList = entityManager.createQuery(
                "select orderStatus from OrderStatus as orderStatus")
                .getResultList();
        return orderStatusList;
    }
}
