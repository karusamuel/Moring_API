package sql2o;

import interfaces.MentorDao;
import models.AssistantTM;
import models.Mentor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oMentorDao implements MentorDao {
    private final Sql2o sql2o;

    public Sql2oMentorDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Mentor mentor) {
        String query = "INSERT INTO mentors (name,email,specialization) values(:name,:email,:specialization)";

        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(query, true)
                    .bind(mentor)
                    .executeUpdate()
                    .getKey();
            mentor.setId(id);
            System.out.println("my id"+id);
        }catch (Sql2oException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<Mentor> getAll() {
        String query = "SELECT * from mentors";

        try (Connection conn = sql2o.open()) {
           return conn.createQuery(query)
                    .executeAndFetch(Mentor.class);


        }
    }

    @Override
    public void update(int id, AssistantTM assistantTM) {

    }

    @Override
    public void deleteById(int id) {

    }


}
