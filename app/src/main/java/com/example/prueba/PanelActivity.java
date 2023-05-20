package com.example.prueba;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PanelActivity extends AppCompatActivity {
    private TextView teaTypeTextView;
    private Spinner sizeSpinner;
    private Spinner milkSpinner;
    private Spinner sugarSpinner;
    private TextView quantityTextView;
    private TextView quantityTextView2;
    private Button decrementButton;
    private Button incrementButton;
    private int quantity = 0;
    private Button enviar;

    private TextView priceTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_activity);

        teaTypeTextView = findViewById(R.id.textView);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        milkSpinner = findViewById(R.id.milkSpinner);
        sugarSpinner = findViewById(R.id.sugarSpinner);
        quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView2 = findViewById(R.id.quantityTextView2);
        decrementButton = findViewById(R.id.decrementButton);
        incrementButton = findViewById(R.id.incrementButton);
        priceTextView2 = findViewById(R.id.priceTextView2);
        enviar = findViewById(R.id.button);

        String teaType = getIntent().getStringExtra("teaType");
        teaTypeTextView.setText(teaType);

        // Configurar el adaptador y los elementos del Spinner de tamaño
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.size_options,
                android.R.layout.simple_spinner_item
        );
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        // Configurar el adaptador y los elementos del Spinner de leche
        ArrayAdapter<CharSequence> milkAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.milk_options,
                android.R.layout.simple_spinner_item
        );
        milkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        milkSpinner.setAdapter(milkAdapter);

        // Configurar el adaptador y los elementos del Spinner de azúcar
        ArrayAdapter<CharSequence> sugarAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.sugar_options,
                android.R.layout.simple_spinner_item
        );
        sugarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sugarSpinner.setAdapter(sugarAdapter);

        // Configurar el clic del botón de decremento
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    quantity--;
                    quantityTextView2.setText(String.valueOf(quantity));
                    calculatePrice();
                }
            }
        });

        // Configurar el clic del botón de incremento
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityTextView2.setText(String.valueOf(quantity));
                calculatePrice();
            }
        });

        //Configurar funcion del boton inferior
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PanelActivity.this, "Solicitud enviada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Calcular el precio en base al tamaño y cantidad seleccionada
    private void calculatePrice() {
        int price;
        int cantidad = Integer.valueOf(String.valueOf(quantityTextView2.getText()));
        String size = sizeSpinner.getSelectedItem().toString();

        if (size.equals("Small (5$/cup)")) {
            price = 500;
        } else if (size.equals("Medium (6$/cup)")) {
            price = 600;
        } else if (size.equals("Big (7$/cup)")) {
            price = 700;
        } else {
            // Tamaño no reconocido
            price = 0;
        }

        priceTextView2.setText("$" + String.valueOf(price * cantidad));
    }
}










