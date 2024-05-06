package byteBridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import byteBridge.Entity.Entities;

public class Project extends Entity
{
	private record Response(String request, 
			boolean successful, 
			String message, 
			JsonNode data) {};
			
	public Project() {}
	
	public Project(String id, String name, Page page)
	{
		this.id = id;
		this.name = name;
		this.page = page;
		links = new HashMap<Entities, ArrayList<String>>();
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
	public String updateData(RestClient client) {
		String projectpost = client.put()
				.uri("http://localhost:9000/v1/byteBridge/Project/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return projectpost;
	}
	public void retrieveData(RestClient client) {
		Response retrieved = client.get()
				.uri("http://localhost:9000/v1/byteBridge/Project/"+this.id)
				.retrieve()
				.body(Response.class);
		System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			Project newProject = objectmapper.treeToValue(retrieved.data, Project.class);
			//System.out.println(newCompany.id);
			this.setId(newProject.getId());
			this.setName(newProject.getName());
			this.setLinks(newProject.getLinks());
			this.setPage(newProject.getPage());
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
