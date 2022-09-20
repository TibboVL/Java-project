// Tibbo Van Leemput
// r0879836
package fact.it.supermarket.model;

import java.util.ArrayList;

public class Customer extends Person{

    // attributes
    private int cardNumber, yearOfBirth;
    private ArrayList<String> shoppingList = new ArrayList<>();

    // constructors
    public Customer(String firstName, String surName) {
        super(firstName, surName);
        this.cardNumber = -1;
    }

    // getters
    public int getCardNumber() {
        return cardNumber;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public ArrayList<String> getShoppingList() {
        return shoppingList;
    }

    // setters
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    // other methods
    public boolean addToShoppingList(String productName) {
        if (this.shoppingList.size() < 5) {
            shoppingList.add(productName);
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOnShoppingList() {
        return shoppingList.size();
    }

    public String toString() {
        return "Customer " + super.toString() + " with card number " + this.cardNumber;
    }
}
