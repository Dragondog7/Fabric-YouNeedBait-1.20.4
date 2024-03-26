package net.cookiebrain.youneedbait.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CrappieVariant {
    DEFAULT(0),
    WHITE(1);

    private static final CrappieVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(CrappieVariant::getId)).toArray(CrappieVariant[]::new);
    private final int id;

    CrappieVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static CrappieVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
