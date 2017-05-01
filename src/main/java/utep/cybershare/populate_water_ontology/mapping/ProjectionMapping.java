package utep.cybershare.populate_water_ontology.mapping;

import java.util.HashMap;
import java.util.Map;

public class ProjectionMapping {

	private Map<String, String> proMap;
	public ProjectionMapping(){
		proMap = new HashMap<String, String>();

		proMap.put("climate_selection","hasClimateSelection");
		proMap.put("human_intervention","hasHumanIntervention");
	}
	public Map<String, String> getProMap() {
		return proMap;
	}
	public void setProMapp(Map<String, String> proMap) {
		this.proMap = proMap;
	}

}
