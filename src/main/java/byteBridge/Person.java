package byteBridge;
import java.util.ArrayList;
import java.util.Hashtable;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

public class Person extends Entity
{
	String occupation;
	
	public Person() {}
	
	private record Response(String request, 
			boolean successful, 
			String message, 
			JsonNode data) {};
	
	public Person(String id, String name, Page page, String occupation)
	{
		this.id = id;
		this.name = name;
		this.page = page;
		this.occupation = occupation;
		links = new Hashtable<Entities, ArrayList<String>>();
		for (Entities link : Entities.values()) { 
			ArrayList<String> temp = new ArrayList<String>();
			links.put(link, temp);
		}
	}
	
	public void addSkill(Skill newSkill) {
		ArrayList<String> skills = links.get(Entities.SKILL);
		skills.add(newSkill.id);
	}
	public void removeSkill(Skill remSkill) {
		ArrayList<String> skills = links.get(Entities.SKILL);
		skills.remove(remSkill.id);
	}
	public void addProject(Project newProject) {
		ArrayList<String> projects = links.get(Entities.PROJECT);
		projects.add(newProject.id);
	}
	public void removeProject(Project remProject) {
		ArrayList<String> projects = links.get(Entities.PROJECT);
		projects.remove(remProject.id);
	}
	public void apply(JobPosting job) {
		job.addApplicant(this.id);
	}
	public void post(News newPost) {
		ArrayList<String> posts = links.get(Entities.NEWS);
		posts.add(newPost.id);
	}
	public void setEmployer(Company newCompany) {
		ArrayList<String> employers = links.get(Entities.EMPLOYER);
		employers.add(newCompany.id);
	}
	public void followPerson(Person newPerson) {
		ArrayList<String> following = links.get(Entities.FOLLOWING);
		following.add(newPerson.id);
	}
	public void followCompany(Company newCompany) {
		ArrayList<String> following = links.get(Entities.FOLLOWING);
		following.add(newCompany.id);
	}
	public String storeData(RestClient client) {
		String personpost = client.post()
			.uri("http://localhost:9000/v1/byteBridge/Person/"+this.id)
			.contentType(MediaType.APPLICATION_JSON)
			.body(this)
			.retrieve()
			.body(String.class);
		return personpost;
	}
	public void retrieveData(RestClient client) {
		Response retrieved = client.get()
				.uri("http://localhost:9000/v1/byteBridge/Person/"+this.id)
				.retrieve()
				.body(Response.class);
		System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			//Person newPerson = objectmapper.treeToValue(retrieved.data, Person.class);
			Person newPerson = objectmapper.readValue(retrieved.data.asText(), Person.class);
			System.out.println(newPerson.id);
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

	/**
	 * @return the occupation
	 */
	public String getOccupation()
	{
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation)
	{
		this.occupation = occupation;
	}

}
