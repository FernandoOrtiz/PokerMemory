import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ThirdWheelLevel extends RankTrioLevel {

	// THIRD WEEL: The goal is to find, on each turn, two pairs with same suit or rank and a third one equal to one of the pairs

	int countCards = 0;

	protected ThirdWheelLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreCounter scoreCounter) {
		super(validTurnTime, mainFrame, scoreCounter);
		super.turnsTakenCounter.setDifficultyModeLabel("Third Wheel");
		super.scoreCounter.setDifficultyModeLabel("Third Wheel");
		cardsToTurnUp = 5;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}
	// checks the ranks values of the cards for the scoreCounter
	public int sumCards(Card a, Card b, Card c, Card d, Card e){
		int num1,num2,num3,num4,num5;
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

		//verify rank fourth card
		if(d.getRank().equals("t")){
			num4=10;
		}
		else if(d.getRank().equals("j")){
			num4=11;
		}
		else if(d.getRank().equals("q")){
			num4=12;
		}
		else if(d.getRank().equals("k")){
			num4=13;
		}
		else if(d.getRank().equals("a")){
			num4=14;
		}
		else{
			num4=Integer.parseInt(d.getRank());
		}

		//verify rank fifth card
		if(e.getRank().equals("t")){
			num5=10;
		}
		else if(e.getRank().equals("j")){
			num5=11;
		}
		else if(e.getRank().equals("q")){
			num5=12;
		}
		else if(e.getRank().equals("k")){
			num5=13;
		}
		else if(e.getRank().equals("a")){
			num5=14;
		}
		else{
			num5=Integer.parseInt(e.getRank());
		}

		return num1+num2+num3+num4+num5;
	}
	// boolean that cheks if there is a third wheel
	public boolean isThirdW (Card a, Card b , Card c){



		if(isPair(a,b)&& c.getSuit().equals(b.getSuit()) && c.getSuit().equals(a.getSuit())||isPair(a,b)&& c.getRank().equals(b.getRank())||isPair(a,b)&& c.getRank().equals(a.getRank())){
			return true;
		}
		return false;


	}
	// boolean that verifies if its a pair
	public boolean isPair(Card a, Card b){
		if(a.getRank().equals(b.getRank())){
			return true;
		}
		else if(a.getSuit().equals(b.getSuit())){
			return true;
		}
		return false;
	}


	@Override
	protected void makeDeck() {
		// In Third Wheel level the grid consists of distinct cards, no repetitions
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


			boolean isThirdWheel = false;
			// verifies if player reached the end of the game
			if (countCards == 45)
			{
				// verifies all possible combinations of cards available in this level
				if( (isThirdW(otherCard1, otherCard2, card)&&isPair(otherCard3, otherCard4)) || (isThirdW(otherCard3, otherCard4, card)&&isPair(otherCard1, otherCard2)) || (isThirdW(otherCard1, otherCard3, card)&&isPair(otherCard2, otherCard4)) || (isThirdW(otherCard2, otherCard4, card)&&isPair(otherCard1, otherCard3)) || (isThirdW(otherCard1, otherCard4, otherCard3)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard3)&&isPair(otherCard1, otherCard4)) ) {
					isThirdWheel = true;
				}
				else if( (isThirdW(otherCard1, card, otherCard3)&&isPair(otherCard2, otherCard4)) || (isThirdW(otherCard2, otherCard4, otherCard3)&&isPair(otherCard1, card)) || (isThirdW(otherCard2, otherCard3, otherCard1)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard1)&&isPair(otherCard2, otherCard3)) || (isThirdW(otherCard1, otherCard2, otherCard4)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard4)&&isPair(otherCard1, otherCard2)) ) {
					isThirdWheel = true;
				}
				else if( (isThirdW(otherCard1, otherCard2, otherCard3)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard3)&&isPair(otherCard1, otherCard2)) || (isThirdW(otherCard2, otherCard3, card)&&isPair(otherCard1, otherCard4)) || (isThirdW(otherCard2, otherCard4, card)&&isPair(otherCard2, otherCard3)) || (isThirdW(otherCard2, otherCard3, otherCard4)&&isPair(otherCard1, card)) || (isThirdW(otherCard1, card, otherCard4)&&isPair(otherCard2, otherCard3)) ) {
					isThirdWheel = true;
				}
				else if( (isThirdW(otherCard3, otherCard1, otherCard4)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard4)&&isPair(otherCard3, otherCard1)) || (isThirdW(otherCard3, otherCard4, otherCard2)&&isPair(otherCard1, card)) || (isThirdW(otherCard1, card, otherCard2)&&isPair(otherCard3, otherCard4)) || (isThirdW(otherCard3, otherCard4, otherCard1)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard1)&&isPair(otherCard3, otherCard4)) ) {
					isThirdWheel = true;
				}
				else if( (isThirdW(otherCard3, otherCard1, otherCard2)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard2)&&isPair(otherCard3, otherCard1)) || (isThirdW(otherCard4, otherCard1, otherCard2)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard2)&&isPair(otherCard4, otherCard1)) || (isThirdW(otherCard4, otherCard2, otherCard1)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard1)&&isPair(otherCard2, otherCard4)) ) {
					isThirdWheel = true;
				}
				else{
					JOptionPane.showMessageDialog(mainFrame, "No more two pairs and a third wheel combos. Your score is "+this.scoreCounter.getScore()+" points");
				}
			}

			if(isThirdWheel == true){
				JOptionPane.showMessageDialog(mainFrame, "YOU WON, Your score is "+this.scoreCounter.getScore()+" points");
			}


			// verifies all possible combinations of cards available in this level
			if( (isThirdW(otherCard1, otherCard2, card)&&isPair(otherCard3, otherCard4)) || (isThirdW(otherCard3, otherCard4, card)&&isPair(otherCard1, otherCard2)) || (isThirdW(otherCard1, otherCard3, card)&&isPair(otherCard2, otherCard4)) || (isThirdW(otherCard2, otherCard4, card)&&isPair(otherCard1, otherCard3)) || (isThirdW(otherCard1, otherCard4, otherCard3)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard3)&&isPair(otherCard1, otherCard4)) ) {
				//  cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();	
				// increments score
				scoreCounter.increment(800+sumCards(card,otherCard1,otherCard2,otherCard3,otherCard4));
				countCards += 5;
			}
			else if( (isThirdW(otherCard1, card, otherCard3)&&isPair(otherCard2, otherCard4)) || (isThirdW(otherCard2, otherCard4, otherCard3)&&isPair(otherCard1, card)) || (isThirdW(otherCard2, otherCard3, otherCard1)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard1)&&isPair(otherCard2, otherCard3)) || (isThirdW(otherCard1, otherCard2, otherCard4)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard4)&&isPair(otherCard1, otherCard2)) ) {
				//  cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();	
				// increments score
				scoreCounter.increment(800+sumCards(card,otherCard1,otherCard2,otherCard3,otherCard4));
				countCards += 5;
			}
			else if( (isThirdW(otherCard1, otherCard2, otherCard3)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard3)&&isPair(otherCard1, otherCard2)) || (isThirdW(otherCard2, otherCard3, card)&&isPair(otherCard1, otherCard4)) || (isThirdW(otherCard2, otherCard4, card)&&isPair(otherCard2, otherCard3)) || (isThirdW(otherCard2, otherCard3, otherCard4)&&isPair(otherCard1, card)) || (isThirdW(otherCard1, card, otherCard4)&&isPair(otherCard2, otherCard3)) ) {
				//  cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();	
				// increments score
				scoreCounter.increment(800+sumCards(card,otherCard1,otherCard2,otherCard3,otherCard4));
				countCards += 5;
			}
			else if( (isThirdW(otherCard3, otherCard1, otherCard4)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard4)&&isPair(otherCard3, otherCard1)) || (isThirdW(otherCard3, otherCard4, otherCard2)&&isPair(otherCard1, card)) || (isThirdW(otherCard1, card, otherCard2)&&isPair(otherCard3, otherCard4)) || (isThirdW(otherCard3, otherCard4, otherCard1)&&isPair(otherCard2, card)) || (isThirdW(otherCard2, card, otherCard1)&&isPair(otherCard3, otherCard4)) ) {
				//  cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();	
				// increments score
				scoreCounter.increment(800+sumCards(card,otherCard1,otherCard2,otherCard3,otherCard4));
				countCards += 5;
			}
			else if( (isThirdW(otherCard3, otherCard1, otherCard2)&&isPair(otherCard4, card)) || (isThirdW(otherCard4, card, otherCard2)&&isPair(otherCard3, otherCard1)) || (isThirdW(otherCard4, otherCard1, otherCard2)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard2)&&isPair(otherCard4, otherCard1)) || (isThirdW(otherCard4, otherCard2, otherCard1)&&isPair(otherCard3, card)) || (isThirdW(otherCard3, card, otherCard1)&&isPair(otherCard2, otherCard4)) ) {
				//  cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();	
				// increments score
				scoreCounter.increment(800+sumCards(card,otherCard1,otherCard2,otherCard3,otherCard4));
				countCards += 5;
			}
			else 
			{
				// The cards do not match, so start the timer to turn them down
				this.turnDownTimer.start();
				//penalty for the score
				scoreCounter.increment(-5);
			}


		}
		return true;
	}
}
