package vn.edu.hust.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null;
    private var factorA: Double = 0.0;
    private var operator: String = "x";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button0: Button = findViewById(R.id.number0)
        button0.setOnClickListener { insertNumber(("0")) }
        val button1: Button = findViewById(R.id.number1)
        button1.setOnClickListener { insertNumber(("1")) }
        val button2: Button = findViewById(R.id.number2)
        button2.setOnClickListener { insertNumber(("2")) }
        val button3: Button = findViewById(R.id.number3)
        button3.setOnClickListener { insertNumber(("3")) }
        val button4: Button = findViewById(R.id.number4)
        button4.setOnClickListener { insertNumber(("4")) }
        val button5: Button = findViewById(R.id.number5)
        button5.setOnClickListener { insertNumber(("5")) }
        val button6: Button = findViewById(R.id.number6)
        button6.setOnClickListener { insertNumber(("6")) }
        val button7: Button = findViewById(R.id.number7)
        button7.setOnClickListener { insertNumber(("7")) }
        val button8: Button = findViewById(R.id.number8)
        button8.setOnClickListener { insertNumber(("8")) }
        val button9: Button = findViewById(R.id.number9)
        button9.setOnClickListener { insertNumber(("9")) }

        val buttonCE: Button = findViewById(R.id.ce)
        buttonCE.setOnClickListener { ce() }
        val buttonC: Button = findViewById(R.id.c)
        buttonC.setOnClickListener { c() }
        val buttonBS: Button = findViewById(R.id.bs)
        buttonBS.setOnClickListener { bs() }

        val buttonDivide: Button = findViewById(R.id.divide)
        buttonDivide.setOnClickListener { assignOperator("/") }
        val buttonMultiply: Button = findViewById(R.id.multiply)
        buttonMultiply.setOnClickListener { assignOperator("x") }
        val buttonMinus: Button = findViewById(R.id.minus)
        buttonMinus.setOnClickListener { assignOperator("-") }
        val buttonPlus: Button = findViewById(R.id.plus)
        buttonPlus.setOnClickListener { assignOperator("+") }

        val buttonChange: Button = findViewById(R.id.change)
        buttonChange.setOnClickListener { change() }
        val buttonDot: Button = findViewById(R.id.dot)
        buttonDot.setOnClickListener { insertNumber(".") }
        val buttonCalc: Button = findViewById(R.id.equal)
        buttonCalc.setOnClickListener { calculate() }

        textView = findViewById(R.id.factor)
    }

    private fun insertNumber(number: String) {
        if (textView?.text.toString().last().isDigit() || textView?.text.toString().last() == '.') {
            if (textView?.text.toString().first() == '0') {
                textView?.text = number
                return
            }
            val text = textView?.text.toString() + number
            textView?.text = text
            return
        }
        textView?.text = number
    }

    private fun ce() {
        textView?.text = "0"
    }

    private fun c() {
        textView?.text = "0"
        factorA = 0.0
    }

    private fun bs() {
        if (textView?.text.toString().length == 1) {
            textView?.text = "0"
            return
        }
        val text = textView?.text.toString().substring(0, textView?.text.toString().length - 1)
        textView?.text = text
    }

    private fun assignOperator(operator: String) {
        this.operator = operator
        if (textView?.text.toString().last().isDigit())
            factorA = textView?.text.toString().toDouble()
        textView?.text = operator
    }

    private fun calculate() {
        if (factorA != 0.0) {
            var result: Double = 0.0
            if (!textView?.text.toString().last().isDigit()) return
            when (operator) {
                "+" -> {
                    result = factorA + textView?.text.toString().toDouble()
                }

                "-" -> {
                    result = factorA - textView?.text.toString().toDouble()
                }

                "/" -> {
                    if (textView?.text.toString() == "0") {
                        textView?.text = "Lỗi"
                        return
                    }
                    result = factorA / textView?.text.toString().toDouble()
                }

                "x" -> {
                    result = factorA * textView?.text.toString().toDouble()
                }
            }
            if (result % 1 == 0.0) {
                textView?.text = result.toInt().toString()
            } else {
                textView?.text = result.toString()
            }
            // Reset factorA and operator
            factorA = 0.0
            operator = "x"
        }
    }

    private fun change() {
        if (textView?.text.toString().toDouble() > 0) {
            var text = "-" + textView?.text.toString()
            textView?.text = text
        } else if (textView?.text.toString().toDouble() < 0) {
            var text = textView?.text.toString().substring(0, textView?.text.toString().length - 1)
            textView?.text = text
        }

    }
}