//package com.revature;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.time.LocalDate;
//
//import com.revature.exceptions.JsonMappingException;
//
//public class JSONMapper implements Mapper {
//
//	// the goal of the method is to take any object and provide the string JSON
//	// representation of that object.
//	@Override
//	public String serialize(Object o) {
//
//		// obtain the name of the fields in the object
//		Class<?> objectClass = o.getClass();
//		Field[] fields = objectClass.getDeclaredFields();
//
//		StringBuilder jsonBuilder = new StringBuilder("{");
//
//		for (Field field : fields) {
//
//			String fieldName = field.getName();
//			//System.out.println(field.getName());
//			// obtain the appropriate getter (using the field name)
//			String getterName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
//			//System.out.println(getterName);
//
//			// use the appropriate getter to get the value of that field
//			try {
//
//				// obtain the getter method from the class we are mapping
//				Method getterMethod = objectClass.getMethod(getterName);
//
//				// invoke that method on the object that we are mapping
//				Object fieldValue = getterMethod.invoke(o);
//
//				//System.out.println(" \"" + fieldName + "\"" + " : \"" + fieldValue + "\",");
//				String jsonKeyValuePair = " \""+fieldName + "\""+" : \"" + fieldValue + "\",";
//				
//				jsonBuilder.append(jsonKeyValuePair);
//
//				//jsonBuilder.append(" \""+fieldName + "\""+" : \"" + fieldValue + " \", ");
//			} catch (NoSuchMethodException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		// construct a key value pair for each field name and field value
//
//		// combine all of the key value pairs into a result string
//
//		return jsonBuilder.substring(0, jsonBuilder.length()-1) + " }";
//	}
//	
//	// the goal of this method is to take a JSON string and convert it into an object 
//			// to do this, we declare a generic type <T> to be used in the method
//			// this type is the type of the class we're providing to the method as well as the type we're going to return from the method
//		/**
//		 * @param 	input	the JSON string to be deserialized
//		 * @param	clazz	the reference type that the JSON string is being converted into
//		 * @return	the Java object created by the JSON string 
//		 */
//		@Override
//	    public <T> T deSerialize(String input, Class<T> clazz) {
//	        if(input==null || input.equals("")){
//	            return null;
//	        }
//	        
//	        // remove the curly brackets from the JSON string
//	        String partialJson = input.substring(1,input.length()-1);
//	        // split the remaining string by the comma to get key value pair string pairs 
//	        String[] keyValueStrings = partialJson.split(",");
//	        
//	        // declare an object to be created
//	        T newObject = null;
//
//	        try {
//	        	// create a new instance of the class being constructed
//	            newObject = clazz.getDeclaredConstructor().newInstance();
//	        
//	        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//	            e.printStackTrace();
//	        }
//
//	        // we need to extract the field name and field value from each string key value pair 
//	        for(String s: keyValueStrings){
//	            // splitting a key value pair (ex. "name" : "Sally") by the colon should give us an array of size 2 ( ["name":"Sally"] )
//	        	String[] keyValueArr = s.split(":");
//	            if(keyValueArr.length != 2){
//	            	// if we don't get an array of size 2, the json is formatted incorrectly 
//	            		// here we tell the developer why the method fails with a custom exception
//	                throw new JsonMappingException("Improperly formatted JSON");
//	            }
//	            
//	            // first we deal with the field name - trim the whitespace and remove the quotes
//	            String keyString = keyValueArr[0].trim();
//	            keyString = keyString.substring(1, keyString.length()-1);
//	            
//	            // next the value - we also trim the whitespace and remove the quotes
//	            String valueString = keyValueArr[1].trim();
//	            valueString = valueString.substring(1, valueString.length()-1);
////	            System.out.println("key string: "+keyString + "   - value string: "+valueString );
//
//	            //obtain each setter method we need to set the values
//	            String setterName = "set"+keyString.substring(0,1).toUpperCase() + keyString.substring(1);
//	            try {
//	                // getting the type of the setter parameter, based on the field type
//	                Class<?> setterParamType = clazz.getDeclaredField(keyString).getType();
//
//	                // obtain the setter method using the setter name and setter parameter type
//	                Method setter = clazz.getMethod(setterName, setterParamType);
//
//	                // below we define a utility method to convert the string field value to the appropriate type for the field  
//	                Object fieldValue = convertStringToFieldType(valueString, setterParamType);
//
//	                // we invoke the setter to populate the field of the object that's being created
//	                setter.invoke(newObject,fieldValue);
//	                
//	            } catch (NoSuchFieldException  e) {
//	                throw new JsonMappingException(keyString+" field does not exist in class "+clazz);
//	            } catch ( NoSuchMethodException e){
//	                throw new JsonMappingException("no valid setter for: "+keyString);
//	            } catch (IllegalAccessException e) {
//	                throw new JsonMappingException("cannot access setter for: "+keyString);
//	            } catch (InvocationTargetException | InstantiationException e) {
//	                throw new JsonMappingException("issue invoking setter for: "+keyString);
//	            }
//
//	        }
//	        // once we iterate over each field, the object we're attempting to create is fully populated and ready to return 
//	        return newObject;
//	    }
//
//	    private Object convertStringToFieldType(String input, Class<?> type) 
//	    		throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//	        switch(type.getName()){
//	            case "byte":
//	                return Byte.valueOf(input);
//	            case "short":
//	                return Short.valueOf(input);
//	            case "int":
//	                return Integer.valueOf(input);
//	            case "long":
//	                return Long.valueOf(input);
//	            case "double":
//	            	return Double.valueOf(input);
//	            case "float":
//	            	return Float.valueOf(input);
//	            case "boolean":
//	                return Boolean.valueOf(input);
//	            case "java.lang.String":
//	                return input;
//	            case "java.time.LocalDate":
//	                return LocalDate.parse(input);
//	            default:
//	                return type.getDeclaredConstructor().newInstance();
//	        }
//	    }
//		
//	}
//
//
//
