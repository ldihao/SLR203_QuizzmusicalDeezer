package Quiz;

// import java.io.BufferedReader;
// import java.io.DataOutputStream;
import java.io.*;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;


import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnection {

    /**
     * input : URL
     * return: json string
     *
     */

    private final String USER_AGENT = "Mozilla/5.0";
    private String url;


    public static void main(String[] args) throws Exception {

        HttpURLConnection http = new HttpURLConnection();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet("https://api.deezer.com/playlist/908622995");

        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost("https://api.deezer.com/playlist/908622995");

    }

    // HTTP GET request
    public String sendGet(String str) throws Exception {

        url = str;

        URL obj = new URL(url);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + '\n');
            System.out.println("1");
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();

    }

    // HTTP POST request
    private void sendPost(String str) throws Exception {

        url = str;

        //création du fichier texte
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("file.txt"), "UTF-8"));
        try {
            out.write("C'est un fichier encodé en UTF-8!");
        } finally {
            out.close();
        }
        //fin création du fichier texte
        //création du fichier binaire
        int i = 99;
        DataOutputStream os = new DataOutputStream(new FileOutputStream("file.bin"));
        os.writeInt(i);
        os.close();
        //fin création du fichier binaire


        String myUrl = "https://httpbin.org/post?";
        myUrl = myUrl + "message1=" + URLEncoder.encode("bonjour", "UTF-8");
        myUrl = myUrl + "&message2=" + URLEncoder.encode("au revoir", "UTF-8");
        myUrl = myUrl + "&message3=" + URLEncoder.encode("\\/%#@!%^&*", "UTF-8");
        myUrl = myUrl + "&message4=" + URLEncoder.encode("C'est l'été à Paris!", "UTF-8");
        String url = myUrl;

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}
