import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EqualPairLevel extends EasyLevel {
	int faceUp=0;
	protected EqualPairLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreCounter scoreCounter) {
		super(validTurnTime, mainFrame,scoreCounter);
		super.turnsTakenCounter.setDifficultyModeLabel("Medium Level");
		super.scoreCounter.setDifficultyModeLabel("Medium Level");
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// there are two cards faced up
			// record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)
			Card otherCard = (Card) this.turnedCardsBuffer.get(0);
			// the cards match, so remove them from the list (they will remain face up)
			if( otherCard.getNum() == card.getNum()){
				this.turnedCardsBuffer.clear();
				//increments the score
				scoreCounter.increment(50);
				faceUp=faceUp+2;
			}
			// the cards do not match, so start the timer to turn them down
			else {
				this.turnDownTimer.start();
				//penalty for the score
				scoreCounter.increment(-5);
			}
		}
		//if player reaches the end of the game
		if (faceUp==16){
			JOptionPane.showMessageDialog(mainFrame, "YOU WON, Your score is "+this.scoreCounter.getScore()+" points");
		}
		return true;
	}

	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.turnedCardsBuffer.size() < getCardsToTurnUp()) 
		{
			return this.addToTurnedCardsBuffer(card);
		}
		// there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
		return false;
	}

	@Override
	protected String getMode() {
		// TODO Auto-generated method stub
		return "MediumMode";
	}



}
