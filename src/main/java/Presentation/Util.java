package Presentation;

/**
 * Created by $Yasi on 12/6/2016.
 */
public class Util {
    public static String createHTMLString(String body) {
        return
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                        "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                        "<html charset=UTF-8\" lang=\"fa\" dir=\"rtl\"> \n" +
                        "<style type=\"text/css\">\n" +
                        "    body {\n" +
                        "        background-image:\n" +
                        "                url('images/background.png');\n" +
                        "}\n" +
                        "</style>" +
                        "<head> \n" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "<title>Response</title> \n" +
                        "</head> \n" +
                        "<body>\n" +
                        "<div align='center' >\n"+
                        "<br><br><br><br>"+
                        body+
                        "</div>\n </body> \n" +
                        "</html>";

    }

}
