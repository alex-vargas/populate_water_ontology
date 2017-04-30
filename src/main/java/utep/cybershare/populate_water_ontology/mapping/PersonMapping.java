package utep.cybershare.populate_water_ontology.mapping;

import java.util.HashMap;
import java.util.Map;

public class PersonMapping {

	private Map<String, String> personMap;
	public PersonMapping(){
		personMap = new HashMap<String, String>();

		personMap.put("email", "hasEmail");
		personMap.put("name", "hasName");
		
	}
	public Map<String, String> getPersonMap() {
		return personMap;
	}
	public void setPersonMap(Map<String, String> personMap) {
		this.personMap = personMap;
	}
}
