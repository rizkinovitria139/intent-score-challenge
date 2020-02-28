package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {
    private EditText scorerName;
    int score;

    public static final String SCORER_KEY="scorer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);

        scorerName = findViewById(R.id.scorerNameInput);
    }

    public void handleAddScore(View view) {
        String scorer = scorerName.getText().toString();

//        score = Integer.parseInt(awayScore.getText().toString());

        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra(SCORER_KEY, scorer);
        startActivity(intent);
    }
}
