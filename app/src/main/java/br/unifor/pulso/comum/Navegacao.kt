package br.unifor.pulso.comum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.unifor.pulso.R
import br.unifor.pulso.aulas.TelaAulas
import br.unifor.pulso.avaliacao.TelaAvaliacaoFisica
import br.unifor.pulso.dados.DadosMockados
import br.unifor.pulso.historico.TelaHistorico
import br.unifor.pulso.inicio.TelaInicio
import br.unifor.pulso.treinos.TelaTreinos
import com.google.android.material.bottomnavigation.BottomNavigationView

fun configurarNavegacao(tela: AppCompatActivity, navegacao: BottomNavigationView, abaAtual: Int) {
    navegacao.selectedItemId = abaAtual

    navegacao.setOnItemSelectedListener { item ->
        var destino: Class<*>? = null

        if (item.itemId == R.id.navInicio) {
            destino = TelaInicio::class.java
        }
        if (item.itemId == R.id.navTreinos) {
            destino = if (DadosMockados.usuarioNovo) TelaHistorico::class.java else TelaTreinos::class.java
        }
        if (item.itemId == R.id.navAulas) {
            destino = TelaAulas::class.java
        }
        if (item.itemId == R.id.navPerfil) {
            destino = TelaAvaliacaoFisica::class.java
        }

        if (destino != null && destino != tela.javaClass) {
            val intent = Intent(tela, destino)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            tela.startActivity(intent)
        }
        false
    }
}
