package ratsoft.android.myliblary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     Button btnStore,btnget;
    private EditText etname;
    private  DatabaseHelper databaseHelper;
    private TextView tvnames;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        tvnames = (TextView) findViewById(R.id.tvnames);

        btnStore = (Button)findViewById(R.id.btnStore);
        btnget = (Button)findViewById(R.id.btnget);
        etname = (EditText) findViewById(R.id.etname);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addStudentDetail(etname.getText().toString());
                etname.setText("");
                Toast.makeText(MainActivity.this,
                        "Berhasil Menyimpan",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = databaseHelper.getAllStudentList();
                tvnames.setText("");
                for(int i = 0 ; i<arrayList.size();i++){
                    tvnames.setText(tvnames.getText().toString()+", "+arrayList.get(i));
                }
            }
        });
    }

}