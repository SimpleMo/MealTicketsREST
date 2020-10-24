package org.hse.example.services;

import org.hse.example.MealTicket;
import org.hse.example.views.TicketListView;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

/**
 * Сервис, выполняющий подсчёт счастливых билетов, реализует паттерн Шаблонный метод
 */
@Service("mealTicketCounterTicketListView")
public class MealTicketCounter implements Supplier<TicketListView>, ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     * Метод выполняет дополнительную фильтрацию билетов
     *
     * @param ticket билет
     * @return результат работы фильтра
     */
    protected boolean doFilter(MealTicket ticket) {
        return true;
    }

    /**
     * Выводит счастливые билеты в консоль и возвращает их количество. Шаблонный метод.
     *
     * @return количество счастливых билетов
     */
    @Override
    public TicketListView get() {
        @SuppressWarnings("unchecked")
        Iterable<MealTicket> tickets =
                () -> (Iterator<MealTicket>) applicationContext.getBean("ticketsGenerator");
        Collection<String> ticketList = new ArrayList<>();
        long count =
                StreamSupport
                    .stream(tickets.spliterator(), false)
                    .filter(MealTicket::isMealTicket)
                    .filter(this::doFilter)
                    .map(MealTicket::toString)
                    .peek(ticketList::add)
                    .count();
        return TicketListView
                    .builder()
                    .tickets(ticketList)
                    .count(count)
                    .build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
