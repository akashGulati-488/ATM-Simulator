package atm.simulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

public class OTP {
	
	private int otp;
	
	public OTP() {
		try {
			// Construct data
			String apiKey = "apikey=" + "NzY1MTUzNjQzNjUxNTM1YTc5NGQzNjcyMzA1MTc0NzE=";
			Random rand = new Random();
			otp = rand.nextInt(9999);
			
			String message = "&message=" + otp + "is your ATM Simulator verification code";
			String sender = "&sender=" + "ATM Simulator";
			String numbers = "&numbers=" + "918123456789";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
//			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
//			return "Error "+e;
		}
	}
}
