package byteBridge;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class News extends Entity
{
	private record Response(String request, 
			boolean successful, 
			String message, 
			JsonNode data) {};
	
	public News() {}
			
	public News(String id, String name, Page page)
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
	@Override
	public String storeData(RestClient client) {
		String newspost = client.post()
				.uri("http://localhost:9000/v1/byteBridge/News/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return newspost;
	}
	@Override
	public String updateData(RestClient client) {
		String newspost = client.put()
				.uri("http://localhost:9000/v1/byteBridge/News/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return newspost;
	}
	@Override
	public void retrieveData(RestClient client) {
		Response retrieved = client.get()
				.uri("http://localhost:9000/v1/byteBridge/News/"+this.id)
				.retrieve()
				.body(Response.class);
		//System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			News newNews = objectmapper.treeToValue(retrieved.data, News.class);
			//System.out.println(newCompany.id);
			this.setId(newNews.getId());
			this.setName(newNews.getName());
			this.setLinks(newNews.getLinks());
			this.setPage(newNews.getPage());
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
