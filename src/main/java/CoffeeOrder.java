import org.sql2o.*;

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


}
