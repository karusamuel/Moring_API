
import com.google.gson.Gson;
import models.AssistantTM;
import models.Mentor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import response.ResponseArray;
import response.ResponseObject;
import spark.ModelAndView;
import sql2o.Sql20AssistantTmDao;
import sql2o.Sql2oMentorDao;

import java.util.Collections;
import java.util.List;

import static  spark.Spark.*;
public class App {

    public static void main(String[] args) {
        String connect = "jdbc:postgresql://localhost:5432/moringa_api";
        Sql2o sql2o = new Sql2o(connect,"samkaru","lumia435");
        Sql2oMentorDao sql2oMentorDao = new Sql2oMentorDao(sql2o);
        Sql20AssistantTmDao sql20AssistantTmDao = new Sql20AssistantTmDao(sql2o);
        Connection conn = sql2o.open();

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
            responseArray.setData(Collections.singletonList(list));
            System.out.println(list.size());
            return gson.toJson(responseArray);
        });

        post("/add_tm",(request,response)->{
            Gson gson = new Gson();
            AssistantTM assistantTM = gson.fromJson(request.body(), AssistantTM.class);
            sql20AssistantTmDao.add(assistantTM);
            ResponseObject responseObject = new ResponseObject(200,"Success Assistant added succesfully");
            return gson.toJson(responseObject);
        });

        get("/get_all_assistants",(request,response)->{
            Gson gson = new Gson();
            List<AssistantTM> list = sql20AssistantTmDao.getAll();
            ResponseArray responseArray = new ResponseArray(200,"success");
            responseArray.setData(Collections.singletonList(list));
            return gson.toJson(responseArray);
        });


        after((req, res) ->{
            res.type("application/json");
        });
    }
}
