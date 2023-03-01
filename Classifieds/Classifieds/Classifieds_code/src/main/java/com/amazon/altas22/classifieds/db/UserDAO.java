package com.amazon.altas22.classifieds.db;

import com.amazon.altas22.classifieds.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User>{
    DB db = DB.getInstance();

    @Override
    public int insert(User object) {
        String sql = "INSERT INTO User (name, phone, email, password, address, status, type) VALUES ('"+object.name+"', '"+object.phone+"', '"+object.email+"', '"+object.password+"', '"+object.address+"', '"+object.status+"', "+object.type+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(User object) {
        String sql = "UPDATE User set name = '"+object.name+"', phone='"+object.phone+"', password='"+object.password+"', address='"+object.address+"', status='"+object.status+"' WHERE email = '"+object.email+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(User object) {
        String sql = "DELETE FROM User WHERE email = '"+object.email+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<User> retrieve() {

        String sql = "SELECT * from User";

        return getUsers(sql);
    }

    private List<User> getUsers(String sql) {
        ArrayList<User> users;
        try (ResultSet set = db.executeQuery(sql)) {

            users = new ArrayList<>();

            try {
                while (set.next()) {

                    User user = new User();

                    // Read the row from ResultSet and put the data into User Object
                    user.id = set.getInt("id");
                    user.name = set.getString("name");
                    user.phone = set.getString("phone");
                    user.email = set.getString("email");
                    user.password = set.getString("password");
                    user.address = set.getString("address");
                    user.status = set.getInt("status");
                    user.type = set.getInt("type");
                    user.createdOn = set.getString("createdOn");

                    users.add(user);
                }
            } catch (Exception e) {
                System.err.println("Something Went Wrong: " + e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return users;
    }

    @Override
    public List<User> retrieve(String sql) {

        return getUsers(sql);
    }
}
