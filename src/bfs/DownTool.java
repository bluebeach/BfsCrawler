package bfs;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class DownTool {
	
	/**
	 * ����url����ҳ���͵õ�������ļ�����
	 */
	private String getFileNameByUrl(String url,String contentType){
		url = url.substring(7);
		
		if(contentType.indexOf("html") != -1){
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
		}
		else{
			//��html�ı�
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + "."
					+contentType.substring(contentType.lastIndexOf("/") + 1);
		}
		return url;
	}
	
	/**
	 * @param data Ҫ������ֽ�����
	 * @param filePath �����ļ������·��������Ϊ��Ŀ¼�µ�temp�ļ�����
	 */
	private void saveToLocal(byte[] data,String filePath){
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					new File(filePath)));
			for(int i = 0; i < data.length;i++){
				out.write(data[i]);
				
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String downloadToFile(String url){
		String filePath = null;
		
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		GetMethod get = new GetMethod(url);
		get.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
				new DefaultMethodRetryHandler());
		
		try {
			int statusCode = client.executeMethod(get);
			
			if(statusCode != HttpStatus.SC_OK){
				System.err.println("Method failed: " + get.getStatusLine());
				filePath = null;
			}
			
			byte[] responseBody = get.getResponseBody();
			
			filePath = "temp\\" + getFileNameByUrl(url,
					get.getResponseHeader("Content-Type").getValue());
			
			saveToLocal(responseBody,filePath);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			System.out.println("�������http��ַ�Ƿ���ȷ");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally{
			get.releaseConnection();
		}
		return filePath;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
