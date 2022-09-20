// Tibbo Van Leemput
// r0879836
package fact.it.supermarket.model;

import java.util.ArrayList;

public class Supermarket {

    // attributes
    private ArrayList<Department> departmentList = new ArrayList<> ();
    private String name;
    private int numberCustomers;
    private Staff generalManager;

    // constructors
    public Supermarket(String name) {
        this.name = name;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getNumberCustomers() {
        return numberCustomers;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    // other methods
    public int getNumberOfDepartments() {

        return this.departmentList.size();
    }

    public ArrayList<Department> getDepartmentList() {
        return departmentList;
    }

    public void addDepartment(Department department) {
        this.departmentList.add(department);
    }

    public Department searchDepartmentByName(String name) {

        for (int index = 0; index <= departmentList.size() - 1; index++ ) {
            if (departmentList.get(index).getName().equals(name)) {
                return departmentList.get(index);
            }
        }

        return null;
    }

    public void registerCustomer(Customer customer) {
        this.numberCustomers += 1;
        customer.setCardNumber(this.getNumberCustomers());
    }


    // extention exam
    public Staff getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(Staff generalManager) {
        this.generalManager = generalManager;
    }
}
