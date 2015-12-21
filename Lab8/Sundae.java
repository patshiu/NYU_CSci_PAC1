public class Sundae extends IceCream {
	private String type; 
	private int sundaeCost;

	//checkout.enterItem(new Sundae("Choc. Chip Ice Cream",145, "Hot Fudge", 50));
	public Sundae(String iceCreamType, int iceCreamCost, String sundaeType, int sundaeCost){
		super(iceCreamType, iceCreamCost);
		this.type = sundaeType;
		this.sundaeCost = sundaeCost;
	}

	/* GETTERS
	-----------------------*/
	public int getCost(){
		int total = super.getCost() + this.sundaeCost; 
		return total; 
	}

	//Caramel Sundae with
	public String getSundaeType(){
		String s = this.type + " Sundae with ";
		return s; 
	}
	public String getSundaeIceCreamType(){
		String s = super.getName();
		return s; 
	}
}