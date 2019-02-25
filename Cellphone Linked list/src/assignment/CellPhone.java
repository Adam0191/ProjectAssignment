// -----------------------------------------------------
// Assignment 4
// Question: PART II, CellPhone.java
// Written by: Fouad Serradj | ID : 40009794
// ---------------------------------------------
package assignment;

import java.util.Scanner;

public class CellPhone implements Cloneable{

    private long serialNum;
    private String brand;
    private int year;
    private double price;
    public CellPhone()
    {
        serialNum=0;
        brand=null;
        year=0;
        price=0;
    }

    public CellPhone(long serialNum, String brand, double price, int year) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    public CellPhone(CellPhone cp, long n)
    {
        this.brand=cp.brand;
        this.year=cp.year;
        this.price=cp.price;
        this.serialNum=n;
    }

    public long getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "[" + serialNum + ": " + brand + " " + price + " " + year + "]";
    }

    public boolean equals(Object oneObject)
    {
        if(this==null||oneObject==null||this.getClass()!=oneObject.getClass())
            return false;
        else
        {
            CellPhone cp=(CellPhone) oneObject;
            return (this.brand.equals(cp.brand)&&this.price==cp.price&&this.year==cp.year);
        }

    }

    
    /*
     *  the clone method can generate some privacy leaks and that by giving other classes (out of the package for exemple) objects with 
     *  the right the change/access private instance variables and methods
     * 
     */
   
    public CellPhone clone()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter the new serial number.");
        String serialNumber=input.next();
        if(this.serialNum==Long.parseLong(serialNumber));
        {
            System.out.println("The serial number that you entered already exist, please enter a new one.");
            clone();
        }
            return new CellPhone(this,Long.parseLong(serialNumber));

    }


}