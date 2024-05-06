package byteBridge;

import java.util.ArrayList;

public class ByteBridgeState
{
	private static ByteBridgeState state = null;
	ServerHandler serverhandler;
	ArrayList<Person> AllUsers;
	
	private ByteBridgeState()
	{
		state = this;
		serverhandler = new ServerHandler();
		AllUsers = new ArrayList<Person>();
	}
	
	public static ByteBridgeState getInstance() {
		if (state == null) {
			state = new ByteBridgeState();
		}
		return state;
	}
	
	public void newPerson(Person user) {
		AllUsers.add(user);
	}
	
	///// Not an entity; the ByteBridgeState does not need to be stored on the server

}
