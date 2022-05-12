package interfaces;

import models.AssistantTM;
import models.Mentor;

import java.util.List;

public interface MentorDao {
    //CRUD
    void add(Mentor mentor);

    List<Mentor> getAll();

    void update(int id, AssistantTM assistantTM);

    void deleteById(int id);
}
