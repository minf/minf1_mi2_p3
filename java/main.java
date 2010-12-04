import Rules.*;
import Net.*;

class main {

	public static void main(String[] args) throws Exception {
		Rules.RuleEngine pEngine = new RuleEngine();
		Net.server pServer = new Net.server(pEngine);

		System.out.println("Starting");

		pServer.receiveStart();
	}
}
