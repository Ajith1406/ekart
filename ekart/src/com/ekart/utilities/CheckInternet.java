package com.ekart.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CheckInternet {
	
	public static boolean isConnected() {
		try {
	         URL url = new URL("http://www.google.com");
	         URLConnection connection = url.openConnection();
	         connection.connect();
	         System.out.println("Internet is connected");
	         return true;
	      } catch (MalformedURLException e) {
	         System.out.println("Internet is not connected");
	      } catch (IOException e) {
	         System.out.println("Internet is not connected");
	      }
		return false;
	}
	
//	public static void main(String[] args) {
//		System.out.println(isConnected());
//	}

}
