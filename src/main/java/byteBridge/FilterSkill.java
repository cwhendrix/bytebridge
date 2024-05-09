package byteBridge;

import java.util.ArrayList;
import byteBridge.Entity.Entities;

public class FilterSkill extends JobFilter
{

	public FilterSkill() {}

	@Override
	public ArrayList<String> constructRecommendations(ByteBridgeState instance, JobPosting job)
	{
		ArrayList<String> recommended = new ArrayList<String>();
		ArrayList<String> skills = job.links.get(Entities.SKILL);
		Person user;
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
		return recommended;
	}

}
