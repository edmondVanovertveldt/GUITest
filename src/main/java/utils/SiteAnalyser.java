package utils;

import entities.*;
import entities.interfaces.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.parser.HTMLParser;

import javax.print.Doc;
import java.util.HashMap;
import java.util.Map;

import static utils.parser.HTMLParser.getContentToUrlGet;
import static utils.parser.HTMLParser.getContentToUrlPost;

/** Analyseur de site
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class SiteAnalyser {

    /**
     * Analyse un site
     * @param url url de base du site
     * @return le site
     * @throws Exception
     */
    public static ISite analyse(String url) throws Exception {
        String[] splitUrl = url.split("//");
        String domaine = splitUrl[1].split("/")[0];

        ISite site = new Site(splitUrl[0] + "//" + domaine);
        SiteAnalyser.addPageAndTheirLinkedPages(site, url);
        return site ;
    }

    /**
     * Ajoute une page et ses relations à un site
     * @param site un site
     * @param url url de la page
     * @throws Exception
     */
    private static void addPageAndTheirLinkedPages(ISite site , String url, String httpMethod) throws Exception {
        // Ajoute la page au site (toutes les 5 sec minimum)
        Thread.sleep(5000);
        IPage page = SiteAnalyser.constructPage(url, httpMethod);
        site.addPage(page);

        // ajoute toutes ses relations
        for(ILink link : page.getLinks()){
            IPage p = new Page(site.getBaseUrl() + link.getUrlToRedirect());
            if(!site.getPages().contains(p)){
                // Page non référencée
                SiteAnalyser.addPageAndTheirLinkedPages(site, site.getBaseUrl() + link.getUrlToRedirect(), "get");
            }
        }

        for(IForm form : page.getFormulaires()){
            IPage p = new Page(site.getBaseUrl() + form.getActionUrl());
            if(!site.getPages().contains(p)){
                // Page non référencée
                Map<String, String> data = new HashMap<>() ;
                // TODO Ajout des données via la method post
                for(form.getInputs())
                data.put()
                SiteAnalyser.addPageAndTheirLinkedPages(site, site.getBaseUrl() + form.getActionUrl(), "post");
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
        IPage page = new Page(url);
        Document doc = null;

        // Obtient le code source de la page
        if(httpMethod.equals("post")){
            // Post method
            doc = getContentToUrlPost(url, data);
        }else{
            // get method
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
            // TODO textarea a compléter
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
}
