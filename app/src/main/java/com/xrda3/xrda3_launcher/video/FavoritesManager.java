package com.xrda3.xrda3_launcher.video;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {

    private static final String PREFS_NAME = "favorites_prefs";
    private static final String KEY_FAVORITES = "favorites";

    // Add a video title to favorites
    public static void addFavorite(Context context, String title) {
        Set<String> favorites = getFavorites(context);
        favorites.add(title);
        saveFavorites(context, favorites);
    }

    // Remove a video title from favorites
    public static void removeFavorite(Context context, String title) {
        Set<String> favorites = getFavorites(context);
        favorites.remove(title);
        saveFavorites(context, favorites);
    }

    // Get all favorites
    public static Set<String> getFavorites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return new HashSet<>(prefs.getStringSet(KEY_FAVORITES, new HashSet<>()));
    }

    // Check if a video is already liked
    public static boolean isFavorite(Context context, String title) {
        return getFavorites(context).contains(title);
    }

    // Save favorites internally
    private static void saveFavorites(Context context, Set<String> favorites) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }
}
