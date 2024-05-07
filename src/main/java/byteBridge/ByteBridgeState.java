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
	ArrayList<Company> AllCompanies;
	ArrayList<JobPosting> AllJobs;
	ArrayList<News> AllNews;
	ArrayList<Project> AllProjects;
	ArrayList<Skill> AllSkills;
	
	private ByteBridgeState()
	{
		state = this;
		serverhandler = new ServerHandler();
		AllUsers = new ArrayList<Person>();
		AllCompanies = new ArrayList<Company>();
		AllJobs = new ArrayList<JobPosting>();
		AllNews = new ArrayList<News>();
		AllProjects = new ArrayList<Project>();
		AllSkills = new ArrayList<Skill>();
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
	public void newCompany(Company company) {
		AllCompanies.add(company);
	}
	public void newJobs(JobPosting job) {
		AllJobs.add(job);
	}
	public void newNews(News news) {
		AllNews.add(news);
	}
	public void newProject(Project project) {
		AllProjects.add(project);
	}
	public void newSkill(Skill skill) {
		AllSkills.add(skill);
	}
	
	//// REST Server Functions: Retrieving objects via ID
	// All return null if object not found
	public Person retrievePerson(String id) {
		Response retrieved = serverhandler.client.get()
				.uri("http://localhost:9000/v1/byteBridge/Person/"+id)
				.retrieve()
				.body(Response.class);
		// System.out.println(retrieved.data);
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
		Company newCompany = null;
		try
		{
			newCompany = objectmapper.treeToValue(retrieved.data, Company.class);
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
	
	////Convenience functions to retrieve all the objects of a given Class from the REST Server
	public ArrayList<Person> retrieveAllUsers() {
		ArrayList<Person> users = new ArrayList<Person>();
		Person tempPerson;
		for (int i=1; i<AllUsers.size()+1; i++) {
			tempPerson = retrievePerson(Integer.toString(i));
			if (tempPerson != null) {
				users.add(tempPerson);
			}
		}
		return users;
	}
	public ArrayList<Company> retrieveAllCompanies() {
		ArrayList<Company> companies = new ArrayList<Company>();
		Company tempCompany;
		for (int i=1; i<AllCompanies.size()+1; i++) {
			tempCompany = retrieveCompany(Integer.toString(i));
			if (tempCompany != null) {
				companies.add(tempCompany);
			}
		}
		return companies;
	}
	public ArrayList<JobPosting> retrieveAllJobs() {
		ArrayList<JobPosting> jobs = new ArrayList<JobPosting>();
		JobPosting tempJob;
		for (int i=1; i<AllJobs.size()+1; i++) {
			tempJob = retrieveJob(Integer.toString(i));
			if (tempJob != null) {
				jobs.add(tempJob);
			}
		}
		return jobs;
	}
	public ArrayList<News> retrieveAllNews() {
		ArrayList<News> news = new ArrayList<News>();
		News tempNews;
		for (int i=1; i<AllNews.size()+1; i++) {
			tempNews = retrieveNews(Integer.toString(i));
			if (tempNews != null) {
				news.add(tempNews);
			}
		}
		return news;
	}
	public ArrayList<Project> retrieveAllProjects() {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project tempProject;
		for (int i=1; i<AllProjects.size()+1; i++) {
			tempProject = retrieveProject(Integer.toString(i));
			if (tempProject != null) {
				projects.add(tempProject);
			}
		}
		return projects;
	}
	public ArrayList<Skill> retrieveAllSkills() {
		ArrayList<Skill> skills = new ArrayList<Skill>();
		Skill tempSkill;
		for (int i=1; i<AllProjects.size()+1; i++) {
			tempSkill = retrieveSkill(Integer.toString(i));
			if (tempSkill != null) {
				skills.add(tempSkill);
			}
		}
		return skills;
	}
}
