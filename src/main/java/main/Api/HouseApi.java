package main.Api;

import jakarta.persistence.PersistenceContext;
import main.Model.House;
import main.Service.HouseService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/HouseMain/{agencyId}")
public class HouseApi {

    private final HouseService houseService;

    @Autowired
    public HouseApi(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public String getAllHouse(@PathVariable("agencyId") Long agencyId, Model model) {
        model.addAttribute("houses", houseService.getAllHouseByAgencyId(agencyId));
        return "/House/getHouseByAgencyId";
    }

    @GetMapping("/new")
    public String createHouse(@PathVariable Long agencyId ,Model model) {
        model.addAttribute("agencyId", agencyId);
        model.addAttribute("house", new House());
        return "/House/createHouse";
    }

    @PostMapping("/save")
    public String saveHouse(@PathVariable Long agencyId,@ModelAttribute("house")House house) {
        houseService.saveHouse(agencyId, house);
        return "redirect:/HouseMain/" + agencyId;
    }

    @PostMapping("/{houseId}/edit")
    public String updateHouse(@PathVariable("agencyId") Long id, Model model,@PathVariable Long agencyId) {
        model.addAttribute("house",houseService.getHouseById(id));
        model.addAttribute("agencyId",agencyId);
        return "/House/updateHouse";
    }

    @PostMapping("/{houseId}/update")
    public String saveUpdate(@ModelAttribute("house") House house,
                             @PathVariable("houseId") Long id,
                             @PathVariable("agencyId")Long agencyId) {
        houseService.updateHouseById(id, house);
        return "redirect:/HouseMain/" + agencyId;
    }

    @DeleteMapping("/{houseId}/deleteHouse")
    public String delete(@PathVariable("houseId") Long houseId,@PathVariable("agencyId") Long agencyId) {
        houseService.deleteHouseById(houseId);
        return "redirect:/HouseMain";
    }

}

