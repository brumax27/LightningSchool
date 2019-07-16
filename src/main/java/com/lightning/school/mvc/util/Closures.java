package com.lightning.school.mvc.util;

import java.util.Optional;
import java.util.function.Supplier;

public class Closures {

    /**
     * Execute une fonction en ignorant les NullPointerException.
     * A utiliser pour encadrer les enchainements de getters pour abstraire le code defensif.
     * Example:  sejourVOOut.getDescriptifSejour().getGeographieVO().getPaysArrivee().getId();
     * @param statement
     * @param <T>
     * @return
     */
    public static <T> Optional<T> opt(final Supplier<T> statement) {
        try {
            return Optional.ofNullable(statement.get());
        } catch (NullPointerException npe) {
            return Optional.empty();
        }
    }

    /**
     * Renvoi le résultat du fallback si null.
     *
     * @param statement
     * @param fallback
     * @param <T>
     * @return
     */
    public static <T> Optional<T> opt(final Supplier<T> statement, final Supplier<T> fallback) {
        try {
            return Optional.of(statement.get());
        } catch (NullPointerException npe) {
            return opt(fallback);
        }
    }
}
