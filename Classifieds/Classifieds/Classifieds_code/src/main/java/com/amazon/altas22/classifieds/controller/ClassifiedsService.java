package com.amazon.altas22.classifieds.controller;

import com.amazon.altas22.classifieds.ClassifiedsSession;
import com.amazon.altas22.classifieds.db.ClassifiedsDAO;
import com.amazon.altas22.classifieds.model.Classifieds;

import java.util.List;
import java.util.Scanner;

public class ClassifiedsService {
    Classifieds updated = null;

    Scanner scanner = new Scanner(System.in);

    private static ClassifiedsService service = new ClassifiedsService();

    ClassifiedsDAO classifiedsDAO = new ClassifiedsDAO();

    private ClassifiedsService(){

    }
    public static ClassifiedsService getInstance(){
        return service;
    }

    public void getClassifiedDetails(){
        System.out.println("~~~~~~~~~~~~~~~Capturing Classified Details~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enter the Headline:");
        ClassifiedsSession.classified.headline = scanner.nextLine();
        System.out.println("Product Name:");
        ClassifiedsSession.classified.product_name = scanner.nextLine();
        System.out.println("Brand:");
        ClassifiedsSession.classified.brand = scanner.nextLine();
        System.out.println("Condition:\n Select \n 1: Brand new(Seal Packed)\n 2: Lightly Used\n 3:Moderately Used\n 4:Heavily Used\n 5:Damaged/Dented\n 6:Not Working");
        ClassifiedsSession.classified.product_condition= scanner.nextLine();
        System.out.println("Description:");
        ClassifiedsSession.classified.description = scanner.nextLine();
        System.out.println("Price:");
        ClassifiedsSession.classified.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Price Recurrence in months (0 means one time payment)");
        ClassifiedsSession.classified.recurrence = Integer.parseInt(scanner.nextLine());
        System.out.println("Image Gallery Link: ");
        ClassifiedsSession.classified.pictures = scanner.nextLine();
        System.out.println("User: " +ClassifiedsSession.user.name);
        ClassifiedsSession.classified.user_id= ClassifiedsSession.user.id;
        System.out.println("Category Id ");
        ClassifiedsSession.classified.category_id = Integer.parseInt(scanner.nextLine());
    }

    public void addClassified(){
        getClassifiedDetails();
        int result = classifiedsDAO.insert(ClassifiedsSession.classified);
        String message = (result > 0) ? "Classified Added Successfully" : "Adding Classified Failed. Try Again..";
        System.out.println(message);
    }

    public void viewClassifieds(){
        List<Classifieds> objects = classifiedsDAO.retrieve();
        for (Classifieds object : objects){
            object.prettyPrint();
        }
    }

    public void pendingClassifieds(){
        String sql = "Select * from Classifieds where status = 0";
        List<Classifieds> objects = classifiedsDAO.retrieve(sql);
        for (Classifieds object : objects){
            object.prettyPrint();
        }
    }

    public void getClassified(){
        String sql = "Select * from Classifieds where id ="+ClassifiedsSession.classified.id;
        List<Classifieds> objects = classifiedsDAO.retrieve(sql);
        ClassifiedsSession.classified = objects.get(0);
    }

    public void getApprovedClassified(){
        String sql = "Select * from Classifieds where status = 1";
        List<Classifieds> objects = classifiedsDAO.retrieve(sql);
        for (Classifieds object : objects){
            object.prettyPrint();
        }
    }

    public void updateClassified(){
        int result = classifiedsDAO.update(ClassifiedsSession.classified);
        String message = (result > 0) ? "Classified Updated Successfully" : "Updating Classified Failed. Try Again..";
        System.out.println(message);
    }

    public void deleteClassified(){
        int result = classifiedsDAO.delete(updated);
        String message = (result > 0) ? "Classified Removed Successfully" : "Deleting Classified Failed. Try Again..";
        System.out.println(message);
    }

public void viewUserClassified(){
    String sql = "Select * from Classifieds where user_id ="+ClassifiedsSession.user.id;
    List<Classifieds> objects = classifiedsDAO.retrieve(sql);
    for (Classifieds object : objects){
        object.prettyPrint();
    }
}

}
