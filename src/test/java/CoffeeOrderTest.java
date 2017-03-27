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

  @Test
  public void twoOrderObjectsareSame_true(){
    CoffeeOrder newOrder1 = new CoffeeOrder ("dark", 1);
    CoffeeOrder newOrder2 = new CoffeeOrder ("dark", 1);
    assertTrue (newOrder1.equals(newOrder2));
  }

  @Test
  public void savedOrderSameAsOriginal_true(){
    CoffeeOrder newOrder = new CoffeeOrder ("dark", 1);
    newOrder.save();
    assertTrue(CoffeeOrder.all().get(0).equals(newOrder));
  }
  @Test
  public void getCoffeeOrderId_1(){
    CoffeeOrder newOrder = new CoffeeOrder ("dark", 1);
    newOrder.save();
    assertTrue(CoffeeOrder.all().get(0).getId()>0);
  }

  @Test
  public void findOrder_newOrder2(){
    CoffeeOrder newOrder1 = new CoffeeOrder ("dark", 1);
    CoffeeOrder newOrder2 = new CoffeeOrder ("light", 1);
    newOrder1.save();
    newOrder2.save();
    assertTrue(newOrder2.equals(CoffeeOrder.find(newOrder2.getId())));
  }


}
