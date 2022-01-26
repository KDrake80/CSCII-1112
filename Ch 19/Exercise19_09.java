/*
Author: Kevin Drake
Date: 1/25/22
Description: This sorts an arraylist of elements
 */
import java.util.ArrayList;
public class Exercise19_09 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(24);
		list.add(4);
		list.add(42);
		list.add(5);
		Exercise19_09.<Integer>sort(list);

		System.out.print(list);
	}

	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
		E currentMin;
		int currentIndex;
		for (int i = 0; i < list.size() - 1; i++) {
			currentMin= list.get(i);
			currentIndex = i;
			
			for (int j = i + 1; j < list.size(); j++) {
				if (currentMin.compareTo(list.get(j)) > 0) {
					currentMin = list.get(j);
					currentIndex = j;
				}
			}
			// Swap list[i] with list[currentIndex] if necessary
			if (currentIndex != i) {
				 list.set(currentIndex, list.get(i));
				list.set(i, currentMin);
			}
		}
	}
}