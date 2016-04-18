package lab1;

import java.util.ArrayList;
import java.util.Arrays;

public class SubSetSum {
	public static void main(String[] args) {
		int[] set = { 20, 31, 3, 19, 25, 2, 30 };
		int sum = 24;
		Arrays.sort(set);

		int i;
		for (i = 0; i < set.length - 1; i++) {
			if (set[i] > sum)
				break;
		}

		int[] reduSet = new int[i];
		java.lang.System.arraycopy(set, 0, reduSet, 0, i);

		for (int j = set.length; j >= 1; --j) {
			int[] secSet = new int[j];
			java.lang.System.arraycopy(set, 0, secSet, 0, j);
			if (SubSetSumFunc(secSet, sum)) {
				System.out.println("Subset Sum exists");
				return;
			}
		}

		System.out.println("Subset Sum doesn't exists");

	}

	public static Boolean SubSetSumFunc(int[] set, int sum) {

		if (set.length == 0) {
			return false;
		}

		if (set[set.length - 1] == sum) {
			return true;
		}

		int newSum = sum - set[set.length - 1];
		int i;
		for (i = 0; i < set.length - 1; i++) {
			if (set[i] > newSum)
				break;
		}

		int[] reduSet = new int[i];
		java.lang.System.arraycopy(set, 0, reduSet, 0, i);

		return SubSetSumFunc(reduSet, newSum);
	}
}
