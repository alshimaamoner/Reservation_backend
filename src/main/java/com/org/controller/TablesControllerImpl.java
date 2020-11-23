package com.org.controller;

import com.org.model.Table;
import com.org.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/resturant/tables")
public class TablesControllerImpl {

    @Autowired
    private TablesService tablesService;

    @GetMapping
    public ResponseEntity<List<Table>> getAllTables() {
        return new ResponseEntity<>(tablesService.getAllTables(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Table> addTable(@RequestBody Table table) {
        return new ResponseEntity<>(tablesService.addTable(table), HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Table>> getAvailableByDate(@RequestParam LocalDateTime dateTime, @RequestParam int persons) {
        return new ResponseEntity<>(tablesService.getAvailableTables(dateTime, persons), HttpStatus.OK);
    }
}
