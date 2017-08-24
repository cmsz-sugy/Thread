package httpclient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class HttpGetTest {
	public static void main(String[] args) {
        HttpGet httpget = new HttpGet("https://www.baidu.com");
        try(CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpget);){
            System.out.printf("内容类型为：%s",response.getEntity().getContentType());
        }catch(Exception e){
            e.printStackTrace();
        }
}
}