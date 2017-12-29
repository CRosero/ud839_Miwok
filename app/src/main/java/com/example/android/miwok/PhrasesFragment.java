package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    private final int NO_ICON = 0;
    WordAdapter itemsAdapter;
    private MediaPlayer soundPlayer;

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        ArrayList<Word> words = new ArrayList<>(10);
        words.add(new Word("Where are you going?", "minto wuksus", NO_ICON, R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", NO_ICON, R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", NO_ICON, R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", NO_ICON, R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", NO_ICON, R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", NO_ICON, R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", NO_ICON, R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", NO_ICON, R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", NO_ICON, R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", NO_ICON, R.raw.phrase_come_here));

        //create adapter for words
        itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        //obtain layout where list is going to be displayed, find ListView to populate
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //attach adapater to ListView
        listView.setAdapter(itemsAdapter);

        return rootView;
    }

    @Override
    public void onStop() {
        soundPlayer = itemsAdapter.getSoundPlayer();
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
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
        }
    }

}
