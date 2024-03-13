package byteBridge;
import java.util.ArrayList;
import java.util.Dictionary;

public class Entity
{
	enum Entities {
	    FOLLOWING,
	    COMPANY,
	    PROJECT,
	    SKILL,
	    NEWS,
	    JOBPOSTING,
	    EMPLOYER,
	    EMPLOYEES,
	    APPLICANTS
	  }
	
	String id;
	String name;
	Page page;
	Dictionary<Entities, ArrayList<Entity>> links;
	
	public Entity()
	{
		// TODO Auto-generated constructor stub
	}

}
