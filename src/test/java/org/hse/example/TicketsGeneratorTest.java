package org.hse.example;

import org.hse.example.builders.MealTicketBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тесты для класса {@link TicketsGenerator}
 */
public class TicketsGeneratorTest {
    @Configuration
    static class TestConfig {
        @Bean
        Iterator<MealTicket> getGenerator(){
            return new TicketsGenerator();
        }

        @Bean("ticketBuilder")
        @Scope("prototype")
        MealTicketBuilder getMealTicketBuilder(){
            MealTicketBuilder mock = mock(MealTicketBuilder.class);
            when(mock.ticket(anyLong())).thenReturn(mock);
            when(mock.build()).thenReturn(new Ticket(321051));
            return mock;
        }

    }

    @Autowired
    Iterator<MealTicket> ticketsGenerator;

    @Test
    public void generatesMillionTickets(){
        //given
        long count = 0;

        //when
        for (; ticketsGenerator.hasNext(); ticketsGenerator.next()) {
            count++;
        }

        //then
        assertEquals(1000000, count);
    }
}