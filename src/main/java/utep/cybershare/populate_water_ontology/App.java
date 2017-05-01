package utep.cybershare.populate_water_ontology;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import utep.cybershare.populate_water_ontology.classes.Institution;
import utep.cybershare.populate_water_ontology.classes.InstitutionList;
import utep.cybershare.populate_water_ontology.classes.Model;
import utep.cybershare.populate_water_ontology.classes.Parameter;
import utep.cybershare.populate_water_ontology.classes.Person;
import utep.cybershare.populate_water_ontology.classes.PersonList;
import utep.cybershare.populate_water_ontology.classes.ProjectionData;
import utep.cybershare.populate_water_ontology.classes.Software;
import utep.cybershare.populate_water_ontology.classes.Variable;
import utep.cybershare.populate_water_ontology.classes.mList;

/**
 * Hello world!
 *
 */
public class App 
{
	static owlpopulation mOwl;
	private static List<Institution> mInstitutionList;
	private static List<Person> mPersonList;
	private static List<Parameter> mParameterList;
	private static List<Variable> mVariableList;
	private static List<Model> mModelList;
	private static List<Software> mSoftwareList;
	private static List<ProjectionData> mProjectionList;
	
    public static void main( String[] args ) throws OWLOntologyCreationException
    {
    	mInstitutionList = new ArrayList<Institution>();
    	mPersonList = new ArrayList<Person>();
    	mParameterList = new ArrayList<Parameter>();
    	mModelList = new ArrayList<Model>();
    	mSoftwareList = new ArrayList<Software>();
    	mVariableList = new ArrayList<Variable>();
    	mProjectionList = new ArrayList<ProjectionData>();
    	
		mOwl = new owlpopulation();
		String bucketMetaFilePath = "C:\\jsonParserCI\\bucket-meta-v2.json";
		String parametersFilePath = "C:\\jsonParserCI\\paramTest.json";
		String variablesFilePath = "C:\\jsonParserCI\\varTest.json";
		String projectionFilePath = "C:\\jsonParserCI\\projectionType.json";

    	populate("Institution", bucketMetaFilePath);
    	populate("Person", bucketMetaFilePath);
    	populate("Parameter", parametersFilePath);
    	populate("Variable", variablesFilePath);
    	populate("Model", bucketMetaFilePath);
    	populate("Simulation_software", bucketMetaFilePath);
    	populate("ProjectionData", projectionFilePath);
    	populate(null, null);
		String finalPathOWL = mOwl.saveToFile();
		System.out.println("Ontology saved to: " + finalPathOWL);

    }

	private static void populate(String className, String filename) {
		if(className == null && filename == null){
			mOwl.addObjectPropertyToOwl(mParameterList, mModelList, mSoftwareList, mPersonList, mInstitutionList,
					mVariableList, mProjectionList);
			return;
		}
        Gson g = new Gson();
        List<Object> mListObjects = new ArrayList<Object>();
        
        JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(filename));
			if(className.equals("Parameter")){
				mList result = g.fromJson(reader, mList.class);
				for(Parameter param : result.getDescriptor().values()){
					mListObjects.add(param);
					mParameterList.add(param);
				}
			}else if(className.equals("Model")){
				Model[] result = g.fromJson(reader, Model[].class);
				for(Model model : result){
					mListObjects.add(model);
					mModelList.add(model);
				}
			}else if(className.equals("Simulation_software")){
				Software[] result = g.fromJson(reader, Software[].class);
				for(Software sw : result){
					mListObjects.add(sw);
					mSoftwareList.add(sw);
				}
			}else if(className.equals("Person")){
				PersonList[] result = g.fromJson(reader, PersonList[].class);
				for(PersonList personArray : result){
					for(Person person : personArray.getCreators()){
						mListObjects.add(person);
						mPersonList.add(person);
					}
				}
			}else if(className.equals("Institution")){
				InstitutionList[] result = g.fromJson(reader, InstitutionList[].class);
				for(InstitutionList institutionArray : result){
					for(Institution institution : institutionArray.getCreators()){
						mListObjects.add(institution);
						mInstitutionList.add(institution);
					}
				}
			}else if(className.equals("Variable")){
				Variable[] result = g.fromJson(reader, Variable[].class);
				for(Variable variable : result){
					mListObjects.add(variable);
					mVariableList.add(variable);
				}
			}else if(className.equals("ProjectionData")){
				ProjectionData[] result = g.fromJson(reader, ProjectionData[].class);
				for(ProjectionData projData : result){
					mListObjects.add(projData);
					mProjectionList.add(projData);
				}
			}
	    				
			if(mListObjects.size() > 0)
				for(Object mObject : mListObjects)
					mOwl.addIndividualToOwl(mObject, className);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (java.lang.IllegalStateException e){
			e.printStackTrace();
		}
		finally{
			System.out.println("end of populating " + className);
		}
	}      
}
