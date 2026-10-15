package br.unifor.pulso.avaliacao

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import br.unifor.pulso.R

class GraficoPeso(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var pontos: List<Pair<String, Double>> = emptyList()
    private val densidade = resources.displayMetrics.density
    private val caminho = Path()

    private val linha = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.anil)
        style = Paint.Style.STROKE
        strokeWidth = 3 * densidade
        strokeJoin = Paint.Join.ROUND
    }

    private val ponto = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.anil)
    }

    private val base = Paint().apply {
        color = ContextCompat.getColor(context, R.color.borda)
        strokeWidth = densidade
    }

    private val rotulo = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.texto_secundario)
        textSize = 11 * resources.displayMetrics.scaledDensity
        typeface = ResourcesCompat.getFont(context, R.font.jakarta_regular)
    }

    fun mostrar(evolucao: List<Pair<String, Double>>) {
        pontos = evolucao
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        if (pontos.size < 2) return

        val margem = 6 * densidade
        val alturaRotulo = rotulo.textSize + 8 * densidade
        val baseY = height - alturaRotulo - 8 * densidade
        val topo = margem
        val passo = (width - 2 * margem) / (pontos.size - 1)
        val minimo = pontos.minOf { it.second }
        val faixa = (pontos.maxOf { it.second } - minimo).takeIf { it > 0 } ?: 1.0

        canvas.drawLine(0f, baseY, width.toFloat(), baseY, base)

        caminho.reset()
        pontos.forEachIndexed { indice, (mes, peso) ->
            val x = margem + indice * passo
            val y = (baseY - 12 * densidade - (peso - minimo) / faixa * (baseY - 12 * densidade - topo)).toFloat()
            if (indice == 0) caminho.moveTo(x, y) else caminho.lineTo(x, y)
            canvas.drawCircle(x, y, 3.5f * densidade, ponto)

            val largura = rotulo.measureText(mes)
            val xRotulo = (x - largura / 2).coerceIn(0f, width - largura)
            canvas.drawText(mes, xRotulo, height - 4 * densidade, rotulo)
        }
        canvas.drawPath(caminho, linha)
    }
}
