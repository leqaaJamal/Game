package menna;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
		ServerSocket socketserver;
		Socket socketdserver;
		final BufferedReader in;
		final PrintWriter out;
		final Scanner sc=new Scanner(System.in);
		try {
			socketserver=new ServerSocket(4242);
			socketdserver=socketserver.accept();
			out=new PrintWriter(socketdserver.getOutputStream());
			in= new BufferedReader(new InputStreamReader(socketdserver.getInputStream()));
			Thread windows=new Thread(new Runnable() {
				String msg;
				@Override
				public void run() {
					while(true){
						msg=sc.nextLine();
						out.println(msg);
						out.flush();
					}
					
				}
			});
			windows.start();
			Thread windows1=new Thread(new Runnable() {
				String msg;
				@Override
				public void run() {
					while(true){
						try {
							msg=in.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Client : "+msg);
					}
					
				}
			});
			windows1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
