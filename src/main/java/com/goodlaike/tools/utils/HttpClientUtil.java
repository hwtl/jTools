package com.goodlaike.tools.utils;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ChaiKun,Huwei
 *
 */
public class HttpClientUtil {
	/**
	 * httpClient get方式，支持gzip
	 * @param url 请求的url地址
	 * @return String
	 */
	public static String doGet(String url) {
		return doGet(url,"");
	}
	/**
	 * httpClient get方式，支持gzip
	 * @param url 请求的url地址
	 * @param param 参数
	 * @return String
	 */
	public static String doGet(String url, String param){
		if(StringUtils.isBlank(url))
			return "404";
		if(StringUtils.isNotBlank(param))
			url += "?" + param;
		String backValue = "";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		solveGzip(httpClient);
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			//判断返回状态,如果没有返回值，直接返回		
			if (response.getStatusLine()==null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return "404";
			}
			HttpEntity entity = response.getEntity();
			backValue = EntityUtils.toString(entity,"UTF-8");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			httpClient.getConnectionManager().shutdown();
		}
		return backValue;
	}

	/**
	 * 增加response返回之前gzip的拦截
	 */
	public static void solveGzip(DefaultHttpClient httpClient){
		//增加response返回之前gzip的拦截
		httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
			   public void process(final HttpResponse response, final HttpContext context) throws HttpException,
			   IOException {
				  //获取内容编码
				   HttpEntity entity = response.getEntity();
					Header ceheader = entity.getContentEncoding();
					if (ceheader != null) {
						for (HeaderElement element : ceheader.getElements()) {
							if (element.getName().equalsIgnoreCase("gzip")) {
									response.setEntity(new GzipDecompressingEntity(response.getEntity()));
									return;
							}
					    }
					}
		}});
	}
    public static String doPost(String url, Map<String,String> params){
        if(StringUtils.isBlank(url))
            return "404";
        String backValue = "";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        solveGzip(httpClient);
        try {
            UrlEncodedFormEntity entity=null;
            if (params != null) {
                List<NameValuePair> nvs=new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> pair : params.entrySet()) {
                    nvs.add(new BasicNameValuePair(pair.getKey(),pair.getValue()));
                }
                entity=new UrlEncodedFormEntity(nvs,"utf-8");
            }
            HttpPost postMethod=new HttpPost(url);
            if (entity !=null) {
                postMethod.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(postMethod);
            HttpEntity data = response.getEntity();
            backValue = EntityUtils.toString(data);
        } catch (Throwable e) {
            backValue=e.toString();
        }
        finally{
        	httpClient.getConnectionManager().shutdown();
        }
        return backValue;
    }
    
    
    /**
	 * 
	 * 得到Http请求结果
	 * @param url 请求地址
	 * @param parms 请求参数
	 * @return
	 */
	public synchronized static String getBody(String url, Map<String, Object> parms) {
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestBody(buildNameValues(parms));
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			httpClient.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) 
				return postMethod.getResponseBodyAsString();
		} catch (org.apache.commons.httpclient.HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			postMethod.releaseConnection();
		}
		return null;
	}
	
	/**
	 * 组装Post数据
	 * @param parms
	 * @return
	 */
	private static org.apache.commons.httpclient.NameValuePair[] buildNameValues(Map<String, Object> parms){
		int index = 0;
		org.apache.commons.httpclient.NameValuePair[] data = new org.apache.commons.httpclient.NameValuePair[parms.size()];
		for (String key : parms.keySet()) {
			
			data[index] = new org.apache.commons.httpclient.NameValuePair(key, String.valueOf(parms.get(key)));
			index ++;
		}
		return data;
	}
}