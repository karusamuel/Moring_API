package Sql2o;

import models.Mentor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import sql2o.Sql2oMentorDao;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oMentorDaoTest {
    private Sql2oMentorDao sql2oMentorDao;
    private Connection conn;


    @Before
    public void setup(){
        String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connect,"","");
        sql2oMentorDao = new Sql2oMentorDao(sql2o);
        conn = sql2o.open();
    }
    @Test
    public void  add_addsNewMentor_true(){
        Mentor mentor = new Mentor("samuel","test@email.com","java");
        int id = mentor.getId();
        sql2oMentorDao.add(mentor);
        assertNotEquals(id,mentor.getId());
    }

    @Test
    public void  geAll_returnsAllMentors_true(){
        Mentor mentor = new Mentor("samuel","test@email.com","java");
        Mentor mentor2 = new Mentor("samuel1","test1@email.com","java");
        sql2oMentorDao.add(mentor);
        sql2oMentorDao.add(mentor2);
        int expected = 2;
        assertEquals(expected,sql2oMentorDao.getAll().size());
    }

    @Test
    public void deleteById_DeletesCorrectMentor_true() {
        Mentor mentor = new Mentor("samuel","test@email.com","java");
        sql2oMentorDao.add(mentor);
        sql2oMentorDao.deleteById(mentor.getId());
        assertEquals(0, sql2oMentorDao.getAll().size());
    }

    @Test
    public void update_updatesMentorDetails_true() {
        Mentor mentor = new Mentor("samuel","test@email.com","java");
        sql2oMentorDao.add(mentor);
        mentor.setName("+samuel");
        sql2oMentorDao.update(mentor.getId(),mentor );
        Mentor updatedMentor = sql2oMentorDao.findById(mentor.getId());
        assertNotEquals("samuel", updatedMentor.getName());
    }







    @After
    public void closeConn(){
        conn.close();
    }




}
