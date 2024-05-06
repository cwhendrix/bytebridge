package byteBridge;

import java.util.ArrayList;

import byteBridge.Entity.Entities;

public class JobRecommender
{
	boolean everyone = true;	// default recommendation
	boolean skills = false;		// Only those with matched skills
	boolean title = true;	// Only those who currently have the same job title
	// more boolean flags added as needed
	ArrayList<String> recommended;
	JobPosting job;
	
	public JobRecommender() {};
	
	public JobRecommender(JobPosting job)
	{
		recommended = new ArrayList<String>();
		this.job = job;
	}
	
	private Person findPerson(String id) {
		ByteBridgeState instance = ByteBridgeState.getInstance();
		for (int i=0; i<instance.AllUsers.size(); i++) {
			if (instance.AllUsers.get(i).getId() == id) {
				return instance.AllUsers.get(i);
			}
		}
		return null;
	}
	
	public void addRecommendations() {
		ByteBridgeState instance = ByteBridgeState.getInstance();
		Person user;
		
		//// Handle everyone ////
		if (everyone) {
			for (int i=0; i<instance.AllUsers.size(); i++) {
				if (!recommended.contains(instance.AllUsers.get(i).getId())) {
					recommended.add(instance.AllUsers.get(i).getId());
				}
			}
		}
		
		//// Handle skills ////
		if (skills) {
			ArrayList<String> skills = job.links.get(Entities.SKILL);
			for (int i=0; i<skills.size(); i++) {	/// For each skill
				for (int j=0; j<instance.AllUsers.size(); j++) {	/// for all the users
					if (!recommended.contains(instance.AllUsers.get(j).getId())) {	/// if they aren't already recommended
						user = instance.AllUsers.get(j);	/// put into temp variable for convenience
						if (user.links.get(Entities.SKILL).contains(skills.get(i))) {	/// if they have the skill, add to recommended
							recommended.add(user.getId());
						}
					}
				}
			}
		}
		
		//// Handle title ////
		if (title) {
			for (int i=0; i<instance.AllUsers.size(); i++) {	/// for all the users
				if (!recommended.contains(instance.AllUsers.get(i).getId())) {	/// if they aren't already recommended
					user = instance.AllUsers.get(i);	/// put into temp variable for convenience
					if (user.occupation == job.name) {	/// if they have the skill, add to recommended
						recommended.add(user.getId());
					}
				}
			}
		}
		
		//// more can be added and toggled as wished ////
	}

	/**
	 * @return the everyone
	 */
	public boolean isEveryone()
	{
		return everyone;
	}

	/**
	 * @param everyone the everyone to set
	 */
	public void setEveryone(boolean everyone)
	{
		this.everyone = everyone;
	}

	/**
	 * @return the skills
	 */
	public boolean isSkills()
	{
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(boolean skills)
	{
		this.skills = skills;
	}

	/**
	 * @return the followers
	 */
	public boolean isTitle()
	{
		return title;
	}

	/**
	 * @param followers the followers to set
	 */
	public void setTitle(boolean title)
	{
		this.title = title;
	}

	/**
	 * @return the recommended
	 */
	public ArrayList<String> getRecommended()
	{
		return recommended;
	}

	/**
	 * @param recommended the recommended to set
	 */
	public void setRecommended(ArrayList<String> recommended)
	{
		this.recommended = recommended;
	}
	

}
