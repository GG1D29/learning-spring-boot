package com.stanley.xie.learningspringboot.service;

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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @InjectMocks
    private ReservationService reservationService;

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

        List<Room> actual = reservationService.getRooms();
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual).containsExactly(room2, room1);
    }
}