package com.stanley.xie.learningspringboot.repository;

import com.stanley.xie.learningspringboot.model.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {
}
