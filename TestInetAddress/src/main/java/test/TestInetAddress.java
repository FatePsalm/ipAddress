package test;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
* @author 作者 Name:CaoGang
* @version 创建时间：2018年3月30日 下午2:06:01
* 类说明
* 获取域名IP地址
*/
public class TestInetAddress {
	public static void main(String[] args) throws UnknownHostException {
		//获取远程服务器地址
		InetAddress byName = InetAddress.getByName("www.dingdangshifu.cn");
		System.out.println(byName);
		System.out.println(byName.getHostName());
		System.out.println(byName.getHostAddress());
		//获取本机地址
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost);
	}
}
