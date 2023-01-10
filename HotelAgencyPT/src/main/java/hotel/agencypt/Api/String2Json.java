/*package hotel.agencypt.Api;

import com.fasterxml.jackson.databind.ObjectMapper;

public class String2Json
{
    public static  void main(String[] args)
    {
        try {
            // user as JSON string
            String json = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"," +
                    "\"roles\":[\"Member\",\"Admin\"],\"admin\":true}";

            // convert JSON string to Java Object
            User user = new ObjectMapper().readValue(json, User.class);

            // print user
            System.out.println(user);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}  */