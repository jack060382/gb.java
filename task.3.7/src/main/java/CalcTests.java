import java.lang.reflect.Method;

public class CalcTests {
    CalculatorMy calculator;

    public void CalcTests() {

    }

    @BeforeSuit
    void init() {
        calculator = new CalculatorMy();
        System.out.println("Init");
    }
/*
    @BeforeSuit
    void initDuplicate() {
        System.out.println("Init");
    }
*/
    @AfterSuit
    void tearDown()  {
        System.out.println("Test finished");
    }

    @Test(priority = 1)
    public void testAdd1() {
        if (calculator.sum(2,3) == 5) {
            System.out.println("test ok!");
        }
        else {
            System.out.println("test fail!");
        }
    }

    @Test(priority = 1)
    public void testAdd2() {
        //Assertions.assertEquals(4, calculator.sum(2, 2));
    }

    @Test(priority = 10)
    public void testAdd3() {
        //Assertions.assertEquals(4, calculator.sum(2, 2));
    }

    @Test(priority = 7)
    void TestMult() {
        //Assertions.assertEquals(15, calculator.mul(3, 5));
    }

    @Test
    void testDiv1() {
        //Assertions.assertThrows(ArithmeticException.class, () ->calculator.div(10,0));
    }

    @Test
    void testDiv2() {
        //Assertions.assertThrows(ArithmeticException.class, () ->calculator.div(10,0));
    }

    void testNoAnnotation() {}

}