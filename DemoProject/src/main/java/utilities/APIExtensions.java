package utilities;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import io.cucumber.java.it.Date;
import utilities.APIExtensions;

public class APIExtensions {
	
	public String apiSetupandExecute(String apiKey,String apiUri,String amtValue,String idVal,String sourceCurrency,Date dateVal,String destCurrency) {
	
		List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
		String result="";
		 paratmers.add(new BasicNameValuePair("amount",amtValue));
		 if(idVal!=null)		
			 paratmers.add(new BasicNameValuePair("id",idVal));
		 if(sourceCurrency!=null)	
			    paratmers.add(new BasicNameValuePair("symbol",sourceCurrency));
		 paratmers.add(new BasicNameValuePair("convert",destCurrency));
		 if(dateVal!=null)		
			 paratmers.add(new BasicNameValuePair("time",dateVal.toString()));
		
			 try {
			    
			     result = makeAPICall(apiUri, paratmers,apiKey);
			   //   System.out.println(result);
			    } catch (IOException e) {
			      System.out.println("Error: cannont access content - " + e.toString());
			    } catch (URISyntaxException e) {
			      System.out.println("Error: Invalid URL " + e.toString());
			    }
			 return result;
	}

	 public static String makeAPICall(String uri, List<NameValuePair> parameters,String apiKey)  throws URISyntaxException, IOException {
		    String response_content = "";

		    URIBuilder query = new URIBuilder(uri);
		    query.addParameters(parameters);

		    CloseableHttpClient client = HttpClients.createDefault();
		    HttpGet request = new HttpGet(query.build());

		    request.setHeader(HttpHeaders.ACCEPT, "application/json");
		    request.addHeader("X-CMC_PRO_API_KEY", apiKey);

		    CloseableHttpResponse response = client.execute(request);

		    try {
		   //   System.out.println(response.getStatusLine());
		      HttpEntity entity = response.getEntity();
		      response_content = EntityUtils.toString(entity);
		      EntityUtils.consume(entity);
		    } finally {
		      response.close();
		    }

		    return response_content;
		  }
	 
	 
	
}
