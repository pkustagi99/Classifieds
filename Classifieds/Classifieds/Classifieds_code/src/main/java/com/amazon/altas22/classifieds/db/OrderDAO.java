package com.amazon.altas22.classifieds.db;

import com.amazon.altas22.classifieds.model.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order> {

    DB db = DB.getInstance();
    @Override
    public int insert(Order object) {
        String sql = "INSERT INTO `order` (classifiedId, fromUserId, toUserId, price, status) values ("+object.classifiedId+","+object.fromUserId+","+object.toUserId+","+object.price+","+object.status+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Order object) {
        String sql = "UPDATE `order` SET status="+object.status+", createdOn='"+object.createdOn+"' WHERE id="+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Order object) {
        return 0;
    }

    @Override
    public List<Order> retrieve() {
        String sql = "SELECT * from `order`";
        ResultSet set = db.executeQuery(sql);

        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            while(set.next()) {
                Order order = new Order();

                // Read the row from ResultSet and put the data into User Object
                order.id = set.getInt("id");
                order.classifiedId = set.getInt("classifiedId");
                order.fromUserId = set.getInt("fromUserId");
                order.toUserId = set.getInt("toUserId");
                order.price = set.getInt("price");
                order.status = set.getInt("status");
                order.createdOn = set.getString("createdOn");

                orders.add(order);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }

        return orders;
    }

    @Override
    public List<Order> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);

        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            while(set.next()) {
                Order order = new Order();

                // Read the row from ResultSet and put the data into User Object
                order.id = set.getInt("id");
                order.classifiedId = set.getInt("classifiedId");
                order.fromUserId = set.getInt("fromUserId");
                order.toUserId = set.getInt("toUserId");
                order.price = set.getInt("price");
                order.status = set.getInt("status");
                order.createdOn = set.getString("createdOn");

                orders.add(order);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }

        return orders;
    }

}
