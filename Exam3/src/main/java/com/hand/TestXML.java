package com.hand;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
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

public class TestXML {

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
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder2=factory.newDocumentBuilder();
			Document document= builder2.newDocument();
			Element root= document.createElement("stock");
			
			Element name=document.createElement("name");
			name.setTextContent(str_info[0]);
			Element open=document.createElement("open");
			open.setTextContent(str_info[1]);
			Element close=document.createElement("close");
			close.setTextContent(str_info[2]);
			Element current=document.createElement("current");
			current.setTextContent(str_info[3]);
			Element high=document.createElement("high");
			high.setTextContent(str_info[4]);
			Element low=document.createElement("low");
			low.setTextContent(str_info[5]);
			
			root.appendChild(name);
			root.appendChild(open);
			root.appendChild(close);
			root.appendChild(current);
			root.appendChild(high);
			root.appendChild(low);
			
			document.appendChild(root);
			
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("encoding", "UTF-8");


			transformer.transform(new DOMSource(document), new StreamResult(new File("hand.xml")));
			System.out.println("hand.xml文件写入成功！");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
