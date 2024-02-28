package com.example.smart_park

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var numberIdEditText: EditText
    private lateinit var slotNumEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var totalTextView: TextView
    private lateinit var fareButton: Button
    private lateinit var bookButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        numberIdEditText = findViewById(R.id.Numberid)
        slotNumEditText = findViewById(R.id.slot_num)
        timeEditText = findViewById(R.id.time)
        totalTextView = findViewById(R.id.total)
        fareButton = findViewById(R.id.fare)
        bookButton=findViewById(R.id.book)

        // Set click listener for book button
        fareButton.setOnClickListener {
            // Handle button click:
            // - Get input from EditTexts
            val numberId = numberIdEditText.text.toString()
            val slotNum = slotNumEditText.text.toString()
            val time = timeEditText.text.toString()


            val pattern = Regex("[MH]{2} \\d{2} [A-Z]{2} \\d{4}")

            if (pattern.matches(numberId)) {
                // Input is valid, proceed with processing
                if (numberId.isEmpty() || slotNum.isEmpty() || time.isEmpty()) {
                    // Show error message
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // - Calculate total (optional, replace with your logic)
                val hours = time.toInt() // Assuming valid integer input
                val basePrice = 10 // Replace with your pricing logic
                val total = hours * basePrice
                totalTextView.setText(total.toString())
            } else {
                // Input is invalid, show an error message
                Toast.makeText(this, "Invalid format. Please enter in the format 'MH 03 BC 7895'", Toast.LENGTH_SHORT).show()
            }

            // - Validate input (optional)

        }
        bookButton.setOnClickListener {
            val numberId = numberIdEditText.text.toString()
            val slotNum = slotNumEditText.text.toString()
            val time = timeEditText.text.toString()
            val pattern = Regex("[MH]{2} \\d{2} [A-Z]{2} \\d{4}")

            if (pattern.matches(numberId)) {
                if (numberId.isEmpty() || slotNum.isEmpty() || time.isEmpty()) {
                    // Show error message
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else {
                    val intent = Intent(this, PaymentActivity::class.java)
                    startActivity(intent)
                }
            } else {
                // Input is invalid, show an error message
                Toast.makeText(this, "Invalid format. Please enter in the format 'AB 03 BC 7895'", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
