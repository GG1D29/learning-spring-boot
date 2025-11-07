package com.stanley.xie.learningspringboot.service;

import com.stanley.xie.learningspringboot.dto.RoomReservation;
import com.stanley.xie.learningspringboot.util.DateUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional // Ensures test methods run in a transaction and rollback after each test
class ReservationServiceIntegrationTest {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DateUtils dateUtils;

    @Test
    void should_GetRoomReservationsForDate_Successfully_When_HasReservationDate() {
        Date date = dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> actual = reservationService.getRoomReservationsForDate(date);

        assertThat(actual.size()).isEqualTo(28);
        RoomReservation actualReservation = actual.get(1);
        assertThat(actualReservation.getRoomId()).isEqualTo(8);
        assertThat(actualReservation.getGuestId()).isEqualTo(200);
        assertThat(actualReservation.getRoomName()).isEqualTo("Cambridge");
        assertThat(actualReservation.getRoomNumber()).isEqualTo("C2");
        assertThat(actualReservation.getFirstName()).isEqualTo("Judith");
        assertThat(actualReservation.getLastName()).isEqualTo("Young");
        assertThat(actualReservation.getDate()).isEqualTo(date);
    }

    @Test
    void should_GetRoomReservationsForDate_Successfully_When_NoReservationDate() {
        Date date = dateUtils.createDateFromDateString("2022-01-02");
        List<RoomReservation> actual = reservationService.getRoomReservationsForDate(date);

        assertThat(actual.size()).isEqualTo(28);
        RoomReservation actualReservation = actual.get(1);
        assertThat(actualReservation.getRoomId()).isEqualTo(8);
        assertThat(actualReservation.getGuestId()).isZero();
        assertThat(actualReservation.getRoomName()).isEqualTo("Cambridge");
        assertThat(actualReservation.getRoomNumber()).isEqualTo("C2");
        assertThat(actualReservation.getFirstName()).isNull();
        assertThat(actualReservation.getLastName()).isNull();
        assertThat(actualReservation.getDate()).isNull();
    }
}