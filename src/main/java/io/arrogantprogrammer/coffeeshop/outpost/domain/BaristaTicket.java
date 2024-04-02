package io.arrogantprogrammer.coffeeshop.outpost.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class BaristaTicket extends PanacheEntity {

    String uuid;
    String item;

    String name;

    Long ttr;

    public BaristaTicket() {
    }

    public BaristaTicket(String uuid, String item, String name, Long ttr) {
        this.uuid = uuid;
        this.item = item;
        this.name = name;
        this.ttr = ttr;
    }

    public String getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public Long getTtr() {
        return ttr;
    }

    public String getUuid() {
        return uuid;
    }
}
