package com.stanley.xie.learningspringboot.service;

import com.stanley.xie.learningspringboot.dto.HotelGuest;
import com.stanley.xie.learningspringboot.model.Guest;
import com.stanley.xie.learningspringboot.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public List<HotelGuest> getHotelGuest() {
        List<HotelGuest> hotelGuests = new ArrayList<>();

        Iterable<Guest> guests = guestRepository.findAll();
        guests.forEach(guest -> {
            HotelGuest hotelGuest = new HotelGuest(guest.getLastName(), guest.getFirstName(), guest.getEmailAddress(), guest.getPhoneNumber());
            hotelGuests.add(hotelGuest);
        });
        sortHotelGuests(hotelGuests);

        return hotelGuests;
    }

    public void addHotelGuest(HotelGuest hotelGuest) {
        Guest guest = new Guest();
        guest.setFirstName(hotelGuest.getFirstName());
        guest.setLastName(hotelGuest.getLastName());
        guest.setEmailAddress(hotelGuest.getEmailAddress());
        guest.setPhoneNumber(hotelGuest.getPhoneNumber());
        guestRepository.save(guest);
    }

    private void sortHotelGuests(List<HotelGuest> hotelGuests) {
        hotelGuests.sort((o1, o2) -> {
            if (o1.getLastName().equals(o2.getLastName())) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }

            return o1.getLastName().compareTo(o2.getLastName());
        });
    }
}
