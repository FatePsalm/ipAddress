package test;
/**
* @author 作者 Name:CaoGang
* @version 创建时间：2018年3月30日 下午5:48:34
* 类说明
*同一资源定位符,一个URL的一个对象,对应着互联网上的一个资源
*我们可以通过URL的对象调用其相应的方法,将此资源读取("下载")
*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class TestURL {
	public static void main(String[] args) throws IOException {
		String string = "http://old.bz55.com/uploads/allimg/130329/1-130329115004.jpg";
		// 创建一个URL对象
		URL url = null;
		try {
			url = new URL(string);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("获取该URL的协议名");
		System.out.println(url.getProtocol());
		System.out.println("获取该URL的主机名");
		System.out.println(url.getHost());
		System.out.println("获取该URL的端口号");
		System.out.println(url.getPort());
		System.out.println("获取该URL的文件路径");
		System.out.println(url.getPath());
		System.out.println("获取该URL的文件名");
		System.out.println(url.getFile());
		System.out.println("获取该URL在文件中的相对位置");
		System.out.println(url.getRef());
		System.out.println("获取该URL的查询名");
		System.out.println(url.getQuery());
		// 如何将服务端的资源读取进来
		readInputStream(url);
		// 将文件资源读进来
		readURLConnection(url);
		System.out.println("执行完成!");
	}

	// 如果既有数据的输入,又有数据的输出,则考虑使用URLConnection
	public static void readURLConnection(URL url) throws IOException {
		URLConnection urlconn = url.openConnection();
		InputStream inputStream = urlconn.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File("3.jpg"));
		byte[] b1 = new byte[1024];
		int len1;
		while ((len1 = inputStream.read(b1)) != -1) {
			fos.write(b1, 0, len1);
		}
		fos.close();
		inputStream.close();
	}

	// 读取数据
	public static void readInputStream(URL url) {
		InputStream is = null;
		try {
			// 读取内容
			is = url.openStream();
			String string2 = IOUtils.toString(is, "UTF-8");
			System.out.println("string:" + string2);
		} catch (Exception e) {
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
