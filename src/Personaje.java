//Clase base
class Personaje {
String nombre;
int fuerza;
int velocidad;
int vidaHp;
int vidaMax;

String[] hechizosAtaque;  
double[] potencias;          
String[] hechizosDefensa;    

boolean defendiendo;  

Personaje(String nombre, int fuerza, int velocidad, int vidaHp, String[] hechizosAtaque, double[] potencias, String[] hechizosDefensa) {  
    this.nombre = nombre;  
    this.fuerza = fuerza;  
    this.velocidad = velocidad;  
    this.vidaHp = vidaHp;  
    this.vidaMax = vidaHp;  
    this.hechizosAtaque = hechizosAtaque;  
    this.potencias = potencias;  
    this.hechizosDefensa = hechizosDefensa;  
    this.defendiendo = false;  
}  

//Ataque normal  
int atacar(Personaje oponente, int hechizo) {  
    int danio = (int) (fuerza * potencias[hechizo]) + numeroAleatorio(0, 5);  
    System.out.println(nombre + " lanza " + hechizosAtaque[hechizo] );  
    return oponente.recibirDanio(danio);  
}  

//Ataque potenciado  
int atacar(Personaje oponente, int hechizo, double multiplicador) {  
    int danio = (int) (fuerza * potencias[hechizo] * multiplicador) + numeroAleatorio(0, 5);  
    vidaHp = vidaHp - 10;  
    if (vidaHp < 1) {  
        vidaHp = 1;  
    }  
    System.out.println(nombre + " aumenta su poder (x" + multiplicador + ") y pierde 10 HP por el esfuerzo ");  
    System.out.println(nombre + " lanza " + hechizosAtaque[hechizo] + " potenciado ");  
    return oponente.recibirDanio(danio);  
}  

//Recibir daño  
int recibirDanio(int danio) {  
    if (defendiendo) {  
        danio = danio / 2;  
        System.out.println(nombre + " bloquea la mitad del daño ");  
        defendiendo = false;  
    }  
    vidaHp = vidaHp - danio;  
    if (vidaHp < 0) {  
        vidaHp = 0;  
    }  
    System.out.println(nombre + " recibe " + danio + " de daño (HP: " + vidaHp + "/" + vidaMax + ")");  
    return danio;  
}  

//Defenderse  
void defender(int hechizo) {  
    defendiendo = true;  
    System.out.println(nombre + " usa " + hechizosDefensa[hechizo] + " y se protege del 50% del proximo ataque ");  
}  

//Recuperar vida  
void recuperarse() {  
    int curacion = numeroAleatorio(15, 25);  
    vidaHp = vidaHp + curacion;  
    if (vidaHp > vidaMax) {  
        vidaHp = vidaMax;  
    }  
    System.out.println(nombre + " toma una pocion herbovitalizante y recupera " + curacion + " HP. (HP: " + vidaHp + "/" + vidaMax + ")");  
}  

//Estadisticas de los personajes  
void mostrarEstadisticas() {  
    System.out.println("------------------------------");  
    System.out.println(" " + nombre);  
    System.out.println(" Fuerza:    " + fuerza);  
    System.out.println(" Velocidad: " + velocidad);  
    System.out.println(" HP:        " + vidaHp + "/" + vidaMax);  
    System.out.println("------------------------------");  
}  

boolean estaVivo() {  
    return vidaHp > 0;  
}  

//Numero entero al azar entre min y max   
static int numeroAleatorio(int min, int max) {  
    return min + (int) (Math.random() * (max - min + 1));  
}

}
