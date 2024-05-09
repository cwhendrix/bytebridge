package byteBridge;

import java.util.ArrayList;

public class JobRecommender
{
	enum AdvertFilters {
		EVERYONE,
		SKILLS,
		TITLES
	}

	ArrayList<String> recommended;
	JobPosting job;
	AdvertFilters filter;
	
	public JobRecommender() {};
	
	public JobRecommender(JobPosting job)
	{
		recommended = new ArrayList<String>();
		this.job = job;
		this.filter = AdvertFilters.EVERYONE;
	}

	@SuppressWarnings("unused")
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
		JobFilter filterImplementer;
		
		if (filter == AdvertFilters.EVERYONE) {
			filterImplementer = new FilterEveryone();
			this.recommended = filterImplementer.constructRecommendations(instance, this.job);
		} else if (filter == AdvertFilters.SKILLS) {
			filterImplementer = new FilterSkill();
			this.recommended = filterImplementer.constructRecommendations(instance, this.job);
		} else if (filter == AdvertFilters.TITLES) {
			filterImplementer = new FilterTitle();
			this.recommended = filterImplementer.constructRecommendations(instance, this.job);
		}
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

	/**
	 * @return the job
	 */
	public JobPosting getJob()
	{
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(JobPosting job)
	{
		this.job = job;
	}

	/**
	 * @return the filter
	 */
	public AdvertFilters getFilter()
	{
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(AdvertFilters filter)
	{
		this.filter = filter;
	}
	

}
