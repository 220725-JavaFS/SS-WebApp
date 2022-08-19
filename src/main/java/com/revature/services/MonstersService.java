package com.revature.services;

import java.util.List;


import com.revature.daos.MonstersDAO;
import com.revature.daos.MonstersDAOImpl;
import com.revature.models.Monsters;


public class MonstersService {
	
private MonstersDAO monstersDAO = new MonstersDAOImpl();
	
	public Monsters getSingleMonsters (int id) {
		return monstersDAO.getMonstersById(id);
	}
	
	public List<Monsters> fullDeck(){
		return monstersDAO.allMonsters();
	}
	
	public void addMonster(Monsters monsters) {
		monstersDAO.newMonsters(monsters);
	}
	
	public void deleteMonster(int id) {
		monstersDAO.eraseMonsters(id);
		
	}
	
	public void deleteNameMonster(Monsters name) {
		monstersDAO.eraseMonstersByName(name);
		
	}

}
