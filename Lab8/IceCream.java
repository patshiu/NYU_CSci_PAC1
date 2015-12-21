public class IceCream extends DessertItem {
	private int cost; 

	public IceCream(String name, int c){
		super(name);
		this.cost = c; 
	}

	/* GETTERS
	-----------------------*/
	public int getCost(){
		return this.cost; 
	}

	//uses super's getName();

	/* SETTERS
	-----------------------*/
	//setName()
	//setCost()

}