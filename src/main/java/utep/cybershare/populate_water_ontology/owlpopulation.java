package utep.cybershare.populate_water_ontology;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import utep.cybershare.populate_water_ontology.classes.Institution;
import utep.cybershare.populate_water_ontology.classes.Model;
import utep.cybershare.populate_water_ontology.classes.Parameter;
import utep.cybershare.populate_water_ontology.classes.Person;
import utep.cybershare.populate_water_ontology.classes.ProjectionData;
import utep.cybershare.populate_water_ontology.classes.Software;
import utep.cybershare.populate_water_ontology.classes.Variable;
import utep.cybershare.populate_water_ontology.mapping.InstitutionMapping;
import utep.cybershare.populate_water_ontology.mapping.ModelMapping;
import utep.cybershare.populate_water_ontology.mapping.ParamMapping;
import utep.cybershare.populate_water_ontology.mapping.PersonMapping;
import utep.cybershare.populate_water_ontology.mapping.ProjectionMapping;
import utep.cybershare.populate_water_ontology.mapping.SoftwareMapping;
import utep.cybershare.populate_water_ontology.mapping.VariableMapping;

public class owlpopulation {
	String ontFile;
	String ontSchemaPath;
	String ontIRI;
	OWLOntologyManager mManager;
	OWLDataFactory mFactory;	
	File mFile;
	OWLOntology mOWLFile;
	PrefixManager prefixManager;

	public owlpopulation() throws OWLOntologyCreationException {
		ontFile = "water_model_populated.owl";
		ontSchemaPath = "C:\\Users\\aleja\\Dropbox\\Info\\PhD\\UTEP\\Cyberinfrastructure applications\\Project2\\water_model.owl";
		ontIRI = "http://ontology.cybershare.utep.edu/fma/watermodelgams#";
		mManager = OWLManager.createOWLOntologyManager();
		mFactory = mManager.getOWLDataFactory();
		
		mFile = new File(ontSchemaPath);
		mOWLFile = mManager.loadOntologyFromOntologyDocument(mFile);
		prefixManager = new DefaultPrefixManager(ontIRI);
	}

	public void addIndividualToOwl(Object mObj, String classToMap) {
		OWLNamedIndividual mIndividual = null;
		if(classToMap.equals("Parameter"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + (Parameter) mObj, prefixManager);
		else if(classToMap.equals("Model"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + (Model) mObj, prefixManager);
		else if(classToMap.equals("Simulation_software"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + (Software) mObj, prefixManager);
		else if(classToMap.equals("Person"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + (Person) mObj, prefixManager);
		else if(classToMap.equals("Institution"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + (Institution) mObj, prefixManager);
		else if(classToMap.equals("Variable"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + (Variable) mObj, prefixManager);
		else if(classToMap.equals("ProjectionData"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + (ProjectionData) mObj, prefixManager);
		
		addIndAxiom(mIndividual, classToMap);
		addDataProperty(mObj, mIndividual, classToMap);
	}

	private void addDataProperty(Object mObj, OWLNamedIndividual mIndividual, String classToMap) {
		Class<?> clazz = mObj.getClass();
		ParamMapping paramMap = null;
		ModelMapping modelMap = null;
		SoftwareMapping softwareMap = null;
		PersonMapping personMap = null;
		InstitutionMapping institutionMap = null;
		VariableMapping varMap = null;
		ProjectionMapping proMap = null;
		
		if(classToMap.equals("Parameter"))
			paramMap = new ParamMapping();
		else if(classToMap.equals("Model"))
			modelMap = new ModelMapping();
		else if(classToMap.equals("Simulation_software"))
			softwareMap = new SoftwareMapping();
		else if(classToMap.equals("Person"))
			personMap = new PersonMapping();
		else if(classToMap.equals("Institution"))
			institutionMap = new InstitutionMapping();
		else if(classToMap.equals("Variable"))
			varMap = new VariableMapping();
		else if(classToMap.equals("ProjectionData"))
			proMap = new ProjectionMapping();

		for(Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				if((paramMap != null && paramMap.getParamMap().containsKey(field.getName())) || 
						(modelMap != null) || (softwareMap != null) || (personMap != null) || (institutionMap != null) ||
						(varMap != null && varMap.getVarMap().containsKey(field.getName())) || (proMap != null)){
					OWLDataProperty mDataProperty = null;
					if(classToMap.equals("Parameter") && paramMap.getParamMap().containsKey(field.getName()) &&
							field.get(mObj) != null)
						mDataProperty = mFactory.getOWLDataProperty(":" + paramMap.getParamMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("Model") && modelMap.getModelMap().containsKey(field.getName()))
						mDataProperty = mFactory.getOWLDataProperty(":" + modelMap.getModelMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("Simulation_software") && 
							softwareMap.getSoftwareMap().containsKey(field.getName()))
						mDataProperty = mFactory.getOWLDataProperty(":" + softwareMap.getSoftwareMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("Person") && personMap.getPersonMap().containsKey(field.getName()))
						mDataProperty = mFactory.getOWLDataProperty(":" + personMap.getPersonMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("Institution") &&
							institutionMap.getInstitutionMap().containsKey(field.getName()))
						mDataProperty = mFactory.getOWLDataProperty(":" + institutionMap.getInstitutionMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("Variable") &&
							varMap.getVarMap().containsKey(field.getName()))
						mDataProperty = mFactory.getOWLDataProperty(":" + varMap.getVarMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("ProjectionData") &&
							proMap.getProMap().containsKey(field.getName()))
						mDataProperty = mFactory.getOWLDataProperty(":" + proMap.getProMap().get(field.getName()), prefixManager);
					
					if(mDataProperty != null){
						OWLAxiom axiom = null;
						
						OWLLiteral dataLiteral = null;
	
								
						if(field.getType().getSimpleName().toString().equals("String"))
							dataLiteral = mFactory.getOWLLiteral(field.get(mObj).toString(),OWL2Datatype.XSD_STRING);
						else if(field.getType().getSimpleName().toString().equals("float"))
							dataLiteral = mFactory.getOWLLiteral(field.get(mObj).toString(),OWL2Datatype.XSD_DECIMAL);
						else if(field.getType().getSimpleName().toString().equals("int"))
							dataLiteral = mFactory.getOWLLiteral(field.get(mObj).toString(),OWL2Datatype.XSD_INT);
						else if(field.getType().getSimpleName().toString().equals("URI"))
							dataLiteral = mFactory.getOWLLiteral(field.get(mObj).toString(),OWL2Datatype.XSD_ANY_URI);
						else if(field.getType().getSimpleName().toString().equals("Date"))
							dataLiteral = mFactory.getOWLLiteral(field.get(mObj).toString(),OWL2Datatype.XSD_DATE_TIME);
						else if(field.getType().getSimpleName().toString().equals("Timestamp"))
							dataLiteral = mFactory.getOWLLiteral(field.get(mObj).toString(),OWL2Datatype.XSD_DATE_TIME_STAMP);
	
						axiom = mFactory.getOWLDataPropertyAssertionAxiom(mDataProperty, mIndividual, dataLiteral);
						mManager.applyChange(new AddAxiom(mOWLFile, axiom));
					}
				} else if(field.getName().equals("paramLabel") || field.getName().equals("varLabel")){
					OWLAnnotation mAnnotationLabel = mFactory.getOWLAnnotation(mFactory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI()),
							mFactory.getOWLLiteral((String) field.get(mObj)));
					OWLAxiom axiom = mFactory.getOWLAnnotationAssertionAxiom(mIndividual.getIRI(), mAnnotationLabel);
					mManager.applyChange(new AddAxiom(mOWLFile, axiom));
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Set all individuals as unique
	 * @author Alejandro Vargas
	 */
	private void setIndividualsAsUnique() {
		Set<OWLNamedIndividual> individuals = mOWLFile.getIndividualsInSignature();
		OWLDifferentIndividualsAxiom differentIndividualAxiom = mFactory.getOWLDifferentIndividualsAxiom(individuals);
		mManager.applyChange(new AddAxiom(mOWLFile, differentIndividualAxiom));
	}

	public String saveToFile() {
		File mOutputFile = new File(ontFile);
		try{
			mOutputFile.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(mOutputFile);
			mManager.saveOntology(mOWLFile, outputStream);
		}catch(Exception e){//IOException, OWLOntologyStorageException
			
		}
		return mOutputFile.getAbsolutePath();
	}

	private void addIndAxiom(OWLNamedIndividual mIndividual, String classToMap) {
		OWLClass mClass = mFactory.getOWLClass(":" + classToMap, prefixManager);
		OWLAxiom mAxiom = mFactory.getOWLClassAssertionAxiom(mClass, mIndividual);
		mManager.applyChange(new AddAxiom(mOWLFile, mAxiom));
	}

	public void addObjectPropertyToOwl(List<Parameter> mParameterList, List<Model> mModelList,
			List<Software> mSoftwareList, List<Person> mPersonList, List<Institution> mInstitutionList,
			List<Variable> mVariableList, List<ProjectionData> mProjectionList) {
		//create isMemberOf object property
		if(!mInstitutionList.isEmpty() && !mPersonList.isEmpty()){
			for(Person mPersonInstance : mPersonList){
				OWLNamedIndividual institutionIndividual = mFactory.getOWLNamedIndividual(":" + mPersonInstance.getOrganization(), prefixManager);
				OWLNamedIndividual personIndividual = mFactory.getOWLNamedIndividual(":" + mPersonInstance, prefixManager);
				OWLObjectProperty isMemberOf = mFactory.getOWLObjectProperty(":isMemberOf", prefixManager);
				OWLAxiom mAxiom = mFactory.getOWLObjectPropertyAssertionAxiom(isMemberOf, personIndividual, institutionIndividual);
				mManager.applyChange(new AddAxiom(mOWLFile, mAxiom));
			}
		}
		//create hasModelCreator object property
		if(!mPersonList.isEmpty() && !mModelList.isEmpty()){
			for(Person mPersonInstance : mPersonList){
				OWLNamedIndividual modelIndividual = mFactory.getOWLNamedIndividual(":" + mModelList.get(0), prefixManager);
				OWLNamedIndividual personIndividual = mFactory.getOWLNamedIndividual(":" + mPersonInstance, prefixManager);
				OWLObjectProperty hasModelCreator = mFactory.getOWLObjectProperty(":hasModelCreator", prefixManager);
				OWLAxiom mAxiom = mFactory.getOWLObjectPropertyAssertionAxiom(hasModelCreator, modelIndividual, personIndividual);
				mManager.applyChange(new AddAxiom(mOWLFile, mAxiom));
			}			
		}
		//create hasInput object property
		if(!mParameterList.isEmpty() && !mModelList.isEmpty()){
			for(Parameter mParameterInstance : mParameterList){
				OWLNamedIndividual modelIndividual = mFactory.getOWLNamedIndividual(":" + mModelList.get(0), prefixManager);
				OWLNamedIndividual parameterIndividual = mFactory.getOWLNamedIndividual(":" + mParameterInstance, prefixManager);
				OWLObjectProperty hasInput = mFactory.getOWLObjectProperty(":hasInput", prefixManager);
				OWLAxiom mAxiom = mFactory.getOWLObjectPropertyAssertionAxiom(hasInput, modelIndividual, parameterIndividual);
				mManager.applyChange(new AddAxiom(mOWLFile, mAxiom));
			}			
		}
		if(!mProjectionList.isEmpty() && !mModelList.isEmpty()){
			for(ProjectionData mProjectionInstance : mProjectionList){
				OWLNamedIndividual modelIndividual = mFactory.getOWLNamedIndividual(":" + mModelList.get(0), prefixManager);
				OWLNamedIndividual projectionIndividual = mFactory.getOWLNamedIndividual(":" + mProjectionInstance, prefixManager);
				OWLObjectProperty hasInput = mFactory.getOWLObjectProperty(":hasInput", prefixManager);
				OWLAxiom mAxiom = mFactory.getOWLObjectPropertyAssertionAxiom(hasInput, modelIndividual, projectionIndividual);
				mManager.applyChange(new AddAxiom(mOWLFile, mAxiom));
			}			
		}
		//create hasOutput object property
		if(!mVariableList.isEmpty() && !mModelList.isEmpty()){
			for(Variable mVarInstance : mVariableList){
				OWLNamedIndividual modelIndividual = mFactory.getOWLNamedIndividual(":" + mModelList.get(0), prefixManager);
				OWLNamedIndividual varIndividual = mFactory.getOWLNamedIndividual(":" + mVarInstance, prefixManager);
				OWLObjectProperty hasInput = mFactory.getOWLObjectProperty(":hasOutput", prefixManager);
				OWLAxiom mAxiom = mFactory.getOWLObjectPropertyAssertionAxiom(hasInput, modelIndividual, varIndividual);
				mManager.applyChange(new AddAxiom(mOWLFile, mAxiom));
			}			
		}
		//create isSimulatedBy/runsA object property
		if(!mSoftwareList.isEmpty() && !mModelList.isEmpty()){
			OWLNamedIndividual modelIndividual = mFactory.getOWLNamedIndividual(":" + mModelList.get(0), prefixManager);
			OWLNamedIndividual softwareIndividual = mFactory.getOWLNamedIndividual(":" + mSoftwareList.get(0), prefixManager);
			OWLObjectProperty isSimulatedBy = mFactory.getOWLObjectProperty(":isSimulatedBy", prefixManager);
			OWLAxiom mAxiom = mFactory.getOWLObjectPropertyAssertionAxiom(isSimulatedBy, modelIndividual, softwareIndividual);
			mManager.applyChange(new AddAxiom(mOWLFile, mAxiom));
			
			OWLObjectProperty runsA = mFactory.getOWLObjectProperty(":runsA", prefixManager);
			OWLAxiom mAxiom2 = mFactory.getOWLObjectPropertyAssertionAxiom(runsA, softwareIndividual, modelIndividual);
			mManager.applyChange(new AddAxiom(mOWLFile, mAxiom2));
		}
	}
	

}
