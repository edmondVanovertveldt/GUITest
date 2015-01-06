package entities.interfaces;

import entities.Input;
import entities.Select;
import entities.Textarea;

import java.util.List;

/**
 * Created by edmondvanovertveldt on 04/01/15.
 */
public interface IForm {
    List<ITextarea> getTextareas();

    List<ISelect> getSelects();

    List<IInput> getInputs();

    String getHttpMethod();

    String getActionUrl();

    void addInput(IInput input);

    void addSelect(ISelect select);

    void addTextarea(ITextarea textArea);
}
