package Quiz;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.List;
import java.util.Scanner;

public class Quiz {
    private static String url = "https://api.deezer.com/playlist/908622995";


    public static void main(String[] args) throws Exception {
        JsonConverter jsonConverter = new JsonConverter(url);
        List<Playlist.TracksBean.DataBean> tracks = jsonConverter.getTracks();
        Playlist playlist = jsonConverter.getPlaylist();
        FuzzySearch fuzzySearch = new FuzzySearch();
        Scanner sc = new Scanner(System.in);



//        System.out.println("playlist " + playlist.getId() + ": \n");
        for (Playlist.TracksBean.DataBean track:tracks) {
//            System.out.println(track.getTitle() + ": " + track.getPreview());
            GetScore getScore = new GetScore(track, fuzzySearch, sc);
            JPlayer jPlayer = new JPlayer(track.getPreview());

            jPlayer.start();
            System.out.println("The song is playing! Try to guess!");
            getScore.start();

            try {
                jPlayer.join();
                if (getScore.getRunState()) {
                    getScore.interrupt();
                    System.out.print("Fail! Please press return to go to next challenge!\n ");
                }
                getScore.join();
            }catch (Exception e){
//                System.out.println("main: "+e);
            }
        }
        try {
            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
