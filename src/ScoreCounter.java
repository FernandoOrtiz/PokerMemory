import java.awt.Color;

import javax.swing.JLabel;

public class ScoreCounter extends JLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// data fields
	private int score = 0;
	private  String DESCRIPTION;

	public ScoreCounter()
	{
		super();
		reset();
	}
	public void setDifficultyModeLabel(String difficultyMode){
		DESCRIPTION = "Score: ";
		setHorizontalTextPosition(JLabel.LEFT);
		setForeground(Color.WHITE);
	}

	public int getScore(){
		return this.score;
	}

	/**
	 * Update the text label with the current counter value
	 */
	private void update()
	{
		setText(DESCRIPTION + Integer.toString(this.score));
		setHorizontalTextPosition(JLabel.LEFT);
	}

	/**
	 * Default constructor, starts counter at 0
	 */


	/**
	 * Increments the counter and updates the text label
	 */
	public void increment(int value)
	{
		this.score = this.score+value;
		update();
	}

	/**
	 * Resets the counter to zero and updates the text label
	 */
	public void reset()
	{
		this.score = 0;
		update();
	}
}