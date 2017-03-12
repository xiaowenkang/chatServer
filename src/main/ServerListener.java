package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {

	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			while (true) {
				Socket socket = serverSocket.accept();// 阻塞的block
				// JOptionPane.showMessageDialog(null, "有客户端已经连接到12345端口");

				ChatSocket cs = new ChatSocket(socket);
				cs.out("你已经连接上12345端口服务器了\n");
				cs.start();
				ChatManage.getCM().add(cs);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
