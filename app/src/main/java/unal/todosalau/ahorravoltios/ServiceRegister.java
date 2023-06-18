package unal.todosalau.ahorravoltios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceRegister extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_service_register);
    Button botonRegresar = findViewById(R.id.botonRetroceso3);
    Button btnElectricidad = findViewById(R.id.buttonElectricidad);
    Button btnAgua = findViewById(R.id.buttonAgua);
    Intent intent1 = new Intent(this, Principal.class);
    Intent intent2 = new Intent(this, ServiceRegisterItem1.class);
    Intent intent3 = new Intent(this, ServiceRegisterItem2.class);

    //Boton Regresar
    botonRegresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent1);
            }
        });

    //Boton Registro de Agua
    btnAgua.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent2);
        }
    });

    //Boton Registro de eléctricidad
    btnElectricidad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent3);
        }
    });
    }
}