package com.fms.model;

public enum Role {
	
	ROLE_ADMIN("admin"),ROLE_PMO("pmo"),ROLE_POC("poc"),ROLE_PARTICIPANT("participant");

	private final String identifier;
	
	Role(String identifier) {
		 this.identifier = identifier;
	}
	
	public String toString() {
        return identifier;
    }
	
	 public static String getName(String value){
		 Role[] values = Role.values();
	        String enumValue = null;
	        for(Role eachValue : values) {
	        	System.out.println(eachValue);
	            enumValue = eachValue.toString();

	            if (enumValue.equalsIgnoreCase(value)) {
	            	System.out.println("name===>"+eachValue.name());
	                return eachValue.name();
	            }
	        }
	        return enumValue;
	    }

}
