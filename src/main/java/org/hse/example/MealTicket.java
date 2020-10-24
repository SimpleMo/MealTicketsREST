package org.hse.example;

/**
 * Предоставляет методя для работы со счастливыми билетами
 */
public interface MealTicket extends HasOrdinal, Visitable<HasOrdinal> {
    /**
     * @return true, если билет счастливый
     */
    boolean isMealTicket();
}
