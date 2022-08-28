package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.JSONMapperZ;

import com.revature.MapperZ;
import com.revature.daos.MonsterDAOZ;
import com.revature.daos.MonstersDAOImplZ;
import com.revature.models.MonsterZ;

import com.revature.services.MonstersServiceZ;

public class MonstersController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MonstersServiceZ MonstersService = new MonstersServiceZ();
	private ObjectMapper objectMapper = new ObjectMapper();
	MapperZ mapper = new JSONMapperZ();
	private MonsterDAOZ mondao = new MonstersDAOImplZ();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String URI = request.getRequestURI();
		
		System.out.println(URI);
		// /WebApp/Monsters/{id}
		
		String[] urlSections = URI.split("/");
		
		if(urlSections.length==3) {
		
		List<MonsterZ> list = MonstersService.fullDeck();
		
		String json = objectMapper.writeValueAsString(list);
		System.out.println(json);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
		
		response.setContentType("application/json");
		
		}else if(urlSections.length==4) {
			
			try {
			
			int id = Integer.valueOf(urlSections[3]);
			
			MonsterZ Monsters = MonstersService.getSingleMonsters(id);
			
			//Monsters Monsters = MonstersService.deleteMonster(id);
			
			PrintWriter printWriter = response.getWriter();
			
			//String json = objectMapper.writeValueAsString(Monsters);
			String json2 = mapper.serialize(Monsters);
			
			//printWriter.print(json);
			printWriter.print(json2);
			
			response.setStatus(200);
			
			response.setContentType("application/json");
			
			}catch(NumberFormatException e) {
				
				response.setStatus(404);
				
				return;
			}
			
		}else {
			
			response.setStatus(404);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		while(line!=null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String json = new String(sb);
		
		System.out.println(json);
		
		MonsterZ Monsters = objectMapper.readValue(json, MonsterZ.class);
		//MonsterZ monsters2 = mapper.deSerialize(json, MonsterZ.class);
		MonstersService.addMonster(Monsters);
		//MonstersService.addMonster(monsters2);
		//System.out.println(monsters2);
		
		Stream.Builder<String> toBuild = Stream.builder();
		Stream<String> toPost = toBuild.add(json).build();
		
		System.out.println("Here is the information that was posted:");
		System.out.println("\n");
		toPost.forEach(System.out::println);
		response.setStatus(201);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String URI = request.getRequestURI();
		
		System.out.println(URI);
		// /WebApp/Monsters/{id}
		
		String[] urlSections = URI.split("/");
		
		if(urlSections.length==3) {
		
		List<MonsterZ> list = MonstersService.fullDeck();
		
		String json = objectMapper.writeValueAsString(list);
		System.out.println(json);
		
		PrintWriter printWriter = response.getWriter();
			
		printWriter.print(json);
		
		response.setStatus(200);
		
		response.setContentType("application/json");
		
		
		
		}else if(urlSections.length==4) {
			
			try {
			
			int id = Integer.valueOf(urlSections[3]);
			
			//Monsters Monsters = MonstersService.getSingleMonsters(id);
			
			MonsterZ Monsters = MonstersService.deleteMonster(id);
			
			PrintWriter printWriter = response.getWriter();
			
			String json = objectMapper.writeValueAsString(Monsters);

			printWriter.print(json);
			
			response.setStatus(201);
			
			response.setContentType("application/json");
			
			}catch(NumberFormatException e) {
				
				response.setStatus(404);
				
				return;
			}
			
		}else {
			
			response.setStatus(404);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		
		String line = reader.readLine();
		
		String URI = request.getRequestURI();
		
		String[] urlSections = URI.split("/");
		
		while(line!=null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String json = new String(sb);
		
		System.out.println(json);
		
		MonsterZ Monsters = objectMapper.readValue(json, MonsterZ.class);
		
		int id = Integer.valueOf(urlSections[3]);
		
		MonstersService.updateMonster(Monsters, id);
		
		Stream.Builder<String> toBuild = Stream.builder();
		Stream<String> toPost = toBuild.add(json).build();
		
		System.out.println("Here is the information that was updated:");
		System.out.println("\n");
		toPost.forEach(System.out::println);
		
		response.setStatus(201);
		
	}

}
