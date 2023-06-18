package unal.todosalau.ahorravoltios.modelos;

public class Electricidad {
    private float Kilovatios;
    private float precio;
    private String mes;

public Electricidad(float kilovatios, float precio, String mes) {
    Kilovatios = kilovatios;
    this.precio = precio;
    this.mes = mes;
    }

public float getKilovatios() {
    return Kilovatios;
}

public void setKilovatios(float kilovatios) {
    Kilovatios = kilovatios;
}

public float getPrecio() {
    return precio;
}

public void setPrecio(float precio) {
    this.precio = precio;
}

public String getMes() {
    return mes;
}

public void setMes(String mes) {
    this.mes = mes;
    }
}
