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
	public Map<String, String> getInstitutionMap() {
		return institutionMap;
	}
	public void setInstitutionMap(Map<String, String> personMap) {
		this.institutionMap = personMap;
	}
}
