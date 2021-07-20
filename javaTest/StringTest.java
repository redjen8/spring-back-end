import java.util.HashMap;

public class StringTest {
    public static void main(String args[]) {
        String pcPassword = "test123!@#";
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("pcPassword", String.format("%" + pcPassword.length() + "s", "").replace(' ', '*'));
        System.out.println(pcPassword);
        System.out.println(String.format("%" + pcPassword.length() + "s", "*"));
        System.out.println(paramMap.get("pcPassword"));
    }
}