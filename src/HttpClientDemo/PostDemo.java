package HttpClientDemo;
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


//Post������½֪��������
public class PostDemo {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		
		String url = "http://duirlb1.uir.cn/loginAction.do";
		PostMethod postMethod = new PostMethod(url);
		// ������������ֵ
		NameValuePair[] data = { new NameValuePair("userName", "20117101"),
		new NameValuePair("userPass", "wangjun")};
		// ������ֵ����postMethod��
		postMethod.addParameters(data);
		// ִ��postMethod
		int statusCode;
		try {
			statusCode = httpClient.executeMethod(postMethod);
			// HttpClient����Ҫ����ܺ�̷����������POST��PUT�Ȳ����Զ�����ת��
			// 301����302
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || 
					statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				// ��ͷ��ȡ��ת��ĵ�ַ
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

//		post����������������
//		HttpClient client = new HttpClient();
//		client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
//		PostMethod post = new PostMethod("/login");
//		NameValuePair email = new NameValuePair("email","wjcj0123@163.com");
//		NameValuePair password = new NameValuePair("password", "wangjun");
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
		

//		Get����
//		GetMethod get = new GetMethod("www.zhihu.com");
//		client.executeMethod(get);
//		System.out.println(get.getResponseBodyAsString());
//		get.releaseConnection();

