package Quiz;

import com.google.gson.Gson;

import javax.sound.midi.Track;
import java.util.List;


public class JsonConverter {
    public static void main(String[] args) throws Exception {
        HttpURLConnection httpDeezer = new HttpURLConnection();
        String url = "https://api.deezer.com/playlist/908622995";
        String jsonPlaylist = httpDeezer.sendGet(url);

        Gson gson = new Gson();
        Playlist playlist = gson.fromJson(jsonPlaylist, Playlist.class);
        System.out.println("playlist " + playlist.getId() + ": \n");
        List <Playlist.TracksBean.DataBean> tracks = playlist.getTracks().getData();
        for (Playlist.TracksBean.DataBean track:tracks){
            System.out.println(track.getTitle() + ": " + track.getLink());
        }

    }

    private String url = "https://api.deezer.com/playlist/908622995";
    private List <Playlist.TracksBean.DataBean> tracks;
    private Playlist playlist;

    public JsonConverter(String Url){
        HttpURLConnection httpDeezer = new HttpURLConnection();
        String url = Url;
        try {
            String jsonPlaylist = httpDeezer.sendGet(url);
            Gson gson = new Gson();
            playlist = gson.fromJson(jsonPlaylist, Playlist.class);
//        System.out.println("playlist " + playlist.getId() + ": \n");
            tracks = playlist.getTracks().getData();
//            for (Playlist.TracksBean.DataBean track:tracks){
//            System.out.println(track.getTitle() + ": " + track.getLink());
        }
        catch (Exception e){
            System.out.println(e);
        }


        }


    public List <Playlist.TracksBean.DataBean> getTracks(){
        return tracks;
    }

    public Playlist getPlaylist() {
        return playlist;
    }



}
