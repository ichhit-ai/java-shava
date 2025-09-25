
package edu.ccrm.domain;

import java.time.LocalDateTime;

public abstract class Person {
    protected final String id;
    protected String fullName;
    protected String email;
    protected LocalDateTime createdAt;

    public Person(String id, String fullName, String email){
        assert id != null;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public String getId(){ return id; }
    public String getFullName(){ return fullName; }
    public String getEmail(){ return email; }
    public void setFullName(String name){ this.fullName = name; }
    public void setEmail(String email){ this.email = email; }

    @Override
    public String toString(){
        return String.format("%s (%s) <%s>", fullName, id, email);
    }
}
