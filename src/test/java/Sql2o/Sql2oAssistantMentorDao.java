package Sql2o;

import models.AssistantTM;
import models.Mentor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import sql2o.Sql20AssistantTmDao;
import sql2o.Sql2oMentorDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oAssistantMentorDao {
    private Sql20AssistantTmDao sql2oAssistantMentorDao;
    private Sql2oMentorDao sql2oMentorDao;
    private Connection conn;



    @Before
    public void setup(){
        String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connect,"","");
        sql2oAssistantMentorDao = new Sql20AssistantTmDao(sql2o);
        sql2oMentorDao = new Sql2oMentorDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void  add_addsNewAssistantMentor_true(){
        Mentor mentor = new Mentor("samuel","test@gmail.com","java");
        sql2oMentorDao.add(mentor);
        AssistantTM assistantTM = new AssistantTM("samuel","test@email.com","java", mentor.getId());
        int id = assistantTM.getId();
        sql2oAssistantMentorDao.add(assistantTM);
        assertNotEquals(id,assistantTM.getId());
    }

    @Test
    public void  geAll_returnsAllAssistantMentors_true(){
        Mentor mentor = new Mentor("samuel","test@gmail.com","java");
        sql2oMentorDao.add(mentor);

        AssistantTM assistantTM = new AssistantTM("samuel","test@email.com","java",mentor.getId());
        AssistantTM assistantTM1 = new AssistantTM("samuel1","test1@email.com","java",mentor.getId());

        sql2oAssistantMentorDao.add(assistantTM1);

        int expected = 2;
        assertEquals(expected, sql2oAssistantMentorDao.getAll().size());
    }


    @Test
    public void deleteById_DeletesCorrectAssitantMentor_true() {
        Mentor mentor = new Mentor("samuel","test@email.com","java");
        sql2oMentorDao.add(mentor);

        AssistantTM assistantTM = new AssistantTM("test","test@email","kotlin", mentor.getId());

        sql2oAssistantMentorDao.add(assistantTM);

        sql2oAssistantMentorDao.deleteById(assistantTM.getId());
        assertEquals(0, sql2oAssistantMentorDao.getAll().size());
    }

    @Test
    public void update_updatesAssistantMentorDetails_true() {
        Mentor mentor = new Mentor("samuel","test@email.com","java");
        sql2oMentorDao.add(mentor);
        AssistantTM assistantTM = new AssistantTM("test","test@gmail.com","kotlin", mentor.getId());
        sql2oAssistantMentorDao.add(assistantTM);
        assistantTM.setName("+samuel");
        sql2oAssistantMentorDao.update(assistantTM.getId(),assistantTM );
        AssistantTM updatedMentor = sql2oAssistantMentorDao.findById(assistantTM.getId());
        assertNotEquals("samuel", updatedMentor.getName());
    }







    @After
    public void closeConn(){
        conn.close();
    }

}
