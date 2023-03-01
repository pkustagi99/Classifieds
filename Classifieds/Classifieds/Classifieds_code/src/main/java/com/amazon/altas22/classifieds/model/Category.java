package com.amazon.altas22.classifieds.model;

/*
My SQL Statement
create table Category(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(256),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP
);
 */
public class Category {
    public Category(int id, String title, String createdOn) {
        this.id = id;
        this.title = title;
        this.createdOn = createdOn;
    }

    //Attributes
    public int id; public String title; public String createdOn;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

    public Category(){

    }
public void prettyprint(){
    System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("Title:\t\t"+title);
    System.out.println("Category Id:\t\t"+id);
    System.out.println("Created On:\t\t"+createdOn);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~");
}
}
