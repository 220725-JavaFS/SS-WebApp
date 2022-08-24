package com.revature.daos;

import java.util.List;

import com.revature.models.Monsters;

public interface MonstersDAO {

	public abstract Monsters getMonstersById(int id);

	List<Monsters> allMonsters();

	public abstract void newMonsters(Monsters Monsters);

	public abstract Monsters eraseMonsters(int id);

	public abstract void eraseMonstersByName(Monsters name);

	public abstract Monsters getMonstersUpdate(Monsters monsters, int id);

	Object getMonstersById(Object object, int id);
	
}
