package byteBridge;
import java.util.ArrayList;
import java.util.Hashtable;

import byteBridge.Entity.Entities;

public class Company extends Entity
{

	public Company(String id, String name, Page page)
	{
		this.id = id;
		this.name = name;
		this.page = page;
		links = new Hashtable<Entities, ArrayList<Entity>>();
		for (Entities link : Entities.values()) { 
			ArrayList<Entity> temp = new ArrayList<Entity>();
			links.put(link, temp);
		}
	}
	public void addEmployee(Person employee) {
		ArrayList<Entity> employees = links.get(Entities.EMPLOYEES);
		employees.add(employee);
	}
	public void removeEmployee(Person remEmployee) {
		ArrayList<Entity> employees = links.get(Entities.EMPLOYEES);
		employees.remove(remEmployee);
	}
	public void addProject(Project newProject) {
		ArrayList<Entity> projects = links.get(Entities.PROJECT);
		projects.add(newProject);
	}
	public void removeProject(Project remProject) {
		ArrayList<Entity> projects = links.get(Entities.PROJECT);
		projects.remove(remProject);
	}
	public void addJobPosting(JobPosting newJob) {
		ArrayList<Entity> jobs = links.get(Entities.JOBPOSTING);
		jobs.add(newJob);
	}
	public void removeJobPosting(JobPosting remJob) {
		ArrayList<Entity> jobs = links.get(Entities.JOBPOSTING);
		jobs.remove(remJob);
	}
}
