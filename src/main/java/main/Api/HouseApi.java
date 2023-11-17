package main.Api;


import lombok.RequiredArgsConstructor;
import main.Model.House;
import main.Service.AgencyService;
import main.Service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/HouseMain")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseService houseService;
    private final AgencyService agencyService;


    @GetMapping
    public String getAllHouse(Model model) {
        model.addAttribute("house", houseService.getAllHouse());
        return "/House/HouseMain";
    }

    @GetMapping("/new/{agencyId}")
    public String createHouseForm(@PathVariable("agencyId") Long agencyId, Model model) {
        model.addAttribute("newHouse", new House());
        model.addAttribute("agencyId", agencyId);
        return "/House/createHouse";
    }

    @PostMapping("/save")
    public String save(@RequestParam Long agencyId, @ModelAttribute("newHouse") House house) {
        houseService.saveHouse(agencyId, house);
        return "redirect:/HouseMain";
    }

    @GetMapping("/{houseId}/update")
    private String edit(@PathVariable("houseId") Long houseId, Model model, @PathVariable Long agencyId) {
        model.addAttribute("house", houseService.getHouseById(houseId));
        model.addAttribute("agencyId", agencyId);
        return "/House/updateHouse";
    }

    @PatchMapping("/{houseId}")
    private String update(@ModelAttribute("house") House house,
                          @PathVariable("agencyId") Long agencyId,
                          @PathVariable("houseId") Long houseId) {
        houseService.updateHouseById(houseId, house);
        return "redirect:/House/HouseMain/" + agencyId;
    }


    /*@PostMapping("/book/{agencyId}")
    public String bookHouse(@PathVariable("agencyId") Long agencyId, @ModelAttribute("bookingApi") BookingApi bookingApi) {
        houseService.bookHouse(bookingApi);
        return "redirect:/HouseMain";
    }

    @PostMapping("/unbook/{agencyId}")
    public String unbookHouse(@PathVariable("agencyId") Long agencyId, @ModelAttribute("bookingApi") BookingApi bookingApi) {
        houseService.unbookHouse(bookingApi);
        return "redirect:/HouseMain";
    }*/
}
