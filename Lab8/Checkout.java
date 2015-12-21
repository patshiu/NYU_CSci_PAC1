import java.util.*;
public class Checkout {
	ArrayList<DessertItem> items; 

	Checkout(){
		this.items = new ArrayList<DessertItem>();
	}

	public void enterItem(DessertItem dessert){
		this.items.add(dessert);
	}

	public void clear(){
		this.items.clear();
	}

	public int numberOfItems(){
		return items.size(); 
	}

	public int totalCost(){
		int totalCost = 0; 
		for (DessertItem o : items){
			totalCost += o.getCost();
		}
		return totalCost; 
	}

	public int totalTax(){
		double totalTax = totalCost() * DessertShoppe.TAX_RATE * 0.01;
		int totalTaxInt = (int) Math.round(totalTax);
		return totalTaxInt; 
	}

	//Returns a String representing a receipt for the current list of items
	public String toString(){ 
		int receiptWidth = DessertShoppe.MAX_ITEM_NAME_SIZE + DessertShoppe.COST_WIDTH; 
		int nameLen = DessertShoppe.STORE_NAME.length();
		int centralize = (receiptWidth - nameLen)/2;
		//	M & M Dessert Shoppe
		String title = "\n\n" + insertSpc(centralize); 
		title = title + DessertShoppe.STORE_NAME + "\n";
		//	--------------------
		String underscore = "" + insertSpc(centralize); 
		for (int i = 0; i < nameLen; i++){
			underscore = underscore + "-";
		}
		underscore += "\n\n";


		//ENUMERATE ITEMS IN LIST
		//for every object in items list, 
		//if cookie or candy, print amount and price 
		//if ice cream, print ice cream flavor
		//if sundae, print ice cream flavor and topping
		
		String everyItem = ""; 

		for (DessertItem d : items){
			if (d instanceof Cookie){
				String c = "" + d.getCost();
				int sp = receiptWidth - d.getName().length() - c.length() - 1;
				everyItem = everyItem + ((Cookie)d).getListPrice() + "\n" + d.getName() + insertSpc(sp) + DessertShoppe.cents2dollarsAndCents(d.getCost()) + "\n";
			
			} else if (d instanceof Candy){
				String c = "" + d.getCost();
				int sp = receiptWidth - d.getName().length() - c.length() - 1;
				everyItem = everyItem + ((Candy)d).getListPrice() + "\n" + d.getName() + insertSpc(sp) + DessertShoppe.cents2dollarsAndCents(d.getCost()) + "\n";
			
			} else if (d instanceof Sundae){
				String c = "" + d.getCost();
				int sp = receiptWidth - ((Sundae)d).getSundaeIceCreamType().length() - c.length() - 1;				
				everyItem = everyItem + ((Sundae)d).getSundaeType() + "\n" + ((Sundae)d).getSundaeIceCreamType() + insertSpc(sp) + DessertShoppe.cents2dollarsAndCents(d.getCost()) + "\n"; 
			
			} else {
				String c = "" + d.getCost();
				int sp = receiptWidth - d.getName().length() - c.length() - 1;
				everyItem = everyItem + d.getName() + insertSpc(sp) + DessertShoppe.cents2dollarsAndCents(d.getCost()) + "\n";
			
			}			
		}


		//EMPTY LINE
		//Tax
		//Total Cost
		int grandTotal = totalCost() + totalTax();
		String taxStr = "" + totalTax();
		String gTotalStr = "" + grandTotal;
		String tax = "\nTax" + insertSpc(receiptWidth - 3 - taxStr.length() - 1) + DessertShoppe.cents2dollarsAndCents(totalTax()) + "\n"; 
		String total = "Total Cost" + insertSpc(receiptWidth - 10 - gTotalStr.length() - 1) + DessertShoppe.cents2dollarsAndCents(grandTotal) + "\n";		

		//Concat all of the above strings
		return title + underscore + everyItem + tax + total; //Return Grand Total
	}

	private String insertSpc(int x){
		String s = "";
		for (int i = 0; i < x; i++){
			s += " ";
		}
		return s; 
	}


}