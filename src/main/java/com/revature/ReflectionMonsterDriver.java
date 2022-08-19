package com.revature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.revature.models.Monsters;

public class ReflectionMonsterDriver {

	public static void main(String[] args) {
		
		Monsters mon = new Monsters("Silver Surfer","Holy Water",2150,1950,"Liquid Entity made of Silver.");
		Class<?> c1 = mon.getClass();
		Class<Monsters> c2 = Monsters.class;
		
		Field[] fields = c1.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f.getName());
		}

		System.out.println("-----------------");

		Method[] methods = c1.getDeclaredMethods();
		for (Method m : methods) {
			System.out.println(m);
		}
		
		System.out.println(mon);
		

	}

}
