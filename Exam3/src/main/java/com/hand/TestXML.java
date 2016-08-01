package com.hand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class TestXML {

	public static void main(String[] args) {
		new Test1().start();
	}

}
class Test1 extends Thread{
	@Override
	public void run() {
		try {
			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			URLConnection connection= url.openConnection();
			InputStream is =connection.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
			String line;
			StringBuilder builder = new StringBuilder();
			while((line=br.readLine())!=null){
				builder.append(line);
			}
			br.close();
			isr.close();
			is.close();
			
//			System.out.println(builder.toString());
			Document document=DocumentHelper.parseText(builder.toString().trim());
			String content=document.asXML();

		//	System.out.println(content);
			
			FileOutputStream fos=new FileOutputStream("xml.xml");
			
			byte output[]=content.getBytes("UTF-8");
			fos.write(output);
			fos.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
	}
}
