// Tibbo Van Leemput
// r0879836

package fact.it.supermarket.controller;


import fact.it.supermarket.model.Customer;
import fact.it.supermarket.model.Department;
import fact.it.supermarket.model.Staff;
import fact.it.supermarket.model.Supermarket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class MainController {
// You will need these ArrayLists in part 3 of the project assignment.
    private ArrayList<Staff> staffArrayList;
    private ArrayList<Customer> customerArrayList;
    private ArrayList<Supermarket> supermarketArrayList;
//    Write your code here

    @PostConstruct
    private void FillAll() {
        staffArrayList = fillStaffMembers();
        customerArrayList = fillCustomers();
        supermarketArrayList = fillSupermarkets();
    }


    @RequestMapping("/newCustomer")
    public String newCustomer(Model model) {

        model.addAttribute("SupermarketList" ,supermarketArrayList);

        return "1_NewCustomer";
    }

    @RequestMapping("/CreateCustomer")
    public String CreateCustomer(HttpServletRequest request, Model model) {

        String FirstName = request.getParameter("FirstName");
        String Surname = request.getParameter("Surname");
        String YearOfBirth = request.getParameter("YearOfBirth");
        int supermarketIndex = Integer.parseInt(request.getParameter("supermarketIndex"));


        Customer customer = new Customer(FirstName,Surname);
        customer.setYearOfBirth(Integer.parseInt(YearOfBirth));

        Supermarket mySupermarket = supermarketArrayList.get(supermarketIndex);
        mySupermarket.registerCustomer(customer);

        customerArrayList.add(customer);

        model.addAttribute("customer",customer);

        return "2_CustomerDetails";
    }


    @RequestMapping("/newStaffMember")
    public String newStaffMember() {
        return "3_NewStaffMember";
    }


    @RequestMapping("/CreateStaffMember")
    public String CreateStaffMember(HttpServletRequest request, Model model) {

        String FirstName = request.getParameter("FirstName");
        String Surname = request.getParameter("Surname");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate EmployedSince = LocalDate.parse(request.getParameter("EmployedSince"),formatter);

        boolean WorkingStudent = (request.getParameter("WorkingStudent") != null);

        Staff staff = new Staff(FirstName,Surname);
        if (WorkingStudent) {
            staff.setStudent(true);
        }
        staff.setStartDate(EmployedSince);

        staffArrayList.add(staff);

        model.addAttribute("staff",staff);

        return "4_StaffDetails";
    }

    @RequestMapping("/OurStaff")
    public String OurStaff(Model model) {

        model.addAttribute("staffList", staffArrayList);

        return "5_OurStaff";
    }

    @RequestMapping("/OurCustomers")
    public String OurCustomers(Model model) {

        model.addAttribute("customerList", customerArrayList);

        return "6_OurCustomers";
    }

    @RequestMapping("/newSupermarket")
    public String newSupermarket(Model model) {
        model.addAttribute("staffList", staffArrayList);

        return "7_NewSupermarket";
    }

    @RequestMapping("/CreateSupermarket")
    public String CreateSupermarket(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");

        Supermarket myNewSupermarket = new Supermarket(name);

        int chosenManagerIndex = Integer.parseInt(request.getParameter("manager"));
        if (chosenManagerIndex < 0) {
            model.addAttribute("ErrorMessage", "You didn't choose a general manager!");
            return "error";
        }
        Staff chosenManager = staffArrayList.get(chosenManagerIndex);
        myNewSupermarket.setGeneralManager(chosenManager);

        supermarketArrayList.add(myNewSupermarket);

        model.addAttribute("supermarket", myNewSupermarket);

        return "0_exam";
    }

    @RequestMapping ("/ourSupermarkets")
    public String ourSupermarkets(Model model) {

        model.addAttribute("supermarketList", supermarketArrayList);
        return "8_OurSupermarkets";
    }

    @RequestMapping ("/newDepartment")
    public String newDepartment(Model model) {

        model.addAttribute("supermarketList", supermarketArrayList);
        model.addAttribute("staffList", staffArrayList);

        return "9_NewDepartment";
    }

    @RequestMapping ("/CreateDepartment")
    public String CreateDepartment(HttpServletRequest request, Model model) {
        String departmentName = request.getParameter("department");
        String photo = request.getParameter("photo");

        boolean refrigerated = (request.getParameter("refrigerated") != null);

        int chosenSupermarketIndex = Integer.parseInt(request.getParameter("supermarket"));
        if (chosenSupermarketIndex < 0) {
            model.addAttribute("ErrorMessage", "You didn't choose a supermakter!");
            return "error";
        }

        Supermarket chosenSupermarket = supermarketArrayList.get(chosenSupermarketIndex);

        int chosenStaffIndex = Integer.parseInt(request.getParameter("responsible"));
        if (chosenStaffIndex < 0) {
            model.addAttribute("ErrorMessage", "You didn't choose a staff member!");
            return "error";
        }
        Staff chosenStaff = staffArrayList.get(chosenStaffIndex);


        Department department = new Department(departmentName);
        department.setPhoto(photo);
        department.setRefrigerated(refrigerated);
        department.setResponsible(chosenStaff);

        chosenSupermarket.addDepartment(department);

        model.addAttribute("supermarket", chosenSupermarket);

        return "10_OurDeparments";
    }

    @RequestMapping("/SupermarketDetails")
    public String SupermarketDetails(HttpServletRequest request, Model model) {
        Supermarket clickedSupermarket = supermarketArrayList.get(Integer.parseInt(request.getParameter("supermarketIndex")));

        model.addAttribute(clickedSupermarket);

        return "10_OurDeparments";
    }

    @RequestMapping ("/Search")
    public String Search(HttpServletRequest request,  Model model) {
        String department = request.getParameter("searchfor");

        for (int i = 0; i < supermarketArrayList.size(); i++ ) {
            Supermarket thisSupermarket = supermarketArrayList.get(i);

            Department founddepartment = thisSupermarket.searchDepartmentByName(department);
            if (founddepartment != null) {

                model.addAttribute("department", founddepartment);
                return "11_Search";
            }
        }

        model.addAttribute("ErrorMessage", "There is no department with the name '" + department + "'");
        return "error";
    }


//You wll need these methods in part 3 of the project assignment
    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Customer> fillCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("Dominik", "Mioens");
        customer1.setYearOfBirth(2001);
        Customer customer2 = new Customer("Zion", "Noops");
        customer2.setYearOfBirth(1996);
        Customer customer3 = new Customer("Maria", "Bonetta");
        customer3.setYearOfBirth(1998);
        Customer customer4 = new Customer("Tibbo", "Van Leemput");
        customer4.setYearOfBirth(2003);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.get(0).addToShoppingList("Butter");
        customers.get(0).addToShoppingList("Bread");
        customers.get(1).addToShoppingList("Apple");
        customers.get(1).addToShoppingList("Banana");
        customers.get(1).addToShoppingList("Grapes");
        customers.get(1).addToShoppingList("Oranges");
        customers.get(2).addToShoppingList("Fish");
        customers.get(3).addToShoppingList("Apple");
        customers.get(3).addToShoppingList("Banana");
        customers.get(3).addToShoppingList("Orange");
        return customers;
    }

    private ArrayList<Supermarket> fillSupermarkets() {
        ArrayList<Supermarket> supermarkets = new ArrayList<>();
        Supermarket supermarket1 = new Supermarket("Delhaize");
        Supermarket supermarket2 = new Supermarket("Colruyt");
        Supermarket supermarket3 = new Supermarket("Albert Heyn");

        // added exam
        supermarket1.setGeneralManager(staffArrayList.get(0));
        supermarket2.setGeneralManager(staffArrayList.get(1));
        supermarket3.setGeneralManager(staffArrayList.get(2));

        Department department1 = new Department("Fruit");
        Department department2 = new Department("Bread");
        Department department3 = new Department("Vegetables");
        department1.setPhoto("/img/fruit.jpg");
        department2.setPhoto("/img/bread.jpg");
        department3.setPhoto("/img/vegetables.jpg");
        department1.setResponsible(staffArrayList.get(0));
        department2.setResponsible(staffArrayList.get(1));
        department3.setResponsible(staffArrayList.get(2));
        supermarket1.addDepartment(department1);
        supermarket1.addDepartment(department2);
        supermarket1.addDepartment(department3);
        supermarket2.addDepartment(department1);
        supermarket2.addDepartment(department2);
        supermarket3.addDepartment(department1);
        supermarket3.addDepartment(department3);
        supermarkets.add(supermarket1);
        supermarkets.add(supermarket2);
        supermarkets.add(supermarket3);
        return supermarkets;
    }


}
