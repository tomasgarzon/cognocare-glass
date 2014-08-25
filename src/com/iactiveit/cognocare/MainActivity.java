package com.iactiveit.cognocare;

import java.util.ArrayList;
import java.util.List;

import com.google.android.glass.app.Card;
import com.google.android.glass.app.Card.ImageLayout;
import com.iactiveit.cognocare.R;
import com.iactiveit.cognocare.CardAdapter;
import com.iactiveit.cognocare.info.PatientInfo;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


public class MainActivity extends Activity {
	 private static final String TAG = MainActivity.class.getSimpleName();
	// Visible for testing.
    static final int INFO = 0;
    static final int TREATMENT = 1;
    
	private CardScrollView mCardScroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardAdapter(createCards(this)));
        setContentView(mCardScroller);
        setCardScrollerListener();
    }


    /**
     * Create list of cards that showcase different type of {@link Card} API.
     */
    private List<Card> createCards(Context context) {
    	
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(0, new Card(context).setText(R.string.patientinfo));
        cards.add(1, new Card(context).setText(R.string.treatment));
        return cards;
    }
    /**
     * Different type of activities can be shown, when tapped on a card.
     */
    private void setCardScrollerListener() {
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int soundEffect = Sounds.TAP;
                switch (position) {
                    case INFO:
                        startActivity(new Intent(MainActivity.this, PatientInfo.class));
                        break;
                    default:
                        soundEffect = Sounds.ERROR;
                        Log.d(TAG, "Don't show anything");
                }

                // Play sound.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(soundEffect);
            }
        });
    }
}
