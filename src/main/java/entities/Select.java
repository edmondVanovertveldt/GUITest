package entities;

import entities.interfaces.ISelect;

import java.util.ArrayList;
import java.util.List;

/** Objet représentant un select
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Select implements ISelect {
    /**
     * Identifiant de l'input
     */
    private String id;

    /**
     * Le nom
     */
    private String name;

    /**
     * Les options
     */
    private List<String> options;

    //region -- Constructeur

    /**
     * Constructeur
     * @param id
     */
    public Select(String id, String name){
        this.id = id;
        this.name = name;
        this.options = new ArrayList<>();
    }

    //endregion

    //region -- Getteurs

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public List<String> getOptions() {
        return options;
    }

    //endregion

    public void addOption(String value){
        this.options.add(value);
    }


}
