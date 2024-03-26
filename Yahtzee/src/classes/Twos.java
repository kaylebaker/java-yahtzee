package classes;

public class Twos extends Score{

	Twos(String description, int scorecardIndex) {
		super(description, scorecardIndex);
	}

	@Override
	public void setValue(int[] dice) {
		int sum = 0;
		for (int i=0; i<dice.length; i++) {
			if (dice[i] == scorecardIndex) {
				sum += dice[i];
			}
		}
		this.value = sum;
		this.filledIn = true;
	}

}
