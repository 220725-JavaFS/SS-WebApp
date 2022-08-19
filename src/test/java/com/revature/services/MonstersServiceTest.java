package com.revature.services;

import org.junit.jupiter.api.BeforeAll;

import com.revature.models.Monsters;



public class MonstersServiceTest {
	
	private int monster_id;
	private String name;
	private String type;
	private int attack;
	private int defense;
	private String description;
	private static Monsters monsters;
	
	@BeforeAll
	public static void createMonster() {
		System.out.println("Creating Monster in Before All.");
		monsters = new Monsters();
	}
	
	

}
