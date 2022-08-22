package com.revature.daos;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


import com.revature.models.Monsters;
import com.revature.utils.ConnectionUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

public class MonstersDAOImpl implements MonstersDAO{

	
	@Override
	public Object getMonstersById(Object object, int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM Monsters WHERE monsterid = " + id +"; ";
			Statement statement = conn.createStatement(); 
			ResultSet result = statement.executeQuery(sql);
			
			Class<?> objectClass = object.getClass();
	        Field[] fields = objectClass.getDeclaredFields();
			System.out.println(objectClass);
			for(Field f:fields) {
				System.out.println(f.getName());
			}
			
//			if(result.next()) {
//				//results sets are cursor base, each time .next is called the cursor moves to the next group of values. 
//				//It starts the one before so you will always need to call the next.
//				
//				Monsters monsters = new Monsters(
//						result.getInt("monsterID"),
//						result.getString("monsterName"),
//						result.getString("attributeType"),
//						result.getInt("attack"),
//						result.getInt("defense"),
//						result.getString("description")
//						);
//				
//				return monsters;
//				
//			}
			
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
	public Monsters getMonstersUpdate(int id, String columnName, String change) {
		try(Connection conn = ConnectionUtil.getConnection()){		
			String sql = "UPDATE Monsters SET "+columnName+" = '"+change+"' WHERE monsterID = "+id+";";
			PreparedStatement prepares = conn.prepareStatement(sql); 
			
			System.out.println(columnName);
			System.out.println(change);
			System.out.println(id);

			//String eMail = result.getString("eMail");
			prepares.execute();

			}
			
		catch(SQLException e) {
			e.printStackTrace();
		
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		Monsters monster = new Monsters();
		MonstersDAO mondao = new MonstersDAOImpl();
		
		mondao.getMonstersById(monster, 2);
		System.out.println(mondao);
		
		
	}

}
