package utep.cybershare.populate_water_ontology.classes;

public class ProjectionData {
	
	String name;
	String value;
	
	public String toString(){
		return getName().replaceAll(" ", "_");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
