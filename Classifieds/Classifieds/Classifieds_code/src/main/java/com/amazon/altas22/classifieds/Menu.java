package com.amazon.altas22.classifieds;

import com.amazon.altas22.classifieds.controller.AuthenticationService;
import com.amazon.altas22.classifieds.controller.CategoryService;
import com.amazon.altas22.classifieds.controller.ClassifiedsService;
import com.amazon.altas22.classifieds.controller.OrderService;
import com.amazon.altas22.classifieds.db.DB;

import java.util.Scanner;

public class Menu {
    AuthenticationService auth = AuthenticationService.getInstance();
    ClassifiedsService asin = ClassifiedsService.getInstance();

    CategoryService cat = CategoryService.getInstance();

    OrderService tran = OrderService.getInstance();

    Scanner scanner = new Scanner(System.in);
    void showMainMenu(){
        //Initial Menu Of the Application
        while(true) {

            System.out.println("1: Admin");
            System.out.println("2: User");
            System.out.println("3: Quit");
            //System.out.println("4.Classified Detail addition");

            System.out.println("Select an Option");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 3) {
                System.out.println("Thank You For using Classifieds App");

                // Close the DataBase Connection, when user has exited the application :)
               DB db = DB.getInstance();
                db.closeConnection();
                scanner.close();
                break;
            }

            try {
                MenuFactory.getMenu(choice).showMenu();
            } catch (Exception e) {
                System.err.println("[Menu] [Exception] Invalid Choice...");
            }
        }
    }

    public void showMenu() {
        System.out.println("Showing the Menu...");
    }

}
