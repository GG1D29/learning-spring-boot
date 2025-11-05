package com.stanley.xie.learningspringboot.util;

import com.stanley.xie.learningspringboot.model.Guest;
import com.stanley.xie.learningspringboot.model.Reservation;
import com.stanley.xie.learningspringboot.model.Room;
import com.stanley.xie.learningspringboot.repository.GuestRepository;
import com.stanley.xie.learningspringboot.repository.ReservationRepository;
import com.stanley.xie.learningspringboot.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Room> rooms = roomRepository.findAll();
        rooms.forEach(System.out::println);

        Iterable<Guest> guests = guestRepository.findAll();
        guests.forEach(System.out::println);

        Iterable<Reservation> reservations = reservationRepository.findAll();
        reservations.forEach(System.out::println);
    }
}
