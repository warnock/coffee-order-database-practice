import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/coffee_order_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stores *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void instanceofStore_true() {
    Store newStore = new Store("name");
    assertEquals(true, newStore instanceof Store);
  }

}
