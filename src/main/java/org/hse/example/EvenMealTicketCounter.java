package org.hse.example;

import java.util.Iterator;

/**
 * Сервис, работающий с чётными счастливыми билетами
 */
public class EvenMealTicketCounter extends MealTicketCounter {

    public EvenMealTicketCounter(Iterator<MealTicket> ticketIterator) {
        super(ticketIterator);
    }

    /**
     * @param ticket билет
     * @return true, если билет чётный, иначе false
     */
    @Override
    protected boolean doFilter(MealTicket ticket) {
        return ticket.accept(hasOrdinal -> hasOrdinal.getOrdinal() % 2 == 0);
    }
}
