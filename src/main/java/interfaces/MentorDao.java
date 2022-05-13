package interfaces;

import models.AssistantTM;
import models.Mentor;

import java.util.List;

public interface MentorDao {
    //CRUD
    void add(Mentor mentor);

    List<Mentor> getAll();

     Mentor findById(int id);

    void update(int id, Mentor mentor);

    void deleteById(int id);


}
