package unal.todosalau.ahorravoltios.modelos;

public class Usuario {
private String nombre;
private String correo;
private String nickname;
private String password;

public Usuario(String nombre, String correo, String nickname, String password){
    this.nombre = nombre;
    this.correo = correo;
    this.nickname = nickname;
    this.password = password;
    }

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getCorreo() {
    return correo;
}

public void setCorreo(String correo) {
    this.correo = correo;
}

public String getNickname() {
    return nickname;
}

public void setNickname(String nickname) {
    this.nickname = nickname;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}
}
