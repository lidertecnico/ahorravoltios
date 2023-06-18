package unal.todosalau.ahorravoltios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import unal.todosalau.ahorravoltios.modelos.Usuario;

public class Login extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    // Obtener referencia al elemento de interfaz de usuario para registrarse
    TextView registrarseTextView = findViewById(R.id.textViewRegistrarse);

    // Obtener referencia al botón de ingreso
    Button botonIngreso = findViewById(R.id.botonIngresar);

    // Obtener referencias a los campos de texto para usuario y contraseña
    EditText usuarioEditText = findViewById(R.id.editTextPersonName);
    EditText passwordEditText = findViewById(R.id.editTextPassword);

    // Crear intents para las actividades de registro de usuario y principal
    Intent registroIntent = new Intent(this, UserRegister.class);
    Intent principalIntent = new Intent(this, Principal.class);

    // Leer los datos del archivo
    File file = new File(getFilesDir(), "datos.txt");
    ArrayList<Usuario> usuarios = new ArrayList<>();
    try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            // Dividir la línea en los datos del usuario separados por comas
            String[] userData = line.split(",");
            String nombre = userData[0];
            String correo = userData[1];
            String nickname = userData[2];
            String passwordData = userData[3];
            // Crear un objeto Usuario y añadirlo a la lista de usuarios
            Usuario nuevoUsuario = new Usuario(nombre, correo, nickname, passwordData);
            usuarios.add(nuevoUsuario);
        }
        reader.close();

        // Imprimir información de los usuarios leídos en el archivo
        for (Usuario usuario : usuarios) {
            Log.d("Usuarios",
                    "Nombre: " + usuario.getNombre() + ", Correo: " + usuario.getCorreo() +
                            ", Nickname: " + usuario.getNickname() + ", Password: " + usuario.getPassword());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Configurar el evento de clic para el botón de ingreso
    botonIngreso.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Verificar si se han ingresado datos de usuario y contraseña
            if (!usuarioEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty()) {
                String inputUsuario = usuarioEditText.getText().toString();
                String inputPassword = passwordEditText.getText().toString();

                // Buscar el usuario coincidente en la lista de usuarios
                for (Usuario usuario : usuarios) {
                    if (usuario.getPassword().equals(inputPassword)) {
                        if (usuario.getNickname().equals(inputUsuario) || usuario.getCorreo().equals(inputUsuario) || usuario.getNombre().equals(inputUsuario)) {
                            // Si se encuentra una coincidencia, iniciar la actividad principal y salir del bucle
                            startActivity(principalIntent);
                            return;
                        }
                    }
                }
                // Si no se encontró una coincidencia, mostrar un mensaje de error utilizando Toast
                Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacíos",
                        Toast.LENGTH_SHORT).show();
            }
        }
    });

    // Configurar el evento de clic para el enlace de registro
    registrarseTextView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Iniciar la actividad de registro de usuario
            startActivity(registroIntent);
        }
    });
}
}