package byteBridge;

import java.util.ArrayList;

public class FilterEveryone extends JobFilter
{

	public FilterEveryone() {}

	@Override
	public ArrayList<String> constructRecommendations(ByteBridgeState instance, JobPosting job)
	{
		ArrayList<String> recommended = new ArrayList<String>();
		for (int i=0; i<instance.AllUsers.size(); i++) {
			if (!recommended.contains(instance.AllUsers.get(i).getId())) {
				recommended.add(instance.AllUsers.get(i).getId());
			}
		}
		return recommended;
	}

}
