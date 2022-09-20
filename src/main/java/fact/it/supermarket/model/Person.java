// Tibbo Van Leemput
// r0879836
package fact.it.supermarket.model;

public class Person {

    // attributes
    private String firstName, surName;

    // constructor
    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    // other methods
    public String toString() {
        return this.surName.toUpperCase() + " " + this.firstName;
    }


}
