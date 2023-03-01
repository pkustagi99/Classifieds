package com.amazon.altas22.classifieds.db;

import com.amazon.altas22.classifieds.model.Category;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO<Category>{
    DB db = DB.getInstance();
    @Override
    public int insert(Category object) {
        String sql = "INSERT INTO Category (title) VALUES ('"+object.title+"')";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Category object) {
        String sql = "Update Category set title = '"+object.title+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Category object) {
        String sql = "DELETE from Category where id = '"+object.id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<Category> retrieve() {
        String sql = "Select * from Category";

        ResultSet set = db.executeQuery(sql);

        ArrayList<Category> categories;
        categories = new ArrayList<>();

        try {
            while (set.next()){
                Category category = new Category();
                // Read from ReultSet and put data into Category object
                category.id = set.getInt("id");
                category.title = set.getString("title");
                category.createdOn = set.getString("createdOn");

                categories.add(category);
            }
        }catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return categories;
    }

    @Override
    public List<Category> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);

        ArrayList<Category> categories;
        categories = new ArrayList<>();

        try {
            while (set.next()){
                Category category = new Category();
                // Read from ReultSet and put data into Category object
                category.id = set.getInt("id");
                category.title = set.getString("title");
                category.createdOn = set.getString("createdOn");

                categories.add(category);
            }
        }catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return categories;
    }
}
