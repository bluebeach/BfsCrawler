package bfs;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserTool {
	//获取url网页中的链接，用filter来过滤链接
	public static Set<String> extracLinks(String url,LinkFilter filter){
		Set<String> links = new HashSet<String>();
		
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			
			NodeFilter frameFilter = new NodeFilter(){
				private static final long serialVersionUID = 1L;
				@Override
				public boolean accept(Node node) {
					// TODO Auto-generated method stub
					if(node.getText().startsWith("frame src=")){
						return true;
					}
					else
						return false;						
				}
				
			};
			
			// <frame>标签 or <a>标签
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(
					LinkTag.class),frameFilter);
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for(int i = 0; i < list.size();i++){
				Node tag = list.elementAt(i);
				if(tag instanceof LinkTag){
					//<a>标签
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();
					if(filter.accept(linkUrl)){
						links.add(linkUrl);
					}
				}
				else{
					//<frame>标签,如：<frame src="/example/html/frame_c.html">
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if(end == -1){
						end = frame.indexOf(">");
					}
					String frameUrl = frame.substring(5,end-1);
					if(filter.accept(frameUrl)){
						links.add(frameUrl);
					}
				}
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;
	}
}
