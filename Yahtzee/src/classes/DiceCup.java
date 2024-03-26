package classes;

public class DiceCup {
	
	public int rollsRemaining;
	
	private Dice[] diceArray;
	
	// Constructor
	public DiceCup() {
		this.diceArray = new Dice[5];
		for (int i = 0; i < this.diceArray.length; i++) {
			this.diceArray[i] = new Dice(); // Initialize each Dice object
		}
		this.rollsRemaining = 3;
	}
	
	public int[] getDiceArray() {
		int[] diceResults = new int[5];
		for (int i=0; i<this.diceArray.length; i++) {
			diceResults[i] = this.diceArray[i].currentValue;
		}
		return diceResults;
	}
	
	public void rollDice() {
		int min = 1;
		int max = 6;
		for (int i=0; i<this.diceArray.length; i++) {
			if (diceArray[i].toBeRolled == true) {
				diceArray[i].currentValue = (int)Math.floor(Math.random() * (max - min + 1) + min);
			}
		}
		this.rollsRemaining -= 1;
	}
	
	public void chooseDiceToKeep(int[] arr) {
			for (int element : arr) {
				this.diceArray[element-1].toBeRolled = false;
			}
		}
	
	public void displayCurrentRoll() {
		for (Dice dice : this.diceArray) {
			if (dice.toBeRolled) {
				System.out.print("[" + dice.currentValue + "]" + " ");
			} else {
				System.out.print(dice.currentValue + " ");
			}
		}
	}
	
	public void setToBeRolledToTrue() {
		for (Dice dice : this.diceArray) {
			dice.toBeRolled = true;
		}
	}
	
	public void setToBeRolledToFalse() {
		for (Dice dice : this.diceArray) {
			dice.toBeRolled = false;
		}
	}
	
	public void resetRolls() {
	    for (int i = 0; i < this.diceArray.length; i++) {
	        this.diceArray[i] = new Dice(); // Create a new Dice object for each element
	    }
	    this.rollsRemaining = 3;
	}

}
