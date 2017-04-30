package utep.cybershare.populate_water_ontology.classes;

import java.util.Map;

public class mList {
	Map<String, Parameter> modelInputs;

	public Map<String, Parameter> getDescriptor() {
		return modelInputs;
	}

	public void setDescriptor(Map<String, Parameter> descriptor) {
		this.modelInputs = descriptor;
	}
}
