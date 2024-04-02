package io.arrogantprogrammer.coffeeshop.outpost.infrastructure;

import io.arrogantprogrammer.coffeeshop.outpost.domain.BaristaTicket;
import io.arrogantprogrammer.coffeeshop.outpost.domain.TicketIn;
import io.arrogantprogrammer.coffeeshop.outpost.domain.TicketUp;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@ApplicationScoped
public class Barista {
    static final Logger LOGGER = LoggerFactory.getLogger(Barista.class);

    public Uni<TicketUp> makeCoffee(TicketIn ticketIn) {
        LOGGER.info("TicketIn: {}",ticketIn);
        Instant timeIn = Instant.now();
        try {
            long delayTime = calculateDelay(ticketIn.item());
            LOGGER.debug("Delay time: {}", delayTime);
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant timeOut = Instant.now();
        Long ttr = timeOut.toEpochMilli() - timeIn.toEpochMilli();
        BaristaTicket baristaTicket = new BaristaTicket(ticketIn.uuid(), ticketIn.item(), ticketIn.name(), ttr);
        baristaTicket.persist();
        LOGGER.debug("Ticket persisted: {}", baristaTicket);
        return Uni.createFrom().item(new TicketUp(ticketIn.uuid(), ticketIn.item(), ticketIn.name()));
    }

    private long calculateDelay(String item) {
        switch (item) {
            case "COFFEE_BLACK":
                return 3000;
            case "COFFEE_WITH_ROOM":
                return 3000;
            case "CAPPUCCINO":
                return 7000;
            case "LATTE":
                return 7100;
            case "ESPRESSO":
                return 5000;
            case "ESPRESSO_DOUBLE":
                return 5100;
            default:
                return 4500;
        }
    }

}
