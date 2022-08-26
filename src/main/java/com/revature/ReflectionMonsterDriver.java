package com.revature;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.MonstersDAO;
import com.revature.daos.MonstersDAOImpl;
import com.revature.models.Monsters;
import com.revature.services.MonstersService;

public class ReflectionMonsterDriver {

	public static void main(String[] args) throws ServletException, IOException {
		
//		Monsters mon = new Monsters("Silver Surfer","Holy Water",2150,1950,"Liquid Entity made of Silver.");
//		Class<?> c1 = mon.getClass();
//		Class<Monsters> c2 = Monsters.class;
//		
//		Field[] fields = c1.getDeclaredFields();
//		for (Field f : fields) {
//			System.out.println(f.getName());
//		}
//
//		System.out.println("-----------------");
//
//	//	Method[] methods = c1.getDeclaredMethods();
//	//	for (Method m : methods) {
//	//		System.out.println(m);
//	//	}
//		
//		//System.out.println(mon);
//		Mapper mapper = new JSONMapper();
//		String json = mapper.serialize(mon);
//		System.out.println(json);
//		
//		Monsters monster = mapper.deSerialize(json, c2);
//		System.out.println(monster);
//		
//		System.out.println(c2);
		
		MonstersDAO mondao = new MonstersDAOImpl();
		Monsters monster = new Monsters();
		MonstersService monServ = new MonstersService();
		ObjectMapper oMap = new ObjectMapper();
		monster = mondao.getMonstersById(1);
		Class<?> c1 = mondao.getClass();
		Class<Monsters> c2 = Monsters.class;
		
		//mondao.getMonstersById(1);
		System.out.println(mondao);
		
		
		Mapper mapper = new JSONMapper();
		String json = mapper.serialize(monster);
		//System.out.println(json);
		int id = 2;
		Monsters mons = monServ.getSingleMonsters(id);
		String json2 = oMap.writeValueAsString(mons);
		System.out.println(json2);
		
		Monsters monk = oMap.readValue(json2, Monsters.class);
		System.out.println(monk);
		
		
		

	}

}
