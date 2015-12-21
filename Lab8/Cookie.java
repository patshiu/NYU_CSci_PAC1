public class Cookie extends DessertItem {
	private int quantity; 
	private int centsPerDozen;  

  public Cookie(){
    super();
    this.quantity = -1;
    this.centsPerDozen = -1;
  }

  public Cookie(String name, int quantity, int price) {
    super(name);
    this.quantity = quantity; 
    this.centsPerDozen = price;
  }

  /* SETTERS
  -----------------------*/
  //setName()
  //setQuantity()
  //setPrice()


  /* GETTERS
  -----------------------*/
  public int getCost(){
    if ( this.centsPerDozen == -1 || this.quantity == -1){
      System.out.println("Price and/or quantity has not been set for " + this.name + ". Cost returned is $0.");
      int cost = 0; 
      return cost; 
    } else {
      //Note that it is important to conver centsPerDozen to double before division.
      double cost = this.centsPerDozen*1.0/12 * this.quantity;
      int costInt = (int)Math.round(cost);
      return costInt;
    }
  }

  //4 @ 3.99 /dz.
  public String getListPrice(){
    String s = this.quantity + " @ " + DessertShoppe.cents2dollarsAndCents(this.centsPerDozen) + " /dz."; 
    return s; 
  }

}