package HttpClientDemo;
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


//Post方法登陆知乎？？？
public class PostDemo {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		
		String url = "http://duirlb1.uir.cn/loginAction.do";
		PostMethod postMethod = new PostMethod(url);
		// 填入各个表单域的值
		NameValuePair[] data = { new NameValuePair("userName", "xxx"),
		new NameValuePair("userPass", "xxx")};
		// 将表单的值放入postMethod中
		postMethod.addParameters(data);
		// 执行postMethod
		int statusCode;
		try {
			statusCode = httpClient.executeMethod(postMethod);
			// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
			// 301或者302
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || 
					statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				// 从头中取出转向的地址
				Header locationHeader = postMethod.getResponseHeader("location");
				String location = null;
				if (locationHeader != null) {
					location = locationHeader.getValue();
					System.out.println("The page was redirected to:" + location);
				} else {
					System.err.println("Location field value is null.");
				}
				return;
			}
			System.out.println(postMethod.getResponseBodyAsString());
			System.out.println(postMethod.getResponseContentLength());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			postMethod.releaseConnection();
		}
		
	}
}
		
//		static final String LOGON_SITE = "www.zhihu.com";
//		static final int LOGON_PORT = 8080;

//		post方法，但是连不上
//		HttpClient client = new HttpClient();
//		client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
//		PostMethod post = new PostMethod("/login");
//		NameValuePair email = new NameValuePair("email","xxx@xxx.com");
//		NameValuePair password = new NameValuePair("password", "xxx");
//		NameValuePair rememberme = new NameValuePair("rememberme", "y");
//		post.setRequestBody(new NameValuePair[]{email,password,rememberme});
//		int status = 0;
//		try {
//			status = client.executeMethod(post);
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			System.out.println(status);
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println(status);
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(post.getResponseBodyAsString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		post.releaseConnection();
//		
//		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
//		
//		Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, 
//				"/", false, client.getState().getCookies());
//		if(cookies.length == 0){
//			System.out.println("None");
//		}
//		else{
//			for(int i = 0; i < cookies.length;i++){
//				System.out.println(cookies[i].toString());
//			}
//		}
		

//		Get方法
//		GetMethod get = new GetMethod("www.zhihu.com");
//		client.executeMethod(get);
//		System.out.println(get.getResponseBodyAsString());
//		get.releaseConnection();

