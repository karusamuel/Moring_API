package interfaces;

import models.AssistantTM;

import java.util.List;

public interface AssistantTMDao {
    //CRUD
    void add(AssistantTM assistantTM);

    List<AssistantTM> getAll();

    AssistantTM findById(int id);


    void update(int id,AssistantTM assistantTM);

    void deleteById(int id);

}
