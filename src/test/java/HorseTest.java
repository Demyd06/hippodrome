import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class HorseTest {
    private String name = "Albert";
    private double speed = 0;
    private double distance = 0;
    @Test
    void getName() {
        Horse horse = new Horse(name, speed, distance);

        String horseName = horse.getName();

        assertEquals(name, horseName);
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse(name, speed, distance);

        double horseSpeed = horse.getSpeed();

        assertEquals(speed, horseSpeed);
    }

    @Test
    void getDistance() {
        Horse horse = new Horse(name, speed, distance);

        double horseDistance = horse.getDistance();

        assertEquals(distance, horseDistance);
    }

    @Test
    void move() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Albert", 50, 100);

            mockedStatic.when(() -> horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);
            horse.move();

            mockedStatic.verify(() -> horse.getRandomDouble(0.2,0.9), times(1));
        }

    }
    @ParameterizedTest
    @CsvSource({
            "110.0, 0.2, 50.0, 100.0,",  // min
            "125.0, 0.5, 50.0, 100.0,",  // max/2
            "145.0, 0.9, 50.0, 100.0,"   // max
    })
    void testMoveUpdatesCorrectly(double expectedDistance, double randomValue, double speed, double initialDistance) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Albert", speed, initialDistance);

            mockedStatic.when(() -> horse.getRandomDouble(0.2,0.9)).thenReturn(randomValue);
            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.0001);
        }
    }

    @Test
    void argumentExceptions() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 50, 100));
        assertEquals("Name cannot be null.", exception.getMessage());

        IllegalArgumentException exceptionA = assertThrows(IllegalArgumentException.class, () -> new Horse("Albert", -10, 100));
        assertEquals("Speed cannot be negative.", exceptionA.getMessage());

        IllegalArgumentException exceptionB = assertThrows(IllegalArgumentException.class, () -> new Horse("Albert", 50, -100));
        assertEquals("Distance cannot be negative.", exceptionB.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void parameterExceptions(String invalidName) {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(invalidName, 50, 100));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
}