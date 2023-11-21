package main.Api;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.Model.Customer;
import main.Service.AgencyService;
import main.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/CustomerMain")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;
    private final AgencyService agencyService;
    @GetMapping
    private String getAllCustomer(Model model) {
        model.addAttribute("getAllCustomer", customerService.getAllCustomer());
        return "/Customer/CustomerMain";
    }

    @GetMapping("/createCustomer")
    public String createCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "/Customer/createCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute(name = "newCustomer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/CustomerMain";
    }
    @DeleteMapping("{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/CustomerMain";
    }

    @GetMapping("/{id}/update")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "/Customer/updateCustomer";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customer") Customer customer, @PathVariable("id") Long id) {
        customerService.updateCustomer(id, customer);
        return "redirect:/CustomerMain";
    }

    @GetMapping("/assignCustomers/{id}")
    public String assignCustomer(@PathVariable("id") Long id, Model model){
        model.addAttribute("assignCustomer", customerService.getCustomerById(id));
        model.addAttribute("agencySearch", agencyService.getAllAgency());
        return "/Customer/assignCustomer";
    }

    @PutMapping("/{customerId}/{agencyId}/assign")
    public String assign(@PathVariable Long customerId, @PathVariable Long agencyId, Model model){
        customerService.assign(customerId, agencyId);
        model.addAttribute("assignAgency", agencyService.getById(agencyId));
        return "redirect:/CustomerMain";
    }
}
