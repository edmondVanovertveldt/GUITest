package entities;

import entities.interfaces.IForm;
import entities.interfaces.IInput;
import entities.interfaces.ISelect;
import entities.interfaces.ITextarea;
import java.util.LinkedList;
import java.util.List;

/** Objet représentant un formulaire
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Form implements IForm {

    /**
     * Lien de redirection
     */
    private String actionUrl ;

    /**
     * Methode http (Post ou get)
     */
    private String httpMethod;

    /**
     * Liste d'inputs du formulaire
     */
    private List<IInput> inputs ;

    /**
     * Liste de selects du formulaire
     */
    private List<ISelect> selects ;

    /**
     * Liste de textareas du formulaire
     */
    private List<ITextarea> textareas ;

    //region -- Constructeur

    public Form(String actionUrl, String httpMethod){
        this.actionUrl = actionUrl ;
        this.httpMethod = httpMethod;
        this.inputs = new LinkedList<>();
        this.selects = new LinkedList<>();
        this.textareas = new LinkedList<>();
    }

    //endregion

    // region -- Getteurs

    @Override
    public List<ITextarea> getTextareas() {
        return textareas;
    }

    @Override
    public List<ISelect> getSelects() {
        return selects;
    }

    @Override
    public List<IInput> getInputs() {
        return inputs;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public String getActionUrl() {
        return actionUrl;
    }

    //endregion

    @Override
    public void addInput(IInput input){
        this.inputs.add(input);
    }

    @Override
    public void addSelect(ISelect select){
        this.selects.add(select);
    }

    @Override
    public void addTextarea(ITextarea textArea){
        this.textareas.add(textArea);
    }
}
