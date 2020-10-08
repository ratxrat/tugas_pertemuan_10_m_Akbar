package ratsoft.android.myliblary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static String DATABAE_NAME = "murid_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENT = "murid";
    private static final String KEY_ID ="id";
    private static final String KEY_FIRST_NAME = "name";

    private static final String CREATE_TABLE_STUDENT =" CREATE TABLE " + TABLE_STUDENT + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KEY_FIRST_NAME+" TEXT);";

    public DatabaseHelper(Context context){
        super(context,DATABAE_NAME,null,DATABASE_VERSION);
        Log.d("table ",CREATE_TABLE_STUDENT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLE_STUDENT + "'");
        onCreate(db);
    }
    public long addStudentDetail(String student){
        SQLiteDatabase db = this.getWritableDatabase();
        //menbuat value konten
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME,student);
        long insert = db.insert(TABLE_STUDENT, null,values);

        return insert;
    }

    public ArrayList<String> getAllStudentList(){
            ArrayList<String> studentArrayList = new ArrayList<String>();
            String name ;
            String selectQuery = " SELECT * FROM " + TABLE_STUDENT;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery,null);

            if(c.moveToFirst()){
                do{
                    name = c.getString(c.getColumnIndex(KEY_FIRST_NAME));
                    //menambahkan ke list student
                    studentArrayList.add(name);
                }while (c.moveToNext());
                Log.d("array", studentArrayList.toString());


            }
        return  studentArrayList;
    }
}
