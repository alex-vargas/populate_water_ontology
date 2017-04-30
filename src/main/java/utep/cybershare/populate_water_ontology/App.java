package utep.cybershare.populate_water_ontology;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

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
		
    	populateParameters();
    	populateModel();
		String finalPathOWL = mOwl.saveToFile();
		System.out.println("Ontology saved to: " + finalPathOWL);

    }

	private static void populateModel() {
        Gson g = new Gson();
        String filename = "C:\\jsonParserCI\\bucket-meta-v2.json";
        
        JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(filename));
			Model[] result = g.fromJson(reader, Model[].class);
			if(result.length > 0)
				for(Model model : result)
					mOwl.addIndividualToOwl(model, "Model");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (java.lang.IllegalStateException e){
			e.printStackTrace();
		}
		finally{
			System.out.println("end of populating model");
		}
	}

	private static void populateParameters() {
        Gson g = new Gson();
        String filename = "C:\\jsonParserCI\\paramTest.json";
        
        JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(filename));
			mList result = g.fromJson(reader, mList.class);
			if(result.getDescriptor().size() > 0)
				for(Parameter param : result.getDescriptor().values())
					mOwl.addIndividualToOwl(param, "Parameter");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			System.out.println("end of populating params");
		}
	}
}
