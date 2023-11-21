package main.Api;

import lombok.RequiredArgsConstructor;
import main.Model.Agency;
import main.Model.Customer;
import main.Service.AgencyService;
import main.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/AgencyMain")
@RequiredArgsConstructor
public class AgencyApi {

    private final AgencyService agencyService;
    private final CustomerService customerService;

    @GetMapping
    public String getAllAgency(Model model){
        model.addAttribute("getAllAgency", agencyService.getAllAgency());
        return "/Agency/AgencyMain";
    }

    @GetMapping("/createAgency")
    public String createNewAgency(Model  model){
        model.addAttribute("newAgency", new Agency());
        return "/Agency/createAgency";
    }

    @PostMapping("/saveAgency")
    public String saveAgency(@ModelAttribute(name = "newAgency") Agency agency){
        agencyService.saveAgency(agency);
        return "redirect:/AgencyMain";
    }

    @DeleteMapping("/{id}/deleteAgency")
    private String deleteAgency(@PathVariable("id") Long id){
        agencyService.deleteById(id);
        return "redirect:/AgencyMain";
    }

    @GetMapping("/{id}/update-rm")
    public String searchAgency(Model model, @PathVariable("id") Long id){
        model.addAttribute("agency", agencyService.getById(id));
        return "/Agency/updateAgency";
    }

    @PatchMapping("/{id}")
    public String updateAgency(@ModelAttribute(name = "agency") Agency agency, @PathVariable("id") Long id){
        agencyService.updateAgencyById(id, agency);
        return "redirect:/AgencyMain";
    }

    @GetMapping("/search")
    public String searchById(@RequestParam("id") Long id, Model model) {
        Agency agency = agencyService.getById(id);
        if (agency == null) {
            return "redirect:/AgencyMain/"+id;
        }
        model.addAttribute("customer", customerService.getCustomerById(id));
        model.addAttribute("agencySearch", agency);
        return "/Agency/profileAgency";
    }

    @GetMapping("/{getById}/get/booking")
    public String showAndAssignCustomer(
            @PathVariable Long getById,
            Model model
    ) {
        Agency agency = agencyService.getById(getById);
        List<Customer> customer = customerService.getAllCustomer();
        model.addAttribute("getAgencyById", agency);
        model.addAttribute("customer", customer);
        return "/Customer/getById1";
    }


    @PostMapping("/{getById}/get/booking")
    public String assignCustomer(@RequestParam List<Long> customerId, @PathVariable Long getById){
        Customer customer = customerService.getCustomerById(getById);
        agencyService.assignCustomerToAgency(customer.getId(), customerId);
        return "redirect:/AgencyMain";
    }
}
