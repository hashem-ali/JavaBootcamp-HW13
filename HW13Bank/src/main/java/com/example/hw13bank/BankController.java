package com.example.hw13bank;

import com.example.hw13bank.Pojo.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/customer")
public class BankController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getAllCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return "Added Successfully";
    }

    @PutMapping("update/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        for (Customer arr: customers) {
            if(arr.getId().equals(id)){
                arr = customer;
                return "Updated Successfully";
            }
        }
        return "Not Updated";
    }
    @DeleteMapping("delete/{index}")
    public String deleteCustomer(int index){
        customers.remove(index);
        return "Deleted Successfully";
    }

    @PutMapping("/deposite/{id}")
    public String deposite(@PathVariable int id, @RequestBody int balance){
        for (Customer customer: customers) {
            if(customer.getId().equals(id)){
                customer.setBalance(balance);
                return "Deposite Successfully";
            }
        }
        return "Not Found";
    }

    @PostMapping("/withdraw/{id}")
    public int withdraw(@PathVariable int id, @RequestBody int money ){
        for (Customer customer: customers) {
            if(customer.getId().equals(id)){
                return (customer.getBalance() - money);
            }
        }
        return 0;
    }
}
