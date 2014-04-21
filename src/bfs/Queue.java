package bfs;

import java.util.LinkedList;

/**
 * @author Administrator
 *����unVisited��
 */
public class Queue {
	
	private LinkedList<Object> queue = new LinkedList<Object>();
	
	/**
	 * @param t �����
	 */
	public void enQueue(Object t){
		queue.addLast(t);
	}
	
	/**
	 * �Ƴ����е�һ�����
	 */
	public Object deQueue(){
		return queue.removeFirst();
	}
	
	/**
	 * @return �����Ƿ�Ϊ��
	 */
	public boolean empty(){
		return queue.isEmpty();
	}
	
	/**
	 * @param �Ƿ����t
	 * @return
	 */
	public boolean contains(Object t){
		return queue.contains(t);
	}
}
