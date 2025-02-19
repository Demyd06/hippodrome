import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;


class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void testMainMethodExecutionTime() throws Exception {
        // Запуск методу main
        String[] args = {};
        Main.main(args);
    }
}