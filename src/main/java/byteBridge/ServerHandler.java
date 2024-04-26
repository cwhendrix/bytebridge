package byteBridge;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class ServerHandler
{
	RestClient client;
	String uriBase;
	public record Team(String name, 
			String description, 
			String location) {};
	
	public ServerHandler()
	{
		client = RestClient.create();
		uriBase = "http://localhost:9000/v1/byteBridge";
		Team byteBridge = new Team("byteBridge", "byteBridge", uriBase);
		String bytepost = client.post()
				.uri(uriBase)
				.contentType(MediaType.APPLICATION_JSON)
				.body(byteBridge)
				.retrieve()
				.body(String.class);
		
		Team Person = new Team("Person", "Person", uriBase+"/Person");
		String personpost = client.post()
				.uri(uriBase+"/Person")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Person)
				.retrieve()
				.body(String.class);
		
	}
	public void storeByteBridge() {
		
	}
	public void receiveByteBridge() {
		
	}
	public void storeEntity() {
		
	}
	public Entity retrieveEntity() {
		return null;
	}
	public void storeObject(Entity entity) {
		
	}
	public Entity retrieveObject() {
		return null;
	}
}
