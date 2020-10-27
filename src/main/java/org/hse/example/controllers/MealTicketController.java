package org.hse.example.controllers;

import org.hse.example.services.HasMealTicketType;
import org.hse.example.views.MealTicketType;
import org.hse.example.views.TicketListView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Контроллер для работы со счастливыми билетами
 */
@RestController
@RequestMapping("/tickets")
public class MealTicketController {
    private final Map<MealTicketType, Supplier<TicketListView>> suppliers;

    public MealTicketController(final List<HasMealTicketType> suppliers) {
        this.suppliers =
                suppliers
                    .stream()
                    .collect(Collectors.toMap(HasMealTicketType::getMealTicketType, HasMealTicketType::getSupplier));
    }

    /**
     * @return набор счастливых билетов
     */
    @GetMapping
    public TicketListView getTickets(){
        return getTicketsSupplierByTicketType(MealTicketType.ALL).get();
    }

    /**
     * @return набор чётных счастливых билетов
     */
    @GetMapping("/even")
    public TicketListView getEvenTickets(){
        return getTicketsSupplierByTicketType(MealTicketType.EVEN).get();
    }

    /**
     * @return набор счастливых билетов, кратных пяти
     */
    @GetMapping("/five")
    public TicketListView getMultipleOfFiveTickets(){
        return getTicketsSupplierByTicketType(MealTicketType.FIVE).get();
    }

    /**
     * @param ticketType тип счастливых билетов
     * @return набор счастливых билетов заданного типа
     */
    @GetMapping("/{ticketType}")
    public TicketListView getMealTicketsByType(@PathVariable(name = "ticketType")
                                               final MealTicketType ticketType) {
        return getTicketsSupplierByTicketType(ticketType).get();
    }

    /**
     * Возвращает обработчик счастливых билетов переданного типа.
     * Если не найден, то {@link UnsupportedOperationException}
     *
     * @param ticketType тип обработчика счастливых билетов
     * @return нужный обработчик
     */
    private Supplier<TicketListView> getTicketsSupplierByTicketType(final MealTicketType ticketType) {
        if (suppliers.get(ticketType) == null) {
            throw new UnsupportedOperationException("Такой тип счастливых билетов не обрабатываеися!");
        }

        return suppliers.get(ticketType);
    }
}
