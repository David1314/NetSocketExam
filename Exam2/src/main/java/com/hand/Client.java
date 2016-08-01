package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket;  
    BufferedInputStream in;  
    BufferedOutputStream out;
    public Client(){
    	try {
			socket = new Socket("127.0.0.1", 8088);  
			
			in = new BufferedInputStream(socket.getInputStream());  
			out = new BufferedOutputStream(socket.getOutputStream());  
			FileInputStream fis=new FileInputStream("target.pdf");
			BufferedInputStream bis=new BufferedInputStream(fis);
			
			byte[] input =new byte[1024];
			while(bis.read(input)!=-1){
				out.write(input);
			}
			
			bis.close();
			fis.close();
			out.close();  
			in.close();  
			  
			socket.close();  
			System.out.println("文件发送成功！");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		new Client();
	}

}
