package entities.interfaces;

import java.util.List;

/**
 * Created by edmondvanovertveldt on 04/01/15.
 */
public interface ISelect {
    String getId();

    String getName();

    List<String> getOptions();

    void addOption(String value);
}
