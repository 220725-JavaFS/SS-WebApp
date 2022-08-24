package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Monsters;
import com.revature.services.MonstersService;

public class MonstersController extends HttpServlet{
	
	private MonstersService MonstersService = new MonstersService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		String URI = request.getRequestURI();
		
		System.out.println(URI);
		// /WebApp/Monsters/{id}
		
		String[] urlSections = URI.split("/");
		
		if(urlSections.length==3) {
		
		List<Monsters> list = MonstersService.fullDeck();
		
		String json = objectMapper.writeValueAsString(list);
		System.out.println(json);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
		
		response.setContentType("application/json");
		
		}else if(urlSections.length==4) {
			
			try {
			
			int id = Integer.valueOf(urlSections[3]);
			
			Monsters Monsters = MonstersService.getSingleMonsters(id);
			
			//Monsters Monsters = MonstersService.deleteMonster(id);
			
			PrintWriter printWriter = response.getWriter();
			
			String json = objectMapper.writeValueAsString(Monsters);
			
			printWriter.print(json);
			
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
		
		Monsters Monsters = objectMapper.readValue(json, Monsters.class);
		
		MonstersService.addMonster(Monsters);
		
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
		
		List<Monsters> list = MonstersService.fullDeck();
		
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
			
			Monsters Monsters = MonstersService.deleteMonster(id);
			
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
		
		Monsters Monsters = objectMapper.readValue(json, Monsters.class);
		
		int id = Integer.valueOf(urlSections[3]);
		
		MonstersService.updateMonster(Monsters, id);
		
		response.setStatus(201);
		
	}

}
