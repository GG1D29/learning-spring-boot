package com.stanley.xie.learningspringboot.api;

import com.stanley.xie.learningspringboot.dto.HotelGuest;
import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.model.Room;
import com.stanley.xie.learningspringboot.service.GuestService;
import com.stanley.xie.learningspringboot.service.ReservationService;
import com.stanley.xie.learningspringboot.service.RoomService;
import com.stanley.xie.learningspringboot.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final RoomService roomService;

    @GetMapping("/reservations")
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("/guests")
    public List<HotelGuest> getGuests() {
        return guestService.getHotelGuest();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody HotelGuest hotelGuest) {
        guestService.addHotelGuest(hotelGuest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }
}
