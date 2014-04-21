package bfs;

import java.util.Set;

public class Crawler {
	
	/**
	 * @param seeds ���������
	 */
	private void initCrawlerWithSeeds(String[] seeds){
		for(int i = 0; i < seeds.length;i++){
			CrawlerQueue.addUnVisitedUrl(seeds[i]);
		}
	}
	
	public void crawling(String[] seeds){
		//�������������ȡ��www.baidu.com��ͷ������
		LinkFilter filter = new LinkFilter(){
			@Override
			public boolean accept(String url) {
				// TODO Auto-generated method stub
				if(url.startsWith("http://www.baidu.com"))
					return true;
				else
					return false;					
			}
		};
		
		initCrawlerWithSeeds(seeds);
		while(!CrawlerQueue.unVisitedUrlIsEmpty()
				&& CrawlerQueue.getVisitedUrlNum() <= 1000){
			String visitedUrl = (String) CrawlerQueue.unVisitedUrlDeQueue();
			if(visitedUrl == null)
				continue;
			DownTool downloader = new DownTool();
			downloader.downloadToFile(visitedUrl);
			Set<String> links = HtmlParserTool.extracLinks(visitedUrl,filter);
			for(String link : links){
				CrawlerQueue.addUnVisitedUrl(link);
			}
		}
	}
	
	//  ��Դ �� http://blog.csdn.net/pleasecallmewhy/article/details/18010015 
	public static void main(String[] args){
		Crawler bfsCrawler = new Crawler();
		bfsCrawler.crawling(new String[]{"http://www.baidu.com"});
	}
}
