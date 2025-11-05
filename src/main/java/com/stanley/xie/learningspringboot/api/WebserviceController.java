package com.stanley.xie.learningspringboot.api;

import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.service.ReservationService;
import com.stanley.xie.learningspringboot.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }
}
