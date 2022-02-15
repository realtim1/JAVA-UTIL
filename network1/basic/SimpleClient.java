package network1.basic;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleClient {
	
	public final static int PORT = 5000;
	public final static String HOST = "192.168.0.9";	//내컴퓨터 찾기  "127.0.0.1"
	
	public static void main( String args[] ) {
		
		Socket client = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		
		// 콘솔 입력을 받기 위해
		Scanner stdin = new Scanner( System.in );
		

		try{ 
					
		// 1. 서버에 연결하기 위해 소켓 생성
			client = new Socket(HOST,PORT);
			
		// 2. 소켓의 입출력 스트림 얻기
			in =new DataInputStream(client.getInputStream());
			out= new DataOutputStream(client.getOutputStream());
		// 3. 키보드 입력 받아 서버에 전송
	System.out.println("짝꿍에게 할 말 =>");
	String msg = stdin.nextLine();

		out.writeUTF(msg);
		
		// 4. 서버로부터 응답을 받음
		String result =in.readUTF();
		System.out.println("짝꿍이 넘긴 말: " +result);
	
		
		// 5. 입출력 스트림의 닫음
			in.close();
			out.close();
			client.close();
			
			
		} catch ( Exception ex ) {
			System.out.println("Error writing.." + ex );	
		}
	}	
}