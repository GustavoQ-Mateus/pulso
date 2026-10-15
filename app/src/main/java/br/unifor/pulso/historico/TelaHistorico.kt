package br.unifor.pulso.historico

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.unifor.pulso.R
import br.unifor.pulso.comum.configurarNavegacao
import br.unifor.pulso.dados.DadosMockados
import br.unifor.pulso.databinding.ItemSessaoBinding
import br.unifor.pulso.databinding.TelaHistoricoBinding

data class TreinoFeito(val periodo: String, val treino: String, val detalhe: String, val quando: String)

class TelaHistorico : AppCompatActivity() {

    private lateinit var binding: TelaHistoricoBinding

    private val historico = listOf(
        TreinoFeito("HOJE", "Treino de Perna", "48 min · 5 exercícios", "07:20"),
        TreinoFeito("ONTEM", "Treino de Costas", "42 min · 5 exercícios", "18:05"),
        TreinoFeito("ESTA SEMANA", "Treino de Peito", "50 min · 5 exercícios", "Ter"),
        TreinoFeito("ESTA SEMANA", "Treino de Perna", "45 min · 5 exercícios", "Seg"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarNavegacao(this, binding.navegacao, R.id.navTreinos)
        binding.botaoTreinoDeHoje.setOnClickListener { finish() }

        if (DadosMockados.usuarioNovo) {
            binding.comSessoes.visibility = View.GONE
            binding.semSessoes.visibility = View.VISIBLE
            return
        }

        binding.textoSequencia.text = resources.getQuantityString(R.plurals.dias_seguidos, 5, 5)

        var ultimoPeriodo = ""
        for (feito in historico) {
            if (feito.periodo != ultimoPeriodo) {
                val titulo = layoutInflater.inflate(R.layout.item_titulo_periodo, binding.listaSessoes, false) as TextView
                titulo.text = feito.periodo
                binding.listaSessoes.addView(titulo)
                ultimoPeriodo = feito.periodo
            }

            val item = ItemSessaoBinding.inflate(layoutInflater, binding.listaSessoes, true)
            item.textoTreino.text = feito.treino
            item.textoDetalhe.text = feito.detalhe
            item.textoQuando.text = feito.quando
            
        }
    }
}
