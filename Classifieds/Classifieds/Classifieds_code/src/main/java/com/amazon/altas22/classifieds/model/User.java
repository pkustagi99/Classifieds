package com.amazon.altas22.classifieds.model;

/*

SQL Query for Table

MySQL:
create table User(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(256),
	phone VARCHAR(256),
	email VARCHAR(256) NOT NULL UNIQUE,
	password VARCHAR(256),
	address VARCHAR(256),
	status INT NOT NULL,
	type INT NOT NULL,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP
);

*/
public class User {
    // Attributes
    public int id;public String name;public String phone;public String email;public String password;public String address;public int status;public int type;public String createdOn;
    //Constructor
    public User(int id, String name, String phone, String email, String password, String address, int status, int type, String createdOn) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.status = status;
        this.type = type;
        this.createdOn = createdOn;
    }
    //Default Constructor
    public User(){

    }
    //Pretty Print
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Name:\t\t"+name);
        System.out.println("Phone:\t\t"+phone);
        System.out.println("Email:\t\t"+email);
        System.out.println("Address:\t"+address);
        System.out.println("Status:\t"+status);
        System.out.println("Id:\t"+id);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }
    // To String
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

}
