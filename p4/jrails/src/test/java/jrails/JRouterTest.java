package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JRouterTest {

    private JRouter jRouter;

    @Before
    public void setUp() throws Exception {
        jRouter = new JRouter();
    }

    @Test
    public void addRoute() {
        jRouter.addRoute("GET", "/", String.class, "index");
        assertThat(jRouter.getRoute("GET", "/"), is("java.lang.String#index"));
    }
}