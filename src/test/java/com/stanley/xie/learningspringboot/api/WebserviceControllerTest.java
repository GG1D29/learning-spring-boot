package com.stanley.xie.learningspringboot.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stanley.xie.learningspringboot.dto.HotelGuest;
import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.model.Room;
import com.stanley.xie.learningspringboot.service.GuestService;
import com.stanley.xie.learningspringboot.service.ReservationService;
import com.stanley.xie.learningspringboot.service.RoomService;
import com.stanley.xie.learningspringboot.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebserviceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GuestService guestService;

    @MockitoBean
    private ReservationService reservationService;

    @MockitoBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_GetReservations_Successfully() throws Exception {
        RoomReservation reservation = new RoomReservation();
        reservation.setRoomNumber("A001");
        reservation.setGuestId(1000L);

        RoomReservation reservation2 = new RoomReservation();
        reservation2.setRoomNumber("A002");
        reservation2.setGuestId(1001L);

        List<RoomReservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        reservations.add(reservation2);

        Mockito.when(reservationService.getRoomReservationsForDate(any(Date.class))).thenReturn(reservations);

        mockMvc.perform(get("/api/reservations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomNumber", is("A001")))
                .andExpect(jsonPath("$[0].guestId", is(1000)))
                .andExpect(jsonPath("$[1].roomNumber", is("A002")))
                .andExpect(jsonPath("$[1].guestId", is(1001)));
    }

    @Test
    void should_GetGuests_Successfully() throws Exception {
        Mockito.when(guestService.getHotelGuest()).thenReturn(getAllGuests());

        mockMvc.perform(get("/api/guests"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Luna")))
                .andExpect(jsonPath("$[0].lastName", is("Alvarez")))
                .andExpect(jsonPath("$[1].firstName", is("Diego")))
                .andExpect(jsonPath("$[1].lastName", is("Alvarez")));
    }

    private List<HotelGuest> getAllGuests() {
        HotelGuest guest1 = new HotelGuest();
        guest1.setFirstName("Luna");
        guest1.setLastName("Alvarez");

        HotelGuest guest2 = new HotelGuest();
        guest2.setFirstName("Diego");
        guest2.setLastName("Alvarez");

        List<HotelGuest> guests = new ArrayList<>();
        guests.add(guest1);
        guests.add(guest2);

        return guests;
    }

    @Test
    void should_AddGuest_Successfully() throws Exception {

        String payload = objectMapper.writeValueAsString(new HotelGuest());
        mockMvc.perform(post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void should_GetRooms_Successfully() throws Exception {
        Room room1 = new Room();
        room1.setRoomNumber("A002");

        Room room2 = new Room();
        room2.setRoomNumber("A001");

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);

        Mockito.when(roomService.getRooms()).thenReturn(roomList);

        mockMvc.perform(get("/api/rooms"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomNumber", is("A002")))
                .andExpect(jsonPath("$[1].roomNumber", is("A001")));

    }
}