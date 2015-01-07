package utils;

import com.google.common.collect.Multimap;
import entities.*;
import entities.interfaces.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.parser.HTMLParser;
import utils.random.RandomString;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static utils.parser.HTMLParser.getContentToUrlGet;
import static utils.parser.HTMLParser.getContentToUrlPost;

/** Analyseur de site
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class SiteAnalyser {

    /**
     * Analyse un site
     * @param url url de base du site
     * @param data, ensemble de données possibles pour chaque champs input
     * @return le site
     * @throws Exception
     */
    public static ISite analyse(String url, Multimap<String,String> data) throws Exception {
        String[] splitUrl = url.split("//");
        String domaine = splitUrl[1].split("/")[0];

        ISite site = new Site(splitUrl[0] + "//" + domaine);
        SiteAnalyser.addPageAndTheirLinkedPages(site, url, "get", data, null);
        return site ;
    }

    //region -- Méthodes privées

    /**
     * Ajoute une page et ses relations à un site
     * @param site un site
     * @param url url de la page
     * @param httpMethod method http à utiliser
     * @param data, ensemble de données possibles pour chaque champs input
     * @param dataToSend les données à envoyer à la page correspondante à l'url
     * @throws Exception
     */
    private static void addPageAndTheirLinkedPages(ISite site , String url, String httpMethod, Multimap<String,String> data, Map<String,String> dataToSend) throws Exception {
        IPage page ;
        int index;

        if((index = url.lastIndexOf("?")) != -1){
            // Présence de paramètres dans l'url
            page = new Page(url.substring(0, index));
        }else {
            page = new Page(url);
        }

        if(!site.getPages().contains(page)) {
            // Page non existante dans l'objet site
            page = SiteAnalyser.constructPage(url, httpMethod, dataToSend);
            site.addPage(page);

            // ajoute toutes ses relations
            for (ILink link : page.getLinks()) {
                IPage p = new Page(site.getBaseUrl() + link.getUrlToRedirect());
                if (!site.getPages().contains(p)) {
                    // Page non référencée
                    SiteAnalyser.addPageAndTheirLinkedPages(site, site.getBaseUrl() + link.getUrlToRedirect(), "get", data, null);
                }
            }

            for (IForm form : page.getFormulaires()) {
                IPage p = new Page(site.getBaseUrl() + form.getActionUrl());
                if (!site.getPages().contains(p)) {
                    // Page non visitée
                    Map<String, String> dataToSendForRedirectPage = GenerateFormData(form, data);
                    SiteAnalyser.addPageAndTheirLinkedPages(site, site.getBaseUrl() + form.getActionUrl(), "post", data, dataToSendForRedirectPage);
                }
            }
        }
    }

    /**
     * Construit une page
     * @param url url de a page
     * @param httpMethod methode http (post/get)
     * @return une page
     * @throws Exception
     */
    private static IPage constructPage(String url, String httpMethod, Map<String,String> data) throws Exception {
        Thread.sleep(3000);
        System.out.println("> analyse de la page : " + url + " en cours..");

        IPage page ;
        int index;

        if((index = url.lastIndexOf("?")) != -1){
            // Présence de paramètres dans l'url
            page = new Page(url.substring(0, index));
        }else {
            page = new Page(url);
        }

        Document doc = null;

        // Obtient le code source de la page
        if(httpMethod.equals("post")){
            // Méthode post avec date
            doc = getContentToUrlPost(url, data);
        }else{
            // Méthode get
            doc = getContentToUrlGet(url);
        }

        // Ajout des liens
        Elements links = HTMLParser.getAllLink(doc);
        for(Element link : links){
            ILink l = new Link(HTMLParser.getAttributeValue(link,"href"));
            page.addLink(l);
        }

        // Ajout des formulaires
        Elements forms = HTMLParser.getAllForm(doc);
        for(Element form : forms){
            IForm f = new Form(HTMLParser.getAttributeValue(form, "action"), HTMLParser.getAttributeValue(form, "method"));
            // Ajout des inputs

            for(Element input : HTMLParser.getAllInput(form)){
                IInput i = new Input(HTMLParser.getAttributeValue(input, "type"), HTMLParser.getAttributeValue(input, "name") ,HTMLParser.getAttributeValue(input, "value"));
                f.addInput(i);
            }

            // Ajout des textareas
            for(Element textarea : HTMLParser.getAllTextarea(form)){
                ITextarea t = new Textarea(HTMLParser.getAttributeValue(textarea, "id"));
                f.addTextarea(t);
            }

            // Ajout des selects
            for(Element select : HTMLParser.getAllSelect(form)){
                ISelect s = new Select(HTMLParser.getAttributeValue(select,"id"), HTMLParser.getAttributeValue(select,"name"));
                for(Element option : select.select("option")){
                    s.addOption(HTMLParser.getAttributeValue(option,"value"));
                }
                f.addSelect(s);
            }

            page.addForm(f);
        }

        return page;
    }

    /**
     * Retourne un ensemble de données-valeur possible pour un formulaire
     * @param form le formulaire
     * @param data ensemble de données possibles pour chaque élément
     * @return
     */
    private static Map<String, String> GenerateFormData(IForm form, Multimap<String, String> data){
        Map<String, String> dataToSend = new HashMap<>() ;

        // Les inputs
        for(IInput input : form.getInputs()){
            if(data.containsKey(input.getName())){
                // une liste de valeur est suggéré pour cet input
                Object[] possibleValues = data.get(input.getName()).toArray();
                Random r = new Random();
                dataToSend.put(input.getName(), (String) possibleValues[r.nextInt(possibleValues.length)]);
            }
            else{
                // valeur aléatoire
                RandomString randomString = new RandomString(8);
                if(input.getType().equals("email")) {
                    // type email
                    dataToSend.put(input.getName(), randomString.nextString() + "@yopmail.com") ;
                }
                else if (input.getType().equals("text") || input.getType().equals("password")) {
                    // type text ou password
                    dataToSend.put(input.getName(), input.getValue() != null ? input.getValue() : randomString.nextString()) ;
                }
                else if(input.getType().equals("hidden")){
                    dataToSend.put(input.getName(), input.getValue());
                }
            }
        }

        // Les selects
        for (ISelect select : form.getSelects()){
            Random r = new Random();
            data.put(select.getName(), select.getOptions().get(r.nextInt(select.getOptions().size())));
        }

        return dataToSend;
    }

    //endregion
}
