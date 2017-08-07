package mx.com.simulasensores.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

public class SimulaSensoresCliente {

	public static void main(String args []){
		
		try {
				  System.out.println("INICIA LECTURA SENSORES "+new Date().toString());
			
					//Lecturas Aleatorias 
				
					
					Random r1 = new Random();
					int Low1 = 15;
					int High1 = 31;
					int lecturaSensor1 = r1.nextInt(High1-Low1) + Low1;
					
					Random r2 = new Random();
					int Low2 = 15;
					int High2 = 41;
					int lecturaSensor2 = r2.nextInt(High2-Low2) + Low2;
					
					Random r3 = new Random();
					int Low3 = 60;
					int High3 = 121;
					int lecturaSensor3 = r3.nextInt(High3-Low3) + Low3;
			
					// create HTTP Client
					HttpClient httpClient = HttpClientBuilder.create().build();
					
					
					//SENSOR TEMPERATURA
					//URL para simulacion de sensor de temperatura
					HttpPost getRequest = new HttpPost("http://52.14.237.8:8282/SistemaCentralizado_IoT/lecturaSensores/reciveLectura?idSensor=1&lecturaSensor="+lecturaSensor1);
		 
					// Add additional header to getRequest which accepts application/xml data
					getRequest.addHeader("accept", "application/json");
		 
					// Execute your request and catch response
					HttpResponse response = httpClient.execute(getRequest);
		 
					// Check for HTTP response code: 200 = success
					if (response.getStatusLine().getStatusCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
					}
		 
					// Get-Capture Complete application/xml body response
					BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
					String output;
					System.out.println("============Output Sensor 1:============");
		 
					// Simply iterate through XML response and show on console.
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}
					
					// SENSOR HUMEDAD
					//URL para simulacion de sensor de Humedad
					HttpPost getRequest2 = new HttpPost("http://52.14.237.8:8282/SistemaCentralizado_IoT/lecturaSensores/reciveLectura?idSensor=2&lecturaSensor="+lecturaSensor2);
		 
					// Add additional header to getRequest which accepts application/xml data
					getRequest2.addHeader("accept", "application/json");
		 
					// Execute your request and catch response
					HttpResponse response2 = httpClient.execute(getRequest2);
		 
					// Check for HTTP response code: 200 = success
					if (response2.getStatusLine().getStatusCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + response2.getStatusLine().getStatusCode());
					}
		 
					// Get-Capture Complete application/xml body response
					BufferedReader br2 = new BufferedReader(new InputStreamReader((response2.getEntity().getContent())));
					String output2;
					System.out.println("============Output Sensor 2:============");
		 
					// Simply iterate through XML response and show on console.
					while ((output2 = br2.readLine()) != null) {
						System.out.println(output2);
					}
					
					
					
					//SENSOR CO2
					
					//URL para simulacion de sensor de CO2
					HttpPost getRequest3 = new HttpPost("http://52.14.237.8:8282/SistemaCentralizado_IoT/lecturaSensores/reciveLectura?idSensor=3&lecturaSensor="+lecturaSensor3);
		 
					// Add additional header to getRequest which accepts application/xml data
					getRequest3.addHeader("accept", "application/json");
		 
					// Execute your request and catch response
					HttpResponse response3 = httpClient.execute(getRequest3);
		 
					// Check for HTTP response code: 200 = success
					if (response3.getStatusLine().getStatusCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + response3.getStatusLine().getStatusCode());
					}
		 
					// Get-Capture Complete application/xml body response
					BufferedReader br3 = new BufferedReader(new InputStreamReader((response3.getEntity().getContent())));
					String output3;
					System.out.println("============Output Sensor 3:============");
		 
					// Simply iterate through XML response and show on console.
					while ((output3 = br3.readLine()) != null) {
						System.out.println(output3);
					}
		 
				} catch (ClientProtocolException e) {
					e.printStackTrace();
		 
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

}
