package byteBridge;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class JobPosting extends Entity
{

	public JobPosting(String id, String name, Page page)
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
	public void addApplicant(String applicant) {
		ArrayList<String> applicants = links.get(Entities.APPLICANTS);
		applicants.add(applicant);
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
}
