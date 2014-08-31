import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RankTrioLevel extends EqualPairLevel {

	// TRIO LEVEL: The goal is to find, on each turn, three cards with the same rank
	int facedUpTrios=0;
	protected RankTrioLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreCounter scoreCounter) {
		super(validTurnTime, mainFrame, scoreCounter);
		super.turnsTakenCounter.setDifficultyModeLabel("Trio Level");
		super.scoreCounter.setDifficultyModeLabel("Trio Level");
		cardsToTurnUp = 3;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}
	// checks the ranks values of the cards for the scoreCounter
	public int sumCards(Card a, Card b, Card c){
		int num1,num2,num3;
		//verify rank first card
		if(a.getRank().equals("t")){
			num1=10;
		}
		else if(a.getRank().equals("j")){
			num1=11;
		}
		else if(a.getRank().equals("q")){
			num1=12;
		}
		else if(a.getRank().equals("k")){
			num1=13;
		}
		else if(a.getRank().equals("a")){
			num1=14;
		}
		else{
			num1=Integer.parseInt(a.getRank());
		}

		//verify rank second card
		if(b.getRank().equals("t")){
			num2=10;
		}
		else if(b.getRank().equals("j")){
			num2=11;
		}
		else if(b.getRank().equals("q")){
			num2=12;
		}
		else if(b.getRank().equals("k")){
			num2=13;
		}
		else if(b.getRank().equals("a")){
			num2=14;
		}
		else{
			num2=Integer.parseInt(b.getRank());
		}

		//verify rank third card
		if(c.getRank().equals("t")){
			num3=10;
		}
		else if(c.getRank().equals("j")){
			num3=11;
		}
		else if(c.getRank().equals("q")){
			num3=12;
		}
		else if(c.getRank().equals("k")){
			num3=13;
		}
		else if(c.getRank().equals("a")){
			num3=14;
		}
		else{
			num3=Integer.parseInt(c.getRank());
		}


		return num1+num2+num3;
	}


	@Override
	protected void makeDeck() {
		// In Trio level the grid consists of distinct cards, no repetitions
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
			if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank()))) {
				// Three cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();
				//increment the score
				scoreCounter.increment(100+sumCards(card,otherCard1,otherCard2));
				facedUpTrios=facedUpTrios+1;

			}
			else 
			{
				// The cards do not match, so start the timer to turn them down
				this.turnDownTimer.start();
				//penalty for the score
				scoreCounter.increment(-5);
			}
		}
		//if player reaches the end of the game
		if (facedUpTrios==12){
			JOptionPane.showMessageDialog(mainFrame, "You Won,There Are No More Posible Trios, Your score is "+this.scoreCounter.getScore()+" points");
			facedUpTrios=0;
		}
		return true;
	}
}
