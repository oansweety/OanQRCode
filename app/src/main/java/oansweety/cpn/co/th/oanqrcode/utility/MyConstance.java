package oansweety.cpn.co.th.oanqrcode.utility;

/**
 * Created by kachutima on 8/3/2561.
 */

public class MyConstance {

    private String urlReadAllFood = "http://androidthai.in.th/cent/getAllFood.php";
    private String urlAddUser = "http://androidthai.in.th/cent/addDataOan.php";
    private String urlReadAllUser = "http://androidthai.in.th/cent/getAllDataOan.php";

    private String[] columnUserTableStrings = new String[]{"id", "Name", "User", "Password"};

    public String getUrlReadAllFood() {
        return urlReadAllFood;
    }

    public String[] getColumnUserTableStrings() {
        return columnUserTableStrings;
    }

    public String getUrlReadAllUser() {
        return urlReadAllUser;
    }

    public String getUrlAddUser() {
        return urlAddUser;
    }


}
