package Net;

import java.net.*;
import java.io.*;

public class server {
	private ServerSocket srv_sck;

	public server() throws Exception {
		srv_sck = new ServerSocket(60666);
		System.setProperty("line.separator", "\r\n");
	}

	public void receive() throws Exception {
		Socket client = srv_sck.accept();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			out.println("OK");
			break;
		}
	}
}
