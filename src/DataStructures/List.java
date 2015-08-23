/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package DataStructures;

/**
 * Instances of this class contain a list of objects. Idea borrowed from Elias
 * Annila.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 * @param <SomeElement> 
 */
public class List<SomeElement> {

	private int firstEmptySlot;
	private Object[] array;
	
	public List() {
		this.array = new Object[32];
		this.firstEmptySlot = 0;
	}
	
	/**
	 * Adds one element to the List.
	 * @param element Object of the type specified at the diamond operator.
	 */
	public void add(SomeElement element) {
		if (firstEmptySlot == array.length) {
			increaseSize();
		}
		array[firstEmptySlot] = element;
		firstEmptySlot++;
	}
	
	/**
	 * Returns one element from the list on the index.
	 * If the given index is too high, method returns null.
	 * @param index Index of the object in the List.
	 * @return Object of the type specified at the diamond operator.
	 */
	public SomeElement get(int index) {
		if (index >= firstEmptySlot) {
			return null;
		}
		return (SomeElement)array[index];
	}
	
	/**
	 * Removes element from the list.
	 * @param element Element to be removed from the list.
	 */
	public void remove(SomeElement element) {
		for (int i = firstEmptySlot-1; i >= 0; i--) {
			if (element.equals(array[i])) {
				array[i] = array[firstEmptySlot - 1];
				firstEmptySlot--;
				break;
			}
		}
	}
	
	/**
	 * Returns the current size of the list.
	 * @return Integer, size of the list.
	 */
	public int size() {
		return firstEmptySlot;
	}
	
	/**
	 * Method increases the size of this List, if there is no more space
	 * for the new element.
	 */
	private void increaseSize() {
		Object[] newArray = new Object[array.length*2];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		this.array = newArray;
	}
}
