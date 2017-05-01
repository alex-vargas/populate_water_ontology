package utep.cybershare.populate_water_ontology.classes;

public class ProjectionData {
	
	String climate_selection;
	String human_intervention;
	
	public String toString(){
		return getClimate_selection().substring(0, 5) + getHuman_intervention().substring(0, 5);
	}
	
	public String getClimate_selection() {
		return climate_selection;
	}
	public void setClimate_selection(String climate_selection) {
		this.climate_selection = climate_selection;
	}
	public String getHuman_intervention() {
		return human_intervention;
	}
	public void setHuman_intervention(String human_intervention) {
		this.human_intervention = human_intervention;
	}

}
