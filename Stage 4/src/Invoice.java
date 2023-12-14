/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelPackage;
import java.util.*;

/**
 *
 * @author 15747
 */
public class Invoice {
    public static ArrayList<Invoice> invoices;
    private int invoiceNum;
    private String cusFirstName;
    private String cusLastName;
    private int nightsStayed;
    private double roomPrice;
    private double totalBill;
    
    public Invoice() {
        if(invoices == null) {
            invoices = new ArrayList<>();
        }
    }
    public Invoice(int invoiceNum, String cusFirstName, String cusLastName, int nightsStayed, double roomPrice) {
        this.invoiceNum = invoiceNum;
        this.cusFirstName = cusFirstName;
        this.cusLastName = cusLastName;
        this.nightsStayed = nightsStayed;
        this.roomPrice = roomPrice;
    }
    
    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCusFirstName() {
        return cusFirstName;
    }

    public void setCusFirstName(String cusFirstName) {
        this.cusFirstName = cusFirstName;
    }
    
    public String getCusLastName() {
        return cusLastName;
    }

    public void setCusLastName(String cusLastName) {
        this.cusLastName = cusLastName;
    }

    public int getNightsStayed() {
        return nightsStayed;
    }

    public void setNightsStayed(int nightsStayed) {
        this.nightsStayed = nightsStayed;
    }
    
    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public double calculateTotale() {
        return nightsStayed * roomPrice;
    }
    
}
