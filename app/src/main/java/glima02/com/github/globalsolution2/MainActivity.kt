package glima02.com.github.globalsolution2

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import glima02.com.github.globalsolution2.data.DicaDAO
import glima02.com.github.globalsolution2.data.DicaDAOimpl

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DicaAdapter
    private lateinit var dicasList: MutableList<Dica>
    private lateinit var dicaDAO: DicaDAO
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando a RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializando o SearchView
        searchView = findViewById(R.id.searchView)

        // Inicializando o DAO
        val databaseHelper = DatabaseHelper(this)
        dicaDAO = DicaDAOimpl(databaseHelper)

        // Carregando as dicas do banco de dados
        dicasList = dicaDAO.getAllDicas().toMutableList()

        // Inicializando o Adapter
        adapter = DicaAdapter(dicasList)
        recyclerView.adapter = adapter

        // Configurando o filtro de pesquisa
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = dicasList.filter {
                    it.titulo.contains(newText ?: "", ignoreCase = true)
                }
                adapter.updateData(filteredList)
                return true
            }
        })

        // Exemplo de como adicionar uma dica ao banco de dados
        addDica()
    }

    // Método para adicionar uma dica
    private fun addDica() {
        val dica = Dica(titulo = "Use lâmpadas LED", descricao = "Lâmpadas LED são mais eficientes e economizam energia.")
        dicaDAO.insert(dica)
    }
}
