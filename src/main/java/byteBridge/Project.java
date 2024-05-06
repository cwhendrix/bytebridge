package byteBridge;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import byteBridge.Entity.Entities;

public class Project extends Entity
{

	public Project(String id, String name, Page page)
	{
		this.id = id;
		this.name = name;
		this.page = page;
		links = new Hashtable<Entities, ArrayList<String>>();
		for (Entities link : Entities.values()) { 
			ArrayList<String> temp = new ArrayList<String>();
			links.put(link, temp);
		}
	}
	public String storeData(RestClient client) {
		String projectpost = client.post()
				.uri("http://localhost:9000/v1/byteBridge/Project/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return projectpost;
	}
	

}
