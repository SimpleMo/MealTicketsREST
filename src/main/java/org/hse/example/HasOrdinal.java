package org.hse.example;

/**
 * Предоставляет метод, возвращающий порядковый номер билета
 */
public interface HasOrdinal {
    /**
     * Реализация по умолчанию, при необходимости переопределить
     *
     * @return порядковый номер билета
     */
    default Long getOrdinal() {
        return null;
    }
}
