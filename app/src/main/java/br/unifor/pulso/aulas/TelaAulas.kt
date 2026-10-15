package br.unifor.pulso.aulas

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.unifor.pulso.R
import br.unifor.pulso.comum.configurarNavegacao
import br.unifor.pulso.databinding.ItemAulaBinding
import br.unifor.pulso.databinding.ItemChipDiaBinding
import br.unifor.pulso.databinding.TelaAulasBinding
import br.unifor.pulso.inscricao.FolhaConfirmarInscricao
import com.google.android.material.snackbar.Snackbar

class TelaAulas : AppCompatActivity() {
    private lateinit var binding: TelaAulasBinding
    private var temInternet = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaAulasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        configurarNavegacao(this, binding.navegacao, R.id.navAulas)
        binding.botaoTentar.setOnClickListener { conferirInternet() }



        val dias = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom")
        for (dia in dias) {
            val chip = ItemChipDiaBinding.inflate(layoutInflater, binding.grupoDias, true).root
            chip.text = dia
            chip.isChecked = dia == "Qui"
        }
        conferirInternet()
    }

    private fun conferirInternet() {

        val conexao = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        temInternet = conexao.activeNetwork != null
        if (temInternet) {
            binding.bannerSemConexao.visibility = View.GONE
        } else {
            binding.bannerSemConexao.visibility = View.VISIBLE
        }
        mostrarAulas()
    }

    private fun mostrarAulas() {
        binding.listaAulas.removeAllViews()

        for (aula in aulasMockadas) {

            val cartao = ItemAulaBinding.inflate(layoutInflater, binding.listaAulas, true)
            cartao.textoServico.text = aula.servico
            cartao.textoHorario.text = aula.horario
            cartao.textoInstrutor.text = aula.instrutor

            if (aula.vagas > 0) {
                cartao.textoVagas.text = resources.getQuantityString(R.plurals.vagas_disponiveis, aula.vagas, aula.vagas)
            } else {
                cartao.textoVagas.text = getString(R.string.sem_vagas)
            }

            cartao.botaoInscrever.visibility = View.GONE
            cartao.textoIndisponivel.visibility = View.GONE
            cartao.botaoSair.visibility = View.GONE

            if (aula.inscrito) {
                cartao.botaoSair.visibility = View.VISIBLE
            } else if (aula.vagas > 0) {
                cartao.botaoInscrever.visibility = View.VISIBLE
            } else {
                cartao.textoIndisponivel.visibility = View.VISIBLE
            }

            if (!temInternet) {
                cartao.botaoInscrever.isEnabled = false
                cartao.textoIndisponivel.alpha = 0.5f
            }

            cartao.botaoInscrever.setOnClickListener {
                FolhaConfirmarInscricao(this, aula) { mostrarAulas() }.mostrar()
            }
            cartao.botaoSair.setOnClickListener { sairDaAula(aula) }
        }
    }

    private fun sairDaAula(aula: Aula) {
        aula.inscrito = false
        aula.vagas = aula.vagas + 1

        mostrarAulas()
        val snackbar = Snackbar.make(binding.root, getString(R.string.inscricao_cancelada, aula.servico), Snackbar.LENGTH_LONG)
        snackbar.setAnchorView(binding.navegacao)
        snackbar.setAction(R.string.desfazer) {
            aula.inscrito = true
            aula.vagas = aula.vagas - 1
            mostrarAulas()
        }
        snackbar.show()
    
    
    }
}
