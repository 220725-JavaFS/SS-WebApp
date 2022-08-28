//package com.revature.models;
//
//public class Monsters {
//	
//	private int monsters_id;
//	private String name;
//	private String attributeType;
//	private int attack;
//	private int defense;
//	private String description;
//	
//	public Monsters(int monsters_id, String name, String attributeType, int attack, int defense, String description) {
//		super();
//		this.monsters_id = monsters_id;
//		this.name = name;
//		this.attributeType = attributeType;
//		this.attack = attack;
//		this.defense = defense;
//		this.description = description;
//	}
//
//	public Monsters(String name, String attributeType, int attack, int defense, String description) {
//		super();
//		this.name = name;
//		this.attributeType = attributeType;
//		this.attack = attack;
//		this.defense = defense;
//		this.description = description;
//	}
//
//	public Monsters(String name, String attributeType, int attack, int defense) {
//		super();
//		this.name = name;
//		this.attributeType = attributeType;
//		this.attack = attack;
//		this.defense = defense;
//	}
//
//	public Monsters() {
//		super();
//	}
//
//	public int getMonsters_id() {
//		return monsters_id;
//	}
//
//	public void setMonsters_id(int monsters_id) {
//		this.monsters_id = monsters_id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getAttributeType() {
//		return attributeType;
//	}
//
//	public void setAttributeType(String attributeType) {
//		this.attributeType = attributeType;
//	}
//
//	public int getAttack() {
//		return attack;
//	}
//
//	public void setAttack(int attack) {
//		this.attack = attack;
//	}
//
//	public int getDefense() {
//		return defense;
//	}
//
//	public void setDefense(int defense) {
//		this.defense = defense;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + attack;
//		result = prime * result + ((attributeType == null) ? 0 : attributeType.hashCode());
//		result = prime * result + defense;
//		result = prime * result + ((description == null) ? 0 : description.hashCode());
//		result = prime * result + monsters_id;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Monsters other = (Monsters) obj;
//		if (attack != other.attack)
//			return false;
//		if (attributeType == null) {
//			if (other.attributeType != null)
//				return false;
//		} else if (!attributeType.equals(other.attributeType))
//			return false;
//		if (defense != other.defense)
//			return false;
//		if (description == null) {
//			if (other.description != null)
//				return false;
//		} else if (!description.equals(other.description))
//			return false;
//		if (monsters_id != other.monsters_id)
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "Monsters [monsters_id=" + monsters_id + ", name=" + name + ", attributeType=" + attributeType
//				+ ", attack=" + attack + ", defense=" + defense + ", description=" + description + "]";
//	}
//
//	
//	
//	
//}
