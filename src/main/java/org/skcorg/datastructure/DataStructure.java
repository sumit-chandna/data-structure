package org.skcorg.datastructure;

import org.skcorg.datastructure.custom.Tree;

public class DataStructure {

	/**
	 * @param args
	 */
	/*
	 * public static void main(String[] args) {
	 * // int arr[] = new int[] { 2, 1, 0, 1, 2, 0, 0, 2 };
	 * // Array array = new Array(arr);
	 * // array.sort012(2, 1, 0);
	 * // array.display();
	 * 
	 * int arr2[] = new int[] { -7, 1, 5, 2, -4, 3, 0 };
	 * Array array2 = new Array(arr2);
	 * System.out.println(array2.equilibrium());
	 * array2.display();
	 * 
	 * final Array inputArray = new Array(1483652);
	 * System.out.println(inputArray.findNxtlarger());
	 * }
	 */
	public static void main(String[] args) {
		final Tree tree = new Tree().buildTree("DBEAFC", "ABDECF");
		System.out.println("InOrder : " + tree.inOrder());
		System.out.println("PreOrder : " + tree.preOrder());
		System.out.println("PostOrder : " + tree.postOrder());
	}

}
