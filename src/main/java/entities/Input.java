package entities;

/**
 * Objet représentant un input
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Input implements entities.interfaces.IInput {
    /**
     * Le type
     */
    private String type;

    /**
     * Le nom
     */
    private String name;

    /**
     * Sa valeur par défaut
     */
    private String value;

    //region -- Constructeur

    /**
     * Constructeur
     * @param type
     * @param value
     */
    public Input(String type, String name, String value){
        this.type = type;
        this.name = name;
        this.value = value;
    }

    //endregion

    //region -- Getteur

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    //endregion Getteur
}
