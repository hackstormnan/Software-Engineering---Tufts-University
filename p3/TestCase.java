public class TestCase {
    @Test public void atest1() {
        System.out.println("atest1 success");
    }

//    @Test public void btest2() {
//        System.out.println("btest2 success");
//    }
//
    @BeforeClass static public void beforeclass1() {
        System.out.println("beforeclass1 success");
    }
    @BeforeClass static public void beforeclass2() {
        System.out.println("beforeclass2 success");
    }

    @Before static public void before1() {
        System.out.println("before1 success");
    }
    @Before static public void before2() {
        System.out.println("before2 success");
    }

    @AfterClass static public void afterclass() {
        System.out.println("afterclass success");
    }

    @After public void after() {
        System.out.println("after success");
    }
}
