package utep.cybershare.populate_water_ontology;

import java.util.HashMap;
import java.util.Map;

public class ModelMapping {
	private Map<String, String> modelMap;
	public ModelMapping(){
		modelMap = new HashMap<String, String>();
		
		modelMap.put("id", "hasModelID");
		modelMap.put("modelName", "hasModelName");
//		modelMap.put("modelDescription", "sdfsdfsd");
		modelMap.put("dateCreated", "modelDateCreated");
		modelMap.put("dateModified", "modelDateModified");
//		modelMap.put("softwareAgent", "sdfsdfsd");
		modelMap.put("license", "hasModelLicense");
		modelMap.put("version", "hasModelVersion");
		modelMap.put("sponsor", "hasModelSponsor");
	}
	public Map<String, String> getModelMap() {
		return modelMap;
	}
	public void setModelMap(Map<String, String> modelMap) {
		this.modelMap = modelMap;
	}

}
