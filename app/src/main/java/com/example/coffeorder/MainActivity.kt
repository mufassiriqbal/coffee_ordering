package com.example.coffeorder

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun increment(view: View) {
        if (quantity == 100) {
            return
        }
        quantity++
        displayQuantity(quantity)
    }

    fun decrement(view: View) {
        if (quantity == 1) {
            return
        }
        quantity--
        displayQuantity(quantity)
    }

    fun submitOrder(view: View) {
        val nameField = findViewById<EditText>(R.id.name_field)
        val name = nameField.text.toString()

        val whippedCreamCheckBox = findViewById<CheckBox>(R.id.whipped_cream_checkbox)
        val hasWhippedCream = whippedCreamCheckBox.isChecked

        val chocolateCheckBox = findViewById<CheckBox>(R.id.chocolate_checkbox)
        val hasChocolate = chocolateCheckBox.isChecked

        val price = calculatePrice(hasWhippedCream, hasChocolate)
        val orderSummary = createOrderSummary(name, price, hasWhippedCream, hasChocolate)

        displayMessage(orderSummary)
    }

    private fun displayQuantity(number: Int) {
        val quantityTextView = findViewById<TextView>(R.id.quantity_text_view)
        quantityTextView.text = "" + number
    }

    private fun calculatePrice(hasWhippedCream: Boolean, hasChocolate: Boolean): Int {
        var basePrice = 5
        if (hasWhippedCream) {
            basePrice += 1
        }
        if (hasChocolate) {
            basePrice += 2
        }
        return quantity * basePrice
    }

    private fun createOrderSummary(name: String, price: Int, addWhippedCream: Boolean, addChocolate: Boolean): String {
        var orderSummary = "Name: $name"
        orderSummary += "\nAdd whipped cream? $addWhippedCream"
        orderSummary += "\nAdd chocolate? $addChocolate"
        orderSummary += "\nQuantity: $quantity"
        orderSummary += "\nTotal: $$price"
        orderSummary += "\nThank you!"
        return orderSummary
    }

    private fun displayMessage(message: String) {
        val orderSummaryTextView = findViewById<TextView>(R.id.order_summary_text_view)
        orderSummaryTextView.text = message
    }
}