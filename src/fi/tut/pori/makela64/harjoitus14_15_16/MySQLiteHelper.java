package fi.tut.pori.makela64.harjoitus14_15_16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "henkilo3.db";
	private static final int DATABASE_VERSION = 1;
	
	// Tietokannan tietueen tiedot
	public static final String TABLE_HENKILOT = "Henkilo";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_SYNTYMAAIKA = "syntymaaika";
	public static final String COLUMN_ETUNIMI = "etunimi";
	public static final String COLUMN_SUKUNIMI = "sukunimi";
	
	

	private static final String DATABASE_CREATE = "create table " + TABLE_HENKILOT
			+ "( " 
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_SYNTYMAAIKA + " text not null, " 
			+ COLUMN_ETUNIMI + " text not null,"
			+ COLUMN_SUKUNIMI + " text not null );";

	public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HENKILOT);
	    onCreate(db);

	}

}
