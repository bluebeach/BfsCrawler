package HttpClientDemo;
import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


//httpclient 3.1

public class Demo_2 {
	
	//带上cookie的get方法，能够获取到需要登陆才可以查看到的网页内容
	//所以，只要能得到cookie，就可以get到你想要的网页了。
	//post的时候，验证成功后，服务器会给你一个cookie，下次你就可以用那个cookie来登陆了。
	//这应该就是浏览器帮你自动登陆的原理吧。
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cookie[] cookies = new Cookie[1];
		
		cookies[0] = new Cookie("duirlb1.uir.cn","JSESSIONID","aDySp_S2iuu-","/",-1,false);
		
		HttpClient client = new HttpClient();
		client.getState().addCookies(cookies);
		
		HttpMethod method = new GetMethod(
				"http://duirlb1.uir.cn/index_jg.jsp");
		
		try {
			client.executeMethod(method);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(method.getStatusLine());
		
		String response = null;
		try {
			response = new String(method.getResponseBodyAsString().getBytes("gb2312"));  //ISO-8859编码
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(response);
		
		method.releaseConnection();		
	}

}
