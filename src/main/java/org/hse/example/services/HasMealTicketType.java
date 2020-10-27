package org.hse.example.services;

import org.hse.example.views.MealTicketType;
import org.hse.example.views.TicketListView;

import java.util.function.Supplier;

/**
 * Служебный интерфейс для формирования списка сервисов в разрезе типов билетов
 */
public interface HasMealTicketType {
    /**
     * @return тип счастливых билетов
     */
    MealTicketType getMealTicketType();

    /**
     * @return поставщик, возвращающий представление счастливых билетов заданного типа
     */
    Supplier<TicketListView> getSupplier();
}
