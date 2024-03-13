package byteBridge;

import java.util.ArrayList;
import java.util.Hashtable;

import byteBridge.Entity.Entities;

public class JobPosting extends Entity
{

	public JobPosting(String id, String name, Page page)
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
	public void addApplicant(Person applicant) {
		ArrayList<Entity> applicants = links.get(Entities.APPLICANTS);
		applicants.add(applicant);
	}
}
