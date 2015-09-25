package org.skcorg.datastructure.custom;

import java.util.Hashtable;

public class Array {
	private int arr[];

	public Array(int[] dataArr) {
		arr = new int[dataArr.length];
		for (int data = 0; data < dataArr.length; data++) {
			arr[data] = dataArr[data];
		}
	}

	public Array(final int input) {
		this.arr = intToArray(input);
	}

	public int getOddOccurance() {
		int occ = 0;
		for (int data = 0; data < arr.length; data++) {
			occ = occ ^ arr[data];
		}
		return occ;
	}

	public void checkPairForSum(int sum) {
		Hashtable<Integer, Integer> temTable = new Hashtable<Integer, Integer>();
		for (int data = 0; data < arr.length; data++) {
			int temp = sum - arr[data];
			if (temp > 0 && temTable.get(temp) == 1) {
				System.out.println("pair Found");
			}
			temTable.put(arr[data], 1);
		}
	}

	public void sort012(int zero, int one, int two) {
		sort012(sort012(0, arr.length - 1, zero, one), arr.length - 1, one, two);
	}

	private int sort012(int start, int end, int left, int right) {
		int _start = start;
		int _end = end;
		while (_start < _end) {
			if (arr[_start] != left && arr[_end] == left) {
				int _temp = arr[_start];
				arr[_start] = arr[_end];
				arr[_end] = _temp;
			}
			if (arr[_start] == left) {
				_start++;
			}
			if (arr[_end] != left) {
				_end--;
			}
		}
		return _end;
	}

	public void display() {
		for (int i : arr) {
			System.out.print(i + "\t");
		}
	}

	public int equilibrium() {
		int sum = 0; // initialize sum of whole array
		int leftsum = 0; // initialize leftsum
		int i;

		/* Find sum of the whole array */
		for (i = 0; i < arr.length; ++i)
			sum += arr[i];

		for (i = 0; i < arr.length; ++i) {
			sum -= arr[i]; // sum is now right sum for index i

			if (leftsum == sum)
				return i;

			leftsum += arr[i];
		}

		/* If no equilibrium index found, then return 0 */
		return -1;
	}

	public int findNxtlarger() {
		int i = arr.length - 1;
		// finding the first smaller number from right to left
		for (; i >= 0; i--) {
			if (arr[i - 1] < arr[i]) {
				break;
			}
		}
		final int _positionToReplace = i - 1;
		// find next larger digit
		int _positionToReplaceWith = i;
		while (arr[_positionToReplace] < arr[_positionToReplaceWith + 1]) {
			_positionToReplaceWith++;
		}
		swap(_positionToReplace, _positionToReplaceWith);
		// sort the remaining array
		for (int j = i; j < arr.length; j++) {
			for (int k = i; k < arr.length - 1; k++) {
				if (arr[k] > arr[k + 1]) {
					swap(k, k + 1);
				}
			}
		}
		return arrayToInt(arr);
	}

	private void swap(final int left, final int right) {
		final int _temp = arr[left];
		arr[left] = arr[right];
		arr[right] = _temp;
	}

	private int arrayToInt(final int[] input) {
		int num = 0;
		for (final int element : input) {
			num = num * 10 + element;
		}
		return num;
	}

	private int[] intToArray(final int num) {
		int _length = String.valueOf(num).length();
		final int[] _array = new int[_length];
		int temp = num;
		while (_length > 0) {
			_array[_length - 1] = temp % 10;
			temp = temp / 10;
			_length--;
		}
		return _array;
	}
}
