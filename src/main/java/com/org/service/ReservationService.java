package com.org.service;

import com.org.model.Reservation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public interface ReservationService {
    List<Reservation> getAllReservations();

    Reservation bookTable(long tableId, long userId, LocalDateTime dateTime);
}
