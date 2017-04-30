package utep.cybershare.populate_water_ontology.classes;

import java.sql.Timestamp;

public class Model {
	
	int id;
	String modelName;
	String modelDescription;
	Timestamp dateCreated;
	Timestamp dateModified;
//	String softwareAgent;
	String license;
	float version;
	String sponsor;
	
	public String toString(){
		return getModelName().replaceAll(" ", "_");
	}
	
	public int get_id() {
		return id;
	}
	public void set_id(int _id) {
		this.id = _id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelDescription() {
		return modelDescription;
	}
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Timestamp getDateModified() {
		return dateModified;
	}
	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}
//	public String getSoftwareAgent() {
//		return softwareAgent;
//	}
//	public void setSoftwareAgent(String softwareAgent) {
//		this.softwareAgent = softwareAgent;
//	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public float getVersion() {
		return version;
	}
	public void setVersion(float version) {
		this.version = version;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

}
