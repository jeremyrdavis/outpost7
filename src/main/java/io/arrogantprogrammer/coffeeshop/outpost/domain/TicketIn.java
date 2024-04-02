package io.arrogantprogrammer.coffeeshop.outpost.domain;

import java.util.UUID;

public record TicketIn(String uuid,
                       String item,
                       String name) {
}
