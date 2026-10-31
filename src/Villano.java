//Villano
class Villano extends Personaje {
int usosTrampa;

Villano(String nombre, int fuerza, int velocidad, int vidaHp, String[] hechizosAtaque, double[] potencias, String[] hechizosDefensa) {  
    super(nombre, fuerza, velocidad, vidaHp, hechizosAtaque, potencias, hechizosDefensa);  
    this.usosTrampa = 2;  
}  

//Trampa, solo los villanos lo pueden hacer  
int hacerTrampa(Personaje oponente, int hechizo) {  
    usosTrampa--;  
    int danio = ((int) (fuerza * potencias[hechizo]) + numeroAleatorio(0, 5)) * 2;  
    System.out.println(nombre + " HACE TRAMPA y duplica el poder de " + hechizosAtaque[hechizo] );  
    return oponente.recibirDanio(danio);  
}

}
