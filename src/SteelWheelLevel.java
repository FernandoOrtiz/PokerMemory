import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SteelWheelLevel extends RankTrioLevel {

	// STEEL WHEEL: The goal is to find, on each turn, cards A-2-3-4-5 of the same distinct suit
	int hands=0;
	protected SteelWheelLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreCounter scoreCounter) {
		super(validTurnTime, mainFrame, scoreCounter);
		super.turnsTakenCounter.setDifficultyModeLabel("Steel Wheel");
		super.scoreCounter.setDifficultyModeLabel("Steel Wheel");
		cardsToTurnUp = 5;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}

	String winningHand[] = { "a" , "2" , "3" , "4" , "5"};

	// verifies hex values of cards for score
	public int hexValue(Card a){
		int value=0;
		if(a.getSuit().equals("s")){
			value=53;
		}
		else if(a.getSuit().equals("h")){
			value=48;
		}
		else if(a.getSuit().equals("d")){
			value=44;
		}
		else if(a.getSuit().equals("c")){
			value=43;
		}
		return value;
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

			// verifies cards for a steel wheel hand
			if(otherCard1.getSuit().equals(otherCard2.getSuit()) && otherCard2.getSuit().equals(otherCard3.getSuit()) && otherCard3.getSuit().equals(otherCard4.getSuit()) && otherCard4.getSuit().equals(card.getSuit())){
				if(Arrays.asList(winningHand).contains(otherCard1.getRank()) && Arrays.asList(winningHand).contains(otherCard2.getRank()) && Arrays.asList(winningHand).contains(otherCard3.getRank()) && Arrays.asList(winningHand).contains(otherCard4.getRank()) && Arrays.asList(winningHand).contains(card.getRank()) ){

					// Five cards match, so remove them from the list (they will remain face up)
					this.turnedCardsBuffer.clear();
					scoreCounter.increment(1100+hexValue(card));
					hands++;

				}
				else 
				{
					// The cards do not match, so start the timer to turn them down
					this.turnDownTimer.start();
					scoreCounter.increment(-5);
				}
			}

			else 
			{
				// The cards do not match, so start the timer to turn them down
				this.turnDownTimer.start();
				scoreCounter.increment(-5);
			}
		}
		//if player reaches end of the game
		if (hands==2){
			JOptionPane.showMessageDialog(mainFrame, "You Won,There Are No More Posible Hands, Your score is "+this.scoreCounter.getScore()+" points");
			hands=0;
		}
		return true;
	}
}