package com.xrda3.xrda3_launcher.video;   // this line allows to find the path

import android.Manifest;     // this line allows to request and check permission
import android.content.pm.PackageManager;   // if perimission is granted or denied
import android.database.Cursor;   // that reads the every moment of cursor used for reading videos from media store
import android.net.Uri;   // where the video is located
import android.os.Build;  //  checks android version
import android.os.Bundle;   // stores the data when screen goes to rotate
import android.provider.MediaStore;   // allow access to video, images and music etc

import androidx.annotation.NonNull;  // this value  never be null
import androidx.appcompat.app.AppCompatActivity;  // for screen support actiopn bar theme support
import androidx.core.app.ActivityCompat;   // Helps request permissions safely across all Android versions.(checkSelfPermission,requestPermissions)
import androidx.core.content.ContextCompat; // same as above
import androidx.recyclerview.widget.LinearLayoutManager;  // how controls lit are been shown horizontal or vertical used for video list and folder list
import androidx.recyclerview.widget.RecyclerView;// same as above

import com.xrda3.xrda3_launcher.R;  // connects java to xml
import com.xrda3.xrda3_launcher.video.model.FolderModel;   // this allows to store one foler data not ui
import com.xrda3.xrda3_launcher.video.model.VideoModel;    // this allows to store one video data not ui
import com.xrda3.xrda3_launcher.video.videoadapter.FolderAdapter; // it converts data to ui  (folder)
import com.xrda3.xrda3_launcher.video.videoadapter.VideoAdapter;   // it converts data to ui  (video)
import java.util.ArrayList;  // list of videos
import java.util.HashMap;   //  group videos by folders

public class VideoHomeActivity extends AppCompatActivity {    // I'm craeting an VideoHomeActivity class which can be accessed by anyone as it is an public class
                                                                // where extends AppCompatActivity means this is an ui screen , it can show layouts
    private static final int REQ_PERMISSION = 100;   //  This number identifies my permission request

    private RecyclerView videoRecycler;   // this are handles to UI components which shows videos they are not connected to UI yet
    private RecyclerView folderRecycler;   // this are handles to UI components which shows folders they are not connected to UI yet

    private final ArrayList<VideoModel> videoList = new ArrayList<>();  // stores all the video raw + media Store
    private final ArrayList<FolderModel> folderList = new ArrayList<>();   // stores all folders

    private VideoAdapter videoAdapter;   // it connects videolist adapter is been declared but not created yet
    @Override                               // i'm going to override a method fom AppCompatActivity
    protected void onCreate(Bundle savedInstanceState) {   // onCreate it is the first method that runs  when this screen opens
                                                            // protected= androids is allowed to call it
                                                            //Bundle savedInstanceState = stores old datat(rotation,backgrd ,etc)
        super.onCreate(savedInstanceState);  //  it lets the android to do its basic setup like window setup or prepres theme
        setContentView(R.layout.activity_videohome);  // this shows the UI written in activity_videohome.xml

        videoRecycler = findViewById(R.id.videoRecycler); // finds the video list from UI and give me control over it
        folderRecycler = findViewById(R.id.folderRecycler); // find the folder list UI from XML.

        videoRecycler.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)  // shows video items in horizontal row
                // this = current screen context
                // LinearLayoutManager = list layout
                // Horizontal = Scroll left -- right
                // false = normal order  (not reversed )  where we can see videos in horizontal like video1--video2--video3 etc
        );

        folderRecycler.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)  // same but it shows folders like whatsapp--camera-- movies etc
        );

        videoAdapter = new VideoAdapter(this, videoList); // Create an object that knows how to convert video data into UI cards.
        // this = context used for layouts and opening player
        // videoList = list oof all videos
        videoRecycler.setAdapter(videoAdapter); //RecyclerView, use this adapter to show your items

        if (hasPermission()) {    // if permission is given load videos
            loadAllContent();
        } else {
            requestPermission(); // else ask for permission
        }
    }



    private boolean hasPermission() {    // if I'm allowed to read videos from the phone  only this file can use it and boolean gives true or false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {       //  Build.VERSION.SDK_INT -- phone android version  TIRAMISU -- android 13
            return ContextCompat.checkSelfPermission(   //Check if the user has allowed video access
                    this, Manifest.permission.READ_MEDIA_VIDEO  //Do I have this permission  to access videos (android 13+)
            ) == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE  // same checks the permission but older versions than 12
            ) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {  // it shows the pop up it just performs an action
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {  // checks android version
            ActivityCompat.requestPermissions(   // system pop up appears where user has to select allow or deny and the android remembers choice
                                                // result is then sent to onRequestPermissionResult()
                    this,
                    new String[]{Manifest.permission.READ_MEDIA_VIDEO},
                    REQ_PERMISSION
            );
        } else {
            ActivityCompat.requestPermissions(
                    this,  // it is also same as above it just ask for old version for its permission
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQ_PERMISSION
            );
        }
    }

    @Override   // it override method
    public void onRequestPermissionsResult(
            int requestCode,        // which permission request this is
            @NonNull String[] permissions,  // what permission was asked
            @NonNull int[] grantResults   // User answer
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);  // Let android finish its internal work first

        if (requestCode == REQ_PERMISSION &&   // is this the result of the permission i asked for
                grantResults.length > 0 &&    //did the user choose something
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {   // did user pressed allow if yes continue else stop

            loadAllContent();  // Now that permission is granted, load videos and folders.
        }
    }

    // ================= LOAD EVERYTHING =================

    private void loadAllContent() {   // loads everything on home screen which ever it needed
        // private -- used only this file   ,, it just perform actions
        loadRawVideos();  // load videos which are packaged inside the apps
        loadMediaStoreVideos();  // load videos from phone storage
        loadFolders();   // group videos into folders
    }

    //  RAW VIDEOS

    private void loadRawVideos() {    // only this class can use it
        videoList.add(                  // add one video item to the list
                new VideoModel(     "Demo Video",     // craete a video object where video model stores the video name and video location URI
                       // DEMO Video is the title shown to the user
                        Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demo_video)));    // Uri.parse -- Convert text into a video location reference.
                                            // android.resource:// -- it indicates that video is inside my app not phone storage
                            // getPackageName() + "/" + R.raw.demo_video  -- this indicates the path of the video
        videoList.add(new VideoModel("White Duck",  // same logic just different file
                Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.whiteduck)));
        videoList.add(new VideoModel("Black Duck", // same logic as above
                Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.duckvideo)));

        videoAdapter.notifyDataSetChanged();  // its like HEy recyclerView new data has been arrived . refresh the screen.
    }

    // MEDIA STORE VIDEOS

    private void loadMediaStoreVideos() {   // loads all the video that are stored in phone

        String[] projection = {     // it tells android what are the information needed of each video
                MediaStore.Video.Media._ID,   // unique ID of each video
                MediaStore.Video.Media.DISPLAY_NAME  // video file name shown
        };

        Cursor cursor = getContentResolver().query(     // give me all the list of all videos on this phone
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,  // i want videos which are stored outside my phone
                projection,     // Only give me ID and name.
                null,  null,     // no filter , no conditions ,just get all the videos

                MediaStore.Video.Media.DATE_ADDED + " DESC"    // Date Added -- show newest videos first
        );

        if (cursor != null) {     // it checks if android actually gave me some data

            int idCol = cursor.getColumnIndex(MediaStore.Video.Media._ID);   // find where the ID and name column ae stored in the table   where cursor works with indexes not names
            int nameCol = cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME);

            while (cursor.moveToNext()) {    // move row by row through all videos

                long id = cursor.getLong(idCol);     // Read this videos ID and Name
                String name = cursor.getString(nameCol);

                Uri uri = Uri.withAppendedPath(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,   // create a playable address for this video
                        String.valueOf(id)
                );

                videoList.add(new VideoModel(name, uri));   // create a video model and store it
            }

            cursor.close();     // release android resources
            videoAdapter.notifyDataSetChanged();   // new videos are added -- update the screen
        }
    }

    //  FOLDERS

    private void loadFolders() {    // create folder data from phone videos and show it on screen
                                    // this method does not load videos it only groups videos into folders

        folderList.clear();   // remove any older folders or prevents duplicate folders

        HashMap<String, FolderModel> map = new HashMap<>();  // Create a container to group videos by folder name

        String[] projection = {
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME };  // only give me the folder of each video we do not need video URI or video title anything for right now

        Cursor cursor = getContentResolver().query(   // android gives me folder information of all videos
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {    // Only continue if Android returned data

            int bucketCol = cursor.getColumnIndex(      //Find where folder names are stored in the table.
                    MediaStore.Video.Media.BUCKET_DISPLAY_NAME
            );

            while (cursor.moveToNext()) {    // go through each video one by one

                String folderName = cursor.getString(bucketCol);   // get the folder name for this video

                if (!map.containsKey(folderName)) {
                    map.put(folderName, new FolderModel(folderName, 1));  // if this folder is seen for the first time create a new folder and set count = 1 and if folder already exist increase video count
                } else {
                    map.get(folderName).videoCount++;
                }
            }

            cursor.close();   // free andrioid resources and this file is always required
        }

        folderList.addAll(map.values());    // copy all unique folders into the list used by ui
        folderRecycler.setAdapter(new FolderAdapter(folderList));   // show folders using RecyclerView
    }
}
