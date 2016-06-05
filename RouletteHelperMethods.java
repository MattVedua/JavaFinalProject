import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

/**
 * This class contains methods to help the GUI to 
 * determine various things, such as winnings, loses, etc.
 * @author Matthew Vedua
 * @author Garrett Gugs
 */
public class RouletteHelperMethods {
	protected static List<JTextField> clickedOn = new ArrayList<JTextField>();
	protected static List<Integer> randomGen = new ArrayList<Integer>();
	protected static int bet, balance, randomTile;
	
	public static int doBet(String type){
		balance -= bet;
		boolean isEqual = true;
		randomTile = ((int)(Math.random()*36)+1);
		switch(type){
			case "Straight":
				int numStClicked = Integer.parseInt(clickedOn.get(0).getText());
				if(numStClicked == randomTile){
					RouletteHelperMethods.winnings(type);
					return 1;
				} else {
					return 0;
				}
			/* Ends Straight */
			case "Split":
			case "Square":
			case "Six Line":
			case "Street":
				for(int i = 0 ; i < clickedOn.size() ; i++){
					int numClicked = Integer.parseInt(clickedOn.get(i).getText());
					if(numClicked == randomTile){
						isEqual = true;
						break;
					} else {
						isEqual = false;
					}
				}
				if(isEqual){
					RouletteHelperMethods.winnings(type);
					return 1;
				} else {
					return 0;
				}
			/* Ends Split, Corner, Six Line, Street */
				default: return -1;
		}
	}

	
	public static boolean checkNumTiles(String betType){
		switch(betType){
		case "Straight":
			if(clickedOn.size() == 1)
				return true;
			else 
				return false;
		/* Ends Straight */
		case "Split":
			if(clickedOn.size() == 2)
				return true;
			else 
				return false;
		/* Ends Split */
		case "Square":
			if(clickedOn.size() == 4)
				return true;
			else 
				return false;
		/* Ends Square */
		case "Six Line":
			if(clickedOn.size() == 6)
				return true;
			else 
				return false;
		/* Ends Six Line */
		case "Street":
			if(clickedOn.size() == 3)
				return true;
			else 
				return false;
		/* Ends Street ,*/
			default: 
				return false;
		}
	}
	
	public static int tilesForBet(String betType){
		switch(betType){
		case "Straight":
			return 1;
		/* Ends Straight */
		case "Split":
			return 2;
		/* Ends Split */
		case "Square":
			return 4;
		/* Ends Square */
		case "Six Line":
			return 6;
		/* Ends Six Line */
		case "Street":
			return 3;
		/* Ends Street ,*/
			default: 
				return -1;
		}
	}
	public static void winnings(String kindOfBet){
		int payout = (36/tilesForBet(kindOfBet)) - 1;
		balance = balance + (bet * payout);
	}
	
}
