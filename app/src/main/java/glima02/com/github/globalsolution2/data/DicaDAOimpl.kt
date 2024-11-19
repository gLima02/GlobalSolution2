package glima02.com.github.globalsolution2.data

import android.content.ContentValues
import glima02.com.github.globalsolution2.DatabaseHelper
import glima02.com.github.globalsolution2.Dica

class DicaDAOimpl(private val dbHelper: DatabaseHelper) : DicaDAO {

    override fun insert(dica: Dica): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITULO, dica.titulo)
            put(DatabaseHelper.COLUMN_DESCRICAO, dica.descricao)
        }
        val id = db.insert(DatabaseHelper.TABLE_DICAS, null, values)
        db.close()
        return id
    }

    override fun getAllDicas(): List<Dica> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_DICAS}", null)
        val dicasList = mutableListOf<Dica>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
                val titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO))
                val descricao = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRICAO))
                dicasList.add(Dica(id, titulo, descricao))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return dicasList
    }

    override fun getDicaById(id: Int): Dica? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_DICAS,
            null,
            "${DatabaseHelper.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        var dica: Dica? = null
        if (cursor.moveToFirst()) {
            val titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO))
            val descricao = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRICAO))
            dica = Dica(id, titulo, descricao)
        }

        cursor.close()
        db.close()
        return dica
    }

    override fun update(dica: Dica): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITULO, dica.titulo)
            put(DatabaseHelper.COLUMN_DESCRICAO, dica.descricao)
        }

        val rowsAffected = db.update(
            DatabaseHelper.TABLE_DICAS,
            values,
            "${DatabaseHelper.COLUMN_ID} = ?",
            arrayOf(dica.id.toString())
        )

        db.close()
        return rowsAffected
    }

    override fun delete(dica: Dica): Int {
        val db = dbHelper.writableDatabase
        val rowsDeleted = db.delete(
            DatabaseHelper.TABLE_DICAS,
            "${DatabaseHelper.COLUMN_ID} = ?",
            arrayOf(dica.id.toString())
        )
        db.close()
        return rowsDeleted
    }
}
