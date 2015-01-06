package entities;

import entities.interfaces.IForm;
import entities.interfaces.ILink;

import java.util.LinkedList;
import java.util.List;

/** Objet représentant une page
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Page implements entities.interfaces.IPage {
    /**
     * Url de la page
     */
    private String url;

    /**
     * Liste des liens de la page
     */
    private List<ILink> links;

    /**
     * Liste de formulaires
     */
    private List<IForm> formulaires;

    //region -- Constructeur

    /**
     * Constructeur
     * @param url url de la page
     */
    public Page(String url) {
        this.url = url ;
        this.links = new LinkedList<>();
        this.formulaires = new LinkedList<>();
    }

    //endregion

    // region -- Getteur

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public List<ILink> getLinks() {
        return links;
    }

    @Override
    public List<IForm> getFormulaires() {
        return formulaires;
    }

    //endregion

    /**
     * Ajoute un lien à la page
     * @param link
     */
    @Override
    public void addLink(ILink link){
        this.links.add(link);
    }

    /**
     * Ajoute un formulaire à la page
     * @param form
     */
    @Override
    public void addForm(IForm form){
        this.formulaires.add(form);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;

        Page page = (Page) o;

        if (!url.equals(page.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
