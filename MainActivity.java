package halla.icsw.mobileproject_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

class dbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "member.db";
    private static final int DATASE_VARSION = 2;
    public dbHelper(Context context){
        super(context,DATABASE_NAME,null,DATASE_VARSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE member (_id TEXT PRIMARY KEY AUTOINCREMENT, "
                  + "password TEXT, name TEXT, bri INT,gender INT, genre TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}


public class MainActivity extends AppCompatActivity {
    dbHelper helper;
    SQLiteDatabase db;
    EditText id,password,password_1,bir,name;
    RadioGroup rg;
    RadioButton men,women;
    int gender;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8;
    CheckBox[] checkBoxes;
    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new dbHelper(this);
        db = helper.getWritableDatabase();
        id = findViewById(R.id.ide);
        password = findViewById(R.id.password_1);
        password_1 = findViewById(R.id.password_02);
        name = findViewById(R.id.name_1);
        bir = findViewById(R.id.bir_1);
        men = findViewById(R.id.men);
        women = findViewById(R.id.women);
        c1 = findViewById(R.id.checkBox);c2 = findViewById(R.id.checkBox2);c3 = findViewById(R.id.checkBox3);c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);c6 = findViewById(R.id.checkBox6);c7 = findViewById(R.id.checkBox7);c8 = findViewById(R.id.checkBox8);

        checkBoxes = new CheckBox[]{c1,c2,c3,c4,c5,c6,c7,c8};

        if (password_1.getText().toString().equals(password)){
            Toast.makeText(this,"비밀번호를 다시 확인해주세요",Toast.LENGTH_LONG).show();
        }
    }

    public void member(View view) {
        if (password_1.getText().toString().equals(password)) {
            Toast.makeText(this, "비밀번호를 다시 확인해주세요", Toast.LENGTH_LONG).show();
        } else {
            String id1 = id.getText().toString();
            String password1 = password.getText().toString();
            String name1 = name.getText().toString();
            String bir1 = bir.getText().toString();
            if (men.isChecked())
                gender = 1;
            if (women.isChecked())
                gender = 2;
            for (int i = 0; i < 8; i++) {
                if (checkBoxes[i].isChecked()) {
                    if (i == 7)
                        genre += checkBoxes[i].getText().toString();
                    else
                        genre +=  checkBoxes[i].getText().toString() +",";
                }
            }
         db.execSQL("INSERT INTO member VALUES ('"+ id1 + "," + password1 + "," + name1 + "," + bir1 + ","  + gender + "," +  genre + "')");
        }
    }
}
