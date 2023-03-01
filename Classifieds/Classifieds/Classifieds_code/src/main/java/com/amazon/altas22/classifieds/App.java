package com.amazon.altas22.classifieds;

import com.amazon.altas22.classifieds.db.DB;

/**
 * Internal Classifieds
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("    Welcome to Classifieds Application");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Menu menu = new Menu();

        if(args.length > 0) {
            DB.FILEPATH = args[0];
        }

        menu.showMainMenu();
    }
}
