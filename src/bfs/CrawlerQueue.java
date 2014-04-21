package bfs;

import java.util.HashSet;
import java.util.Set;

public class CrawlerQueue {
	private static Set<Object> visitedUrl = new HashSet<>();
	
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	
	public static void removeVisetedUrl(String url){
		visitedUrl.remove(url);
	}
	
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	
	private static Queue unVisitedUrl = new Queue();
	
	public static Queue getUnVisitedUrl(){
		return unVisitedUrl;
	}
	
	public static Object unVisitedUrlDeQueue(){
		return unVisitedUrl.deQueue();
	}
	
	public static void addUnVisitedUrl(String url){
		if(url != null && !url.trim().equals("") && 
				!visitedUrl.contains(url) && !unVisitedUrl.contains(url))
			unVisitedUrl.enQueue(url);
	}
	
	public static boolean unVisitedUrlIsEmpty(){
		return unVisitedUrl.empty();
	}
}
