package Presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

    public class servlet extends HttpServlet {

        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            String requestUrl = request.getRequestURI();
            String name = requestUrl.substring("/people/".length());

            Person person = DataStore.getInstance().getPerson(name);

            if(person != null){
                String json = "{\n";
                json += "\"name\": " + JSONObject.quote(person.getName()) + ",\n";
                json += "\"about\": " + JSONObject.quote(person.getAbout()) + ",\n";
                json += "\"birthYear\": " + person.getBirthYear() + "\n";
                json += "}";
                try {
                    response.getOutputStream().println(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                //That person wasn't found, so return an empty JSON object. We could also return an error.
                try{ response.getOutputStream().println("{}");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            String name = request.getParameter("name");
            String about = request.getParameter("about");
            int birthYear = Integer.parseInt(request.getParameter("birthYear"));

            DataStore.getInstance().putPerson(new Person(name, about, birthYear));
        }
    }
