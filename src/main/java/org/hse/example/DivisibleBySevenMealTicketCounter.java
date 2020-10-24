package org.hse.example;

import java.util.Iterator;

/**
 * Работает со счастливыми билетами кратными семи
 */
public class DivisibleBySevenMealTicketCounter extends MealTicketCounter {

    public DivisibleBySevenMealTicketCounter(Iterator<MealTicket> ticketIterator) {
        super(ticketIterator);
    }

    /**
     * @param ticket билет
     * @return true, если билет кратен семи, false в противном случае
     */
    @Override
    protected boolean doFilter(MealTicket ticket) {
        return ticket.accept(hasOrdinal -> hasOrdinal.getOrdinal() % 7 == 0);
    }
}
