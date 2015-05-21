package com.lancer.ecomm.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import android.util.Log;

public class ServerUtil {
	
	public static final String GET_METHOD = "GET";
	public static final String POST_METHOD = "POST";

	public static String serverGet(final String requestURL) throws IOException {
		String strResponse = "";
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			final URL url = new URL(requestURL);
			con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod(GET_METHOD);
			int code = con.getResponseCode();
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputline;
			final StringBuilder sbResponse = new StringBuilder();
			
			while((inputline = in.readLine()) != null) {
				sbResponse.append(inputline);
			}
			
			strResponse = sbResponse.toString();
			Log.e("asdf", "GET Response: " + strResponse);
			Log.e("asdf", "GET code: " + code);
		} catch(IOException e) {
			throw e;
		} finally {
			if(in != null) {
				in.close();
			}
			if(con != null) {
				con.disconnect();
			}
		}
		return strResponse;
	} // end serverGet
	
	public static String serverPost(final String requestURL, final HashMap<String, String> urlParams) throws IOException {
		String strResponse = "";
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			final URL url = new URL(requestURL);
			final StringBuilder sbUrlParams = new StringBuilder();
			final Iterator<Map.Entry<String,String>> it = urlParams.entrySet().iterator();
			Map.Entry<String,String> pair = null;
			while(it.hasNext()) {
				pair = it.next();
				sbUrlParams.append(pair.getKey());
				sbUrlParams.append("=");
				sbUrlParams.append(pair.getValue());
				sbUrlParams.append("&");
				it.remove();
			}
			sbUrlParams.setLength(sbUrlParams.length()-1); // remove excess "&"
			final String strUrlParams = sbUrlParams.toString();
			con = (HttpsURLConnection) url.openConnection();
			
			con.setRequestMethod(POST_METHOD);
			con.setDoOutput(true);
			final DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(strUrlParams);
			out.flush();
			out.close();
			int code = con.getResponseCode();
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputline;
			final StringBuilder sbResponse = new StringBuilder();
			while( (inputline = in.readLine()) != null ) {
				sbResponse.append(inputline);
			}
			
			strResponse = sbResponse.toString();
			
			Log.e("asdf", "POST response: " + strResponse);
			Log.e("asfd", "POST code: " + code);
		} catch(IOException e) {
			throw e;
		} finally {
			if(in != null) {
				in.close();
			}
			if(con != null) {
				con.disconnect();
			}
		}
		return strResponse;
	} // end serverPost
	
	public static String sendDirect(final String requestURL) throws IOException {
		String strResponse = "";
		BufferedReader in = null;
		try {
			final URL url = new URL(requestURL);
			
			in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			String inputline;
			final StringBuilder sbResponse = new StringBuilder();
			while( (inputline = in.readLine()) != null ) {
				sbResponse.append(inputline);
			}
			
			strResponse = sbResponse.toString();
			
			Log.e("asdf", "OTHER response: " + strResponse);
		} catch(IOException e) {
			throw e;
		} finally {
			if(in != null) {
				in.close();
			}
		}
		return strResponse;
	} // end sendDirect
	
} // end ServerUtil