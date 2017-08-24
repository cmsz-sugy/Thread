package httpclient;

import java.io.IOException;  

import org.apache.http.HttpEntity;  
import org.apache.http.HttpHost;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.protocol.HttpClientContext;  
import org.apache.http.conn.routing.HttpRoute;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;  
import org.apache.http.protocol.HttpContext;  
  
@SuppressWarnings("unused")  
public class HttpClientManyThread {  
    private static void manyThreadHttpRequest() throws Exception {  
        //httpclient线程池管理器  
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();  
        //最大连接数  
        connManager.setMaxTotal(1);  
        //设置每个路由连接数  
        connManager.setDefaultMaxPerRoute(1);  
        //针对的主机  
        HttpHost host = new HttpHost("https://www.baidu.com");  
        //每个路由映射的最大的连接数  
        connManager.setMaxPerRoute(new HttpRoute(host), 2);  
        //创建httpclisent连接  
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();  
  
        String[] urls = { "https://www.baidu.com","https://www.baidu.com","https://www.baidu.com","https://www.baidu.com","https://www.baidu.com"};  
  
        ThreadGroupContext[] threadGroupContexts = new ThreadGroupContext[5];  
  
        for (int i = 0; i < threadGroupContexts.length; i++) {  
            HttpGet httpget = new HttpGet(urls[i]);  
            threadGroupContexts[i] = new ThreadGroupContext(httpClient, httpget);  
        }  
  
        for (int j = 0; j < threadGroupContexts.length; j++) {  
            threadGroupContexts[j].start();  
        }  
  
        for (int j = 0; j < threadGroupContexts.length; j++) {  
            threadGroupContexts[j].join();  
        }  
  
    }  
    public static void main(String[] args) throws Exception {  
        manyThreadHttpRequest();  
    }  
  
  
}  
