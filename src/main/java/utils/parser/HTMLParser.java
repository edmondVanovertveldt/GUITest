package utils.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

/** Parseur HTML
 * Created by edmondvanovertveldt on 03/01/15.
 */
public class HTMLParser {

    //region -- Méthodes

    /**
     * Retourne le contenu d'une page à partir de son url via une methode HTTP GET
     * @param url, l'url de la page
     * @return le contenu de la page
     * @throws MalformedURLException - if the request URL is not a HTTP or HTTPS URL, or is otherwise malformed
     * @throws HttpStatusException - if the response is not OK and HTTP response errors are not ignored
     * @throws UnsupportedMimeTypeException - if the response mime type is not supported and those errors are not ignored
     * @throws SocketTimeoutException - if the connection times out
     * @throws IOException - on error
     */
    public static Document getContentToUrlGet(String url) throws Exception {
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

        return doc;
    }

    /**
     * Retourne le contenu d'une page à partir de son url via une methode HTTP POST
     * @param url url de la page
     * @param data les données du formulaire
     * @return le contenu html de la page
     * @throws Exception
     */
    public static Document getContentToUrlPost(String url, Map<String,String> data) throws Exception {
        Document doc = Jsoup.connect(url).data(data).userAgent("Mozilla").post();

        return doc;
    }



    //region -- Document

    /**
     * Retourne tous les liens d'une page
     * @param doc le contenu de la page
     * @return tous les liens de la page
     */
    public static Elements getAllLink(Document doc) {
        return doc.select("a[href~=^\\/[^\\/].*]");
    }

    /**
     * Retourne tous les formulaires d'une page
     * @param doc, le contenu html de la page
     * @return tous les formulaires de la page
     */
    public static Elements getAllForm(Document doc){
        return doc.select("form[action~=^\\/[^\\/].*]");
    }

    //endregion

    //region -- Element

    /**
     * Retourne tous les inputs d'un element
     * @param elmt un formulaire
     * @return tous les inputs
     */
    public static Elements getAllInput(Element elmt){
        return elmt.select("input");
    }

    /**
     * Retourne tous les selects d'un element
     * @param elmt un formulaire
     * @return tous les selects
     */
    public static Elements getAllSelect(Element elmt){
        return elmt.select("select");
    }

    /**
     * Retourne tous les elements textarea d'un element
     * @param elmt
     * @return tous les elements textarea
     */
    public static Elements getAllTextarea(Element elmt){
        return elmt.select("textarea");
    }

    /**
     * Retourne la valeur de l'attribut d'un element
     * @param elmt element
     * @param attr attribut
     * @return la valeur de l'attribut
     */
    public static String getAttributeValue(Element elmt, String attr){
        return elmt.attr(attr);
    }

    //endregion

    //endregion
}
