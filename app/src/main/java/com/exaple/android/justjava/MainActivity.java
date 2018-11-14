package com.exaple.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean hasWhippedCream = false;
    boolean hasChoco = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int finalPrice = 5;
        //add 1 if the coffe e has whipped cream
        if (hasWhippedCream) {
            finalPrice += 1;
        }
        //add 2 if the coffee has choccolate
        if (hasChoco) {
            finalPrice += 2;
        }
        finalPrice = quantity * finalPrice;
        return finalPrice;
    }

    /**
     * Get the checked state of the checkbox whipped cream
     */
    public void whippedCreamState(View view) {
        hasWhippedCream = ((CheckBox) findViewById(R.id.whippedCream)).isChecked();
    }

    /**
     * Get the checked state of the checkbox whipped cream
     */
    public void chocoState(View view) {
        hasChoco = ((CheckBox) findViewById(R.id.choco)).isChecked();
    }

    /**
     * Get the name of the person ordering
     */
    public String getName() {
        EditText edit = (EditText) findViewById(R.id.personName);
        String result = edit.getText().toString();
        return result;
    }

    /**
     * Create the order summary on the screen
     *
     * @return the text summary
     */
    private void createOrderSummary() {
        String name = getName().trim();
        if (quantity != 0 && !name.isEmpty()) {

            String priceMessage;// = "Name: " + getName();
            priceMessage = "\nAdd whipped cream?: " + hasWhippedCream;
            priceMessage += "\nAdd Chocolate?: " + hasChoco;
            priceMessage += "\nQuantity: " + quantity;
            priceMessage += "\nTotal: $" + calculatePrice();
            priceMessage += "\nThank you!";


            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + "nefelaki123@gmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + name);
            emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            }
        }
    }


    /**
     * This method is called when the =order= button is clicked.
     */
    public void submitOrder(View view) {
//        displayMessage(createOrderSummary());
        createOrderSummary();
    }


    /**
     * This method is called when the +plus+ button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
        }
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
}