package com.org.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NumberFormat
    private int numberOfPersons;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tables_reservation",
            joinColumns = {
                    @JoinColumn(name = "table_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "reservation_id")
            }
    )
    private Set<Reservation> reservations = new HashSet<>();

    public Table() {
    }

    public Table(Long id, int numberOfPersons, Set<Reservation> reservations) {
        this.id = id;
        this.numberOfPersons = numberOfPersons;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return numberOfPersons == table.numberOfPersons &&
                id.equals(table.id) &&
                Objects.equals(reservations, table.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfPersons, reservations);
    }
}
