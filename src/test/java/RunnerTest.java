import org.junit.jupiter.api.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.JUnitCore;
import org.junit.runner.Runner;
import org.junit.runner.Result;

public class RunnerTest {
    @Test
    public void parallelRunner() {
        Results results = Runner.path().relativeTo(getClass()).parallel(5); // Ну или не 5, а сколько хотите потоков =)
    }
}
