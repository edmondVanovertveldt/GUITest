package entities.interfaces;

import entities.Page;

import java.util.List;

/**
 * Created by edmondvanovertveldt on 04/01/15.
 */
public interface ISite {
    /**
     * Ajoute une page à un site
     * @param page une page
     */
    void addPage(IPage page);

    List<IPage> getPages();

    String getBaseUrl();
}
