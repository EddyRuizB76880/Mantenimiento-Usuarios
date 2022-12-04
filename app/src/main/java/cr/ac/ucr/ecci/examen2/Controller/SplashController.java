package cr.ac.ucr.ecci.examen2.Controller;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import cr.ac.ucr.ecci.examen2.DAO.Entities.User;
import cr.ac.ucr.ecci.examen2.Model.UserModel;
import cr.ac.ucr.ecci.examen2.SplashActivity;

public class SplashController {
    SplashActivity activity;
    UserModel userModel;
    public SplashController(SplashActivity activity){
        this.activity = activity;
        this.userModel = new UserModel(activity.getApplicationContext(), this);
        loadAPI();
    }

    private void loadAPI(){
        //Code based on Google's official docs.
        //https://google.github.io/volley/simple.html
        final String url = "https://dummyjson.com/users?select=firstName,lastName,age,phone,email,image";
        RequestQueue queue = Volley.newRequestQueue(this.activity.getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject result = new JSONObject(response);
                            JSONArray users = result.getJSONArray("users");//.getJSONObject(0).getString("firstName");
                            for(int i = 0; i < users.length(); i++){
                                User user = new User();
                                user.id = users.getJSONObject(i).getInt("id");
                                user.firstName = users.getJSONObject(i).getString("firstName");
                                user.lasttName = users.getJSONObject(i).getString("lastName");
                                user.age = users.getJSONObject(i).getInt("age");
                                user.email = users.getJSONObject(i).getString("email");
                                user.phone = users.getJSONObject(i).getString("phone");
                                user.image = users.getJSONObject(i).getString("image");
                                userModel.insertUser(user);
                            }
                        activity.boot();
                        }catch (Exception e){}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                activity.onFailure();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
