package com.amazon.altas22.classifieds.controller;

import java.util.List;
import java.util.Scanner;

import com.amazon.altas22.classifieds.ClassifiedsSession;
import com.amazon.altas22.classifieds.db.UserDAO;
import com.amazon.altas22.classifieds.model.User;

public class AuthenticationService {
    Scanner scanner = new Scanner(System.in);
    private static AuthenticationService service = new AuthenticationService();
    UserDAO dao = new UserDAO();

    private AuthenticationService(){

    }
    public static AuthenticationService getInstance() {
        return service;
    }
    public boolean loginUser(User user) {

        String sql = "SELECT * FROM User WHERE email = '"+user.email+"' AND password = '"+user.password+"'";
        List<User> users = dao.retrieve(sql);

        if(users.size() > 0) {
            User u = users.get(0);
            user.id = u.id;
            user.name = u.name;
            user.phone = u.phone;
            user.email = u.email;
            user.address = u.address;
            user.status = u.status;
            user.type = u.type;
            user.createdOn = u.createdOn;
            return true;
        }

        return false;
    }

    public boolean registerUser(User user) {
        return dao.insert(user) > 0;
    }

    public boolean updateUser(User user) {

        return dao.update(user) > 0;
    }

    public void getUserDetails(){

        System.out.println("Enter Your Name:");
        String name = scanner.nextLine();
        if(!name.isEmpty()) {
            ClassifiedsSession.user.name = name;
        }

        System.out.println("Enter Your Phone:");
        String phone = scanner.nextLine();
        if(!phone.isEmpty()) {
            ClassifiedsSession.user.phone = phone;
        }

        System.out.println("Enter Your Password:");
        String password = scanner.nextLine();
        if(!password.isEmpty()) {
            ClassifiedsSession.user.password = password;
        }

        System.out.println("Enter Your Address:");
        String address = scanner.nextLine();
        if(!address.isEmpty()) {
            ClassifiedsSession.user.address = address;
        }
        ClassifiedsSession.user.status = 1;
        ClassifiedsSession.user.type = 2;

        if(updateUser(ClassifiedsSession.user)) {
            System.out.println("Profile Updated Successfully");
        }else {
            System.err.println("Profile Update Failed...");
        }
    }

    public void allUsers(){
        List<User> users = dao.retrieve();
        for (User user : users){
            user.prettyPrint();
        }
    }
    public void selectUser(){
        String sql = "Select * from User where id = "+ClassifiedsSession.user.id;
        List<User> users = dao.retrieve(sql);
        if(users.size() > 0) {
            User u = users.get(0);
            ClassifiedsSession.user.id = u.id;
            ClassifiedsSession.user.name = u.name;
            ClassifiedsSession.user.phone = u.phone;
            ClassifiedsSession.user.email = u.email;
            ClassifiedsSession.user.address = u.address;
            ClassifiedsSession.user.status = u.status;
            ClassifiedsSession.user.type = u.type;
            ClassifiedsSession.user.createdOn = u.createdOn;
        }
    }
}
