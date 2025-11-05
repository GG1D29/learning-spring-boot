package com.stanley.xie.learningspringboot.service;

import com.stanley.xie.learningspringboot.dto.HotelGuest;
import com.stanley.xie.learningspringboot.model.Guest;
import com.stanley.xie.learningspringboot.repository.GuestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {
    @InjectMocks
    private GuestService guestService;

    @Mock
    private GuestRepository guestRepository;

    @Captor
    ArgumentCaptor<Guest> guestArgumentCaptor;

    @Test
    void should_GetHotelGuest_Successfully() {
        Guest guest1 = new Guest();
        guest1.setFirstName("Luna");
        guest1.setLastName("Alvarez");

        Guest guest2 = new Guest();
        guest2.setFirstName("Diego");
        guest2.setLastName("Alvarez");

        List<Guest> guests = new ArrayList<>();
        guests.add(guest1);
        guests.add(guest2);

        Mockito.when(guestRepository.findAll()).thenReturn(guests);

        List<HotelGuest> actual = guestService.getHotelGuest();
        assertThat(actual).size().isEqualTo(2);
        assertThat(actual.get(0).getFirstName()).isEqualTo("Diego");
        assertThat(actual.get(0).getLastName()).isEqualTo("Alvarez");

        assertThat(actual.get(1).getFirstName()).isEqualTo("Luna");
        assertThat(actual.get(1).getLastName()).isEqualTo("Alvarez");
    }

    @Test
    void should_AddHotelGuest_Successfully() {
        HotelGuest hotelGuest = new HotelGuest("hartono", "shenli", "sh@email.com", "08123456");
        guestService.addHotelGuest(hotelGuest);

        Mockito.verify(guestRepository).save(guestArgumentCaptor.capture());
        Guest capturedGuest = guestArgumentCaptor.getValue();
        assertThat(capturedGuest.getLastName()).isEqualTo("hartono");
        assertThat(capturedGuest.getFirstName()).isEqualTo("shenli");
        assertThat(capturedGuest.getEmailAddress()).isEqualTo("sh@email.com");
        assertThat(capturedGuest.getPhoneNumber()).isEqualTo("08123456");

    }
}