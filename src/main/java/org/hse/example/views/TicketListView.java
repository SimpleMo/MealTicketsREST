package org.hse.example.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

/**
 * Класс-представление данных о наборе счастливых билетов
 */
@Data
@Builder
@AllArgsConstructor
public class TicketListView {
    private final Collection<String> tickets;
    private final Long count;

}
