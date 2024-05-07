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
		System.out.println(bytepost);
		
		Team Person = new Team("Person", "Person", uriBase+"/Person");
		String personpost = client.post()
				.uri(uriBase+"/Person")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Person)
				.retrieve()
				.body(String.class);
		System.out.println(personpost);
		
		Team Company = new Team("Company", "Company", uriBase+"/Company");
		String teampost = client.post()
				.uri(uriBase+"/Company")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Company)
				.retrieve()
				.body(String.class);
		System.out.println(teampost);
		
		Team Project = new Team("Project", "Project", uriBase+"/Project");
		String projectpost = client.post()
				.uri(uriBase+"/Project")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Project)
				.retrieve()
				.body(String.class);
		System.out.println(projectpost);
		
		Team JobPosting = new Team("JobPosting", "JobPosting", uriBase+"/JobPosting");
		String jobpost = client.post()
				.uri(uriBase+"/JobPosting")
				.contentType(MediaType.APPLICATION_JSON)
				.body(JobPosting)
				.retrieve()
				.body(String.class);
		System.out.println(jobpost);
		
		Team News = new Team("News", "News", uriBase+"/News");
		String newspost = client.post()
				.uri(uriBase+"/News")
				.contentType(MediaType.APPLICATION_JSON)
				.body(News)
				.retrieve()
				.body(String.class);
		System.out.println(newspost);
		
		Team Skill = new Team("Skill", "Skill", uriBase+"/Skill");
		String skillpost = client.post()
				.uri(uriBase+"/Skill")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Skill)
				.retrieve()
				.body(String.class);
		System.out.println(skillpost);
	}
}
