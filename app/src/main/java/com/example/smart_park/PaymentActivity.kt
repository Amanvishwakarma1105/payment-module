package com.example.smart_park

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast

class PaymentActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var proceedButton: Button
    private val bankSelectionLayout: LinearLayout by lazy {
        findViewById(R.id.bank_selection_layout)
    }
    private lateinit var bankSelectorSpinner: Spinner
    private val availableBanks = listOf("State Bank of India", "Punjab National Bank", "Bank of Baroda ","Canara Bank","Union Bank of India","HDFC Bank","ICICI Bank","Axis Bank","Kotak Mahindra Bank",) // Replace with actual bank names
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        radioGroup = findViewById(R.id.payment_method_group)
        proceedButton = findViewById(R.id.proceed_button)
        bankSelectorSpinner = findViewById(R.id.bank_selector_spinner)

        // Set up the spinner adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, availableBanks)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bankSelectorSpinner.adapter = adapter

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.net_banking_button -> {
                    bankSelectionLayout.visibility = View.VISIBLE // Show bank selection layout
                }
                else -> {
                    bankSelectionLayout.visibility = View.GONE // Hide bank selection layout for other options
                }
            }
        }

        proceedButton.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                // No option selected
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when (selectedId) {
                R.id.credit_card_button -> {
                    val intent = Intent(this, CreditCardActivity::class.java)
                    startActivity(intent)
                }

                R.id.debit_card_button -> {
                    // Launch activity for debit card details and handle payment
                    val intent = Intent(this, DebitCardActivity::class.java)
                    startActivity(intent)
                }

                R.id.net_banking_button -> {

                    val selectedBank = bankSelectorSpinner.selectedItem.toString()
                    Toast.makeText(this, "Selected Bank: $selectedBank", Toast.LENGTH_SHORT).show()

                    val url = "https://www.$selectedBank.com/netbanking" // Replace with actual URL

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)

                    // Implement your chosen approach for handling net banking based on security guidelines and terms of service (e.g., redirecting to bank's website or displaying information)
                    // **Important:** Do not collect net banking credentials within your app.
                }

                else -> {
                    // No option selected (shouldn't happen)
                    Toast.makeText(this, "Unexpected error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


}
