package com.org.service;

import com.org.exceptions.TableNotFoundException;
import com.org.exceptions.UserNotFoundException;
import com.org.model.Reservation;
import com.org.model.Table;
import com.org.model.User;
import com.org.repository.ReservationRepository;
import com.org.repository.TablesRepository;
import com.org.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation bookTable(long tableId, long userId, LocalDateTime dateTime) {
        Optional<Reservation> reservationOptional = reservationRepository.findByDateTime(dateTime);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Table table = tablesRepository.findById(tableId).orElseThrow(TableNotFoundException::new);
            reservation.getUsers().add(user);
            reservation.getTables().add(table);
            user.getReservations().add(reservation);
            table.getReservations().add(reservation);
            tablesRepository.save(table);
            userRepository.save(user);
            return reservationRepository.save(reservation);
        } else {
            Reservation reservation = new Reservation();
            User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Table table = tablesRepository.findById(tableId).orElseThrow(TableNotFoundException::new);
            reservation.getUsers().add(user);
            reservation.getTables().add(table);
            user.getReservations().add(reservation);
            table.getReservations().add(reservation);
            tablesRepository.save(table);
            userRepository.save(user);
            return reservationRepository.save(reservation);
        }

    }
}
