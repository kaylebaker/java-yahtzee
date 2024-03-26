package classes;

public class Yahtzee extends Score {

	Yahtzee(String description, int scorecardIndex) {
		super(description, scorecardIndex);
	}
	
	private int yahtzeeBonusTally = 0;

	@Override
	public void setValue(int[] dice) {
		boolean isYahtzee = false;
		for (int i=0; i < dice.length - 1; i++) {
			if (dice[i] != dice[i+1]) {
				break;
			} else {
				isYahtzee = true;
			}
		}
		// Check if Yahtzee has already been scored and add additional 100 points for Yahtzee bonus
		if (isYahtzee && this.value >= 50) {
			this.value += 100;
			this.yahtzeeBonusTally += 1;
		} else if (isYahtzee) {
			this.value = 50;
			this.setYahtzeeBonusDescription();
		} else {
			this.value = 0;
			this.filledIn = true;
		}
	}
				
			
	public int getYahtzeeBonusTally() {
		return yahtzeeBonusTally;
	}
	
	public void setYahtzeeBonusDescription() {
		this.description = "YAHTZEE BONUS\t| +100 additional points";
	}
}
