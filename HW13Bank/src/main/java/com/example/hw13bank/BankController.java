package com.example.hw13bank;

import com.example.hw13bank.Pojo.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
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
    public String updateCustomer(@PathVariable String id, @RequestBody Customer customer){

        for (Customer arr: customers) {
            if(arr.getId().equals(id)){
                customers.set(customers.indexOf(arr), customer);
//                arr = customer;
                return "Updated Successfully";
            }
        }
        return "Not Updated";
    }
    @DeleteMapping("delete/{index}")
    public String deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return "Deleted Successfully";
    }

    @PutMapping("/deposit/{id}/{balance}")
    public String deposit(@PathVariable String id, @PathVariable int balance){
        for (Customer customer: customers) {
            if(customer.getId().equals(id)){
                customer.setBalance(balance + customer.getBalance());
                return "Deposit Successfully";
            }
        }
        return "Not Found";
    }

    @PostMapping("/withdraw/{id}/{amount}")
    public String withdraw(@PathVariable String id, @PathVariable int amount){
        for (Customer customer: customers) {
            if(customer.getId().equals(id) && customer.getBalance() >= amount){
                 customer.setBalance(customer.getBalance() - amount);
                 return "Withdraw Amount of:"+amount+ " Successfully, and now balance is: "+customer.getBalance();
            }
        }
        return "Error!, Account not found!";
    }
}

