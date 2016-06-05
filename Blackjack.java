import javax.swing.*;
public class Blackjack extends BlackjackHand{
    protected static BlackjackHand players;
    protected static int bet;
    protected static int balance;
    private static String decision;
    static boolean keepPlaying = true;

    public static int checkWinner(){ 
        if(calcHand("user") > 21){ // 0 = Bust
            balance -= bet;
            return 0;
        }
        if(calcHand("user") <= calcHand("dealer")){// 1 = dealer bust
            if(calcHand("dealer") > 21){
                balance += bet;
                return 1;
            }
            balance -= bet;
            return 2; // 2 = User < Dealer
            } else {
            balance += bet;
            return 3; // 3 = user > dealer
            
        }  

    }

    public static void checkAce(){
        Object[] possibilities = {"1","11"};
        boolean didItRun = false;
        for(int i = 0 ; i < players.playerHand.size() ; i++){
            if(players.playerHand.get(i).value == 1 || players.playerHand.get(i).value == 11){
                players.playerHand.get(i).value = Integer.parseInt((String)(JOptionPane.showInputDialog(null,"What would your ace to be worth?","Ace Worth",JOptionPane.PLAIN_MESSAGE,null,possibilities,"1")));
            }
        }
    }

    public static void hitDealer(){
        while(calcHand("dealer") < 17){
            players.dealerHand.add(new Card());
        }
    }

    public static void choice(){
        Object[] possibilities = {"Stand","Hit","Double Down","Surrender"};
        decision = (String)(JOptionPane.showInputDialog(null,"What would you like to do?","Choose",JOptionPane.PLAIN_MESSAGE,null,possibilities,"1"));
        switch(decision){
            case "Stand":       break;
            case "Hit":         players.playerHand.add(new Card());
            break;
            case "Double Down": players.playerHand.add(new Card());
            bet += bet;
            break;
            case "Surrender":   for(int i = 0 ; i < players.playerHand.size() ; i++){
                players.playerHand.get(i).value = 0;
            }
            break;
        }
    }
}   

