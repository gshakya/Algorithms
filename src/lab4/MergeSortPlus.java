package lab4;

public class MergeSortPlus {

	private static int[] theArray;
	
	private MergeSortPlus (){}; 
	

	public static int[] sort(int[] input) {
		int n = input.length;
		theArray = input;
		int[] tempStorage = new int[n];
		
		tempStorage = input;
		
		mergeSort(tempStorage, 0, n - 1);
		theArray = tempStorage;
		return theArray;
	}

	static void mergeSort(int[] temp, int lower, int upper) {
		if (lower == upper) {
			return;
		} else {
			int mid = (lower + upper) / 2;
			mergeSort(temp, lower, mid);
			mergeSort(temp, mid + 1, upper);
			merge(temp, lower, mid + 1, upper);
		}
	}

	public static void merge(int[] tempStorage, int lowerPointer, int upperPointer, int upperBound) {
		int j = 0; // tempStorage index
		int lowerBound = lowerPointer; // total number of elements to rearrange
		int n = upperBound - lowerBound + 1;
		// view the range [lowerBound,upperBound] as two arrays
		// [lowerBound, mid], [mid+1,upperBound] to be merged
		int mid = upperPointer - 1;
		while (lowerPointer <= mid && upperPointer <= upperBound) {
			if (theArray[lowerPointer] <= theArray[upperPointer]) {
				tempStorage[j++] = theArray[lowerPointer++];
			} else {
				tempStorage[j++] = theArray[upperPointer++];
			}
		}

		// left array may still have elements
		while (lowerPointer <= mid) {
			tempStorage[j++] = theArray[lowerPointer++];
		}
		// right array may still have elements
		while (upperPointer <= upperBound) {
			tempStorage[j++] = theArray[upperPointer++];
		}
		// replace the range [lowerBound,upperBound] in theArray with
		// the range [0,n-1] just created in tempStorage
		for (j = 0; j < n; ++j) {
			theArray[lowerBound + j] = tempStorage[j];
		}
	}
	
	public static void main(String[] args) {
		for (int  i : MergeSortPlus.sort(new int[] {104, 100, 145, 88,56,191,164,61,2,109,60,149,44,123,179,187,92,134,105,166,147,96,179,47,
				130,82,123,154,145,41,81,149,185,59,111,26,11,39,12,64,83,131,152,18,106,183,178,135,26,
				168,67,52,59,158,199,200,20,151,23,185,93,168,183,113,53,142,116,199,63,40,185,101,83,71,
				111,171,22,168,153,115,111,16,138,131,139,144,128,57,152,162,123,163,153,5,191,98,98,135,
				142,103,61,82,106,58,186,84,154,143,13,14,2,27,183,34,165,131,126,118,89,43,3,192,67,49,180
				,83,6,155,118,108,96,70,167,26,11,154,156,53,82,29,172,167,153,3,72,159,122,150,103,68,47,167
				,131,117,25,192,29,77,182,173,52,47,78,116,126,184,162,87,124,102,126,191,82,96,121,83,60
				,47,186,31,67,106,50,1,112,105,177,85,42,63,163,84,81,178,10,167,91,46,122,122,66,84,15,107
				,18,101,144,40,191,144,154,31,36,38,66,116,113,110,143,182}))
		{
			System.out.print(i+"->");
		}
	}
}
