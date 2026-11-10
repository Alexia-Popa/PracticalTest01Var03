package ro.pub.cs.systems.eim.practicaltest01var03

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class PracticalTest01Var03Main : AppCompatActivity() {
    private lateinit var addButton: Button
    private lateinit var minusButton: Button
    private lateinit var nr1EditText: EditText
    private lateinit var nr2EditText: EditText
    private lateinit var summaryEditText: EditText
    private lateinit var navigateToSecondActivityButton: Button
    private lateinit var startSecondaryActivityForResult: ActivityResultLauncher<Intent>

    private var sum = 0
    private var dif=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var03_main)
        addButton = findViewById(R.id.add_button)
        minusButton = findViewById(R.id.minus_button)
        nr1EditText = findViewById(R.id.nr1_edit_text)
        nr2EditText = findViewById(R.id.nr2_edit_text)
        summaryEditText = findViewById(R.id.summary_edit_text)
        navigateToSecondActivityButton = findViewById(R.id.navigate_button)
        startSecondaryActivityForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Verificăm ce buton s-a apăsat în activitatea secundară
            if (result.resultCode == RESULT_OK) {
                Toast.makeText(this, "Rezultat primit: suma (Accept)", Toast.LENGTH_LONG).show()
            } else if (result.resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Rezultat primit: dif (Decline)", Toast.LENGTH_LONG).show()
            }
        }

// c) În onCreate: Setarea ascultătorului pe butonul de navigare
        navigateToSecondActivityButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var03SecondaryActivity::class.java).apply {
                // [AICI SCHIMBI]: Trimiti datele cerute (Int/String) folosing putExtra
                putExtra("total_sum_key", sum)
                putExtra("total_dif_key", dif)
            }
            // Lansăm activitatea secundară așteptând rezultat
            startSecondaryActivityForResult.launch(intent)
        }

        addButton.setOnClickListener {

            if (nr1EditText != null && nr2EditText != null) {

                val sum = nr2EditText.text.toString().toInt() + nr1EditText.text.toString().toInt()


                Toast.makeText(this, "Suma este: $sum!", Toast.LENGTH_SHORT).show()

                summaryEditText.setText("$nr1EditText+$nr2EditText=$sum")
            } else {
                Toast.makeText(this, "Nu contine nr intregi!", Toast.LENGTH_SHORT).show()

            }
        }
        minusButton.setOnClickListener {
            if (nr1EditText != null && nr2EditText != null) {
                val dif = nr2EditText.text.toString().toInt() - nr1EditText.text.toString().toInt()
                Toast.makeText(this, "Diferenta este: $dif!", Toast.LENGTH_SHORT).show()

                summaryEditText.setText("$nr2EditText-$nr1EditText=$dif")

            } else {
                Toast.makeText(this, "Nu contine nr intregi!", Toast.LENGTH_SHORT).show()

            }
        }
        // 5. RESTAURAREA STĂRII ÎN ONCREATE (B.2)
        if (savedInstanceState != null) {
            val sum = savedInstanceState.getInt("total_sum_key", 0)
            val dif = savedInstanceState.getInt("total_dif_key", 0)
            summaryEditText.setText("suma: $sum | dif: $dif ")
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("total_sum_key", sum)
        outState.putInt("total_dif_key", dif)
    }

    // 7. RESTAURAREA STĂRII ÎN ONRESTOREINSTANCESTATE (B.2)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        sum = savedInstanceState.getInt("total_sum_key", 0)
        dif = savedInstanceState.getInt("total_dif_key", 0)
        summaryEditText.setText("Suma: $sum | Dif: $dif")
    }
    

}



