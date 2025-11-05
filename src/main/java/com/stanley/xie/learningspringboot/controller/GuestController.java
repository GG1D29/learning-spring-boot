package com.stanley.xie.learningspringboot.controller;

import com.stanley.xie.learningspringboot.dto.HotelGuest;
import com.stanley.xie.learningspringboot.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
@AllArgsConstructor
public class GuestController {
    private GuestService guestService;

    @GetMapping
    public String getGuests(Model model) {
        List<HotelGuest> guests = guestService.getHotelGuest();
        model.addAttribute("guests", guests);
        return "guests";
    }
}
