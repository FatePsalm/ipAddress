package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * @author 作者 Name:CaoGang
 * @version 创建时间：2018年3月30日 下午3:07:15 类说明
 *          TCP编程案列三,客户端文件给服务端发送信息,服务端将保存,同时发送"已收到信息"给客户端
 */
public class TestTcp3 {
	// 客户端
	@Test
	public void client() {
		Socket socket = null;
		OutputStream os = null;
		FileInputStream fis=null;
		InputStream is = null;
		try {
			socket = new Socket("127.0.0.1", 9090);
			os = socket.getOutputStream();
			 fis = new FileInputStream(new File("1.jpg"));
			byte[] b = new byte[1024];
			int len;
			while ((len = fis.read(b)) != -1) {
				os.write(b, 0, len);
			}
			socket.shutdownOutput();
			is = socket.getInputStream();
			byte[] b1 = new byte[40];
			int len1;
			while ((len1 = is.read(b1)) != -1) {
				String str = new String(b1, 0, len1);
				System.out.println(str);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 服务端
	@Test
	public void server() {
		ServerSocket ss = null;
		Socket s = null;
		FileOutputStream fos=null;
		InputStream is = null;
		try {
			ss = new ServerSocket(9090);
			s = ss.accept();
			is = s.getInputStream();
			 fos = new FileOutputStream(new File("2.jpg"));
			byte[] b = new byte[1024];
			int len;
			while ((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			OutputStream os = s.getOutputStream();
			os.write("发送成功!".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
