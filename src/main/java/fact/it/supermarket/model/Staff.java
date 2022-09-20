// Tibbo Van Leemput
// r0879836

package fact.it.supermarket.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Staff extends Person{

    // attributes
    private LocalDate startDate;
    private boolean student;

    // constructor

    public Staff(String firstName, String surName) {
        super(firstName, surName);
        this.startDate = LocalDate.now();
    }

    // getters
    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isStudent() {
        return student;
    }

    // setters
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    // other methods
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (student) {
            return "Staff member " + super.toString() + " (working student) is employed since " + this.startDate.format(formatter);
        } else {
            return "Staff member " + super.toString() + " is employed since " + this.startDate.format(formatter);
        }

    }
}
