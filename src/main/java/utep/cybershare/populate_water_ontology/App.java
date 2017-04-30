package utep.cybershare.populate_water_ontology;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import utep.cybershare.populate_water_ontology.classes.Model;
import utep.cybershare.populate_water_ontology.classes.Parameter;
import utep.cybershare.populate_water_ontology.classes.Software;
import utep.cybershare.populate_water_ontology.classes.mList;

/**
 * Hello world!
 *
 */
public class App 
{
	static owlpopulation mOwl;
    public static void main( String[] args ) throws OWLOntologyCreationException
    {
		mOwl = new owlpopulation();
		
    	populate("Parameter", "C:\\jsonParserCI\\paramTest.json");
    	populate("Model", "C:\\jsonParserCI\\bucket-meta-v2.json");
    	populate("Simulation_software", "C:\\jsonParserCI\\bucket-meta-v2.json");
		String finalPathOWL = mOwl.saveToFile();
		System.out.println("Ontology saved to: " + finalPathOWL);

    }

	private static void populate(String className, String filename) {
        Gson g = new Gson();
        List<Object> mListObjects = new ArrayList<Object>();
        
        JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(filename));
			if(className.equals("Parameter")){
				mList result = g.fromJson(reader, mList.class);
				for(Parameter param : result.getDescriptor().values())
					mListObjects.add(param);
			}else if(className.equals("Model")){
				Model[] result = g.fromJson(reader, Model[].class);
				for(Model model : result)
					mListObjects.add(model);
			}else if(className.equals("Simulation_software")){
				Software[] result = g.fromJson(reader, Software[].class);
				for(Software model : result)
					mListObjects.add(model);
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
        String filename = "C:\\jsonParserCI\\paramTest.json";
      
}