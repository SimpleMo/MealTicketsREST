package org.hse.example.controllers;

import org.hse.example.views.TicketListView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

/**
 * Контроллер для работы со счастливыми билетами
 */
@RestController
@RequestMapping("/tickets")
public class MealTicketController {
    private final Supplier<TicketListView> ticketsSupplier;
    private final Supplier<TicketListView> evenTicketsSupplier;
    private final Supplier<TicketListView> multipleOfFiveTicketsSupplier;

    public MealTicketController(@Qualifier("mealTicketCounterTicketListView")
                                final Supplier<TicketListView> ticketsSupplier,
                                @Qualifier("evenMealTicketCounterTicketListView")
                                final Supplier<TicketListView> evenTicketsSupplier,
                                @Qualifier("multipleOfFiveMealTicketCounter")
                                final Supplier<TicketListView> multipleOfFiveTicketsSupplier) {
        this.ticketsSupplier = ticketsSupplier;
        this.evenTicketsSupplier = evenTicketsSupplier;
        this.multipleOfFiveTicketsSupplier = multipleOfFiveTicketsSupplier;
    }

    /**
     * @return набор счастливых билетов
     */
    @GetMapping
    public TicketListView getTickets(){
        return ticketsSupplier.get();
    }

    /**
     * @return набор чётных счастливых билетов
     */
    @GetMapping("/even")
    public TicketListView getEvenTickets(){
        return evenTicketsSupplier.get();
    }

    /**
     * @return набор счастливых билетов, кратных пяти
     */
    @GetMapping("/five")
    public TicketListView getMultipleOfFiveTickets(){
        return multipleOfFiveTicketsSupplier.get();
    }
}
