package com.amazon.altas22.classifieds.controller;
//Admin Reporting Services


import com.amazon.altas22.classifieds.db.ClassifiedsDAO;
import com.amazon.altas22.classifieds.db.OrderDAO;
import com.amazon.altas22.classifieds.db.UserDAO;
import com.amazon.altas22.classifieds.model.Classifieds;
import com.amazon.altas22.classifieds.model.Order;
import com.amazon.altas22.classifieds.model.User;

import java.util.List;
import java.util.Scanner;

public class ReportService {
    private static ReportService service = new ReportService();
    private ReportService(){
    }
    public static ReportService getInstance(){
        return service;
    }
    Scanner scanner = new Scanner(System.in);
    ClassifiedsDAO classifiedsDAO = new ClassifiedsDAO();
    OrderDAO orderDAO = new OrderDAO();
    UserDAO userDAO = new UserDAO();
    ClassifiedsService asin = ClassifiedsService.getInstance();

    public int getTotalClassified(){
        String sql = "Select * from Classifieds";
        List<Classifieds> objects = classifiedsDAO.retrieve(sql);
        return objects.size();
    }

    public void getTotalSales(){
        String sql = "Select * from `order` where status = 3";
        List<Order> objects = orderDAO.retrieve(sql);
        int num = objects.size();
        System.out.println("Total number of sales : " +num);
        num =0;
        for (Order object:objects){
            num += object.price;
        }
        System.out.println("Total Sales net : " +num);
    }

    public void getDateRange(){
        List<Order> objects = null;
        System.out.println("Enter the Date in YYYY-MM-DD Format Ex- 2023-02-28");
        System.out.println("Enter the From Date : ");
        String from = scanner.nextLine();
        System.out.println("Enter the To Date : ");
        String to = scanner.nextLine();
        //Select between date range
        String sql = "SELECT * from `order` where createdOn between '"+from+"' and '"+to+"'";
        objects = orderDAO.retrieve(sql);
        for (Order object:objects) {
            object.prettyPrint();
        }
    }

    public void getInactiveUsers(){
        System.out.println("List of Inactive users\n");
        String sql = "SELECT * from User where status = 2";
        List<User> objects = userDAO.retrieve(sql);
        for (User object:objects) {
            object.prettyPrint();
        }

    }

    public void getRejectedClassifieds(){
        System.out.println("List of Rejected Classifieds\n");
        String sql = "SELECT * from Classifieds where status = 2";
        List<Classifieds> objects = classifiedsDAO.retrieve(sql);
        for (Classifieds object:objects) {
            object.prettyPrint();
        }
    }

}
