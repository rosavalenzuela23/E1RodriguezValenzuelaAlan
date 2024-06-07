package mx.itson.edu.e1rodriguezvalenzuelaalan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private var percentage: Float = 0.0f
    private data class TotalPriceWithDiscount(val price: Float, val discount: Float)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textOriginalPrice: EditText = findViewById<EditText>(R.id.textOriginalPrice)

        val button10: Button = findViewById<Button>(R.id.button10)
        val button15: Button = findViewById<Button>(R.id.button15)
        val button20: Button = findViewById<Button>(R.id.button20)
        val button25: Button = findViewById<Button>(R.id.button25)
        val button30: Button = findViewById<Button>(R.id.button30)
        val button40: Button = findViewById<Button>(R.id.button40)

        val viewPercent: TextView = findViewById(R.id.textPercentage)
        val viewTotal: TextView = findViewById(R.id.textTotal)

        val buttonTip: Button = findViewById(R.id.buttonTip)
        val buttonDiscount: Button = findViewById(R.id.buttonDiscount)

        fun setUpDiscountButtons() {
            button10.setOnClickListener {
                this.percentage = 0.1f
            }
            button15.setOnClickListener {
                this.percentage = 0.15f
            }
            button20.setOnClickListener {
                this.percentage = 0.2f
            }
            button25.setOnClickListener {
                this.percentage = 0.25f
            }
            button30.setOnClickListener {
                this.percentage = 0.3f
            }
            button40.setOnClickListener {
                this.percentage = 0.4f
            }
        }
        setUpDiscountButtons()

        fun update(result: TotalPriceWithDiscount) {
            viewPercent.text = result.discount.toString()
            viewTotal.text = result.price.toString()
        }

        fun getOrignalPrice(): String {
            return if (textOriginalPrice.text.toString() == "" || textOriginalPrice.text.toString() == ".") {
                "0"
            } else {
                textOriginalPrice.text.toString()
            }
        }

        buttonTip.setOnClickListener {
            val numberConverter = NumberFormat.getInstance();
            val valor: Number = numberConverter.parse(getOrignalPrice())
            val total: TotalPriceWithDiscount = getPriceWithTip(valor.toFloat())
            update(total)
        }

        buttonDiscount.setOnClickListener {
            val numberConverter = NumberFormat.getInstance();
            val valor: Number = numberConverter.parse(getOrignalPrice())
            val total: TotalPriceWithDiscount = getPriceWithDiscount(valor.toFloat())
            update(total)
        }

    }

    private fun getPriceWithDiscount(originalPrice: Float): TotalPriceWithDiscount {
        val totalDiscount: Float = originalPrice * percentage
        return TotalPriceWithDiscount(originalPrice - totalDiscount, totalDiscount)
    }

    private fun getPriceWithTip(originalPrice: Float): TotalPriceWithDiscount {
        val totalTip: Float = originalPrice * percentage
        return TotalPriceWithDiscount(originalPrice + totalTip, totalTip)
    }

}