public class Candy extends DessertItem {

	private int centsPerLb;  
	private double lb; 

  public Candy(){
    super();
    this.centsPerLb = -1; 
    this.lb = -1; 
  }

  public Candy(String name, double weight, double price) {
    super(name);
    this.centsPerLb = (int) price;
    this.lb = weight; 
  }

  /* SETTERS
  -----------------------*/
  //setName()
  //setQuantity()
  //setPrice()


  /* GETTERS
  -----------------------*/

  public int getCost(){
    if (this.lb != -1 && this.centsPerLb != -1 ){
      double cost = (this.centsPerLb*1.0) * this.lb;
      int costInt = (int)Math.round(cost);
      return costInt;
    } else {
      System.out.println("Price and/or weight has not been set for " + this.name + ". Cost returned is $0.");
      int cost = 0; 
      return cost; 
    }
  }

  //2.25 lbs. @ 3.99 /lb.
  public String getListPrice(){
    String s = this.lb + " lbs. @ " + DessertShoppe.cents2dollarsAndCents(this.centsPerLb) + " /lb."; 
    return s;
  }

}