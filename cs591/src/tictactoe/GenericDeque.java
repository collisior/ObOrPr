package tictactoe;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class GenericDeque {
	
	public GenericDeque() {
	}
	/*
	 * Inserts the specified element at the end of this queue 
	 */
	public static <T>  Deque<T> enqueue(Deque<T> queue, T item) {
		queue.offerLast(item);
//		queue.addLast(item);
		return queue;
	}
	/*
	 * Inserts the specified element at the front of this queue 
	 */
	public static <T> Deque<T> dequeue(Deque<T> queue, T item) {
		queue.addFirst(item);
		return queue;
	}
	/*
	 * Retrieves, but does not remove, the first element of this queue.
	 */
	public static <T> T getFirst(Deque<T> queue) {
		return queue.getFirst();
	}
	/*
	 * Retrieves, but does not remove, the last element of this queue.
	 */
	public static <T> T getLast(Deque<T> queue) {
		return queue.getLast();
	}
	/*
	 * Retrieves and removes the first element of this queue
	 */
	public static <T> T pollFirst(Deque<T> queue) {
		return queue.pollFirst();
	}
	/*
	 * Retrieves and removes the last element of this queue,
	 */
	public static <T> T pollLast(Deque<T> queue) {
		return queue.pollLast();
	}
	/*
	 * Returns true if queue is empty
	 */
	public static <T> boolean hasItems(Deque<T> queue) {
		return !queue.isEmpty();
	}
	/*
	 * Returns size of this dequeue 
	 */
	public static <T> int size(Deque<T> queue) {
		return queue.size();
	}
	/*
	 * Return queue of given list (players/teams).
	 */
	public static <T> Deque<T> setQueue(ArrayList<T> array) {
		Deque<T> queue = new LinkedList<T>();
		for (int i = 0; i < array.size(); i++) {
			queue.add(array.get(i));
		}
		return queue;
	}
}
