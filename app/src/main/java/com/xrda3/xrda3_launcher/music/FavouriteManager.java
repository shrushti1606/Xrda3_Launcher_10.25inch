package com.xrda3.xrda3_launcher.music;

import com.xrda3.xrda3_launcher.music.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteManager {

    private static final List<SongModel> favouriteSongs = new ArrayList<>();

    public static void addToFavourite(SongModel song) {
        if (!favouriteSongs.contains(song)) {
            favouriteSongs.add(song);
        }
    }

    public static void removeFromFavourite(SongModel song) {
        favouriteSongs.remove(song);
    }

    public static List<SongModel> getFavouriteSongs() {
        return new ArrayList<>(favouriteSongs);
    }
}
