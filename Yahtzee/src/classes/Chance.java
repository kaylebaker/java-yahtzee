package classes;

import java.util.Arrays;

public class Chance extends Score {

	Chance(String description, int scorecardIndex) {
		super(description, scorecardIndex);
	}

	@Override
	public void setValue(int[] dice) {
		this.value = Arrays.stream(dice).sum();
		this.filledIn = true;
	}

}
