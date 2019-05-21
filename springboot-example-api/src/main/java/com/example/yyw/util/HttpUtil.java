package com.example.yyw.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/7 16:25
 * @describe
 */
public class HttpUtil {
	
	public static String httpGet(String url) {
		CloseableHttpClient httpclient = null;
		HttpGet httpget = new HttpGet(url);
		String result = "";
		try {
			httpclient = HttpClients.custom().build();
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
            if (entity != null) {
            	result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }  
		return result;
	}

	public static String httpGet(String url, Map<String,String> headers) {
		CloseableHttpClient httpclient = null;
		HttpGet httpget = new HttpGet(url);
		if (headers!=null){
			Set<String> keys=headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();){
				String key=(String)i.next();
				httpget.addHeader(key,headers.get(key));
			}
		}
		String result = "";
		try {
			httpclient = HttpClients.custom().build();
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String httpPost(String url, Map<String, String> params) {
		CloseableHttpClient httpclient = null;
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			param.add(new BasicNameValuePair(key, val));
		}
		String result = "";
		try {
			httpclient = HttpClients.custom().build();
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(param, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
            if (entity != null) {
            	result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }  
		return result;
	}
}
