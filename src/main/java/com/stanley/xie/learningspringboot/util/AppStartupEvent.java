package com.stanley.xie.learningspringboot.util;

import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.model.Guest;
import com.stanley.xie.learningspringboot.model.Reservation;
import com.stanley.xie.learningspringboot.model.Room;
import com.stanley.xie.learningspringboot.repository.GuestRepository;
import com.stanley.xie.learningspringboot.repository.ReservationRepository;
import com.stanley.xie.learningspringboot.repository.RoomRepository;
import com.stanley.xie.learningspringboot.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> reservations = this.reservationService.getRoomReservationsForDate(date);
        reservations.forEach(System.out::println);
    }
}
