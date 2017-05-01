package utep.cybershare.populate_water_ontology.classes;

public class Variable {
	
	String name;
	String varLabel;
	String units;
	
	public String toString(){
		return name.replaceAll(" ", "_");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVarLabel() {
		return varLabel;
	}
	public void setVarLabel(String varLabel) {
		this.varLabel = varLabel;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}

}
