package br.unifor.pulso.inscricao

import androidx.appcompat.app.AppCompatActivity
import br.unifor.pulso.R
import br.unifor.pulso.aulas.Aula
import br.unifor.pulso.databinding.FolhaConfirmarInscricaoBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class FolhaConfirmarInscricao(

    private val tela: AppCompatActivity,
    private val aula: Aula,
    private val aoConfirmar: () -> Unit,
) {

    fun mostrar() {
        val dialogo = BottomSheetDialog(tela)
        val folha = FolhaConfirmarInscricaoBinding.inflate(tela.layoutInflater)
        dialogo.setContentView(folha.root)

        folha.textoServico.text = aula.servico
        folha.textoData.text = aula.data
        folha.textoHorario.text = aula.horario + " · 60 min"
        folha.textoInstrutor.text = tela.getString(R.string.instrutor_nome, aula.instrutor)
        folha.textoSala.text = aula.sala
        folha.textoVagas.text = tela.getString(R.string.vagas_de_total, aula.vagas, aula.totalDeVagas)
        folha.botaoFechar.setOnClickListener { dialogo.dismiss() }
        folha.linkVoltar.setOnClickListener { dialogo.dismiss() }
        folha.botaoConfirmar.setOnClickListener {
            
            aula.inscrito = true
            aula.vagas = aula.vagas - 1
            dialogo.dismiss()
            aoConfirmar()
        }

        dialogo.show()
    }
}
