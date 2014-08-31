import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ComboLevel extends RankTrioLevel {

	// Combo Level combines all precious 5 card levels and an extra poker hand

	protected ComboLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreCounter scoreCounter) {
		super(validTurnTime, mainFrame, scoreCounter);
		super.turnsTakenCounter.setDifficultyModeLabel("Combo Level");
		super.scoreCounter.setDifficultyModeLabel("Combo Level");
		cardsToTurnUp = 5;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}

	String winningHand[] = { "a" , "2" , "3" , "4" , "5"};
	String comboHand[] = { "a" , "k" , "q" , "j" , "t"};


	// boolean that verifies if there's a third wheel
	public boolean isThirdW (Card a, Card b , Card c){



		if(isPair(a,b)&& c.getSuit().equals(b.getSuit()) && c.getSuit().equals(a.getSuit())||isPair(a,b)&& c.getRank().equals(b.getRank())||isPair(a,b)&& c.getRank().equals(a.getRank())){
			return true;
		}
		return false;


	}

	// boolean that verifies if there is a pair
	public boolean isPair(Card a, Card b){
		if(a.getRank().equals(b.getRank())){
			return true;
		}
		else if(a.getSuit().equals(b.getSuit())){
			return true;
		}
		return false;
	}




	// third wheel pop-up
	public  void ThirdWheel(){
		String description = "Third Wheel Hand";
		Icon thinker = new ImageIcon("images/icon.jpg");
		Object stringArray[] = { "No Way", "Do It" };
		int responce = JOptionPane.showOptionDialog(this.mainFrame, "Your hand is a "+description+" for 800 points, would you like to stay with it? Y/N", "Select an Option",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, thinker, stringArray,
				stringArray[0]);
		//if user says yes.
		if(responce != JOptionPane.YES_OPTION){
			scoreCounter.increment(800);
			this.turnedCardsBuffer.clear();
		}
		else{
			this.turnDownTimer.start();
			scoreCounter.increment(-5);
		}

	}

	//Steel wheel pop-up
	public void SteelWheel(){
		String description = "Steel Wheel Hand";
		Icon thinker = new ImageIcon("images/icon.jpg");
		Object stringArray[] = { "No Way", "Do It" };
		int responce = JOptionPane.showOptionDialog(this.mainFrame, "Your hand is a "+description+" for 1100 points, would you like to stay with it? Y/N", "Select an Option",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, thinker, stringArray,
				stringArray[0]);
		//If the user say yes. 
		if(responce != JOptionPane.YES_OPTION){
			scoreCounter.increment(1100);
			this.turnedCardsBuffer.clear();
		}
		else{
			this.turnDownTimer.start();
			scoreCounter.increment(-5);
		}
	}
	//combo pop-up
	public void Combo(){
		String description = "Combo Hand";
		Icon thinker = new ImageIcon("images/icon.jpg");
		Object stringArray[] = { "No Way", "Do It" };
		int responce = JOptionPane.showOptionDialog(this.mainFrame, "Your hand is a "+description+" for 1500 points, would you like to stay with it? Y/N", "Select an Option",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, thinker, stringArray,
				stringArray[0]);
		//If the user say yes. 
		if(responce != JOptionPane.YES_OPTION){
			scoreCounter.increment(1500);
			this.turnedCardsBuffer.clear();
		}
		else{
			this.turnDownTimer.start();
			scoreCounter.increment(-5);
		}
	}



	@Override
	protected void makeDeck() {
		// In Steel Wheel level the grid consists of distinct cards, no repetitions
		ImageIcon cardIcon[] = this.loadCardIcons();

		//back card
		ImageIcon backIcon = cardIcon[TotalCardsPerDeck];

		int cardsToAdd[] = new int[getRowsPerGrid() * getCardsPerRow()];
		for(int i = 0; i < (getRowsPerGrid() * getCardsPerRow()); i++)
		{
			cardsToAdd[i] = i;
		}

		// randomize the order of the deck
		this.randomizeIntArray(cardsToAdd);

		// make each card object
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// number of the card, randomized
			int num = cardsToAdd[i];
			// make the card object and add it to the panel
			String rank = cardNames[num].substring(0, 1);
			String suit = cardNames[num].substring(1, 2);
			this.grid.add( new Card(this, cardIcon[num], backIcon, num, rank, suit));
		}
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)

			Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
			Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
			Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
			Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);

			// verifies all possible combinations to obtain a Third Wheel
			if( (isThirdW(otherCard1, otherCard2, card)&&isPair(otherCard3, otherCard4)) || (isThirdW(otherCard3, otherCard4, card)&&isPair(otherCard1, otherCard2)) || (isThirdW(otherCard1, otherCard3, card)&&isPair(otherCard2, otherCard4)) || (isThirdW(otherCard2, otherCard4, card)&&isPair(otherCard1, otherCard3)) || (isThirdW(otherCard1, otherCard4, otherCard3)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard3)&&isPair(otherCard1, otherCard4)) ) {

				ThirdWheel();
			}
			else if( (isThirdW(otherCard1, card, otherCard3)&&isPair(otherCard2, otherCard4)) || (isThirdW(otherCard2, otherCard4, otherCard3)&&isPair(otherCard1, card)) || (isThirdW(otherCard2, otherCard3, otherCard1)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard1)&&isPair(otherCard2, otherCard3)) || (isThirdW(otherCard1, otherCard2, otherCard4)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard4)&&isPair(otherCard1, otherCard2)) ) {

				ThirdWheel();
			}
			else if( (isThirdW(otherCard1, otherCard2, otherCard3)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard3)&&isPair(otherCard1, otherCard2)) || (isThirdW(otherCard2, otherCard3, card)&&isPair(otherCard1, otherCard4)) || (isThirdW(otherCard2, otherCard4, card)&&isPair(otherCard2, otherCard3)) || (isThirdW(otherCard2, otherCard3, otherCard4)&&isPair(otherCard1, card)) || (isThirdW(otherCard1, card, otherCard4)&&isPair(otherCard2, otherCard3)) ) {

				ThirdWheel();
			}
			else if( (isThirdW(otherCard3, otherCard1, otherCard4)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard4)&&isPair(otherCard3, otherCard1)) || (isThirdW(otherCard3, otherCard4, otherCard2)&&isPair(otherCard1, card)) || (isThirdW(otherCard1, card, otherCard2)&&isPair(otherCard3, otherCard4)) || (isThirdW(otherCard3, otherCard4, otherCard1)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard1)&&isPair(otherCard3, otherCard4)) ) {

				ThirdWheel();
			}
			else if( (isThirdW(otherCard3, otherCard1, otherCard2)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard2)&&isPair(otherCard3, otherCard1)) || (isThirdW(otherCard4, otherCard1, otherCard2)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard2)&&isPair(otherCard4, otherCard1)) || (isThirdW(otherCard4, otherCard2, otherCard1)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard1)&&isPair(otherCard2, otherCard4)) ) {

				ThirdWheel();
			}


			//verifies the possibilities for a Steel Wheel hand
			if(otherCard1.getSuit().equals(otherCard2.getSuit()) && otherCard2.getSuit().equals(otherCard3.getSuit()) && otherCard3.getSuit().equals(otherCard4.getSuit()) && otherCard4.getSuit().equals(card.getSuit())){
				if(Arrays.asList(winningHand).contains(otherCard1.getRank()) && Arrays.asList(winningHand).contains(otherCard2.getRank()) && Arrays.asList(winningHand).contains(otherCard3.getRank()) && Arrays.asList(winningHand).contains(otherCard4.getRank()) && Arrays.asList(winningHand).contains(card.getRank()) ){


					SteelWheel();

				}
			}

			//verify combo hand is a four of a kind
			if(card.getRank().equals(otherCard1.getRank())&&card.getRank().equals(otherCard2.getRank())&&card.getRank().equals(otherCard3.getRank())){
				Combo();
			}
			else if(card.getRank().equals(otherCard1.getRank())&&card.getRank().equals(otherCard2.getRank())&&card.getRank().equals(otherCard4.getRank())){
				Combo();
			}
			else if(card.getRank().equals(otherCard1.getRank())&&card.getRank().equals(otherCard3.getRank())&&card.getRank().equals(otherCard4.getRank())){
				Combo();
			}
			else if(card.getRank().equals(otherCard2.getRank())&&card.getRank().equals(otherCard3.getRank())&&card.getRank().equals(otherCard4.getRank())){
				Combo();
			}
			else if(otherCard1.getRank().equals(otherCard2.getRank())&&otherCard1.getRank().equals(otherCard3.getRank())&&otherCard1.getRank().equals(otherCard4.getRank())){
				Combo();
			}



			else 
			{
				// The cards do not match, so start the timer to turn them down
				this.turnDownTimer.start();
				scoreCounter.increment(-5);
			}
		}
		// if user reaches the end of the game
		if(scoreCounter.getScore()==70000){
			JOptionPane.showMessageDialog(mainFrame, "You Won, Congratulations, Your score is "+this.scoreCounter.getScore()+" points");
		}
		return true;
	}
}