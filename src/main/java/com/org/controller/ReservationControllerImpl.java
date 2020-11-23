package com.org.controller;

import com.org.dto.BookTableRequestDTO;
import com.org.model.Reservation;
import com.org.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/resturant/reservation")
public class ReservationControllerImpl implements ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Override
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Reservation> bookTable(@RequestBody @Valid BookTableRequestDTO bookTableRequestDTO) {
        return new ResponseEntity<>(reservationService.bookTable(bookTableRequestDTO.getTableId(), bookTableRequestDTO.getUserId(), bookTableRequestDTO.getDateTime()), HttpStatus.CREATED);
    }
}
