package br.unifor.pulso.avaliacao

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.unifor.pulso.R
import br.unifor.pulso.comum.configurarNavegacao
import br.unifor.pulso.databinding.ItemDobraBinding
import br.unifor.pulso.databinding.TelaAvaliacaoFisicaBinding

class TelaAvaliacaoFisica : AppCompatActivity() {

    private lateinit var binding: TelaAvaliacaoFisicaBinding

    private val evolucaoDoPeso = listOf(
        Pair("Mai", 61.0),
        Pair("Jun", 62.0),
        Pair("Jul", 60.5),
        Pair("Ago", 62.5),
        Pair("Set", 61.5),
        Pair("Out", 62.0),
    )

    private val dobrasCutaneas = listOf(
        Pair("Tríceps", "12 mm"),
        Pair("Subescapular", "14 mm"),
        Pair("Suprailíaca", "11 mm"),
        Pair("Abdominal", "16 mm"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaAvaliacaoFisicaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarNavegacao(this, binding.navegacao, R.id.navPerfil)

        binding.botaoVoltar.setOnClickListener { 
            finish() 
        }
        binding.medidaPeso.textoValor.text = "62 kg"
        binding.medidaPeso.textoRotulo.setText(R.string.peso)
        binding.medidaAltura.textoValor.text = "1,65 m"
        binding.medidaAltura.textoRotulo.setText(R.string.altura)
        binding.medidaImc.textoValor.text = "22,8"
        binding.medidaImc.textoRotulo.setText(R.string.imc)
        binding.medidaGordura.textoValor.text = "18%"
        binding.medidaGordura.textoRotulo.setText(R.string.gordura)
        binding.grafico.mostrar(evolucaoDoPeso)

        for (dobra in dobrasCutaneas) {
            val linha = ItemDobraBinding.inflate(layoutInflater, binding.listaDobras, true)
            linha.textoNome.text = dobra.first
            linha.textoValor.text = dobra.second
            if (dobra == dobrasCutaneas[0]) {
                linha.divisor.visibility = View.GONE
            }
            
        }
    }
}
