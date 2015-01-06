package entities;

/** Objet représentant un lien
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Link implements entities.interfaces.ILink {

    /**
     * Url de redirection
     */
    private String urlToRedirect;

    /**
     * Constructeur
     * @param urlToRedirect
     */
    public Link(String urlToRedirect){
        this.urlToRedirect = urlToRedirect;
    }

    @Override
    public String getUrlToRedirect() {
        return urlToRedirect;
    }
}
