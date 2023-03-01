package com.amazon.altas22.classifieds.model;


import java.util.Scanner;

/*MySQL:
        create table Classifieds(
        id INT PRIMARY KEY AUTO_INCREMENT,
        category_id INT,
        user_id INT,
        status INT DEFAULT 0,
        headline VARCHAR(100),
        product_name VARCHAR(50),
        brand VARCHAR(25),
        product_condition VARCHAR(256),
        description VARCHAR(500),
        price Float,
        recurrence INT DEFAULT 0,
        pictures VARCHAR(500),
        lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (category_id) REFERENCES Category(id),
        FOREIGN KEY (user_id) REFERENCES User(id)
        );

        */
public class Classifieds {

    Scanner scanner = new Scanner(System.in);

    //Attributes
    public int id; public int category_id; public int status; public int user_id;
    public String headline;public String product_name; public String brand;public String product_condition;public String description;public Float price;
    public String lastUpdatedOn;public String pictures;

    @Override
    public String toString() {
        return "Classifieds{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", status=" + status +
                ", user_id=" + user_id +
                ", headline='" + headline + '\'' +
                ", product_name='" + product_name + '\'' +
                ", brand='" + brand + '\'' +
                ", product_condition='" + product_condition + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", lastUpdatedOn='" + lastUpdatedOn + '\'' +
                ", pictures='" + pictures + '\'' +
                ", recurrence=" + recurrence +
                '}';
    }

    public Classifieds(int id, int category_id, int status, int user_id, String headline, String product_name, String brand, String product_condition, String description, Float price, String lastUpdatedOn, String pictures, int recurrence) {
        this.id = id;
        this.category_id = category_id;
        this.status = status;
        this.user_id = user_id;
        this.headline = headline;
        this.product_name = product_name;
        this.brand = brand;
        this.product_condition = product_condition;
        this.description = description;
        this.price = price;
        this.lastUpdatedOn = lastUpdatedOn;
        this.pictures = pictures;
        this.recurrence = recurrence;
    }

    public int recurrence;
    // Default Constructor to initialize everything at the start of the program
    public Classifieds() {
    }
    //Variable loading

    public void prettyPrint(){
        System.out.println("~~~~~~~~~~~~~~~Classified Details~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Headline:\t\t"+headline);
        System.out.println("Product Name:\t\t"+product_name);
        System.out.println("Brand:\t\t"+brand);
        System.out.println("product_condition: \t\t"+product_condition);
        System.out.println("Description: \t\t"+description);
        System.out.println("Price:\t\t"+price);
        System.out.println("Price Recurrence in months (0 means one time payment)"+recurrence);
        System.out.println("Image Gallery Link: \t"+pictures);
        System.out.println("User Id: \t \t "+user_id);
        System.out.println("Category Id \t\t"+category_id);
        System.out.println("Classified Id \t\t"+ id);
        System.out.println("Last Updated On:\t"+lastUpdatedOn);
    }

}
