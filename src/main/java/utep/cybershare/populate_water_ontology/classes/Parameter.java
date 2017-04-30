package utep.cybershare.populate_water_ontology.classes;

import java.net.URI;

public class Parameter {

	String paramName;
	String paramLabel;
	String paramUnit;
	URI paramDefaultValue;
	String paramDefaultSource;
	float maxValue;
	int minValue;
	String structType;
	int structDimension;
	String dataType;
	String definitionType;
	URI paramValue;
	
	public String toString(){
		return getParamName().replaceAll(" ", "_");
	}
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamLabel() {
		return paramLabel;
	}
	public void setParamLabel(String paramLabel) {
		this.paramLabel = paramLabel;
	}
	public String getParamUnit() {
		return paramUnit;
	}
	public void setParamUnit(String paramUnit) {
		this.paramUnit = paramUnit;
	}
	public URI getParamDefaultValue() {
		return paramDefaultValue;
	}
	public void setParamDefaultValue(URI paramDefaultValue) {
		this.paramDefaultValue = paramDefaultValue;
	}
	public String getParamDefaultSource() {
		return paramDefaultSource;
	}
	public void setParamDefaultSource(String paramDefaultSource) {
		this.paramDefaultSource = paramDefaultSource;
	}
	public float getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public String getStructType() {
		return structType;
	}
	public void setStructType(String structType) {
		this.structType = structType;
	}
	public int getStructDimension() {
		return structDimension;
	}
	public void setStructDimension(int structDimension) {
		this.structDimension = structDimension;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDefinitionType() {
		return definitionType;
	}
	public void setDefinitionType(String definitionType) {
		this.definitionType = definitionType;
	}
	public URI getParamValue() {
		return paramValue;
	}
	public void setParamValue(URI paramValue) {
		this.paramValue = paramValue;
	}
}
