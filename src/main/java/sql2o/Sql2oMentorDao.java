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
    public Mentor findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM mentors WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Mentor.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, Mentor mentor) {
        String query = "UPDATE mentors SET name = :name WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .bind(mentor)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE from mentors WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


}
