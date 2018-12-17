package team.gajigo.irang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    public static final String KEY_SNO = "sno";
    public static final String KEY_SNAME = "sname";
    public static final String KEY_SADDRESS = "saddress";
    public static final String KEY_STEL = "stel";
    public static final String KEY_SCATG = "scatg";
    public static final String KEY_SLAT = "slat";
    public static final String KEY_SLONG = "slong";
    public static final String KEY_TNO = "tno";
    public static final String KEY_TNAME = "tname";
    public static final String KEY_IMG = "img";
    public static final String KEY_ROWID = "_id";
    private static final String TAG = "DBAdapter";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    //SQL
    private static final String DATABASE_CREATE = "create table notes (_id integer primary key autoincrement, "
            + " tno text not null, tname text not null, sno text not null, sname text not null," +
            "saddress text not null, stel text not null, scatg text not null, img text not null," +
            "slat text not null, slong text not null);";
    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 2;
    private final Context mCtx;


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //table 생성.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            Log.w(TAG, "CREATING database=====================================");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    public DBAdapter(Context ctx) {
        this.mCtx = ctx;
    }


    public DBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    //데이터베이스 초기화
    public DBAdapter reset() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(mDb, 2, 3);
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    //SQL 생성
    public long createNote(String tno, String tname, String sno, String sname, String saddress, String stel,
                           String scatg, String slat, String slong, String img ) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TNO, tno);
        initialValues.put(KEY_TNAME, tname);
        initialValues.put(KEY_SNO, sno);
        initialValues.put(KEY_SNAME, sname);
        initialValues.put(KEY_SADDRESS, saddress);
        initialValues.put(KEY_STEL, stel);
        initialValues.put(KEY_SCATG, scatg);
        initialValues.put(KEY_SLAT, slat);
        initialValues.put(KEY_SLONG, slong);
        initialValues.put(KEY_IMG, img);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    //Cursor 설정
    public Cursor fetchAllNotes(String tcatg) {
        return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TNO, KEY_TNAME, KEY_SNO, KEY_SNAME, KEY_SADDRESS,
                        KEY_STEL, KEY_SCATG, KEY_SLAT, KEY_SLONG, KEY_IMG},
                "tno = ?", new String[] {tcatg}, null, null, null);
    }

}
