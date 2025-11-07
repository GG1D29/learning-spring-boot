package com.stanley.xie.learningspringboot.service;

import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.model.Room;
import com.stanley.xie.learningspringboot.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {
    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Test
    void should_GetRooms_Successfully() {
        Room room1 = new Room();
        room1.setRoomNumber("A002");

        Room room2 = new Room();
        room2.setRoomNumber("A001");

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);

        Mockito.when(roomRepository.findAll()).thenReturn(roomList);

        List<Room> actual = roomService.getRooms();
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual).containsExactly(room2, room1);
    }

    @Test
    void should_GetRoomReservationMap_Successfully() {
        Room room1 = new Room();
        room1.setId(1L);
        room1.setName("Room 1");
        room1.setRoomNumber("A001");

        Room room2 = new Room();
        room2.setId(2L);
        room2.setName("Room 2");
        room2.setRoomNumber("A002");

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);

        Mockito.when(roomRepository.findAll()).thenReturn(roomList);

        Map<Long, RoomReservation> actual = roomService.getRoomReservationMap();
        assertThat(actual.size()).isEqualTo(2);
        RoomReservation roomReservation = actual.get(1L);
        assertThat(roomReservation.getRoomName()).isEqualTo("Room 1");
        assertThat(roomReservation.getRoomNumber()).isEqualTo("A001");

        RoomReservation roomReservation2 = actual.get(2L);
        assertThat(roomReservation2.getRoomName()).isEqualTo("Room 2");
        assertThat(roomReservation2.getRoomNumber()).isEqualTo("A002");
    }
}