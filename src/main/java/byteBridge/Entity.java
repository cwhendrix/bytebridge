package byteBridge;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;

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
	Map<Entities, ArrayList<String>> links;
	
	public Entity() {}
	
	public void retrieveData() {
		
	}
	public void storeData() {
		
	}
	public void updateData() {
		
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
	public Map<Entities, ArrayList<String>> getLinks()
	{
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(Map<Entities, ArrayList<String>> links)
	{
		this.links = links;
	}
}
