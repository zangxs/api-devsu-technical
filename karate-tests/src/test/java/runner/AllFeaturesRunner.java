package runner;
import com.intuit.karate.junit5.Karate;

public class AllFeaturesRunner {
    @Karate.Test
    Karate testCliente() {
        return Karate.run("classpath:features/cliente").relativeTo(getClass());
    }

    @Karate.Test
    Karate testCuenta() {
        return Karate.run("classpath:features/cuenta").relativeTo(getClass());
    }

    @Karate.Test
    Karate testMovimiento() {
        return Karate.run("classpath:features/movimiento").relativeTo(getClass());
    }
}
