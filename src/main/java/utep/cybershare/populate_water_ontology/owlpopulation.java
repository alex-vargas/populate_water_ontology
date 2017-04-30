package utep.cybershare.populate_water_ontology;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
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
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import utep.cybershare.populate_water_ontology.classes.Parameter;
import utep.cybershare.populate_water_ontology.mapping.ModelMapping;
import utep.cybershare.populate_water_ontology.mapping.ParamMapping;
import utep.cybershare.populate_water_ontology.mapping.SoftwareMapping;

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
		ontIRI = "http://www.semanticweb.org/aleja/ontologies/2017/3/water_model#";
		mManager = OWLManager.createOWLOntologyManager();
		mFactory = mManager.getOWLDataFactory();
		
		mFile = new File(ontSchemaPath);
		mOWLFile = mManager.loadOntologyFromOntologyDocument(mFile);
		prefixManager = new DefaultPrefixManager(ontIRI);
	}

	public void addIndividualToOwl(Object mObj, String classToMap) {
		OWLNamedIndividual mIndividual = null;
		if(classToMap.equals("Parameter"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + ((Parameter) mObj).getParamName(), prefixManager);
		else if(classToMap.equals("Model"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + "mModel", prefixManager);
		else if(classToMap.equals("Simulation_software"))
			mIndividual = mIndividual = mFactory.getOWLNamedIndividual(":" + "gams", prefixManager);
		
		addIndAxiom(mIndividual, classToMap);
		addDataProperty(mObj, mIndividual, classToMap);
	}

	private void addDataProperty(Object mObj, OWLNamedIndividual mIndividual, String classToMap) {
		Class<?> clazz = mObj.getClass();
		ParamMapping paramMap = null;
		ModelMapping modelMap = null;
		SoftwareMapping softwareMap = null;
		
		if(classToMap.equals("Parameter"))
			paramMap = new ParamMapping();
		else if(classToMap.equals("Model"))
			modelMap = new ModelMapping();
		else if(classToMap.equals("Simulation_software"))
			softwareMap = new SoftwareMapping();

		for(Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);

			try {
				if((paramMap != null && paramMap.getParamMap().containsKey(field.getName())) || 
						(modelMap != null)|| (softwareMap != null)){
					OWLDataProperty mDataProperty = null;
					if(classToMap.equals("Parameter"))
						mDataProperty = mFactory.getOWLDataProperty(":" + paramMap.getParamMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("Model"))
						mDataProperty = mFactory.getOWLDataProperty(":" + modelMap.getModelMap().get(field.getName()), prefixManager);
					else if(classToMap.equals("Simulation_software"))
						mDataProperty = mFactory.getOWLDataProperty(":" + softwareMap.getSoftwareMap().get(field.getName()), prefixManager);
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
				} else if(field.getName().equals("paramLabel")){
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
	

}
