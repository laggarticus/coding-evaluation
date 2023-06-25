package com.aa.act.interview.org;

import java.util.UUID;

public class Employee {

	//  Identifier and all associated values/returns changed to UUID
	//  in order to mock a real world situation which would virtually
	//  guarantee unique identification for employees. Also makes 
	//  creating unique identifiers for this exercise easier - IRL
	//  this is something to discuss with the product owner to
	//  find what best achieves the desired result of the variable.
    private UUID identifier;
    private Name name;

    public Employee(UUID identifier, Name name) {
        if(name == null)
            throw new IllegalArgumentException("name cannot be null");
        this.identifier = identifier;
        this.name = name;
    }
    
    public UUID getIdentifier() {
        return identifier;
    }
    
    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString() + ": " + identifier;
    }
}
