import java.time.LocalDate;
import java.util.*;

public class Transaction {
    String tranName;
    User payer;
    LocalDate tranDate;
    Double totalAmount;
    User[] billParticipent;
    Double[] billSplitAmount;
    HashMap<User, Double> billSplit;


    public Transaction (String tranName, User payer, LocalDate tranDate, Double totalAmount, User[] billParticipent, Double[] billSplitAmount){
        this.tranName = tranName;
        this.payer = payer;
        this.tranDate = tranDate;
        this.totalAmount = totalAmount;
        this.billParticipent = billParticipent;
        this.billSplitAmount = billSplitAmount;
        this.billSplit = createBillSplit(billParticipent, billSplitAmount);
    }


    public String getTranName() {return tranName;}
    public void setTranName(String tranName) {this.tranName = tranName;}

    public User getPayer() {return payer;}
    public void setPayer(User payer) {this.payer = payer;}

    public LocalDate getTranDate() {return tranDate;}
    public void setTranDate(LocalDate tranDate) {this.tranDate = tranDate;}

    public Double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(Double totalAmount) {this.totalAmount = totalAmount;}

    public User[] getBillParticipent() {return billParticipent;}
    public void setBillParticipent(User[] billParticipent) {this.billParticipent = billParticipent;}

    public Double[] getBillSplitAmount() {return billSplitAmount;}
    public void setBillSplitAmount(Double[] billSplitAmount) {this.billSplitAmount = billSplitAmount;}

    public HashMap<User, Double> getBillSplit() {return billSplit;}
    public void setBillSplit(HashMap<User, Double> billSplit) {this.billSplit = billSplit;}



    ///////////////////////////////////
    //Bill Split
    ///////////////////////////////////
    public HashMap<User, Double> createBillSplit(User[] users, Double[] amount){
        HashMap<User, Double> result = new HashMap<>();
        for(int i = 0; i < users.length; i++){
            result.put(users[i], amount[i]);
        }
        return result;
    }


    ///////////////////////////////////
    //Helpers
    ///////////////////////////////////
    public void printOneTransaction(){
        System.out.println(getTranName() + ", " + getPayer().getUserName() + ", " + getTranDate() + ", " + getTotalAmount());
    }




}





/*
Date firstDate1 = new Date(int year, int month, int date);
Date firstDate2 = new Date(int year, int month, int date, int hrs, int min);
Date firstDate3 = new Date(int year, int month, int date, int hrs, int min, int sec);
https://stackoverflow.com/questions/34119441/how-to-initialize-a-variable-of-date-type-in-java

https://examples.javacodegeeks.com/java-map-example/
*/