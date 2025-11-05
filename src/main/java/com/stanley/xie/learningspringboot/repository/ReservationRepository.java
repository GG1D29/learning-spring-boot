package com.stanley.xie.learningspringboot.repository;

import com.stanley.xie.learningspringboot.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findReservationByReservationDate(Date date);
}
