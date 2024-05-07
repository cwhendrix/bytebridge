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

public class Skill extends Entity
{
	private record Response(String request, 
			boolean successful, 
			String message, 
			JsonNode data) {};
	
	public Skill() {}
	
	public Skill(String id, String name, Page page)
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
		String skillpost = client.post()
				.uri("http://localhost:9000/v1/byteBridge/Skill/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return skillpost;
	}
	public String updateData(RestClient client) {
		String skillpost = client.put()
				.uri("http://localhost:9000/v1/byteBridge/Skill/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return skillpost;
	}
	public void retrieveData(RestClient client) {
		Response retrieved = client.get()
				.uri("http://localhost:9000/v1/byteBridge/Skill/"+this.id)
				.retrieve()
				.body(Response.class);
		//System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			Skill newSkill = objectmapper.treeToValue(retrieved.data, Skill.class);
			//System.out.println(newCompany.id);
			this.setId(newSkill.getId());
			this.setName(newSkill.getName());
			this.setLinks(newSkill.getLinks());
			this.setPage(newSkill.getPage());
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
