package sg.edu.rp.c346.id21036147.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetInsert;
    TextView tvResults;
    ListView lvTasks;
    EditText etDesc, etDate;
    ArrayList<String> data;
    ArrayList<Task> alTask;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetInsert = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lvTasks = findViewById(R.id.lvTasks);
        etDesc = findViewById(R.id.editDesc);
        etDate = findViewById(R.id.editDate);

        DBHelper db = new DBHelper(MainActivity.this);
        alTask = db.getTasks();

        aaTasks = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTask);
        lvTasks.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.insertTask(etDesc.getText().toString(), etDate.getText().toString());
                aaTasks.notifyDataSetChanged();
            }
        });

        btnGetInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper db = new DBHelper(MainActivity.this);
                data = db.getTaskContent();
                String txt = "";
                for(int i = 0;i < data.size(); i++){
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }

                tvResults.setText(txt);
                lvTasks.setAdapter(aaTasks);
            }
        });

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), lvTasks.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}