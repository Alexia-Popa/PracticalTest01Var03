package ro.pub.cs.systems.eim.practicaltest01var03

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class PracticalTest01Var03Service : Service() {
    private var processingThread: ProcessingThread? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // [AICI SCHIMBI]: Preluăm prețul total trimis din MainActivity (getDoubleExtra pentru Double)
        val sum = intent?.getIntExtra("total_sum_key", 0)?: 0
        val dif = intent?.getIntExtra("total_dif_key", 0)?: 0


        // Pornim firul de execuție dedicat
        processingThread = ProcessingThread(this, sum, dif)
        processingThread?.start()

        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        // Oprirea firului de execuție la distrugerea serviciului
        processingThread?.stopThread()
        super.onDestroy()
    }
}

// CLASA DE EXECUȚIE ÎN FUNDAL (ProcessingThread)
class ProcessingThread(private val context: Context, private val nr1: Int, private val nr2: Int) : Thread() {
    private var isRunning = true

    // [AICI SCHIMBI]: Acțiunile de broadcast (trebuie să fie unice)
    private val actions = listOf(
        "ro.pub.cs.systems.eim.action1",
        "ro.pub.cs.systems.eim.action2"

    )

    override fun run() {
        while (isRunning) {
            val date = java.util.Date().toString()
            val sum=nr1+nr2
            val dif=nr1-nr2
            // [AICI SCHIMBI]: Calculul specific cerinței (ex: Conversie RON în EUR și USD)


            val intent = Intent().apply {
                action = actions.random() // Alege aleator o acțiune
                // [AICI SCHIMBI]: Mesajul trimis prin Broadcast
                putExtra("message", "Time: $date | suma: $sum | dif: $dif")
            }

            // Trimitem mesajul cu difuzare
            context.sendBroadcast(intent)

            try {
                sleep(5000) // Pauză de 10 secunde
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun stopThread() {
        isRunning = false
    }
}
