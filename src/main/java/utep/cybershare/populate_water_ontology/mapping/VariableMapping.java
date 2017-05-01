package utep.cybershare.populate_water_ontology.mapping;

import java.util.HashMap;
import java.util.Map;

public class VariableMapping {

	private Map<String, String> varMap;
	public VariableMapping(){
		varMap = new HashMap<String, String>();

		varMap.put("name","hasVarName");
//		varMap.put("label","hasVarUnit");
		varMap.put("units","hasVarUnit");
	}
	public Map<String, String> getVarMap() {
		return varMap;
	}
	public void setVarMapp(Map<String, String> varMap) {
		this.varMap = varMap;
	}
}
