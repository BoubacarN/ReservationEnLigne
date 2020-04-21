package com.frankmoley.lil.learningspring.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.business.service.ReservationService;
import com.frankmoley.lil.learningspring.data.entity.Room;
import com.frankmoley.lil.learningspring.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservations")
public class RoomReservationWebServiceController {

    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebServiceController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value="resa")
    public List<RoomReservation> getRoomReservations(@RequestParam(name="date", required = false)String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }


    @GetMapping(value="rooms")
    public List<Room> getRooms(@RequestParam(name="date", required = false)String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRooms();
    }
    @GetMapping(value="guests")
    public List<Guest> getGuests(@RequestParam(name="date", required = false)String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        return this.reservationService.getGuests();
    }

    @GetMapping(value="guestbyId")
    public Optional<Guest> getGuestById(@RequestParam(name="Id", required = true)Long Id){
        return this.reservationService.getGuestsById(Id);
    }

    @DeleteMapping(value="delguest")
    public void getdelguestyId(@RequestParam(name="Id", required = true)Long Id){
         this.reservationService.deleteGuest(Id);
    }

    @PostMapping(value = "saveguest")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void savegest(@RequestBody Guest guest){
        this.reservationService.saveGuest(guest);
    }
}
