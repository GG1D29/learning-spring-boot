package com.stanley.xie.learningspringboot.service;

import com.stanley.xie.learningspringboot.model.Room;
import com.stanley.xie.learningspringboot.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private RoomRepository roomRepository;

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
}
