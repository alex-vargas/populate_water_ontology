package utep.cybershare.populate_water_ontology;

import java.util.Date;

public class Model {
	
	int id;
	String modelName;
	String modelDescription;
	Date dateCreated;
	Date dateModified;
	String softwareAgent;
	String license;
	float version;
	String sponsor;
	
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public String getSoftwareAgent() {
		return softwareAgent;
	}
	public void setSoftwareAgent(String softwareAgent) {
		this.softwareAgent = softwareAgent;
	}
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
