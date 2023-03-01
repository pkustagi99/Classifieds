package com.amazon.altas22.classifieds;

import com.amazon.altas22.classifieds.model.Classifieds;
import com.amazon.altas22.classifieds.model.Order;
import com.amazon.altas22.classifieds.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

public class UserMenu extends Menu{
    private static UserMenu menu = new UserMenu();

    public static UserMenu getInstance() {
        return menu;
    }

    private UserMenu() {

    }

    //User's main menu

     public void showMenu(){
         System.out.println("Navigating to User Menu...");
         System.out.println("1: Register");
         System.out.println("2: Login");
         System.out.println("3: Cancel");

         System.out.println("Enter Your Choice: ");
         int initialChoice = Integer.parseInt(scanner.nextLine());

         boolean result = false;

         User user = new User();
         Classifieds classifieds = new Classifieds();
         Order order = new Order();
         ClassifiedsSession.order = order;


         if(initialChoice == 1) {

             System.out.println("Enter Your Name:");
             user.name = scanner.nextLine();

             System.out.println("Enter Your Phone:");
             user.phone = scanner.nextLine();

             System.out.println("Enter Your Email:");
             user.email = scanner.nextLine();

             System.out.println("Enter Your Password:");
             user.password = scanner.nextLine();

             try {
                 // Hash the Password of User :)
                 MessageDigest digest = MessageDigest.getInstance("SHA-256");
                 byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                 user.password = Base64.getEncoder().encodeToString(hash);
             }catch (Exception e) {
                 System.err.println("Something Went Wrong: "+e);
             }

             System.out.println("Enter Your Address:");
             user.address = scanner.nextLine();

             // As we know default status is active
             user.status = 1;

             // As we know, user is registering
             user.type = 2;

             result = auth.registerUser(user);

         }else if(initialChoice == 2) {

             System.out.println("Enter Your Email:");
             user.email = scanner.nextLine();

             System.out.println("Enter Your Password:");
             user.password = scanner.nextLine();

             try {
                 // Encoded to Hash i.e. SHA-256 to match correctly
                 MessageDigest digest = MessageDigest.getInstance("SHA-256");
                 byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                 user.password = Base64.getEncoder().encodeToString(hash);
             }catch (Exception e) {
                 System.err.println("Something Went Wrong: "+e);
             }

             result = auth.loginUser(user);

         }else if(initialChoice == 3) {
             System.out.println("Thank You for Using Classifieds App");
         }else {
             System.err.println("Invalid Choice...");
             System.out.println("Thank You for Using Classifieds App");
         }


         if(result && user.type == 2) {

             // Link the User to the Session User :)
             ClassifiedsSession.user = user;
             ClassifiedsSession.classified = classifieds;

             System.out.println("^^^^^^^^^^^^^^^^^^^");
             System.out.println("Welcome to User App");
             System.out.println("Hello, "+user.name);
             System.out.println("Its: "+new Date());
             System.out.println("^^^^^^^^^^^^^^^^^^^");

             boolean quit = false;

             while(true) {
                 System.out.println("****************************");
                 System.out.println("1: Manage Profile");
                 System.out.println("2: Post a Classified");
                 System.out.println("3: View all Classifieds / Shop / Purchase Classified");
                 System.out.println("4: Order Menu");
                 System.out.println("5: Your Classifieds");
                 System.out.println("6: My Cart / Your Approved Buy Request");
                 System.out.println("7: Quit");
                 System.out.println("Select an Option");

                 int choice = Integer.parseInt(scanner.nextLine());

                 switch (choice) {
                     case 1: //View or update profile
                         System.out.println("My Profile");
                         user.prettyPrint();
                         System.out.println("Do you wish to update Profile (1: update 0: cancel)");
                         choice = Integer.parseInt(scanner.nextLine());
                         if(choice == 1){
                            auth.getUserDetails();
                         }
                         break;

                     case 2: //add classified
                         System.out.println("Enter the Details to post a Classified");
                         System.out.println("****************************");
                         asin.addClassified();
                         break;

                     case 3:  //view all approved classifieds to buy
                         System.out.println("~~~~~~~~~~~~~~~Classifieds Menu~~~~~~~~~~~~~~~~~~~~~");
                         asin.viewClassifieds();
                         System.out.println("Enter classified ID to Purchase or 0 to Go Back");
                         choice = Integer.parseInt(scanner.nextLine());
                         if(choice == 0){
                             break;
                         } else {
                             // Classifieds Purchase Menu and buy option

                             tran.buy(choice);
                         }
                         break;
                     case 4:  //sell classified
                         System.out.println("~~~~~~~~~~~~~~~Sell Menu~~~~~~~~~~~~~~~~~~~~~");
                         tran.sell();
                         break;
                     case 5: //View user's listed classifieds
                            System.out.println("~~~~~~~~~~~Your Classifieds~~~~~~~~~~~~~~");
                            asin.viewUserClassified();
                            break;
                     case 6:  //Display cart to payment
                         System.out.println("~~~~~~~~~~Your Cart~~~~~~~~~~~");
                            System.out.println(tran.viewCart());
                         break;
                     case 7:  //quit
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
