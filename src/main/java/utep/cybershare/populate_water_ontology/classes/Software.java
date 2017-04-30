package utep.cybershare.populate_water_ontology.classes;

public class Software {

	String softwareAgent;
	
	public String toString(){
		return getSoftwareAgent().replaceAll(" ", "_");
	}

	public String getSoftwareAgent() {
		return softwareAgent;
	}

	public void setSoftwareAgent(String softwareAgent) {
		this.softwareAgent = softwareAgent;
	}

}
