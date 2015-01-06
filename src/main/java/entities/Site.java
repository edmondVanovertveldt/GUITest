package entities;

import entities.interfaces.IPage;

import java.util.LinkedList;
import java.util.List;

/**
 * Objet représentant un site
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Site implements entities.interfaces.ISite {
    /**
     * Ensemble de page
     */
    private List<IPage> pages ;

    /**
     * Url de base
     */
    private String baseUrl;

    /**
     * Construteur de site
     * @param baseUrl l'url de base du site
     */
    public Site(String baseUrl){
        if(baseUrl.endsWith("/")){
            this.baseUrl = baseUrl.substring(0,baseUrl.length()-1);
        }
        else {
            this.baseUrl = baseUrl ;
        }
        this.pages = new LinkedList<>();
    }

    /**
     * Ajoute une page à un site
     * @param page une page
     */
    @Override
    public void addPage(IPage page){
        this.pages.add(page);
    }

    @Override
    public List<IPage> getPages() {
        return pages;
    }

    public String getBaseUrl() { return this.baseUrl; }
}
