package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private Properties properties;
	private final String propertyFilePath= "config//Configuration.properties";
	
	
	public ConfigReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}	
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public String getReportPath() {
		String path = properties.getProperty("reportTemplate");
		if(path != null) return path;
		else throw new RuntimeException("Template not specified in the Configuration.properties file.");
	}
	
	public String getApiKey() {
		String keyVal = properties.getProperty("apiKey");
		if(keyVal != null) return keyVal;
		else throw new RuntimeException("Api Key not specified in the Configuration.properties file.");
	}
	
	public String getApiUrl() {
		String apiUrl = properties.getProperty("apiUrl");
		if(apiUrl != null) return apiUrl;
		else throw new RuntimeException("Api Key not specified in the Configuration.properties file.");
	}
}
