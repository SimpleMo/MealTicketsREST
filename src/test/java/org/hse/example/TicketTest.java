package org.hse.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Набор тестов для класса {@link Ticket}
 */
public class TicketTest {

    /**
     * Проверяет, что констуруктор генерирует билет для корректных данных (номер билета)
     */
    @Test
    public void ticketConstructorNormalWork(){
        //when
        MealTicket ticket = new Ticket(10);

        //then
        assertNotNull(ticket);
    }

    /**
     * Проверяет, что билет с минимальным номером (ноль в данном случае) создаётся без сбоев
     */
    @Test
    public void minimumTicketNumber() {
        //when
        MealTicket ticket = new Ticket(0);

        //then
        assertNotNull(ticket);
    }

    /**
     * Проверяет, что билет с максимальным номером (999 999 в данном случае) создаётся без сбоев
     */
    @Test
    public void maximumTicketNumber() {
        //when
        MealTicket ticket = new Ticket(999999);

        //then
        assertNotNull(ticket);
    }

    /**
     * Проверяет, что, если на вход конструктору поступило отрицательное число,
     * то выкидываем исключение {@link IllegalArgumentException}
     */
    @Test
    public void negativeTicket() {
        //when
        Exception exception = null;
        try {
            new Ticket(-1);
        } catch (Exception e) {
            exception = e;
        }

        //then
        assertNotNull(exception);
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals("Попытка создать билет с отрицательным номером!", exception.getMessage());
    }

    /**
     * Проверяет, что будет, если подали на вход более, чем шестизначное число
     */
    @Test
    public void moreThanSixDigitsTicket(){
        //when
        Exception exception = null;
        try {
            new Ticket(1000000);
        } catch (Exception e) {
            exception = e;
        }

        //then
        assertNotNull(exception);
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals("Номер билета состоит более, чем из 6-ти цифр!", exception.getMessage());
    }

    /**
     * Проверяем, что счастливый билет определяется правильно
     */
    @Test
    public void ticketIsLucky() {
        //when
        MealTicket luckyTicket = new Ticket(123402);

        //then
        assertTrue(luckyTicket.isMealTicket());
    }

    /**
     * Проверяет, что несчастливый билет тоже определяется правильно
     */
    @Test
    public void ticketIsNotLucky() {
        //when
        MealTicket ticket = new Ticket(123412);

        //then
        assertFalse(ticket.isMealTicket());
    }

}