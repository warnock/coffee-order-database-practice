import org.sql2o.*;
import java.util.List;

public class CoffeeOrder {
  private String roast;
  private int id;
  private int store_id;

  public CoffeeOrder(String roast, int store_id) {
    this.roast = roast;
    this.store_id = store_id;
  }

  public String getRoast (){
    return roast;
  }

  public int getStoreId(){
    return store_id;
  }

  public int getId(){
    return id;
  }
  @Override
  public boolean equals (Object otherOrder){
    if (!(otherOrder instanceof CoffeeOrder)){
      return false;
    } else {
      CoffeeOrder newOrder = (CoffeeOrder) otherOrder;
      return this.getRoast().equals(newOrder.getRoast()) && this.getId() == newOrder.getId() && this.getStoreId() == newOrder.getStoreId();
    }
  }

  public void save(){
    try(Connection con =  DB.sql2o.open()){
      String sql = "INSERT INTO orders (roast, store_id) VALUES (:roast, :store_id)";
      this.id = (int) con.createQuery (sql, true)
        .addParameter("roast", this.roast)
        .addParameter("store_id", this.store_id)
        .executeUpdate()
        .getKey();
    }
  }
  public static List<CoffeeOrder> all(){
    try(Connection con =  DB.sql2o.open()){
      String sql = "SELECT * FROM orders";
      return con.createQuery(sql).executeAndFetch(CoffeeOrder.class);
    }
  }

  public static CoffeeOrder find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM orders WHERE id=:id";
      CoffeeOrder order = con.createQuery (sql)
        .addParameter("id", id)
        .executeAndFetchFirst(CoffeeOrder.class);
        return order;
    }
  }
}
