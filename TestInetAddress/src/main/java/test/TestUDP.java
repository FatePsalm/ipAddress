package test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

/**
 * @author 作者 Name:CaoGang
 * @version 创建时间：2018年3月30日 下午5:15:39 类说明
 */
public class TestUDP {
	//客户端
	@Test
	public void send() {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket();
			byte[] b = "你好,我是要发送的数据".getBytes();
			// 创建一个数据报:每一个数据报不能大于64k,都记录着数据信息,发送端的IP.端口号,以及要发送
			// 的接收端的IP.端口号.
			DatagramPacket pack = new DatagramPacket(b, 0, b.length, InetAddress.getByName("127.0.0.1"), 9090);
			//发送
			ds.send(pack);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (ds != null) {
				ds.close();
			}
		}
	}
	//服务器端
	@Test
	public void rceive() {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(9090);
			byte[] b = new byte[1024];
			DatagramPacket pack = new DatagramPacket(b, 0, b.length);
			//接收
			ds.receive(pack);
			//String str = new String(pack.getData(), 0, pack.getLength());
			String str = new String(b);
			System.out.println(str);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ds.close();
		}
	}
}
