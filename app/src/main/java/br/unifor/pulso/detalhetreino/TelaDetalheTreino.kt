package br.unifor.pulso.detalhetreino

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.unifor.pulso.R
import br.unifor.pulso.dados.DadosMockados
import br.unifor.pulso.databinding.ItemExercicioDetalheBinding
import br.unifor.pulso.databinding.TelaDetalheTreinoBinding

class TelaDetalheTreino : AppCompatActivity() {
    private lateinit var binding: TelaDetalheTreinoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaDetalheTreinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val grupo = intent.getStringExtra("grupo") ?: "Peito"
        val treino = DadosMockados.treino(grupo)

        binding.textoGrupo.text = treino.grupo
        binding.botaoVoltar.setOnClickListener { finish() }
        for (exercicio in treino.exercicios) {
            val item = ItemExercicioDetalheBinding.inflate(layoutInflater, binding.listaExercicios, true)
            
            item.textoNome.text = exercicio.nome
            item.textoSeries.text = getString(R.string.grupo_series_repeticoes, exercicio.grupo, exercicio.series, exercicio.repeticoes)
            item.textoEquipamentos.text = getString(R.string.equipamentos, exercicio.equipamentos)
            item.botaoDemonstracao.setOnClickListener {
                item.textoEquipamentos.visibility = View.GONE
                item.botaoDemonstracao.visibility = View.GONE
                item.player.visibility = View.VISIBLE
            }
            item.botaoReproduzir.setOnClickListener { mostrarVideo(item) }
        }
    }

    private fun mostrarVideo(item: ItemExercicioDetalheBinding) {

        item.botaoReproduzir.visibility = View.GONE
        item.carregando.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            item.carregando.visibility = View.GONE
            item.controles.visibility = View.VISIBLE
            item.barraProgresso.progress = 35
            item.textoTempo.text = getString(R.string.tempo_video, "0:16", "0:45")
        }, 1500)

        var pausado = false
        item.botaoPausar.setOnClickListener {
            pausado = !pausado


            if (pausado) {
                item.botaoPausar.setImageResource(R.drawable.icone_reproduzir)
            } else {
                item.botaoPausar.setImageResource(R.drawable.icone_pausar)
            }
        }
    }
}
