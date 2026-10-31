//SuperHeroe
class SuperHeroe extends Personaje {
String nombreEspecial;
int usosEspecial;

SuperHeroe(String nombre, int fuerza, int velocidad, int vidaHp, String[] hechizosAtaque, double[] potencias, String[] hechizosDefensa, String nombreEspecial) {   
    super(nombre, fuerza, velocidad, vidaHp, hechizosAtaque, potencias, hechizosDefensa);  
    this.nombreEspecial = nombreEspecial;  
    this.usosEspecial = 2;  
}  

//Ataque especial, solo los heroes lo pueden hacer  
int ataqueEspecial(Personaje oponente) {  
    usosEspecial--;  
    int danio = (int) (fuerza * 2.5) + numeroAleatorio(0, 5);  
    System.out.println(" ATAQUE ESPECIAL " + nombre + " lanza " + nombreEspecial );  
    return oponente.recibirDanio(danio);  
}

}
