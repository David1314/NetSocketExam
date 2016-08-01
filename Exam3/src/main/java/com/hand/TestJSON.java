package com.hand;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestJSON {

	public static void main(String[] args) {
		new Test2().start();
	}

}
class Test2 extends Thread{
	HttpClient client = HttpClients.createDefault();
	@Override
	public void run() {
		HttpGet get = new HttpGet("http://hq.sinajs.cn/list=sz300170");
		
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String result =EntityUtils.toString(entity,"UTF-8");
			
			JsonParser parser= new JsonParser();
			JsonObject object = (JsonObject) parser.parse(result);
			System.out.println("name"+object.get("name").getAsString());
//			String content = "this is text content..";
//	        File file = new File("xml.xml");
//	           
//	         if(!file.exists()){
//	             file.createNewFile();
//	         }
//			 FileWriter fw = new FileWriter(file.getName());
//	         BufferedWriter bw = new BufferedWriter(fw);
//	         bw.write(content);
//	         bw.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
