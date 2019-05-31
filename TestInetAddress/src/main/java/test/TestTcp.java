package test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * @author 作者 Name:CaoGang
 * @version 创建时间：2018年3月30日 下午2:18:58 类说明
 */
public class TestTcp {
	//网络编程实际上就是socket的编程
	@Test
	// 客户端
	public void Client() {
		Socket socket = null;
		OutputStream os = null;
		BufferedOutputStream bof=null;
		try {
			// 1.创建一个socket的对象,通过构造器指明服务器的地址,以及其接收程序的端口号
			socket = new Socket("127.0.0.1", 9090);
			// 2.getOutputStream();发送数据,方法返回OutputStream的对象
			os = socket.getOutputStream();
			new BufferedOutputStream(os);//缓冲流
			// 3.具体的输出过程
			os.write("我是客户端!".getBytes());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("链接失败!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4.关闭响应的流和socket
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	// 服务端
	public void Server() {
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		try {
			// 1.创建一个serversocket的对象,通过构造器指明自身的端口号
			ss = new ServerSocket(9090);
			// 2.调用accept()方法,返回一个Socket对象
			s = ss.accept();
			// 3.调用Socket对象的getInputStream()获取一个从客户端发送过来的输入流
			is = s.getInputStream();
			// 4.对获取的输入流进行操作
			byte[] b = new byte[20];
			int len;
			while ((len = is.read(b)) != -1) {
				String str = new String(b, 0, len);
				System.out.println(str);
			}
			System.out.println("收到的消息来自:"+s.getInetAddress().getHostAddress());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 5.关闭响应的流及socket.serversocket的对象 倒序关闭
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
