import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;

public class BlackjackGUI extends Blackjack{

	static boolean keepPlaying = true;
    static JLabel lblUserValue;
    static JLabel lblDealerValue;
    static JButton btnHit;
    static JButton btnNewHand;
    static JButton btnStand;
    static JButton btnSurrender;
    static JButton btnDoubleDown;
    private static JFrame frame;
    private JTextField txUserCards;
    private JTextField txDealerCards;
    private static JTextField txBet;
    private static JTextArea textArea;
    private static JTextField txBalance;
    private static JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        BlackjackGUI window = new BlackjackGUI();
                        txBet.setEditable(false);
                        btnHit.setEnabled(false);
                        btnStand.setEnabled(false);
                        btnSurrender.setEnabled(false);
                        btnDoubleDown.setEnabled(false);
                        window.frame.setVisible(true);
                        textArea.setText("Welcome to Blackjack.\n\nPress [New Hand] to begin.");
                        
                        JLabel lblBalance = new JLabel("Balance:");
                        lblBalance.setForeground(Color.BLACK);
                        lblBalance.setBounds(175, 136, 61, 14);
                        frame.getContentPane().add(lblBalance);
                        
                        txBalance = new JTextField();
                        txBalance.setEditable(false);
                        txBalance.setBounds(175, 161, 86, 20);
                        frame.getContentPane().add(txBalance);
                        txBalance.setColumns(10);
                        balance = 100;
                        txBalance.setText("" + (balance - bet));
                        
                        lblNewLabel = new JLabel("Blackjack");
                        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
                        lblNewLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 50));
                        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        lblNewLabel.setBounds(175, 73, 246, 62);
                        frame.getContentPane().add(lblNewLabel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    /**
     * Create the application.
     */
    public BlackjackGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 128, 0));
        frame.setBounds(100, 100, 710, 299);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnStand = new JButton("Stand");
        btnStand.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnStand.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                	btnHit.setEnabled(false);
                    btnStand.setEnabled(false);
                    btnSurrender.setEnabled(false);
                    btnDoubleDown.setEnabled(false);
                	hitDealer();
                    txDealerCards.setText(printDealerHand());
                	lblDealerValue.setText("Value: " + calcHand("dealer"));
                	txBalance.setText("" + (balance - bet));
                	int winner = Blackjack.checkWinner();
                	switch(winner){
                	case 0:
                		textArea.setText("Sorry, you bust!\n");
                		textArea.append("+-+-+-+ +-+-+-+-+ +-+-+");
                		textArea.append("\n|Y|O|U| |L|O|S|T| |:|(|");
                		textArea.append("\n+-+-+-+ +-+-+-+-+ +-+-+");
                		txBalance.setText("" + balance);
                		break;
                	case 1:
                		textArea.setText("Congratulations, the dealer bust!");
                		textArea.append("\n+-+-+-+ +-+-+-+ +-+-+");
                		textArea.append("\n|Y|O|U| |W|O|N| |:|)|");
                		textArea.append("\n+-+-+-+ +-+-+-+ +-+-+");
                		txBalance.setText("" + balance);
                		
                		break;
                	case 2:
                		textArea.setText("Sorry, the dealer beat you.");
                		textArea.append("\n+-+-+-+ +-+-+-+-+ +-+-+");
                		textArea.append("\n|Y|O|U| |L|O|S|T| |:|(|");
                		textArea.append("\n+-+-+-+ +-+-+-+-+ +-+-+");
                		txBalance.setText("" + balance);
                		break;
                	case 3:
                		textArea.setText("Congrats, you beat the dealer!");
                		textArea.append("\n+-+-+-+ +-+-+-+ +-+-+");
                		textArea.append("\n|Y|O|U| |W|O|N| |:|)|");
                		textArea.append("\n+-+-+-+ +-+-+-+ +-+-+");
                		txBalance.setText("" + balance);
                		break;
                	}
                }
            });
        btnStand.setBounds(303, 193, 118, 23);
        frame.getContentPane().add(btnStand);

        btnHit = new JButton("Hit");
        btnHit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    players.playerHand.add(new Card());
                    txUserCards.setText(BlackjackHand.printHand("user"));
                    checkAce();
                    lblUserValue.setText("Value: " + calcHand("user"));
                    if(calcHand("user") > 21){
                    	btnHit.setEnabled(false);
                        btnStand.setEnabled(false);
                        btnSurrender.setEnabled(false);
                        btnDoubleDown.setEnabled(false);
                    	textArea.setText("Sorry, you bust!");
                    	textArea.append("\n+-+-+-+ +-+-+-+-+ +-+-+");
                		textArea.append("\n|Y|O|U| |L|O|S|T| |:|(|");
                		textArea.append("\n+-+-+-+ +-+-+-+-+ +-+-+");
                    	balance -= bet;
                    	txBalance.setText("" + balance);
                    }
                }
            });
        btnHit.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnHit.setBounds(175, 227, 118, 23);
        frame.getContentPane().add(btnHit);

        btnDoubleDown = new JButton("Double Down");
        btnDoubleDown.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnDoubleDown.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    players.playerHand.add(new Card());
                    txUserCards.setText(BlackjackHand.printHand("user"));
                    checkAce();
                    bet += bet;
                    txBalance.setText("" + (balance - bet));
                    lblUserValue.setText("Value: " + calcHand("user"));
                    if(calcHand("user") > 21){
                    	btnHit.setEnabled(false);
                        btnStand.setEnabled(false);
                        btnSurrender.setEnabled(false);
                        btnDoubleDown.setEnabled(false);
                    	textArea.setText("Sorry, you bust!");
                    	textArea.append("\n+-+-+-+ +-+-+-+-+ +-+-+");
                		textArea.append("\n|Y|O|U| |L|O|S|T| |:|(|");
                		textArea.append("\n+-+-+-+ +-+-+-+-+ +-+-+");
                    	balance -= bet;
                    	txBalance.setText("" + balance);
                    }
                }
            });
        btnDoubleDown.setBounds(175, 193, 118, 23);
        frame.getContentPane().add(btnDoubleDown);

        btnSurrender = new JButton("Surrender");
        btnSurrender.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnSurrender.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	btnHit.setEnabled(false);
                    btnStand.setEnabled(false);
                    btnSurrender.setEnabled(false);
                    btnDoubleDown.setEnabled(false);
                	for(int i = 0 ; i < players.playerHand.size() ; i++){
                        players.playerHand.get(i).value = 0;
                	}
                	bet /= 2;
                	textArea.setText("You surrenderred.\nYou get half your bet back.");
                	checkWinner();
                	txBalance.setText("" + balance);
                }
            });
        btnSurrender.setBounds(303, 227, 118, 23);
        frame.getContentPane().add(btnSurrender);

        JLabel lblYourCardsAre = new JLabel("Your cards are:");
        lblYourCardsAre.setForeground(Color.BLACK);
        lblYourCardsAre.setBounds(10, 30, 153, 14);
        frame.getContentPane().add(lblYourCardsAre);

        JLabel lblTheDealersCards = new JLabel("The Dealer's cards are:");
        lblTheDealersCards.setForeground(Color.BLACK);
        lblTheDealersCards.setBounds(10, 136, 153, 14);
        frame.getContentPane().add(lblTheDealersCards);

        txUserCards = new JTextField();
        txUserCards.setForeground(Color.WHITE);
        txUserCards.setBackground(Color.BLACK);
        txUserCards.setEditable(false);
        txUserCards.setBounds(10, 61, 142, 20);
        frame.getContentPane().add(txUserCards);
        txUserCards.setColumns(10);

        txDealerCards = new JTextField();
        txDealerCards.setForeground(Color.WHITE);
        txDealerCards.setBackground(Color.BLACK);
        txDealerCards.setEditable(false);
        txDealerCards.setBounds(10, 161, 142, 20);
        frame.getContentPane().add(txDealerCards);
        txDealerCards.setColumns(10);

        lblUserValue = new JLabel("Value:");
        lblUserValue.setForeground(Color.BLACK);
        lblUserValue.setBounds(10, 92, 96, 14);
        frame.getContentPane().add(lblUserValue);

        lblDealerValue = new JLabel("Value:");
        lblDealerValue.setForeground(Color.BLACK);
        lblDealerValue.setBounds(10, 193, 96, 14);
        frame.getContentPane().add(lblDealerValue);

        JLabel lblBet = new JLabel("Bet:");
        lblBet.setForeground(Color.BLACK);
        lblBet.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBet.setBounds(375, 136, 46, 14);
        frame.getContentPane().add(lblBet);

        txBet = new JTextField();
        txBet.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bet = Integer.parseInt(txBet.getText());
                    if(bet > balance){
                    	textArea.setText("You bet more than your balance.\nLower your bet.");
                    } else {
	                    txUserCards.setText(BlackjackHand.printHand("user"));
	                    txDealerCards.setText(BlackjackHand.printHand("dealer"));
	                    lblUserValue.setText("Value: " + BlackjackHand.calcHand("user"));
	                    lblDealerValue.setText("Value: " + players.dealerHand.get(0).value);
	                    checkAce();
	                    lblUserValue.setText("Value: " + calcHand("user"));
	                    textArea.setText("Choose what you want to do.");
	                    btnHit.setEnabled(true);
	                    btnStand.setEnabled(true);
	                    btnSurrender.setEnabled(true);
	                    btnDoubleDown.setEnabled(true);
                    }
                }
            });
        txBet.setBounds(333, 162, 86, 20);
        frame.getContentPane().add(txBet);
        txBet.setColumns(10);

        btnNewHand = new JButton("New Hand");
        btnNewHand.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnNewHand.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if(balance == 0){
                    	textArea.setText("You ran out of money.\nWe reset you back to $100.");
                    }
                    txUserCards.setText("");
                    txDealerCards.setText("");
                    lblUserValue.setText("Value: ");
                    lblDealerValue.setText("Value: ");
                	new BlackjackHand();
                    bet = 0;
                    txBet.setEditable(false);
                    btnHit.setEnabled(false);
                    btnStand.setEnabled(false);
                    btnSurrender.setEnabled(false);
                    btnDoubleDown.setEnabled(false);
                    textArea.setText("Please enter your bet.");
                    txBet.setEditable(true);

                }
            });
        btnNewHand.setBounds(10, 227, 110, 23);
        frame.getContentPane().add(btnNewHand);

        JLabel lblBlackjack = new JLabel("Welcome to\r");
        lblBlackjack.setForeground(Color.BLACK);
        lblBlackjack.setFont(new Font("Monotype Corsiva", Font.ITALIC, 50));
        lblBlackjack.setHorizontalAlignment(SwingConstants.CENTER);
        lblBlackjack.setBounds(175, 34, 246, 54);
        frame.getContentPane().add(lblBlackjack);

        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        textArea.setBounds(431, 30, 253, 220);
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);

    }
   
}