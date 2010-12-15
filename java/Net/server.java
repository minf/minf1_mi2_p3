package Net;

import java.net.*;
import java.io.*;
import java.util.*;
import Rules.*;

public class server {
	private ServerSocket srv_sck;
	private RuleEngine pRuleEngine;

	public server(RuleEngine pRuleEngine) throws Exception {
		srv_sck = new ServerSocket(60666);
		System.setProperty("line.separator", "\r\n");
		this.pRuleEngine = pRuleEngine;
		srv_sck.setSoTimeout(1000); // timeout to 1s
	}

	private int checkError(String input) {
		if (input.length() < 3) return 1;
		else if (!input.substring(0,3).equals("GET")) return 1;
		else if (input.length() > 3 && !input.substring(0,4).equals("GET ")) return 1;
		return 0;
	}

	/* "Fine, Java MIGHT be a good example of what a programming language should be like.
	 * But Java applications are good examples of what applications SHOULDN'T be like." - pixadel
	 */
	private String getList(String sIn) {
		String[] aIn;
		ArrayList<String>lOut;
		StringBuilder s = new StringBuilder();

		if (sIn.length() > 4) {
			// parse input list to integer list
			aIn = sIn.substring(4).split(",");
			// calculate new list
			lOut = pRuleEngine.calculate(Arrays.asList(aIn));
		} else {
			// send back full list
			lOut = pRuleEngine.getAll();
		}

		// parse back to string output
		for (int i=0; i<lOut.size(); i++) {
			s.append(lOut.get(i));
			if (i<lOut.size()-1) s.append(",");
		}
		return s.toString();
	}

	private String getResponse(String input) {
		int err = checkError(input);

		if (err != 0) {
			return "ERR " + err;
		}
		
		return "OK " + getList(input);
	}

	public void receive() throws Exception {
		Socket client = srv_sck.accept();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			//System.out.println(line);
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
					} catch (java.net.SocketTimeoutException e) {
						// its ok
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
