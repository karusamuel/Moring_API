package interfaces;

import models.AssistantTM;

import java.util.List;

public interface AssistantTMDao {
    //CRUD
    void add(AssistantTM assistantTM);

    List<AssistantTM> getAll();

    void update(int id,AssistantTM assistantTM);

    void delete(int id);

}
