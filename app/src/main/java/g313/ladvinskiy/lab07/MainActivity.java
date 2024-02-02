package g313.ladvinskiy.lab07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    EditText txt_key;
    EditText txt_value;
    DB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_key = findViewById(R.id.Key_txt);
        txt_value = findViewById(R.id.Value_txt);
        mydb = new DB(this, "mybase.db", null, 1);
    }


    public void on_insert_click(View v){
        String key = txt_key.getText().toString();
        String value = txt_value.getText().toString();
        if (mydb.CheckKey(key)) {
            Toast.makeText(MainActivity.this,"Такой ключ уже есть в БД!", Toast.LENGTH_SHORT).show();
        }
        else {
            mydb.do_insert(key, value);
            Toast.makeText(MainActivity.this,"Успешное добавление записи", Toast.LENGTH_SHORT).show();
        }
    }
    public void on_update_click(View v){

        String key = txt_key.getText().toString();
        String value = txt_value.getText().toString();
        if (!mydb.CheckKey(key)) {
            Toast.makeText(MainActivity.this,"Такого ключа нет в БД!", Toast.LENGTH_SHORT).show();
        }
        else {
            mydb.do_update(key, value);
            Toast.makeText(MainActivity.this,"Успешное обновление записи", Toast.LENGTH_SHORT).show();

        }
    }
    public void on_select_click(View v){
        String key = txt_key.getText().toString();
        if (!mydb.CheckKey(key)) {
            Toast.makeText(MainActivity.this,"Такого ключа нет в БД!", Toast.LENGTH_SHORT).show();
        }
        else {
            txt_value.setText(String.valueOf(mydb.do_select(key)));
        }
    }
    public void on_delete_click(View v){
        String key = txt_key.getText().toString();

        if (!mydb.CheckKey(key)) {
            Toast.makeText(MainActivity.this,"Такого ключа нет в БД!", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Вы уверены?");
            dialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mydb.do_delete(key);
                }
            });
            dialog.setNegativeButton("Нет", null);
            dialog.show();
        }
    }
}