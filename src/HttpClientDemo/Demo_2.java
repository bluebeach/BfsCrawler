package HttpClientDemo;
import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


//httpclient 3.1

public class Demo_2 {
	
	//����cookie��get�������ܹ���ȡ����Ҫ��½�ſ��Բ鿴������ҳ����
	//���ԣ�ֻҪ�ܵõ�cookie���Ϳ���get������Ҫ����ҳ�ˡ�
	//post��ʱ����֤�ɹ��󣬷����������һ��cookie���´���Ϳ������Ǹ�cookie����½�ˡ�
	//��Ӧ�þ�������������Զ���½��ԭ��ɡ�
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
			response = new String(method.getResponseBodyAsString().getBytes("gb2312"));  //ISO-8859����
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(response);
		
		method.releaseConnection();		
	}

}
