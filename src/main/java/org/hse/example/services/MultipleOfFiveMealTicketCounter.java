package org.hse.example.services;

import org.hse.example.MealTicket;
import org.hse.example.views.MealTicketType;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы со счастливыми билетами, кратыми пяти
 */
@Service("multipleOfFiveMealTicketCounter")
public class MultipleOfFiveMealTicketCounter extends MealTicketCounter {

    /**
     * Метод выполняет дополнительную фильтрацию билетов
     *
     * @param ticket билет
     * @return результат работы фильтра
     */
    @Override
    protected boolean doFilter(MealTicket ticket) {
        return ticket.accept(hasOrdinal -> hasOrdinal.getOrdinal() % 5 == 0);
    }

    @Override
    public MealTicketType getMealTicketType() {
        return MealTicketType.FIVE;
    }
}
