package byteBridge;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Page
{
	enum Permissions {
		WRITE
	}
	String id;
	String description;
	Entity entity;
	Map<Permissions, ArrayList<String>> permissions;
	
	public Page() {}
	
	public Page(String description, Entity entity)
	{
		this.description = description;
		this.entity = entity;
		permissions = new HashMap<Permissions, ArrayList<String>>();
		for (Permissions permission : Permissions.values()) { 
			ArrayList<String> temp = new ArrayList<String>();
			permissions.put(permission, temp);
		}
	}
	public boolean hasPermission(Entity accessor) {
		ArrayList<String> writepermissions = permissions.get(Permissions.WRITE);
		return writepermissions.contains(accessor.getId());
	}
	public void addPermission(Entity accessor) {
		ArrayList<String> writepermissions = permissions.get(Permissions.WRITE);
		if (!hasPermission(accessor)) {
			writepermissions.add(accessor.getId());
		}
	}
	public void changePermissions(Entity accessor, Permissions permission) {
		if (permissions.get(accessor.getId()) != null) {
			removePermission(accessor);
		}
		addPermission(accessor);
	}
	
	public void removePermission(Entity accessor) {
		ArrayList<String> writepermissions = permissions.get(Permissions.WRITE);
		writepermissions.remove(accessor.getId());
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
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the entity
	 */
	public Entity getEntity()
	{
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Entity entity)
	{
		this.entity = entity;
	}

	/**
	 * @return the permissions
	 */
	public Map<Permissions, ArrayList<String>> getPermissions()
	{
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Map<Permissions, ArrayList<String>> permissions)
	{
		this.permissions = permissions;
	}

}
