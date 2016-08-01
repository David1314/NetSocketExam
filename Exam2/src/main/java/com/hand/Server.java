package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket ss;  
    private Socket socket;  
    private BufferedInputStream in;  
    private BufferedOutputStream out; 
    
    public Server(){
    	try {
			ss = new ServerSocket(8088);  
			System.out.println("it is waiting for client to recieve!");  
			socket = ss.accept();  
			in = new BufferedInputStream(socket.getInputStream());  
			out = new BufferedOutputStream(socket.getOutputStream()); 
			FileOutputStream fos=new FileOutputStream("target_new.pdf");
			BufferedOutputStream bis=new BufferedOutputStream(fos);
			    
			byte[] input =new byte[1000];
			while(in.read(input)!=-1){
					bis.write(input);
			}
				
			bis.close();
			fos.close();
			out.close();  
			in.close();  
			socket.close();
			ss.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
	public static void main(String[] args) {
		
	    new Server();
	    
	}

}
