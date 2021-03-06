package com.citystartravel.backend.entity.bus;

import com.citystartravel.backend.config.audit.UserDateAudit;
import com.citystartravel.backend.entity.bus.event.BusEvent;
import com.citystartravel.backend.entity.spare.Spare;
import com.citystartravel.backend.entity.voucher.stockissue.StockIssue;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "buses", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
public class Bus extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 10)
    private String platesLetters;

    @NotBlank
    @Size(max = 10)
    private String platesNumbers;

    private boolean inOperation;

    //@Size(max = 4)
    private int busCondition;

    private long km;

    @OneToMany(
            mappedBy = "bus",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private Set<BusEvent> events = new HashSet<>();

    @OneToMany(
            mappedBy = "bus",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private Set<Spare> spares = new HashSet<>();

    @OneToMany(
            mappedBy = "bus",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    private Set<StockIssue> stockIssueVouchers = new HashSet<>();


    public Bus() {}

    Bus(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 10) String platesLetters, @NotBlank @Size(max = 10) String platesNumbers, boolean inOperation, @Size(max = 4) int busCondition, Set<BusEvent> events, long km) {
        this.name = name;
        this.platesLetters = platesLetters;
        this.platesNumbers = platesNumbers;
        this.inOperation = inOperation;
        this.busCondition = busCondition;
        this.events = events;
        this.km = km;
    }

    /*Healthy bus, in operation and in good condition.*/
    Bus(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 10) String platesLetters, @NotBlank @Size(max = 10) String platesNumbers, long km) {
        this.name = name;
        this.platesLetters = platesLetters;
        this.platesNumbers = platesNumbers;
        this.inOperation = true;
        this.busCondition = 4;
        this.km = km;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatesLetters() {
        return platesLetters;
    }

    public void setPlatesLetters(String platesLetters) {
        this.platesLetters = platesLetters;
    }

    public String getPlatesNumbers() {
        return platesNumbers;
    }

    public void setPlatesNumbers(String platesNumbers) {
        this.platesNumbers = platesNumbers;
    }

    public boolean isInOperation() {
        return inOperation;
    }

    public void setInOperation(boolean inOperation) {
        this.inOperation = inOperation;
    }

    public int getBusCondition() {
        return busCondition;
    }

    public void setBusCondition(int busCondition) {
        this.busCondition = busCondition;
    }

    public Set<BusEvent> getEvents() {
        return events;
    }

    public void setEvents(Set<BusEvent> events) {
        this.events = events;
    }

    public void addBusEvent(BusEvent busEvent) {
        events.add(busEvent);
        busEvent.setBus(this);
    }

    public void removeBusEvent(BusEvent busEvent) {
        events.remove(busEvent);
        busEvent.setBus(null);
    }

    public Set<Spare> getSpares() {
        return spares;
    }

    public void setSpares(Set<Spare> spares) {
        this.spares.retainAll(spares);
        this.spares.addAll(spares);
    }

    public void addSpare(Spare spare) {
        spares.add(spare);
        spare.setBus(this);
    }

    public void removeSpare(Spare spare) {
        spares.remove(spare);
        spare.setBus(null);
    }

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public void addKm(long km) {
        this.km += km;
    }

    public Set<StockIssue> getStockIssueVouchers() {
        return stockIssueVouchers;
    }

    public void setStockIssueVouchers(Set<StockIssue> stockIssueVouchers) {
        this.stockIssueVouchers.retainAll(stockIssueVouchers);
        this.stockIssueVouchers.addAll(stockIssueVouchers);
    }

    public void addStockIssueVoucher(StockIssue stockIssue) {
        stockIssueVouchers.add(stockIssue);
        stockIssue.setBus(this);
    }

    public void removeStockIssueVoucher(StockIssue stockIssue) {
        stockIssueVouchers.remove(stockIssue);
        stockIssue.setBus(null);
    }
}
