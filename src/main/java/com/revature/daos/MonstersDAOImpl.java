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
import com.revature.models.Monsters;
import com.revature.utils.ConnectionUtil;

public class MonstersDAOImpl implements MonstersDAO{
ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Object getMonstersById(Object object, int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			
			String sql = "SELECT * FROM Monsters WHERE monsterid = " + id +"; ";
			Statement statement = conn.createStatement(); 
			ResultSet result = statement.executeQuery(sql);
			ResultSetMetaData resmd = result.getMetaData();
			//List<Monsters> list = new ArrayList();
			int count = 0;
			while (result.next()) {
				Class<?> objectClass = object.getClass();
		        Field[] fields = objectClass.getDeclaredFields();
		        int len = resmd.getColumnCount();
		        count++;
			System.out.println();
			
			System.out.println(objectClass);
			StringBuilder jsonBuilder = new StringBuilder("{");
			//String json = mapper.writeValueAsString(result);
			
			//if(result.next()) {
			
			for(Field f:fields) {
				String fieldName = f.getName();
				String getterName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String setterName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				System.out.println(f.getName());
				System.out.println(getterName);
				System.out.println(setterName);
				
				
				try {

					// obtain the getter method from the class we are mapping
					Method getterMethod = objectClass.getMethod(getterName);
					Method setterMethod = objectClass.getMethod(setterName);
					// invoke that method on the object that we are mapping
					Object fieldValue = getterMethod.invoke(result);
					Object fieldSValue = setterMethod.invoke(result, fields);
					System.out.println(fieldSValue);
					//System.out.println(sql);
					//System.out.println(" \"" + fieldName + "\"" + " : \"" + fieldValue + "\",");
					String jsonKeyValuePair = " \""+fieldName + "\""+" : \"" + fieldValue + "\",";
					
					System.out.println(jsonBuilder.append(jsonKeyValuePair));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			}
		//}

		// construct a key value pair for each field name and field value

		// combine all of the key value pairs into a result string

		return jsonBuilder.substring(0, jsonBuilder.length()-1) + " }";
			}
			}catch (SQLException e) {
					e.printStackTrace();
					}
		
		return null;
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
			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
	
	
	
	
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
		
		Monsters monster = new Monsters();
		MonstersDAO mondao = new MonstersDAOImpl();
		
		mondao.getMonstersById(monster, 2);
		System.out.println(mondao);
		
		
	}

}
