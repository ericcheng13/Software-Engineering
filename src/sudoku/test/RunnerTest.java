package sudoku.test;
import org.junit.jupiter.api.Test;
import sudoku.Runner;

class RunnerTest {

    @Test
    void main() {
        Runner run = new Runner();
        Runner.main(new String[]{});
    }
}