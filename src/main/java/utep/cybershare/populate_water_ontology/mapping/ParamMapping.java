package utep.cybershare.populate_water_ontology.mapping;

import java.util.HashMap;
import java.util.Map;

public final class ParamMapping {
	private Map<String, String> paramMap;
	public ParamMapping(){
		paramMap = new HashMap<String, String>();

		paramMap.put("paramName","hasParamName");
		paramMap.put("paramClassification","hasParamClassification");
		paramMap.put("paramUnit","hasUnit");
		paramMap.put("paramDefaultValue","hasDefaultValue");
		paramMap.put("paramDefaultSource","hasDefaultSource");
		paramMap.put("maxValue","hasMaxValue");
		paramMap.put("minValue","hasMinValue");
		paramMap.put("structType","hasStructType");
		paramMap.put("structDimension","hasStructDimension");
		paramMap.put("dataType","hasDataType");
		paramMap.put("definitionType","hasDefinitionType");
		paramMap.put("paramValue","hasValue");
	}
	public Map<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
}
