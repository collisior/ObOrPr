package quest;

/*
 * Source: http://www.java2s.com/Code/Java/Collections-Data-Structure/TheGenericQueueClass.htm
 */
import java.util.LinkedList;

/*
 * The Generic Queue Class
 */

class GenericQueue<E> {
	private LinkedList<E> list = new LinkedList<E>();

	public void enqueue(E item) {
		list.addLast(item);
	}

	public E dequeue() {
		return list.poll();
	}

	public boolean hasItems() {
		return !list.isEmpty();
	}

	public int size() {
		return list.size();
	}

	public void addItems(GenericQueue<? extends E> q) {
    while (q.hasItems())
      list.addLast(q.dequeue());
    }

}