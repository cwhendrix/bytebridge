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

public class Company extends Entity
{

	public Company () {}
	
	private record Response(String request, 
			boolean successful, 
			String message, 
			JsonNode data) {};
			
	public Company(String id, String name, Page page)
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
	public void addEmployee(Person employee) {
		ArrayList<String> employees = links.get(Entities.EMPLOYEES);
		employees.add(employee.id);
	}
	public void removeEmployee(Person remEmployee) {
		ArrayList<String> employees = links.get(Entities.EMPLOYEES);
		employees.remove(remEmployee.id);
	}
	public void addProject(Project newProject) {
		ArrayList<String> projects = links.get(Entities.PROJECT);
		projects.add(newProject.id);
	}
	public void removeProject(Project remProject) {
		ArrayList<String> projects = links.get(Entities.PROJECT);
		projects.remove(remProject.id);
	}
	public void addJobPosting(JobPosting newJob) {
		ArrayList<String> jobs = links.get(Entities.JOBPOSTING);
		jobs.add(newJob.id);
	}
	public void removeJobPosting(JobPosting remJob) {
		ArrayList<String> jobs = links.get(Entities.JOBPOSTING);
		jobs.remove(remJob.id);
	}
	public String storeData(RestClient client) {
		String companypost = client.post()
				.uri("http://localhost:9000/v1/byteBridge/Company/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return companypost;
	}
	public String updateData(RestClient client) {
		String companypost = client.put()
				.uri("http://localhost:9000/v1/byteBridge/Company/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return companypost;
	}
	public void retrieveData(RestClient client) {
		Response retrieved = client.get()
				.uri("http://localhost:9000/v1/byteBridge/Company/"+this.id)
				.retrieve()
				.body(Response.class);
		// System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			Company newCompany = objectmapper.treeToValue(retrieved.data, Company.class);
			//System.out.println(newCompany.id);
			this.setId(newCompany.getId());
			this.setName(newCompany.getName());
			this.setLinks(newCompany.getLinks());
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
	

