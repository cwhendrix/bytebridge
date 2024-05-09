package byteBridge;

import java.util.ArrayList;

public class FilterTitle extends JobFilter
{

	public FilterTitle() {}

	@Override
	public ArrayList<String> constructRecommendations(ByteBridgeState instance, JobPosting job)
	{
		ArrayList<String> recommended = new ArrayList<String>();
		Person user;
		for (int i=0; i<instance.AllUsers.size(); i++) {	/// for all the users
			if (!recommended.contains(instance.AllUsers.get(i).getId())) {	/// if they aren't already recommended
				user = instance.AllUsers.get(i);	/// put into temp variable for convenience
				if (user.occupation == job.name) {	/// if they have the skill, add to recommended
					recommended.add(user.getId());
				}
			}
		}
		return recommended;
	}
}
