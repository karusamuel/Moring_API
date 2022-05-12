
import com.google.gson.Gson;
import models.Mentor;
import org.sql2o.Sql2o;
import response.ResponseArray;
import response.ResponseObject;
import sql2o.Sql2oMentorDao;

import java.util.List;

import static  spark.Spark.*;
public class App {

    public static void main(String[] args) {
        String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connect,"","");
        Sql2oMentorDao sql2oMentorDao = new Sql2oMentorDao(sql2o);
        sql2o.open();

        post("/add_mentor",(request, response) -> {
            Gson gson = new Gson();
            System.out.println(request.body());
            Mentor mentor = gson.fromJson(request.body(),Mentor.class);
            sql2oMentorDao.add(mentor);
            System.out.println(mentor.getEmail());
            ResponseObject responseObject = new ResponseObject(201,"Success! mentor Added");
            responseObject.setData(new Object());
            response.status(201);
            return gson.toJson(responseObject);
        });

        get("/get_all_mentor",(request, response) -> {
            Gson gson = new Gson();
            List<Mentor> list = sql2oMentorDao.getAll();
            ResponseArray responseArray =  new ResponseArray(200,"success");
            responseArray.setData(list);
            System.out.println(list.size());
            return gson.toJson(responseArray);
        });


        after((req, res) ->{
            res.type("application/json");
        });
    }
}
