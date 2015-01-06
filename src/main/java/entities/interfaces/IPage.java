package entities.interfaces;

import entities.Form;
import entities.Link;

import java.util.List;

/**
 * Created by edmondvanovertveldt on 04/01/15.
 */
public interface IPage {
    String getUrl();

    List<ILink> getLinks();

    List<IForm> getFormulaires();

    /**
     * Ajoute un lien à la page
     * @param link
     */
    void addLink(ILink link);

    /**
     * Ajoute un formulaire à la page
     * @param form
     */
    void addForm(IForm form);
}
