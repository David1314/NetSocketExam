package com.hand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonObject;



public class TestJSON {

	public static void main(String[] args) {
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
			
			String str=builder.toString();
			String[] s=str.split("\"");
			String result = s[1];
//			System.out.println(result);
			
			String[] str_info = result.split(",");
//			for (int i = 0; i < str_info.length; i++) {
//				System.out.println(str_info[i]);
//			}
			JsonObject object = new JsonObject();
			object.addProperty("name", str_info[0]);
			object.addProperty("open", str_info[2]);
			object.addProperty("close", str_info[3]);
			object.addProperty("current", str_info[4]);
			object.addProperty("high", str_info[5]);
			object.addProperty("low", str_info[6]);
			
			System.out.println(object.toString());
			
			File file=new File("hand.json");
			FileOutputStream fos=new FileOutputStream(file);
			byte output[]=object.toString().getBytes("UTF-8");
			fos.write(output);
			fos.close();
			System.out.println("hand.json文件写入成功！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
