package glima02.com.github.globalsolution2.data

import glima02.com.github.globalsolution2.Dica

interface DicaDAO {
    fun insert(dica: Dica): Long
    fun getAllDicas(): List<Dica>
    fun getDicaById(id: Int): Dica?
    fun update(dica: Dica): Int
    fun delete(dica: Dica): Int
}
