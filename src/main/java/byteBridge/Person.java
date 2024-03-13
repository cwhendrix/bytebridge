package byteBridge;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Person extends Entity
{
	String occupation;
	
	public Person(String id, String name, Page page, String occupation)
	{
		this.id = id;
		this.name = name;
		this.page = page;
		this.occupation = occupation;
		links = new Hashtable<Entities, ArrayList<Entity>>();
		for (Entities link : Entities.values()) { 
			ArrayList<Entity> temp = new ArrayList<Entity>();
			links.put(link, temp);
		}
	}
	
	public void addSkill(Skill newSkill) {
		ArrayList<Entity> skills = links.get(Entities.SKILL);
		skills.add(newSkill);
	}
	public void removeSkill(Skill remSkill) {
		ArrayList<Entity> skills = links.get(Entities.SKILL);
		skills.remove(remSkill);
	}
	public void addProject(Project newProject) {
		ArrayList<Entity> projects = links.get(Entities.PROJECT);
		projects.add(newProject);
	}
	public void removeProject(Project remProject) {
		ArrayList<Entity> projects = links.get(Entities.PROJECT);
		projects.remove(remProject);
	}
	public void apply(JobPosting job) {
		job.addApplicant(this);
	}
	public void post(News newPost) {
		ArrayList<Entity> posts = links.get(Entities.NEWS);
		posts.add(newPost);
	}
	public void setEmployer(Company newCompany) {
		ArrayList<Entity> employers = links.get(Entities.EMPLOYER);
		employers.add(newCompany);
	}
	public void followPerson(Person newPerson) {
		ArrayList<Entity> following = links.get(Entities.FOLLOWING);
		following.add(newPerson);
	}
	public void followCompany(Company newCompany) {
		ArrayList<Entity> following = links.get(Entities.FOLLOWING);
		following.add(newCompany);
	}

}
