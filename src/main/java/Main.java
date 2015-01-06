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
        ISite site = SiteAnalyser.analyse(BASEURL);

        System.out.println(site);

        // Génération fichier alloy

        // Génération path alloy(test)

        // Génération du chemin avec alloy(test)

        // lancement de selenium(path, test)
    }
}
