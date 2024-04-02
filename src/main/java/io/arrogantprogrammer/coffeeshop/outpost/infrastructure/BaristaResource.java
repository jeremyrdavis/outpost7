package io.arrogantprogrammer.coffeeshop.outpost.infrastructure;

import io.arrogantprogrammer.coffeeshop.outpost.domain.TicketIn;
import io.arrogantprogrammer.coffeeshop.outpost.domain.TicketUp;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BaristaResource {
    static final Logger LOGGER = LoggerFactory.getLogger(BaristaResource.class);

    @Inject
    Barista barista;

    @POST
    @Path("/make")
    @Transactional
    public Uni<Response> make(TicketIn ticketIn) {
        LOGGER.info("Making coffee for {}", ticketIn.name());
        TicketUp ticketUp = new TicketUp(ticketIn.uuid(), ticketIn.item(), ticketIn.name());
        return barista.makeCoffee(ticketIn)
                .onItem()
                .transform(ticketUp1 -> Response.status(201).entity(ticketUp1).build());
    }
}
