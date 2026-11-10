package ro.pub.cs.systems.eim.practicaltest01var03
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var03SecondaryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var03_secondary)
    val correctButton = findViewById<Button>(R.id.correct_button)
        val incorrectButton = findViewById<Button>(R.id.incorect_button)
        val summaryTextView = findViewById<TextView>(R.id.secondary_count_text_view)

        val receivedData=intent.getIntExtra("total_sum_key",0)
        val receivedData2=intent.getIntExtra("total_dif_key",0)
        summaryTextView.text = "Suma: $receivedData"
        summaryTextView.text = "Diferenta: $receivedData2"
        correctButton.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent())
            finish()
        }
        incorrectButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, Intent())
            finish()
        }

    }
}