package Quiz;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.Scanner;

public class GetScore extends Thread{
    private Playlist.TracksBean.DataBean track;
    private FuzzySearch fuzzySearch;
    private Scanner sc;
    private boolean run_state;
    public GetScore(Playlist.TracksBean.DataBean track, FuzzySearch fuzzySearch, Scanner sc){
        this.track = track;
        this.fuzzySearch = fuzzySearch;
        this.sc = sc;
    }

    public boolean getRunState(){
        return run_state;
    }


    public void run(){
        run_state = true;
        String str=null;
        int score = 0;
        int score2 = 0;
//        System.out.println("Thread GetScore "+this.getId()+" runs!");
        while(!isInterrupted()){
            try {
                System.out.println("Le nom du chanson: " + track.getTitle());
                if (sc.hasNextLine()) str = sc.nextLine();
                if (isInterrupted()) {
                    break;
                }

                score = fuzzySearch.ratio(track.getTitle().toUpperCase(), str.toUpperCase());
                System.out.println("score: " + score);

                System.out.println("Le nom de l'artiste: " + track.getArtist().getName());

                if (sc.hasNextLine()) str = sc.nextLine();
                if (isInterrupted()) {
                    break;
                }

                score2 = fuzzySearch.ratio(track.getArtist().getName().toUpperCase(), str.toUpperCase());
                System.out.println("score: " + score2);
                if (score + score2 > 180) {
                    System.out.println("Good Guess! Now enjoy the rest of the song!\n");
                    break;
                }
                else {
                    System.out.println("Wrong Answer! Please try again!");
                }
            } catch(Exception e){
//                System.out.println("GetScore: ");
                e.printStackTrace();
                break;
            }

        }
        run_state = false;
//        System.out.println("Thread GetScore "+this.getId()+" exits!");


    }
}
