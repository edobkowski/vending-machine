package com.codecool.View;

public class VendingMachineView {

    public void displayOutOfOrder() {
        System.out.println("PRODUCT OUT OF ORDER");
    }

    public void displayInsert(){
        System.out.println("PLEASE, INSERT A COIN");
    }

    public void displayThankYou(){
        System.out.println("THANK YOU");
    }

    public void displayRejected(){
        System.out.println("COIN NOT ACCEPTED!");
    }

    public void displaySum(double sum){
        System.out.printf("CURRENT SUM: .2%f", sum);
    }

    public void displayPrice(double price) {
        System.out.printf("PRICE: .2%f", price);
    }
}
