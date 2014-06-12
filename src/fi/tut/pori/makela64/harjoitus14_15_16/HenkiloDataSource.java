package fi.tut.pori.makela64.harjoitus14_15_16;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HenkiloDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	public boolean tuhottu;

	private String[] sarakkeet = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_SYNTYMAAIKA, MySQLiteHelper.COLUMN_ETUNIMI, MySQLiteHelper.COLUMN_SUKUNIMI };

	// Konstruktori
	public HenkiloDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Henkilo createHenkilo(String syntymaaika, String etunimi, String sukunimi) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_SYNTYMAAIKA, syntymaaika);
		values.put(MySQLiteHelper.COLUMN_ETUNIMI, etunimi);
		values.put(MySQLiteHelper.COLUMN_SUKUNIMI, sukunimi);
		long insertId = database.insert(MySQLiteHelper.TABLE_HENKILOT, null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_HENKILOT, sarakkeet,
				MySQLiteHelper.COLUMN_ID + "=" + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Henkilo newHenkilo = cursorToHenkilo(cursor);
		cursor.close();
		return newHenkilo;
	}
	
	public void removeHenkilo(String syntymaaika, String etunimi, String sukunimi) {
		ContentValues values = new ContentValues();
		values.remove(syntymaaika);
		values.remove(etunimi);
		values.remove(sukunimi);
		
		long removeId = database.delete(MySQLiteHelper.TABLE_HENKILOT, MySQLiteHelper.COLUMN_SYNTYMAAIKA + "=" + syntymaaika, null);
		
		
	}

	private Henkilo cursorToHenkilo(Cursor cursor) {
		Henkilo tempHenkilo = new Henkilo();
		tempHenkilo.setId(cursor.getLong(0));
		tempHenkilo.setHetu(cursor.getString(1));
		tempHenkilo.setEtunimi(cursor.getString(2));
		tempHenkilo.setSukunimi(cursor.getString(3));
		return tempHenkilo;
	}

	public List<Henkilo> getAllHenkilot() {
		List<Henkilo> HenkiloList = new ArrayList<Henkilo>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_HENKILOT, sarakkeet,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Henkilo henkilo = cursorToHenkilo(cursor);
			HenkiloList.add(henkilo);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return HenkiloList;
	}
}
