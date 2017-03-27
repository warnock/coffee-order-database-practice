import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CoffeeOrderTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/coffee_order_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM orders *;";
      con.createQuery(sql).executeUpdate();
    }
  }
  @Test
  public void createInstanceofOrder_true(){
    CoffeeOrder newOrder = new CoffeeOrder ("dark", 1);
    assertTrue(newOrder instanceof CoffeeOrder);
  }
  @Test
  public void returnRoastName_dark(){
    CoffeeOrder newOrder = new CoffeeOrder ("dark", 1);
    assertEquals("dark", newOrder.getRoast());
  }
  @Test
  public void returnStoreIDofOrder_1(){
    CoffeeOrder newOrder = new CoffeeOrder ("dark", 1);
    assertEquals(1, newOrder.getStoreId());
  }

}
