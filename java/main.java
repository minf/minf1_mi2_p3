import Net.*;

class main {

	public static void main(String[] args) throws Exception {
		Net.server p = new Net.server();

		System.out.println("Starting");

		while(true) {
			p.receive();
		}
	}
}
