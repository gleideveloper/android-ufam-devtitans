package gleides.vinente.myapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import gleides.vinente.myapp.model.UsuarioLembrete;
import gleides.vinente.myapp.util.Util;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_NOTE + " TEXT "
                + " );";
        Log.d("SQL_Create", "Executando: " + CREATE_NOTE_TABLE);
        sqLiteDb.execSQL(CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME + ";";
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});
        onCreate(db);
    }

    public void criarTabela(){
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_NOTE_TABLE = "CREATE TABLE "+ Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_NOTE + " TEXT "
                + " );";
        Log.d("SQL_Create", "Executando: " + CREATE_NOTE_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
    }

    public void dropDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("SQL_Drop", "Apagando tudo.. ");
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME + ";";
        db.execSQL(DROP_TABLE);
    }

    public void addLembrete(UsuarioLembrete nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertSQL = "INSERT INTO "+Util.TABLE_NAME+
                "("+ Util.KEY_NAME +","+ Util.KEY_NOTE +") VALUES " +
                "('"+ nota.getNomeCompleto() +"','"+ nota.getLembrete() +"');";
        db.execSQL(insertSQL);
        Log.d("SQL_Insert", "Inseri o contato: " + nota.getNomeCompleto());
        db.close();
    }

    public String getLembretes(String nomeChave) {
        SQLiteDatabase db = this.getReadableDatabase();
        String consulta = "SELECT * FROM "+Util.TABLE_NAME+" WHERE "+Util.KEY_NAME+" = '"+ nomeChave+"';";
        Log.d("SQL_Retrieve", "Fazendo a consulta "+consulta);
        Cursor cursor = db.rawQuery(consulta, null);
        String lembretes = "";
        Log.d("SQL_Retrieve", "Pedindo o contato.." + cursor.getCount());
        while (cursor.moveToNext()) {
            lembretes = lembretes + cursor.getString(2);
            Log.d("SQL_Retrieve", "Recuperei o lembrete: " + cursor.getString(2));
        }
        return lembretes;
    };
}
