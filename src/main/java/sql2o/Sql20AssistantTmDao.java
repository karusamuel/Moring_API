package sql2o;

import interfaces.AssistantTMDao;
import models.AssistantTM;
import models.Mentor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.util.List;

public class Sql20AssistantTmDao implements AssistantTMDao {

    private final Sql2o sql2o;

    public Sql20AssistantTmDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(AssistantTM assistantTM) {
        String query = "INSERT INTO assistant_mentors (name,email,specialization,mentor_id) values(:name,:email,:specialization,:mentor_id)";

        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(query, true)
                    .bind(assistantTM)
                    .executeUpdate()
                    .getKey();
            assistantTM.setId(id);
            System.out.println("my id"+id);
        }catch (Sql2oException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<AssistantTM> getAll() {
        String query = "SELECT * from assistant_mentors";

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(query)
                    .executeAndFetch(AssistantTM.class);


        }
    }

    @Override
    public AssistantTM findById(int id) {
        String query = "SELECT * FROM assistant_mentors WHERE id=:id";
        Connection conn =  sql2o.open();
        return conn.createQuery(query)
                .addParameter("id",id)
                .executeAndFetchFirst(AssistantTM.class);
    }

    @Override
    public void update(int id, AssistantTM assistantTM) {
        String query = "UPDATE assistant_mentors SET name = :name WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .bind(assistantTM)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE from assistant_mentors WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
