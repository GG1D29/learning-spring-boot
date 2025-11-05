package com.stanley.xie.learningspringboot.service;

import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.model.Guest;
import com.stanley.xie.learningspringboot.model.Reservation;
import com.stanley.xie.learningspringboot.model.Room;
import com.stanley.xie.learningspringboot.repository.GuestRepository;
import com.stanley.xie.learningspringboot.repository.ReservationRepository;
import com.stanley.xie.learningspringboot.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ReservationService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();

        Map<Long, RoomReservation> roomReservationMap = constructRoomReservationMap(rooms);

        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        updateRoomReservationMap(roomReservationMap, reservations, date);

        List<RoomReservation> roomReservations = convertToRoomReservationList(roomReservationMap);
        sortRoomReservations(roomReservations);

        return roomReservations;
    }

    public List<Room> getRooms() {
        Iterable<Room> rooms = this.roomRepository.findAll();
        List<Room> roomList = new ArrayList<>();
        rooms.forEach(roomList::add);
        sortRooms(roomList);

        return roomList;
    }

    private void sortRooms(List<Room> roomList) {
        roomList.sort(Comparator.comparing(Room::getRoomNumber));
    }

    private Map<Long, RoomReservation> constructRoomReservationMap(Iterable<Room> rooms) {
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        return roomReservationMap;
    }

    private void updateRoomReservationMap(Map<Long, RoomReservation> roomReservationMap, Iterable<Reservation> reservations, Date date) {
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
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

