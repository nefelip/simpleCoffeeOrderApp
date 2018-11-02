package com.exaple.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int pricePerCup = 5;
    String serverName = "Nefeli Pappa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        return quantity * pricePerCup;
    }

    /**
     * Create the order summary on the screen
     * @return the text summary
     */
    private String createOrderSummary() {
        String priceMessage = "Name: " + serverName;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + calculatePrice();
        return priceMessage += "\nThank you!";
    }


    /**
     * This method is called when the =order= button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary());
    }


    /**
     * This method is called when the +plus+ button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuanity(quantity);
    }

    /**
     * This method is called when the -minus- button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
        }
        displayQuanity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuanity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView order_summary_text_view = (TextView) findViewById(R.id.order_summary_text_view);
        order_summary_text_view.setText(message);
    }
}