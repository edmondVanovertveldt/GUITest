package entities;

/**
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Textarea implements entities.interfaces.ITextarea {
    /**
     * Identifiant
     */
    private String id;

    /**
     * Constructeur
     * @param id
     */
    public Textarea(String id){
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
