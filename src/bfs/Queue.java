package bfs;

import java.util.LinkedList;

/**
 * @author Administrator
 *保存unVisited表
 */
public class Queue {
	
	private LinkedList<Object> queue = new LinkedList<Object>();
	
	/**
	 * @param t 入队列
	 */
	public void enQueue(Object t){
		queue.addLast(t);
	}
	
	/**
	 * 移除队列第一项并返回
	 */
	public Object deQueue(){
		return queue.removeFirst();
	}
	
	/**
	 * @return 队列是否为空
	 */
	public boolean empty(){
		return queue.isEmpty();
	}
	
	/**
	 * @param 是否包含t
	 * @return
	 */
	public boolean contains(Object t){
		return queue.contains(t);
	}
}
