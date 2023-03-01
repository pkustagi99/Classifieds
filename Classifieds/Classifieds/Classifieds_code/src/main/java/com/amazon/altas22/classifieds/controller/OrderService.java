package com.amazon.altas22.classifieds.controller;

import com.amazon.altas22.classifieds.ClassifiedsSession;
import com.amazon.altas22.classifieds.db.OrderDAO;
import com.amazon.altas22.classifieds.db.UserDAO;
import com.amazon.altas22.classifieds.model.Classifieds;
import com.amazon.altas22.classifieds.model.Order;
import com.amazon.altas22.classifieds.model.User;

import java.util.Scanner;
import java.util.List;

//Purchasing and Updating the Classifieds DB
public class OrderService {

    private static OrderService service = new OrderService();
    private OrderService(){
    }
    public static OrderService getInstance(){
        return service;
    }

    ClassifiedsService asin = ClassifiedsService.getInstance();
    Scanner scanner = new Scanner(System.in);
    OrderDAO orderDAO = new OrderDAO();
    UserDAO userDAO = new UserDAO();
    public void buy(int selectId){
        // View all classifieds which can be buy

        ClassifiedsSession.classified.id = selectId;
        asin.getClassified();

        // Buy
        Order order = new Order();
        order.fromUserId = ClassifiedsSession.user.id;
        System.out.println("Enter your price:");
        order.price = Integer.parseInt(scanner.nextLine());
        buyaddition(order, ClassifiedsSession.classified);
    }

    public void buyaddition(Order order, Classifieds classified) {
        order.classifiedId = classified.id;
        order.toUserId = classified.user_id;
        order.status = 0;

        int result = orderDAO.insert(order);
        String message = (result > 0) ? "Order updated Successfully" : "Order Update Failed. Try Again..";
        System.out.println(message);
    }

    public void sell(){
        // View pending orders

        String sql = "SELECT * from `order` where toUserId="+ClassifiedsSession.user.id+" AND status=0";
        List<Order> objects = orderDAO.retrieve(sql);
        for (Order object : objects){
            object.prettyPrint();
        }

        System.out.println("Choose a classified (0 to cancel):");
        int selectId = Integer.parseInt(scanner.nextLine());

        if (selectId == 0) {
            return;
        }else {
            ClassifiedsSession.order.id = selectId;
            getOrder();
        }
        System.out.println("1. Approve");
        System.out.println("2. Reject");
        int approvedStatus = Integer.parseInt(scanner.nextLine());

        Order order = ClassifiedsSession.order;
        order.status = approvedStatus;


        int result = orderDAO.update(order);
        String message = (result > 0) ? "Order updated Successfully" : "Order Update Failed. Try Again..";
        System.out.println(message);

    }

    public void getOrder(){
        String sql = "Select * from `order` where id ="+ClassifiedsSession.order.id;
        List<Order> objects = orderDAO.retrieve(sql);
        ClassifiedsSession.order = objects.get(0);
    }

    public String viewCart(){
        String message = "Failed...";
        String sql = "Select * from `order` where fromUserId = "+ClassifiedsSession.user.id+" and status = 1";
        List<Order> objects = orderDAO.retrieve(sql);
        for (Order object : objects){
            object.prettyPrint();
        }
        boolean result = false;
        boolean ordersession = false;
        System.out.println("Enter the Order Id to Proceed with payment");
        int choice = Integer.parseInt(scanner.nextLine());
        for (Order object : objects){
            if (object.id == choice){
                ClassifiedsSession.order = object;
                ordersession = true;
            }
        }if(ordersession == false){
            message = "Did not find Order associated to the Order Id";
            return message;
        }
        System.out.println("*******Payment Gateway************\n");
        System.out.println("Select the Payment Mode\n 1. Card \n 2. Netbanking/UPI  \n 3. COD");
        choice = Integer.parseInt(scanner.nextLine());
        if(choice == 1 || choice == 2){
            System.out.println("To Confirm Payment press 1 | To Cancel press 0");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1){
                System.out.println("Proceeding with Payment");
                result = payment(ClassifiedsSession.order.classifiedId);
                if (result){
                    message= "Transaction Successful";
                }else {
                    message = "Transaction Failed";
                }
            } else if (choice == 0) {
                message = "Transaction Failed";
                return message;
            }
        } else if (choice == 3) {
            sql = "Select * from `user` where id ="+ClassifiedsSession.order.fromUserId;
            List<User> users = userDAO.retrieve(sql);
            User user = new User();
            user = users.get(0);
            System.out.println("You have selected Cash on delivery "+user.name);
            System.out.println("Kindly provide your contact Info: "+user.phone);
            result = payment(ClassifiedsSession.order.classifiedId);
            if (result){
                message= "Transaction Successful";
            }else {
                message = "Transaction Failed";
            }
        }
        return message;
    }

    public boolean payment(int id){
        ClassifiedsSession.order.status=3;
        int result = orderDAO.update(ClassifiedsSession.order);
        ClassifiedsSession.classified.id = id;
        asin.getClassified();
        ClassifiedsSession.classified.status = 3;
        asin.updateClassified();
        if(result == 0){
            return false;
        }
        return true;
    }
}