package elementsOnPlayground.helpClasses;

/**
 * Difficulty:
 * <p>It sets the walking speed of the Snake.
 * 
 * @author kevinschmidiger
 * @version 1.0.2
 * @date 27.04.2014
 */
public enum Difficulty{
	EASY(200), MEDIUM(100), HARD(50), VERY_HARD(40), SICK(30);

	public int	level;
	
	/**
	 * Constructor
	 * 
	 * @param level		Integer which is used for the sleep() int the while(true)
	 * 					to simulate the speed of the snake.
	 */
	Difficulty(int level){
		this.level = level;
	}
}
