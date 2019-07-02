package com.lightning.school.mvc.util;

import java.util.Optional;
import java.util.function.Supplier;

public class Closures {

    private Closures() {
    }

    public static <T> Optional<T> opt(final Supplier<T> statement) {
        try {
            return Optional.ofNullable(statement.get());
        } catch (NullPointerException npe) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> opt(final Supplier<T> statement, final Supplier<T> fallback) {
        try {
            return Optional.of(statement.get());
        } catch (NullPointerException npe) {
            return opt(fallback);
        }
    }
}
