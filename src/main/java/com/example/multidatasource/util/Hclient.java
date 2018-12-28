package com.example.multidatasource.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: HttpClientUtil
 * @Description: TODO doGet/doPost
 * @author wy
 * @date 2018年12月10日 下午3:59:57
 */
public class Hclient {

	/**
	 * 编码格式。发送编码格式统一用UTF-8
	 */
	private static final String ENCODING = "UTF-8";
	
	/**
	 * @Title: doPost 
	 * @Description: TODO Post无参请求 
	 * @param: @param url
	 * @param: @return
	 * @param: @throws ParseException
	 * @param: @throws IOException
	 * @return: String
	 * @throws
	 */
	public static String doPost(String url) throws ParseException, IOException {
		return doPost(url, null);
	}
	
	/**
	 * @Title: doJsonPost 
	 * @Description: TODO JSON格式Post请求
	 * @param: @param url
	 * @param: @param json
	 * @param: @return
	 * @param: @throws ParseException
	 * @param: @throws IOException
	 * @return: String
	 * @throws
	 */
	public static String doJsonPost(String url,JSONObject json) throws ParseException, IOException {
		//JSONObject转为Map
		Map<String, String> map = (Map)json;
		return doPost(url, map);
	}
	
	/**
	 * @Title: doPost 
	 * @Description: TODO Map<String, String>格式Post请求
	 * @param: @param url
	 * @param: @param map
	 * @param: @return
	 * @param: @throws ParseException
	 * @param: @throws IOException
	 * @return: String
	 * @throws
	 */
	public static String doPost(String url, Map<String, String> map) throws ParseException, IOException {
		String body = "";

		// 创建HttpClient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		// 装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));

		System.out.println("请求地址：" + url);
		System.out.println("请求参数：" + nvps.toString());

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		// 获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// 按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, ENCODING);
		}
		EntityUtils.consume(entity);
		// 释放链接
		response.close();
		client.close();
		return body;
	}

	
	/**
	 * @Title: doGet 
	 * @Description: TODO Get无参请求 
	 * @param: @param url
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * @Title: doGet 
	 * @Description: TODO Map<String, String>格式Get请求
	 * @param: @param url
	 * @param: @param param
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String doGet(String url, Map<String, String> param) {

		// 创建HttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建URI
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), ENCODING);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
}
