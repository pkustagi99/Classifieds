package com.amazon.altas22.classifieds.model;

/*
My SQL Statement
create table Category(
	id INT PRIMARY KEY AUTO_INCREMENT,
	classifiedId INT,
	fromUserId INT,
	toUserId INT,
    price int,
    status INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (classifiedId) REFERENCES Classifieds(id),
    FOREIGN KEY (fromUserId) REFERENCES User(id),
    FOREIGN KEY (toUserId) REFERENCES User(id)
);
 */

public class Order {
    public int id;
    public int classifiedId;
    public int fromUserId;
    public int toUserId;
    public int price;
    public int status;
    public String createdOn;

    public  Order(){

    }

    public Order(int id, int classifiedId, int fromUserId, int toUserId, int price, int status, String createdOn) {
        this.id = id;
        this.classifiedId = classifiedId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.price = price;
        this.status = status;
        this.createdOn = createdOn;
    }

    public void prettyPrint(){
        System.out.println("**********************************");
        System.out.println("Order Id:"+id);
        System.out.println("Classified ID: " + classifiedId);
        System.out.println("Buyer: " + fromUserId);
        System.out.println("Seller: " + toUserId);
        System.out.println("Proposed Price: " + price);
        System.out.println("Status: " + status);
        System.out.println("Posted On: " + createdOn);
    }

}
