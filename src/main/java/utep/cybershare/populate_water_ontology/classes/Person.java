package utep.cybershare.populate_water_ontology.classes;

public class Person {

	String organization;
	String name;
	String email;
	
	//Not to be mapped to owl
	public String getOrganization() {
		return organization.replaceAll(" ", "_");
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String toString(){
		return getName().replaceAll(" ", "_");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
