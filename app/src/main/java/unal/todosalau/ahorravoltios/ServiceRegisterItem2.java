package unal.todosalau.ahorravoltios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import unal.todosalau.ahorravoltios.modelos.Electricidad;

public class ServiceRegisterItem2 extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_service_register_item2);
    Button botonRegresar = findViewById(R.id.botonRegresar6);
    Button botonRegistrar = findViewById(R.id.btnRegistrarElectricidad);
    Intent intent1 = new Intent(this, ServiceRegister.class);
    Intent intent2 = new Intent(this, Principal.class);
    EditText kilovatio = findViewById(R.id.editTextKiloVatiosHora);
    EditText precio = findViewById(R.id.editTextPrecioE);
    EditText mes = findViewById(R.id.editTextMesE);

    //BotonRegresar
    botonRegresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent1);
        }
    });
    // Configuración del botón "Registrar"
    botonRegistrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Verificar que los campos no estén vacíos
            if (!kilovatio.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !mes.getText().toString().isEmpty()) {
                String mesBuscado = mes.getText().toString();
                boolean mesExiste = verificarMes(mesBuscado);
                if (mesExiste) {
                    // El mes ya existe en el archivo
                    Toast.makeText(ServiceRegisterItem2.this, "El mes ya existe",
                            Toast.LENGTH_SHORT).show();
                } else {
                    boolean datosGuardados = guardarDatos(Float.parseFloat(kilovatio.getText().toString()), Float.parseFloat(precio.getText().toString()), mes.getText().toString());
                    if (datosGuardados) {
                        // Se cambia de actividad
                        startActivity(intent2);
                    } else {
                        Toast.makeText(ServiceRegisterItem2.this, "Error al guardar el archivo",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                // Algunos campos están vacíos
                Toast.makeText(ServiceRegisterItem2.this, "Los campos no pueden estar vacíos",
                        Toast.LENGTH_SHORT).show();
            }
        }
    });
}

/**
 * Verifica si un mes existe en el archivo "electricidad.txt".
 *
 * @param mesBuscado El mes a buscar.
 * @return true si el mes existe, false en caso contrario.
 */
public boolean verificarMes(String mesBuscado) {
    File file = new File(getFilesDir(), "electricidad.txt");
    mesBuscado = mesBuscado.toLowerCase();
    try {
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String linea;

        while ((linea = bufferedReader.readLine()) != null) {
            String mes = linea.split(",")[2]; // Suponiendo que el mes está en la tercera columna separada por coma (,)
            if (mes.equalsIgnoreCase(mesBuscado)) {
                bufferedReader.close();
                return true; // El mes existe
            }
        }

        bufferedReader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return false; // El mes no existe
}

/**
 * Guarda los datos en el archivo "electricidad.txt" si el mes no existe.
 *
 * @param kilovatios El kilovatio a guardar.
 * @param precio     El precio a guardar.
 * @param mes        El mes a guardar.
 * @return true si se guarda correctamente, false en caso contrario.
 */
public boolean guardarDatos(float kilovatios, float precio, String mes) {
    File file = new File(getFilesDir(), "electricidad.txt");
    mes = mes.toLowerCase();
    try {
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        // Escribir los datos en el archivo
        Electricidad electricidad = new Electricidad(kilovatios, precio, mes);
        String linea = electricidad.getKilovatios() + "," + electricidad.getPrecio() + "," + electricidad.getMes();
        bufferedWriter.write(linea);
        bufferedWriter.newLine();
        bufferedWriter.close();
        return true; // Los datos se guardaron correctamente
    } catch (IOException e) {
        e.printStackTrace();
    }

    return false; // Error al guardar los datos
}
}
