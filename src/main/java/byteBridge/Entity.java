package byteBridge;
import java.util.ArrayList;
import java.util.Dictionary;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

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
	Dictionary<Entities, ArrayList<String>> links;
	
	public Entity() {}
	
	public void retrieveData() {
		
	}
	public void storeData() {
		
	}
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the page
	 */
	public Page getPage()
	{
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page)
	{
		this.page = page;
	}
	/**
	 * @return the links
	 */
	public Dictionary<Entities, ArrayList<String>> getLinks()
	{
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(Dictionary<Entities, ArrayList<String>> links)
	{
		this.links = links;
	}
}
