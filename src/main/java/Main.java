import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import edu.mit.csail.sdg.alloy4compiler.parser.CompModule;
import entities.interfaces.ISite;
import utils.SiteAnalyser;

/**
 * Created by edmondvanovertveldt on 03/01/15.
 */
public class Main {

    /*
     * Url de base du site
     */
    private static String BASEURL = "http://www.ter.sncf.com/alsace";

    public static void main(String[] args) throws Exception {
        // Parse du site
        Multimap<String,String> data = ArrayListMultimap.create();
        // Recherche horaire
        data.put("Departure.Name","Lille Flandres");
        data.put("Arrival.Name", "Strasbourg");
        data.put("Via.Name","Paris-Nord");
        //recherche gare
        data.put("Form.Field", "Lille Flandres");
        //Bulletin de retard
        data.put("DepartureStation.Name", "Lille Flandres");
        data.put("ArrivalStation.Name", "Strasbourg");
        // Fiche horaire
        data.put("Station", "");
        // Calculer un prix
        data.put("DepartureStation", "Lille Flandres");
        data.put("ArrivalStation", "Strasbourg");

        ISite site = SiteAnalyser.analyse(BASEURL, data);

        System.out.println(site);

        // Génération fichier alloy

        // Génération path alloy(test)

        // Génération du chemin avec alloy(test)

        // lancement de selenium(path, test)
    }
}
