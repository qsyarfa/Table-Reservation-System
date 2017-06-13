package a3bitm.tablereservationsystem;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    DatabaseHelper myDb;
    EditText editName,editCourse,editPerson ,editTextId, editDate,editTime;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnMenu;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editCourse = (EditText)findViewById(R.id.editText_course);
        editPerson = (EditText)findViewById(R.id.editText_Person);
        editTextId = (EditText)findViewById(R.id.editText_id);
        editDate = (EditText)findViewById(R.id.editText_Date);
        editTime = (EditText)findViewById(R.id.editText_time);

        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        btnMenu= (Button)findViewById(R.id.button_menu);
        AddData();
        viewAll();
        menuView();
        UpdateData();
        DeleteData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editCourse.getText().toString(),
                                editPerson.getText().toString(),
                                editDate.getText().toString(),
                                editTime.getText().toString()
                        );
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editCourse.getText().toString(),
                                editPerson.getText().toString(),
                                editDate.getText().toString(),
                                editTime.getText().toString()
                        );
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Table Number :"+ res.getString(0)+"\n");
                            buffer.append("Customer Name :"+ res.getString(1)+"\n");
                            buffer.append("Dine Course :"+ res.getString(2)+"\n");
                            buffer.append("No.Person per Table :"+ res.getString(3)+"\n");
                            buffer.append("Date Reserved :"+ res.getString(4)+"\n");
                            buffer.append("Time Reservation :"+ res.getString(5)+"\n\n");

                        }

                        // Show all data
                        showMessage("Customer List",buffer.toString());
                    }
                }
        );
    }

    public void menuView() {
        btnMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        StringBuffer buffer = new StringBuffer();

                            buffer.append("Course A :"+"\n");
                            buffer.append("Nasi Putih x2"+"\n");
                            buffer.append("Sirap Ais x2"+"\n");
                            buffer.append("Tomyam Campur"+"\n");
                            buffer.append("Kangkung Goreng Belacan"+"\n\n");

                        buffer.append("Course B :"+"\n");
                        buffer.append("Nasi Putih x5"+"\n");
                        buffer.append("Sirap Ais x5"+"\n");
                        buffer.append("Tomyam Campur x2"+"\n");
                        buffer.append("Kailan Ikan Masin x2"+"\n");
                        buffer.append("Kangkung Goreng Belacanx2"+"\n\n");

                        buffer.append("Course C :"+"\n");
                        buffer.append("Nasi Putih x10"+"\n");
                        buffer.append("Sirap Ais x2 jag"+"\n");
                        buffer.append("Tomyam Campur x4"+"\n");
                        buffer.append("Kailan Ikan Masin x4"+"\n");
                        buffer.append("Kailan Ikan Masin x4"+"\n");
                        buffer.append("Kangkung Goreng Belacanx4"+"\n\n");




                        // Show all data
                        showMessage("Menu Courses",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}