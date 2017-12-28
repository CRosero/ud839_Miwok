package com.example.android.miwok;

/**
 * Created by CarlosRosero on 12/24/17.
 */

public class Word {

    private String miwokTranslation = "";
    private String defaultTranslation = "";
    private int imageResourceId = 0;
    private int soundResourceId = 0;

    public Word(String defaultWord, String miwokWord) {
        miwokTranslation = miwokWord;
        defaultTranslation = defaultWord;
    }

    public Word(String defaultWord, String miwokWord, int iconId) {
        miwokTranslation = miwokWord;
        defaultTranslation = defaultWord;
        imageResourceId = iconId;
    }

    public Word(String defaultWord, String miwokWord, int iconId, int audioId) {
        miwokTranslation = miwokWord;
        defaultTranslation = defaultWord;
        imageResourceId = iconId;
        soundResourceId = audioId;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getSoundResourceId() {
        return soundResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != 0;
    }

    @Override
    public String toString() {
        return "Word: " + defaultTranslation + " , translation: " + miwokTranslation
                + "\n icon id: " + imageResourceId + " , sound id: " + soundResourceId;
    }
}
