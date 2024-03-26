package classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ThreeOfAKind extends Score {

	ThreeOfAKind(String description, int scorecardIndex) {
		super(description, scorecardIndex);
	}

	@Override
	public void setValue(int[] dice) {
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		
		// Populate the HashMap with the frequency of each element
		for (int element : dice) {
			// getOrDefault attempt to retrieve value associated with key 'element'
			// If 'element' is not in the map, returns default argument (0)
			frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
		}
		
		// Check for a value that is exactly 3
		for (Integer value : frequencyMap.values()) {
			if (value >= 3) {
				this.value = Arrays.stream(dice).sum();
				this.filledIn = true;
				break;
			} else {
				this.value = 0;
				this.filledIn = true;
			}
		}
		
	}

}
