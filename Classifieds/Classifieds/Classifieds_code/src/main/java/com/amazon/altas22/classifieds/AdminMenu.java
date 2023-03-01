package com.amazon.altas22.classifieds;

import com.amazon.altas22.classifieds.model.Category;
import com.amazon.altas22.classifieds.model.Classifieds;
import com.amazon.altas22.classifieds.model.User;
import com.amazon.altas22.classifieds.controller.ReportService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

public class AdminMenu extends Menu{
    private static AdminMenu menu = new AdminMenu();

    public static AdminMenu getInstance() {
        return menu;
    }

    private AdminMenu() {

    }

    //display admin menu

    public void showMenu() {
        System.out.println("Navigating to Admin Menu...");
            System.out.println("Login to Admin Panel");
        boolean result = false;
        Classifieds classifieds = new Classifieds();
        //Order order = new Order();
        Category category = new Category();
        User adminuser = new User();
        System.out.println("Enter Your Email:");
        adminuser.email = scanner.nextLine();

        System.out.println("Enter Your Password:");
        adminuser.password = scanner.nextLine();

        try {
            // Encoded to Hash i.e. SHA-256 to match correctly
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(adminuser.password.getBytes(StandardCharsets.UTF_8));
            adminuser.password = Base64.getEncoder().encodeToString(hash);
        }catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }

        result = auth.loginUser(adminuser);

        if(result && adminuser.type == 1) {

            // Link the User to the Session User :)
            ClassifiedsSession.user = adminuser;
            ClassifiedsSession.classified = classifieds;
            ClassifiedsSession.category = category;


            System.out.println("^^^^^^^^^^^^^^^^^^^");
            System.out.println("Welcome to Admin App");
            System.out.println("Hello, "+adminuser.name);
            System.out.println("Its: "+new Date());
            System.out.println("^^^^^^^^^^^^^^^^^^^");

            boolean quit = false;

            while(true) {

                System.out.println("1: Approve/Reject Classified");
                System.out.println("2: Add/Remove Classifieds");
                System.out.println("3: Activate/Deactivate Users");
                System.out.println("4: Manage Type/Categories of Classifieds");
                System.out.println("5: Reports Dashboard");
                System.out.println("6. Quit");
                System.out.println("Select an Option");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: //view pending approvals
                        System.out.println("Pending Classifieds Requests");
                        asin.pendingClassifieds();
                        System.out.println("Enter the Classified Id to Approve/Reject");
                        ClassifiedsSession.classified.id = Integer.parseInt(scanner.nextLine());
                        asin.getClassified();
                            System.out.println("Do you wish to update classified (1: Approve 0: Reject)");
                            choice = Integer.parseInt(scanner.nextLine());
                        if(choice == 1){  //approve classified
                            System.out.println("Approving Classified");
                            ClassifiedsSession.classified.status = 1;
                            asin.updateClassified();
                        } else if (choice == 0) {  //reject classified
                            System.out.println("Rejecting Classified");
                            ClassifiedsSession.classified.status = 2;
                            asin.updateClassified();
                        }
                        break;

                    case 2:
                        System.out.println("Enter 1 to Add Classified | 2 to Remove Classified");
                        choice = Integer.parseInt(scanner.nextLine());
                        if(choice==1){  //list a classified
                            System.out.println("Enter the Details to post a Classified");
                            System.out.println("****************************");
                            asin.addClassified();
                        } else if (choice == 2) {  //deletes classified
                            asin.viewClassifieds();
                            System.out.println("Enter the Classified Id to Remove");
                            ClassifiedsSession.classified.id = Integer.parseInt(scanner.nextLine());
                            asin.getClassified();
                            asin.deleteClassified();
                        }
                        break;

                    case 3:
                        System.out.println("~~~~~~~~~~~~~~~User Management~~~~~~~~~~~~~~~~~~~~~");
                        auth.allUsers();
                        System.out.println("Enter the userId to change their Status between Activate:Deactivate");
                        ClassifiedsSession.user.id = Integer.parseInt(scanner.nextLine());
                        auth.selectUser();
                        if(ClassifiedsSession.user.status == 1){  //remove/deactivate a user
                            ClassifiedsSession.user.status =2;
                        }else{
                            ClassifiedsSession.user.status = 1;   //activate a user
                        }
                        if(auth.updateUser(ClassifiedsSession.user)) {
                            System.out.println("Status Updated Successfully");
                        }else {
                            System.err.println("Status Update Failed...");
                        }
                        break;

                    case 4:
                            System.out.println("**********Classified Category Management***********");
                            System.out.println("Enter\n 1:View All Categories\n 2: Add Category \n 3: Delete Category");
                            choice = Integer.parseInt(scanner.nextLine());
                            if(choice == 1){
                                cat.viewCategories();
                            } else if (choice ==2 ) {
                                System.out.println("Enter the title of the category to create");
                                ClassifiedsSession.category.title = scanner.nextLine();
                                cat.createCategory();
                            } else if (choice == 3) {
                                System.out.println("Enter the Category Id to delete");
                                ClassifiedsSession.category.id = Integer.parseInt(scanner.nextLine());
                                cat.deleteCategory();
                            }
                        break;

                    case 5:
                        System.out.println("Dashboard");    //Reports
                        System.out.println("1: Number of Classifieds");
                        System.out.println("2: Total number of sales");
                        System.out.println("3: Sales between the date range");
                        System.out.println("4: View Inactive users");
                        System.out.println("5. View Blocked classifieds");
                        choice = Integer.parseInt(scanner.nextLine());
                        ReportService report = ReportService.getInstance();
                        if(choice == 1){
                            int num = report.getTotalClassified();
                            System.out.println("Total number of classifieds : "+num);
                            break;
                        } else if (choice == 2) {
                            report.getTotalSales();
                            break;
                        } else if (choice == 3) {
                            report.getDateRange();
                            break;
                        } else if (choice == 4) {
                            report.getInactiveUsers();
                            break;
                        } else if (choice == 5) {
                            report.getRejectedClassifieds();
                            break;
                        } else {
                            System.out.println("Invalid choice!!!");
                            break;
                        }

                    case 6:
                        quit = true;
                        break;

                    default:
                        System.err.println("Invalid Choice...");
                        break;
                }
                if(quit) {
                    break;
                }

            }
        }else {
            System.err.println("Authentication Failed..");
        }
    }
}
