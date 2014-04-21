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
	 * 根据url和网页类型得到保存的文件名。
	 */
	private String getFileNameByUrl(String url,String contentType){
		url = url.substring(7);
		
		if(contentType.indexOf("html") != -1){
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
		}
		else{
			//非html文本
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + "."
					+contentType.substring(contentType.lastIndexOf("/") + 1);
		}
		return url;
	}
	
	/**
	 * @param data 要保存的字节数组
	 * @param filePath 保存文件的相对路径，设置为根目录下的temp文件夹下
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
			System.out.println("请检查你的http地址是否正确");
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
