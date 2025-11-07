package com.stanley.xie.learningspringboot.service;

import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.model.Guest;
import com.stanley.xie.learningspringboot.model.Reservation;
import com.stanley.xie.learningspringboot.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReservationService {

    private final RoomService roomService;
    private final GuestService guestService;
    private final ReservationRepository reservationRepository;

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Map<Long, RoomReservation> roomReservationMap = roomService.getRoomReservationMap();

        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        updateRoomReservationMap(roomReservationMap, reservations, date);

        List<RoomReservation> roomReservations = convertToRoomReservationList(roomReservationMap);
        sortRoomReservations(roomReservations);

        return roomReservations;
    }

    private void updateRoomReservationMap(Map<Long, RoomReservation> roomReservationMap, Iterable<Reservation> reservations, Date date) {
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestService.getGuestById(reservation.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getId());
        });
    }

    private List<RoomReservation> convertToRoomReservationList(Map<Long, RoomReservation> roomReservationMap) {
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }

        return roomReservations;
    }

    private void sortRoomReservations(List<RoomReservation> roomReservations) {
        roomReservations.sort((o1, o2) -> {
            if (o1.getRoomName().equals(o2.getRoomName())) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
            return o1.getRoomName().compareTo(o2.getRoomName());
        });
    }
}

