package main.Api;

import lombok.RequiredArgsConstructor;
import main.Model.House;
import main.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/HouseMain/{agencyId}")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseService houseService;


    @GetMapping
    public String getAllHouse(@PathVariable("agencyId") Long agencyId, Model model) {
        model.addAttribute("houses", houseService.getAllHouseByAgencyId(agencyId));
        return "/House/getHouseByAgencyId";
    }

    @GetMapping("/new")
    public String createHouse(@PathVariable Long agencyId, Model model) {
        model.addAttribute("agencyId", agencyId);
        model.addAttribute("house", new House());
        return "/House/createHouse";
    }

    @PostMapping("/save")
    public String saveHouse(@PathVariable Long agencyId, @ModelAttribute("house") House house) {
        houseService.saveHouse(agencyId, house);
        return "redirect:/HouseMain/" + agencyId;
    }

    @GetMapping("/edit/{houseId}")
    public String edit(@PathVariable("houseId") Long id, Model model) {
        model.addAttribute("house", houseService.getHouseById(id));
        return "/House/updateHouse";
    }

    @PostMapping("/update/{houseId}")
    public String update(@ModelAttribute("house") House house, @PathVariable Long agencyId, @PathVariable Long houseId) {
        houseService.updateHouseById(houseId, house);
        return "redirect:/HouseMain/" + agencyId;
    }


    @DeleteMapping("/{houseId}/deleteHouse")
    public String delete(@PathVariable("houseId") Long houseId, @PathVariable Long agencyId) {
        houseService.deleteHouseById(houseId);
        return "redirect:/HouseMain/"+agencyId;
    }

}

