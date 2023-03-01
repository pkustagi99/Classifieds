package com.amazon.altas22.classifieds.db;

import com.amazon.altas22.classifieds.model.Classifieds;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassifiedsDAO implements DAO<Classifieds> {

    DB db = DB.getInstance();
    @Override
    public int insert(Classifieds object) {
        String sql = "INSERT INTO Classifieds (category_id, user_id, status, headline, product_name, brand, product_condition, description, price, recurrence, pictures) " +
                "VALUES ("+object.category_id+", "+object.user_id+", '"+object.status+"', '"+object.headline+"', '"+object.product_name+"', '"+object.brand+"', '"+object.product_condition+"', '"+object.description+"', "+ object.price+", "+ object.recurrence+",'"+ object.pictures+"')";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Classifieds object) {
        String sql = "UPDATE Classifieds set category_id = "+object.category_id+", user_id = "+object.user_id+", status = '"+object.status+"', headline = '"+object.headline+"', product_name = '"+object.product_name+"', brand = '"+object.brand+"', product_condition = '"+object.product_condition+"', description = '"+object.description+"', price = "+ object.price+", recurrence = "+ object.recurrence+", pictures =  '"+ object.pictures+"' Where id = "+object.id+"";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Classifieds object) {
        String sql = "DELETE from classifieds where id = "+object.id+"";
        return db.executeSQL(sql);
    }

    @Override
    public List<Classifieds> retrieve() {
        String sql = "Select * from classifieds where status = 1";
        ResultSet set = db.executeQuery(sql);
        ArrayList<Classifieds> classifieds = new ArrayList<Classifieds>();

        try {
            while (set.next()){
                Classifieds classified = new Classifieds();
                classified.id = set.getInt("id");
                classified.description = set.getString("description");
                classified.brand = set.getString("brand");
                classified.product_name = set.getString("product_name");
                classified.product_condition = set.getString("product_condition");
                classified.headline = set.getString("headline");
                classified.category_id = set.getInt("category_id");
                classified.status = set.getInt("status");
                classified.user_id = set.getInt("user_id");
                classified.pictures = set.getString("pictures");
                classified.recurrence = set.getInt("recurrence");
                classified.price = set.getFloat("price");
                classified.lastUpdatedOn = set.getString("lastUpdatedOn");
                classifieds.add(classified);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return classifieds;
    }

    @Override
    public List<Classifieds> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Classifieds> classifieds = new ArrayList<Classifieds>();

        try {
            while (set.next()){
                Classifieds classified = new Classifieds();
                classified.id = set.getInt("id");
                classified.description = set.getString("description");
                classified.brand = set.getString("brand");
                classified.product_name = set.getString("product_name");
                classified.product_condition = set.getString("product_condition");
                classified.headline = set.getString("headline");
                classified.category_id = set.getInt("category_id");
                classified.status = set.getInt("status");
                classified.user_id = set.getInt("user_id");
                classified.pictures = set.getString("pictures");
                classified.recurrence = set.getInt("recurrence");
                classified.price = set.getFloat("price");
                classified.lastUpdatedOn = set.getString("lastUpdatedOn");
                classifieds.add(classified);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return classifieds;
    }
}
