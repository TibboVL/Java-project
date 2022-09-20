// Tibbo Van Leemput
// r0879836
package fact.it.supermarket.model;

import org.springframework.boot.autoconfigure.web.WebProperties;

public class Department {

    // attributes
    private Staff responsible;
    private String name, photo;
    private boolean refrigerated;

    // constructors
    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    // getters
    public Staff getResponsible() {
        return responsible;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    // setters
    public void setResponsible(Staff responsibe) {
        this.responsible = responsibe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }

    // other methods
}
