package byteBridge;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Page
{
	enum Permissions {
		WRITE
	}
	String description;
	Entity entity;
	Dictionary<Permissions, ArrayList<Entity>> permissions;
	
	public Page(String description, Entity entity)
	{
		this.description = description;
		this.entity = entity;
		permissions = new Hashtable<Permissions, ArrayList<Entity>>();
		for (Permissions permission : Permissions.values()) { 
			ArrayList<Entity> temp = new ArrayList<Entity>();
			permissions.put(permission, temp);
		}
	}
	public boolean hasPermission(Entity accessor) {
		ArrayList<Entity> writepermissions = permissions.get(Permissions.WRITE);
		return writepermissions.contains(accessor);
	}
	public void addPermission(Entity accessor) {
		ArrayList<Entity> writepermissions = permissions.get(Permissions.WRITE);
		if (!hasPermission(accessor)) {
			writepermissions.add(accessor);
		}
	}
	public void removePermission(Entity accessor) {
		ArrayList<Entity> writepermissions = permissions.get(Permissions.WRITE);
		writepermissions.remove(accessor);
	}

}
