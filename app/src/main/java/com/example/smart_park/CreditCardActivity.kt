package com.example.smart_park

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreditCardActivity : AppCompatActivity() {
    private lateinit var cardNumberEt: EditText
    private lateinit var expiryMonthEt: EditText
    private lateinit var expiryYearEt: EditText
    private lateinit var cvvEt: EditText
    private lateinit var payButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_card)
        cardNumberEt = findViewById(R.id.card_number_et)
        expiryMonthEt = findViewById(R.id.expiry_month_et)
        expiryYearEt = findViewById(R.id.expiry_year_et)
        cvvEt = findViewById(R.id.cvv_et)
        payButton = findViewById(R.id.pay_button)
        payButton.setOnClickListener {
            // Replace with actual payment processing logic using the obtained card details
            Toast.makeText(this, "Card details captured!", Toast.LENGTH_SHORT).show()
        }
    }
}