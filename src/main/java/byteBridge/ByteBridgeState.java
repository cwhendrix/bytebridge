package byteBridge;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ByteBridgeState
{
	private record Response(String request, 
			boolean successful, 
			String message, 
			JsonNode data) {};
	
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
	
	//// REST Server Functions: Retrieving objects via ID
	// All return null if object not found
	public Person retrievePerson(String id) {
		Response retrieved = serverhandler.client.get()
				.uri("http://localhost:9000/v1/byteBridge/Person/"+id)
				.retrieve()
				.body(Response.class);
		System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		Person newPerson = null;
		try
		{
			newPerson = objectmapper.treeToValue(retrieved.data, Person.class);
			return newPerson;
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Company retrieveCompany(String id) {
		Response retrieved = serverhandler.client.get()
				.uri("http://localhost:9000/v1/byteBridge/Company/"+id)
				.retrieve()
				.body(Response.class);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			Company newCompany = objectmapper.treeToValue(retrieved.data, Company.class);
			//System.out.println(newCompany.id);
			return newCompany;
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public JobPosting retrieveJob(String id) {
		Response retrieved = serverhandler.client.get()
				.uri("http://localhost:9000/v1/byteBridge/JobPosting/"+id)
				.retrieve()
				.body(Response.class);
		ObjectMapper objectmapper = new ObjectMapper();
		try
		{
			JobPosting newJob = objectmapper.treeToValue(retrieved.data, JobPosting.class);
			return newJob;
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public News retrieveNews(String id) {
		Response retrieved = serverhandler.client.get()
				.uri("http://localhost:9000/v1/byteBridge/News/"+id)
				.retrieve()
				.body(Response.class);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			News newNews = objectmapper.treeToValue(retrieved.data, News.class);
			return newNews;
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Project retrieveProject(String id) {
		Response retrieved = serverhandler.client.get()
				.uri("http://localhost:9000/v1/byteBridge/Project/"+id)
				.retrieve()
				.body(Response.class);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			Project newProject = objectmapper.treeToValue(retrieved.data, Project.class);
			return newProject;
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Skill retrieveSkill(String id) {
		Response retrieved = serverhandler.client.get()
				.uri("http://localhost:9000/v1/byteBridge/Skill/"+id)
				.retrieve()
				.body(Response.class);
		System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			Skill newSkill = objectmapper.treeToValue(retrieved.data, Skill.class);
			return newSkill;
		} catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	////Convenience function to retrieve all the users from the REST Server
	public ArrayList<Person> retrieveAllUsers() {
		ArrayList<Person> users = new ArrayList<Person>();
		Person tempPerson;
		for (int i=0; i<AllUsers.size(); i++) {
			tempPerson = retrievePerson(Integer.toString(i));
			if (tempPerson != null) {
				users.add(tempPerson);
			}
		}
		return users;
	}

}
