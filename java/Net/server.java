package Net;

import java.net.*;
import java.io.*;

public class server {
	private ServerSocket srv_sck;

	public server() throws Exception {
		srv_sck = new ServerSocket(60666);
		System.setProperty("line.separator", "\r\n");
	}

	private int checkError(String input) {
		if (input.length() < 3) return 1;
		else if (!input.substring(0,3).equals("GET")) return 1;
		return 0;
	}

	private String getList(String[] input) {
		System.out.println(java.util.Arrays.toString(input));
		return "eee,fff,ggg";
	}

	private String getResponse(String input) {
		int err = checkError(input);

		if (err != 0) {
			return "ERR " + err;
		}
		
		return "OK " + getList(input.split(","));
	}

	public void receive() throws Exception {
		Socket client = srv_sck.accept();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			out.println(getResponse(line));
		}
	}

	public Thread receiveStart() {
		Thread t;

		t = new Thread()
		{
			public void run() {
				while(true) {
					try {
						receive();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();

		return t;
	}
}
