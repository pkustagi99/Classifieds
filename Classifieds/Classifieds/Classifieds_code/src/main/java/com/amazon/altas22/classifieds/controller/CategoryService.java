package com.amazon.altas22.classifieds.controller;

import com.amazon.altas22.classifieds.ClassifiedsSession;
import com.amazon.altas22.classifieds.db.CategoryDAO;
import com.amazon.altas22.classifieds.model.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryService {

    Scanner scanner = new Scanner(System.in);

    private static CategoryService service = new CategoryService();


    public static CategoryService getInstance() {
        return service;
    }

    private CategoryService(){

    }

    CategoryDAO catDAO = new CategoryDAO();

    public void createCategory(){
        int result = catDAO.insert(ClassifiedsSession.category);
        String message = (result > 0) ? "Category Added Successfully" : "Adding Category Failed. Try Again..";
        System.out.println(message);
    }

    public void viewCategories(){
        List<Category> categorys = catDAO.retrieve();
        for (Category category : categorys){
            category.prettyprint();
        }
    }

    public void deleteCategory(){
        String sql = "Select * from category where id = "+ClassifiedsSession.category.id;
        List<Category> object = catDAO.retrieve(sql);
        if (object.size() >0){
            ClassifiedsSession.category = object.get(0);
            int result = catDAO.delete(ClassifiedsSession.category);
            String message = (result > 0) ? "Category Deleted Successfully" : "Deleting Category Failed. Try Again..";
            System.out.println(message);
        }
    }
}
