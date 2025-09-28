package com.inditex.prices.infrastructure.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PriceIdTest {

    @Test
    void givenPriceIdComponents_whenValidatingEqualsAndHashCode_thenContractIsSatisfied() {
        PriceId id1 = new PriceId(1, 2, 35455L);
        PriceId id2 = new PriceId(1, 2, 35455L);
        PriceId id3 = new PriceId(1, 2, 35455L);

        assertEquals(id1, id1); // Reflexivity
        assertEquals(id1, id2);
        assertEquals(id2, id1); // Symmetry
        assertEquals(id1, id2);
        assertEquals(id2, id3);
        assertEquals(id1, id3); // Transitivity
        assertEquals(id1, id2);
        assertEquals(id1, id2); // Consistency
        assertEquals(id1.hashCode(), id2.hashCode()); // hashCode consistency
        assertNotEquals(null, id1); // Null comparison
        assertNotEquals(new PriceId(99, 2, 35455L), id1);
        assertNotEquals(new PriceId(1, 99, 35455L), id1);
        assertNotEquals(new PriceId(1, 2, 99999L), id1);
        PriceId empty = new PriceId();
        assertNotEquals(id1, empty);
        assertNotEquals(empty, id1);
    }
}
