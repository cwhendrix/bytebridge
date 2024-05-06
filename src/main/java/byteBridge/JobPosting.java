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

public class JobPosting extends Entity
{

	JobRecommender recommender;
	private JobPosting() {}
	
	private record Response(String request, 
			boolean successful, 
			String message, 
			JsonNode data) {};
			
	public JobPosting(String id, String name, Page page)
	{
		this.id = id;
		this.name = name;
		this.page = page;
		links = new HashMap<Entities, ArrayList<String>>();
		for (Entities link : Entities.values()) { 
			ArrayList<String> temp = new ArrayList<String>();
			links.put(link, temp);
		}
		recommender = new JobRecommender(this);
	}
	public void addApplicant(String applicant) {
		ArrayList<String> applicants = links.get(Entities.APPLICANTS);
		applicants.add(applicant);
	}
	public void addSkills(String skill) {
		ArrayList<String> skills = links.get(Entities.SKILL);
		skills.add(skill);
	}
	public String storeData(RestClient client) {
		String jobpost = client.post()
				.uri("http://localhost:9000/v1/byteBridge/JobPosting/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return jobpost;
	}
	public String updateData(RestClient client) {
		String jobpost = client.put()
				.uri("http://localhost:9000/v1/byteBridge/JobPosting/"+this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this)
				.retrieve()
				.body(String.class);
			return jobpost;
	}
	public void retrieveData(RestClient client) {
		Response retrieved = client.get()
				.uri("http://localhost:9000/v1/byteBridge/JobPosting/"+this.id)
				.retrieve()
				.body(Response.class);
		System.out.println(retrieved.data);
		ObjectMapper objectmapper = new ObjectMapper();
		
		try
		{
			JobPosting newJob = objectmapper.treeToValue(retrieved.data, JobPosting.class);
			//System.out.println(newCompany.id);
			this.setId(newJob.getId());
			this.setName(newJob.getName());
			this.setLinks(newJob.getLinks());
			this.setPage(newJob.getPage());
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
	private void setRecommender(JobRecommender recommender) {
		this.recommender = recommender;
	}
	private JobRecommender getRecommender() {
		return this.recommender;
	}
}
