package unal.todosalau.ahorravoltios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Advice extends AppCompatActivity {
private List<String> consejosList;
private TextView consejos;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_advice);

    Button botonRetroceso = findViewById(R.id.botonRetroceso4);
    final Intent intent = new Intent(this, Principal.class);

    consejos = findViewById(R.id.textConsejos);
    consejosList = new ArrayList<>();

    // Leer los consejos del archivo
    leerConsejos();

    //Mostrar consejos aleatorios
    mostrarConsejoAleatorio();


    botonRetroceso.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent);
        }
    });
}

private void leerConsejos() {
    File file = new File(getFilesDir(), "consejos.txt");

    try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            consejosList.add(line);
        }
        bufferedReader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void mostrarConsejoAleatorio() {
    if (consejosList.isEmpty()) {
        consejos.setText("No hay consejos disponibles.");
        return;
    }

    Random random = new Random();
    int index = random.nextInt(consejosList.size());
    String consejo = consejosList.get(index);
    consejos.setText(consejo);
}


}
