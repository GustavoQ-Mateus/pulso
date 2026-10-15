package br.unifor.pulso.execucao

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.unifor.pulso.R
import br.unifor.pulso.databinding.ItemSerieBinding
import br.unifor.pulso.databinding.TelaExecucaoTreinoBinding
import br.unifor.pulso.historico.TelaHistorico

class TelaExecucaoTreino : AppCompatActivity() {

    private lateinit var binding: TelaExecucaoTreinoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaExecucaoTreinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textoProgresso.text = getString(R.string.exercicio_de, 2, 5)
        binding.textoExercicio.text = "Leg Press"
        binding.textoTempo.text = "0:45"
        binding.anelDescanso.progress = 750

        for (numero in 1..3) {
            val serie = ItemSerieBinding.inflate(layoutInflater, binding.listaSeries, true)
            serie.titulo.text = getString(R.string.serie_numero, numero)
            serie.detalhe.text = getString(R.string.carga_repeticoes, "80", 12)
            serie.numero.text = numero.toString()
           
            if (numero == 1) {
                serie.indicador.setBackgroundResource(R.drawable.fundo_circulo_anil)
                serie.numero.visibility = View.GONE
                serie.visto.visibility = View.VISIBLE
            }

            if (numero == 2) {
                serie.root.setBackgroundResource(R.drawable.fundo_cartao_anil)
                serie.indicador.setBackgroundResource(R.drawable.fundo_circulo_branco)
                serie.numero.setTextColor(getColor(R.color.anil))
                serie.titulo.setTextColor(getColor(R.color.branco))
                serie.detalhe.setTextColor(getColor(R.color.gelo))
            }

            if (numero == 3) {
                serie.indicador.setBackgroundResource(R.drawable.fundo_circulo_contorno)
            }
        }

        binding.botaoFechar.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        binding.botaoAcao.setOnClickListener { mostrarTreinoConcluido() }
        binding.botaoVoltarInicio.setOnClickListener { finish() }
        binding.linkHistorico.setOnClickListener {
            startActivity(Intent(this, TelaHistorico::class.java))
            finish()
        }
    }

    private fun mostrarTreinoConcluido() {
        binding.textoResumo.text = getString(R.string.resumo_treino, "Perna", 48)
        binding.textoTotalExercicios.text = "5"
        binding.textoTotalSeries.text = "15"
        binding.textoVolume.text = getString(R.string.volume_kg, "4.320")
        binding.textoSequencia.text = resources.getQuantityString(R.plurals.sequencia_de_dias, 5, 5)
        binding.textoMedalha.text = resources.getQuantityString(R.plurals.faltam_dias_medalha, 2, 2)
        binding.execucao.visibility = View.GONE
        binding.concluido.visibility = View.VISIBLE

    }
}
