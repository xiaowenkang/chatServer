package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {

	private Socket socket;

	public ChatSocket(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				ChatManage.getCM().publish(this, line);
			}
			reader.close();
			ChatManage.getCM().remove(this);
			System.out.println("断开了一个客户端连接");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			ChatManage.getCM().remove(this);
			System.out.println("断开了一个客户端连接");
		}
	}

	public void out(String out) {
		try {
			socket.getOutputStream().write(out.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
