package utep.cybershare.populate_water_ontology.mapping;

import java.util.HashMap;
import java.util.Map;

public class InstitutionMapping {

	private Map<String, String> institutionMap;
	public InstitutionMapping(){
		institutionMap = new HashMap<String, String>();

		institutionMap.put("department", "hasDepartment");
		institutionMap.put("organization", "hasInstitutionName");
		institutionMap.put("city", "hasCity");
		institutionMap.put("country", "hasCountry");
		institutionMap.put("state", "hasState");
	}
	public Map<String, String> getPersonMap() {
		return institutionMap;
	}
	public void setPersonMap(Map<String, String> personMap) {
		this.institutionMap = personMap;
	}
}
