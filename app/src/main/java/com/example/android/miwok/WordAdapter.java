package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CarlosRosero on 12/25/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundColor = 0;
    private MediaPlayer soundPlayer = null;
    //Word currentWord;
    private AudioManager audioManager;
    MediaPlayer.OnCompletionListener completionListener;


//    /* #2 & #3 Step, create OnAudioFocusChangeListener(), implement callback methods and
//    adapt playback behavior when audio focus state changes */
//    AudioManager.OnAudioFocusChangeListener afChangeListener =
//            new AudioManager.OnAudioFocusChangeListener() {
//                public void onAudioFocusChange(int focusChange) {
//                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==
//                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                        // Pause playback because your Audio Focus was
//                        // temporarily stolen, but will be back soon.
//                        // i.e. for a phone call
//
//                        soundPlayer.pause();
//                        soundPlayer.seekTo(0);
//
//                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                        // Stop playback, because you lost the Audio Focus.
//                        // i.e. the user started some other playback app
//                        // Remember to unregister your controls/buttons here.
//                        // And release the kra — Audio Focus!
//                        // You’re done.
//                        soundPlayer.stop();
//                        if (soundPlayer != null) {
//                            soundPlayer.release();
//                            soundPlayer = null;
//                        }
//
//                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                        // Resume playback, because you hold the Audio Focus
//                        // again!
//                        // i.e. the phone call ended or the nav directions
//                        // are finished
//                        // If you implement ducking and lower the volume, be
//                        // sure to return it to normal here, as well.
//                        soundPlayer.start();
//                    }
//                }
//            };



    //create a new WordAdapter, custom constructor
    //context is used to inflate layout file
    //list is data to populate the ListView
    public WordAdapter(Activity context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        backgroundColor = color;
        //Create and setup AudioManager to request audio focus
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Check if existing view is being reused, otherwise inflate view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_item, parent, false);
        }

        //get Word object located at this position in the list
         Word currentWord = getItem(position);

        //get background of ListView item
        View itemInList = listItemView.findViewById(R.id.position_for_words);
        int color = ContextCompat.getColor(getContext(), backgroundColor);
        //establish the item's background color
        itemInList.setBackgroundColor(color);

        //find the miwok version of the word in list_item.xml layout
        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwok_version);
        //get the miwok translation of the word and set thte text on the corresponding TextView
        miwokWord.setText(currentWord.getMiwokTranslation());

        //find the default version of the word in list_item.xml layout
        TextView defaultWord = (TextView) listItemView.findViewById(R.id.default_language_version);
        //get the default translation of the word and set the text on the corresponding TextView
        defaultWord.setText(currentWord.getDefaultTranslation());

        //enable sound for word
        listItemView.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();
                soundPlayer = MediaPlayer.create(getContext(), currentWord.getSoundResourceId());
                soundPlayer.start();

                //create listener for completion of audio file
                if (soundPlayer != null) {
                    completionListener = new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    };
                }


//                // #1 Step
//
//                // Request audio focus so in order to play the audio file. The app needs to play a
//                // short audio file, so we will request audio focus with a short amount of time
//                // with AUDIOFOCUS_GAIN_TRANSIENT.
//                int result = audioManager.requestAudioFocus(afChangeListener,
//                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    // We have audio focus now.
//
//                    // Create and setup the {@link MediaPlayer} for the audio resource associated
//                    // with the current word
//                    soundPlayer = MediaPlayer.create(getContext(), currentWord.getSoundResourceId());
//
//                    // Start the audio file
//                    soundPlayer.start();
//
//                    // Setup a listener on the media player, so that we can stop and release the
//                    // media player once the sound has finished playing.
//                    soundPlayer.setOnCompletionListener(completionListener);
//                }




            }
        });



        //handle case for no icons
        ImageView wordIcon = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            wordIcon.setImageResource(currentWord.getImageResourceId());
        } else {
            wordIcon.setVisibility(View.GONE);
        }

        return listItemView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (soundPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            soundPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            soundPlayer = null;
//
//            //#4 step, release audio focus when no longer needed
//
//            // Regardless of whether or not we were granted audio focus, abandon it. This also
//            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
//            audioManager.abandonAudioFocus(afChangeListener);


        }
    }

    public MediaPlayer getSoundPlayer() {
        return this.soundPlayer;
    }




}
