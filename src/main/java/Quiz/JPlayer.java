package Quiz;

import javazoom.jl.player.*;
import java.io.InputStream;
import java.net.URL;


public class JPlayer extends Thread{

    /**
     * input: url
     * function: play the mp3
     */

    private URL url;

    public static void main(String[] args){

        JPlayer jPlayer = new JPlayer("https://cdns-preview-d.dzcdn.net/stream/c-deda7fa9316d9e9e880d2c6207e92260-5.mp3");
        jPlayer.start();

        try {
            Thread.sleep(4000);
            System.out.println("1");
            Thread.sleep(1000);
            jPlayer.stop();
            System.out.println("2");
            jPlayer.join();
        }
        catch (Exception e){}
        System.out.println("done!");


    }

    public JPlayer(String str){
        try {
            this.url = new URL(str);
        }
        catch (Exception e) {}
    }

    public void run() {

        try {
            InputStream is = url.openStream();
            Player playMP3 = new Player(is);
            playMP3.play();

        } catch (Exception e){
            System.out.println(e);
        }
    }

}

