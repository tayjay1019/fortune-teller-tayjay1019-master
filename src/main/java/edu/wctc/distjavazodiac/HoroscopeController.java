package edu.wctc.distjavazodiac;

import edu.wctc.distjavazodiac.entity.Birthday;
import edu.wctc.distjavazodiac.service.HoroscopeService;
import edu.wctc.distjavazodiac.service.MonthListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HoroscopeController {

    private MonthListService monthListService;
    private HoroscopeService horoscopeService;

    @Autowired
    public HoroscopeController(MonthListService mls, HoroscopeService hs) {
        this.monthListService = mls;
        this.horoscopeService = hs;
    }

    @PostMapping("/get-horoscope")
    public String processForm(@ModelAttribute Birthday bday,
                              Model model) {
        model.addAttribute("pageTitle", "Know Your Fate");
        model.addAttribute("hs", horoscopeService.getHoroscope(bday));

        return "horoscope";
    }

    @GetMapping("/enter-birthday")
    public String showForm(Model model) {
        model.addAttribute("pageTitle", "Enter Your Birthday");
        model.addAttribute("monthList", monthListService.getMonths());

        Birthday formDefaults = new Birthday();
        formDefaults.setYear(1975);
        formDefaults.setMonth(9);
        formDefaults.setDay(29);
        formDefaults.setZodiacType("Eastern");
        model.addAttribute("birthday", formDefaults);
        return "birthday-form";
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("pageTitle", "Mystic Oracle Horoscopes");
        return "index";
    }
}
