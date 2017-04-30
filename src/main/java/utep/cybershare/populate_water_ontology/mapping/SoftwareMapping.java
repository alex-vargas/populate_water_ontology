package utep.cybershare.populate_water_ontology.mapping;

import java.util.HashMap;
import java.util.Map;

public class SoftwareMapping {
	private Map<String, String> softwareMap;
	public SoftwareMapping(){
		softwareMap = new HashMap<String, String>();
		
		softwareMap.put("softwareAgent", "hasSwName");
	}
	public Map<String, String> getSoftwareMap() {
		return softwareMap;
	}
	public void setSoftwareMap(Map<String, String> softwareMap) {
		this.softwareMap = softwareMap;
	}

}
