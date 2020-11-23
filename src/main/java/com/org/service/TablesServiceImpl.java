package com.org.service;

import com.org.model.Reservation;
import com.org.model.Table;
import com.org.repository.ReservationRepository;
import com.org.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TablesServiceImpl implements TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Table> getAllTables() {
        return tablesRepository.findAll();
    }

    @Override
    public Table addTable(Table table) {
        return tablesRepository.save(table);
    }

    @Override
    public List<Table> getAvailableTables(LocalDateTime dateTime, int persons) {
        List<Reservation> reservations = reservationRepository.findAll()
                .stream()
                .filter(r -> r.getDateTime().equals(dateTime))
                .collect(Collectors.toList());
        List<Table> tables = tablesRepository.findAll()
                .stream()
                .filter(t -> t.getNumberOfPersons() >= persons)
                .collect(Collectors.toList());
        List<Table> availableTables = new ArrayList<>();
        reservations.forEach(r -> {
            for (Table table : tables) {
                if (!r.getTables().contains(table))
                    availableTables.add(table);
            }
        });
        return availableTables;
    }
}
