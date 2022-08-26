package com.revature.daos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.JSONMapper;
import com.revature.Mapper;
import com.revature.models.Monsters;
import com.revature.utils.ConnectionUtil;

public class MonstersDAOImpl implements MonstersDAO{
	static ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Object getMonstersById(Object object, int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			//Monsters m = Monsters.FindById(id);
			String sql = "SELECT * FROM Monsters WHERE monsterid = " + id +"; ";
			Statement statement = conn.createStatement(); 
			ResultSet result = statement.executeQuery(sql);
			//ResultSetMetaData resmd = result.getMetaData();
			//List<Monsters> list = new ArrayList();
			
			if(result.next()) {
				//results sets are cursor base, each time .next is called the cursor moves to the next group of values. 
				//It starts the one before so you will always need to call the next.
				
				Monsters monsters = new Monsters(
						result.getInt("monsterID"),
						result.getString("monsterName"),
						result.getString("attributeType"),
						result.getInt("attack"),
						result.getInt("defense"),
						result.getString("description")
						);
				
				return monsters;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	@Override
	public Monsters getMonstersById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM Monsters WHERE monsterid = " + id +"; ";
			Statement statement = conn.createStatement(); 
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				//results sets are cursor base, each time .next is called the cursor moves to the next group of values. 
				//It starts the one before so you will always need to call the next.
				
				Monsters monsters = new Monsters(
						result.getInt("monsterID"),
						result.getString("monsterName"),
						result.getString("attributeType"),
						result.getInt("attack"),
						result.getInt("defense"),
						result.getString("description")
						);
				
				return monsters;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Monsters> allMonsters() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM Monsters;";
			Statement statement = conn.createStatement(); 
			ResultSet result = statement.executeQuery(sql);
			
			List<Monsters> monstersList = new LinkedList<Monsters>();
			
			while(result.next()) {
				//results sets are cursor base, each time .next is called the cursor moves to the next group of values. 
				//It starts the one before so you will always need to call the next.
				
				Monsters monsters = new Monsters(
						result.getInt("monsterID"),
						result.getString("monsterName"),
						result.getString("attributeType"),
						result.getInt("attack"),
						result.getInt("defense"),
						result.getString("description"));
						
				
				monstersList.add(monsters);
				
			}
			
			return monstersList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void newMonsters(Monsters monsters) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = 
					"INSERT INTO Monsters "
					+ "(monsterName, attributeType, attack, defense, description) "
					+ "VALUES (?, ?, ?, ?, ?); ";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, monsters.getName());
			statement.setString(++count, monsters.getAttributeType());
			statement.setInt(++count, monsters.getAttack());
			statement.setInt(++count, monsters.getDefense());
			statement.setString(++count, monsters.getDescription());
			
			statement.execute();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Monsters eraseMonsters(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM Monsters WHERE monsterID = "+id+";"; 
			PreparedStatement prepares = conn.prepareStatement(sql);
		
			prepares.execute();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
		
		
	}

	@Override
	public void eraseMonstersByName(Monsters name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM Monsters WHERE monsterName = '"+name+"';"; 
			PreparedStatement prepares = conn.prepareStatement(sql);
		
			prepares.execute();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public Monsters getMonstersUpdate(Monsters monsters, int id) {
		try(Connection conn = ConnectionUtil.getConnection()){		
			String sql = "UPDATE Monsters SET monsterName = ?, "
					+ "attributeType = ?, attack = ?, defense = ?, "
					+ "description = ? WHERE monsterID = "+id+";";
			PreparedStatement prepares = conn.prepareStatement(sql); 
			
			int count = 0;
			prepares.setString(++count, monsters.getName());
			prepares.setString(++count, monsters.getAttributeType());
			prepares.setInt(++count, monsters.getAttack());
			prepares.setInt(++count, monsters.getDefense());
			prepares.setString(++count, monsters.getDescription());
			
			prepares.execute();
			
//			System.out.println(columnName);
//			System.out.println(change);
//			System.out.println(id);

			//String eMail = result.getString("eMail");
			prepares.execute();

			}
			
		catch(SQLException e) {
			e.printStackTrace();
		
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		MonstersDAO mondao = new MonstersDAOImpl();
		Monsters monster = new Monsters();
		monster = mondao.getMonstersById(1);
		Class<?> c1 = mondao.getClass();
		Class<Monsters> c2 = Monsters.class;
		
		//mondao.getMonstersById(1);
		System.out.println(mondao);
		
		Mapper mapper = new JSONMapper();
		String json = mapper.serialize(monster);
		System.out.println(json);
		
	}

}
