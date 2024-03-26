package classes;

import java.util.Arrays;

public class LgStraight extends Score {

	LgStraight(String description, int scorecardIndex) {
		super(description, scorecardIndex);
	}

	@Override
	public void setValue(int[] dice) {
		Arrays.sort(dice);
		for (int i=0; i < dice.length - 1; i++) {
			if (dice[i] != dice[i+1] - 1) {
				this.value = 0;
			} else {
				this.value = 40;
			}
		}
		this.filledIn = true;
	}

}
