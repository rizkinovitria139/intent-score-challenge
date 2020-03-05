package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView homeTeamText;
    private TextView awayTeamText;
    private TextView homeScore;
    private TextView awayScore;
    private TextView homeScorer;
    private TextView awayScorer;

    private ImageView avatar1, avatar2;
    int home;
    int away;
    String returnString;
    String name="";

    public static final String WIN_KEY="win";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        homeTeamText = findViewById(R.id.txt_home);
        awayTeamText = findViewById(R.id.txt_away);

        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);

        homeScorer = findViewById(R.id.homeScorer);
        awayScorer = findViewById(R.id.awayScorer);

        avatar1 = findViewById(R.id.home_logo);
        avatar2 = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("homeImg");
            Bitmap bmp2 = extra.getParcelable("awayImg");

            avatar1.setImageBitmap(bmp);
            avatar2.setImageBitmap(bmp2);
            homeTeamText.setText(extras.getString(MainActivity.HOME_KEY));
            awayTeamText.setText(extras.getString(MainActivity.AWAY_KEY));
            homeScorer.setText(extras.getString(ScorerActivity.SCORER_KEY));
            //receive img
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0){
            return;
        }

        if(requestCode == 1){
            if(data != null){
                home++;
                homeScore.setText(" "+home);

                returnString = data.getStringExtra("scorer");
                name = returnString + "\n"+homeScorer.getText().toString();
                homeScorer.setText(name);

            }
        }
        if(requestCode == 2){
            if(data!= null){
                away++;
                awayScore.setText(" "+away);

                returnString = data.getStringExtra("scorer");
                name = returnString + "\n"+awayScorer.getText().toString();
                awayScorer.setText(name);

            }
        }
    }

    public void handleResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);

        if(away > home){
            intent.putExtra(WIN_KEY, "Selamat "+awayTeamText.getText().toString()+"\nScorer Name \n:" +awayScorer.getText().toString());
        }else if(away < home){
            intent.putExtra(WIN_KEY, "Selamat "+homeTeamText.getText().toString()+"\nScorer Name : \n"+homeScorer.getText().toString());
        }else{
            intent.putExtra(WIN_KEY, "Seri!");
        }
        startActivity(intent);
    }

    public void handleAddAway(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 2);
    }

    public void handleAddHome(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 1);
    }
}
