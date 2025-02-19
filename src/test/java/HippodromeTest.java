import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void hippodromeConstructorException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    void hippodromeConstructorInputException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(List.of());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    void getHorses() {
        List<Horse> horses = IntStream.range(0, 30)
                .mapToObj(i -> new Horse("Horse " + i, 10 + i, 100 + i))
                .toList();

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = IntStream.range(0, 50)
                .mapToObj(i -> mock(Horse.class))
                .toList();

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = List.of(
                new Horse("Alpha", 10, 150),
                new Horse("Gamma", 12, 200),
                new Horse("Betta", 15, 180)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(1), hippodrome.getWinner());
    }
}