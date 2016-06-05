import java.util.*;
public class BlackjackHand{
    static List<Card> playerHand = new ArrayList<Card>();
    static List<Card> dealerHand = new ArrayList<Card>();

    BlackjackHand(){
        playerHand.clear();
        dealerHand.clear();
        playerHand.add(new Card());
        playerHand.add(new Card());
        dealerHand.add(new Card());
        dealerHand.add(new Card());
    }

    @Override
    public String toString(){
        String output = "The Dealer's first card is:\n";
        output += dealerHand.get(0).getNameValue() + dealerHand.get(0).getCardSuit() +"\n";
        output += "\nYour cards are:" + "\n";
        for(int i = 0 ; i < playerHand.size() ; i++){
            output += playerHand.get(i).getNameValue() + playerHand.get(i).getCardSuit() +"\n";
        }
        return output;
    }

    public static String printHand(String who){
    
    	String hand = "";
    	if(who.equals("user")){
    		for(int i = 0 ; i < playerHand.size(); i++){
    			hand += playerHand.get(i).getNameValue() + playerHand.get(i).getCardSuit() + "  ";
    		}
    	 } 
    	else {
       	    	hand += dealerHand.get(0).getNameValue() + dealerHand.get(0).getCardSuit() + "  ";
    	    }
    	return hand;
     }
    
    public static String printDealerHand(){
    	String hand = "";
    	for(int i = 0 ; i < dealerHand.size(); i++){
    		hand += dealerHand.get(i).getNameValue() + dealerHand.get(i).getCardSuit() + "  ";
    	}
    	return hand;
     }
    
    public static int calcHand(String who){
        int sumValue = 0;
        if(who.equals("user")){
            for(int i = 0 ; i < playerHand.size() ; i++){
                sumValue += playerHand.get(i).value;
            }
        } else {
            for(int i = 0 ; i < dealerHand.size() ; i++){
                sumValue += dealerHand.get(i).value;
            }
        }
        return sumValue;
    }
}