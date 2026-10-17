//Personajes heroes -Harry Potter -Hermione Granger -Ron Weasley 
//Personajes villanos -Lord Voldemort -Bellatrix Lestrange -Gellert Grindelwald

//Hechizos de ataque Harry Potter -Expelliarmus -Sectumsempra -Desmaius -Hechizo basico 
//Hechizos de defensa Harry Potter -Protego -Escudo del Capitan America
//Hechizos de ataque Hermione Granger -Bombarda Maxima -Petrificus Totalus -Confundus -Hechizo basico
//Hechizos de defensa Hermione Granger -Protego -Escudo del Capitan America
//Hechizos de ataque Ron Wasley -Jaque Mate -Imperio -Langlock -Hechizo basico
//Hechizos de defensa Ron Weasley -Protego -Escudo del Capitna America

//Hechizos de ataque Lord Voldemort -Avada Kedavra -Parcel -Sclopetum
//Hechizos de defensa Lord Voldemort -Protego avanzado -Creacion de inferi
//Hechizos de ataque Bellatrix Lestrange -Crucio -Stupefy -Confringo
//Hechizos de defensa Bellatrix Lestrange -Protego -Morsmordre
//Hechizos de ataque Gellert Grindelwald -Whip-like spell -Ripple spell -Nigromancia
//Hechizos de defensa Gellert Grindelwald -Protego Diabolica -Aparicion

import java.util.Scanner;

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

//Interfaz 
public class DefendHogwarts {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---------------------------------------------");
        System.out.println("             Batalla de Hogwarts");
        System.out.println("---------------------------------------------");

        SuperHeroe[] heroes = crearHeroes();
        Villano[] villanos = crearVillanos();

        // Elegir heroe
        System.out.println("\nElige tu heroe:");
        for (int i = 0; i < heroes.length; i++) {
            System.out.println(" " + (i + 1) + ". " + heroes[i].nombre);
        }
        SuperHeroe jugador = heroes[leerOpcion(1, heroes.length) - 1];

        //El villano es aleatorio
        Villano rival = villanos[Personaje.numeroAleatorio(0, villanos.length - 1)];
        System.out.println("\nTu rival es " + rival.nombre );

        jugador.mostrarEstadisticas();
        rival.mostrarEstadisticas();

        //Turno para empezar
        boolean turnoJugador;
        if (jugador.velocidad >= rival.velocidad) {
            turnoJugador = true;
            System.out.println(jugador.nombre + " es mas rapido y empieza el duelo ");
        } else {
            turnoJugador = false;
            System.out.println(rival.nombre + " es mas rapido y empieza el duelo ");
        }

        int turno = 1;              
        int danioTotalJugador = 0;  
        int danioTotalRival = 0;    

        while (jugador.estaVivo() && rival.estaVivo()) {
            System.out.println("\n------- Turno " + turno + " --------");
            if (turnoJugador) {
                danioTotalJugador += turnoDelJugador(jugador, rival);
            } else {
                danioTotalRival += turnoDelVillano(rival, jugador);
            }
            turnoJugador = !turnoJugador;
            turno++;
        }


        if (jugador.estaVivo()) {
            System.out.println(" VICTORIA " + jugador.nombre + " ha derrotado a " + rival.nombre + ", Hogwarts esta a salvo ");
        } else {
            System.out.println(" DERROTA " + rival.nombre + " ha vencido y Hogwarts cae en la oscuridad ");
        }
        System.out.println("----------------------------------------------");
        System.out.println("Turnos jugados: " + (turno - 1));
        System.out.println("Daño total de " + jugador.nombre + ": " + danioTotalJugador);
        System.out.println("Daño total de " + rival.nombre + ": " + danioTotalRival);
        System.out.println("----------------------------------------------");
    }

    //Interfaz turno del jugador
    static int turnoDelJugador(SuperHeroe jugador, Villano rival) {
        jugador.defendiendo = false;
        int danio = 0;

        while (true) {
            System.out.println("\nTurno de " + jugador.nombre + " (HP: " + jugador.vidaHp + "/" + jugador.vidaMax + ")  vs  " + rival.nombre + " (HP: " + rival.vidaHp + "/" + rival.vidaMax + ")");
            System.out.println("Que deseas hacer?");
            System.out.println(" 1. Atacar");
            System.out.println(" 2. Aumentar poder (ataque x1.5, cuesta 10 HP)");
            System.out.println(" 3. Ataque especial: " + jugador.nombreEspecial + " (usos: " + jugador.usosEspecial + ")");
            System.out.println(" 4. Defender");
            System.out.println(" 5. Recuperarse");
            System.out.println(" 6. Ver estadisticas (no gasta turno)");

            int opcion = leerOpcion(1, 6);

            //Muestra las estadisticas sin perder turno
            if (opcion == 6) {
                jugador.mostrarEstadisticas();
                rival.mostrarEstadisticas();
                continue;
            }
            if (opcion == 3 && jugador.usosEspecial == 0) {
                System.out.println("Ya no te quedan usos del ataque especial.");
                continue;
            }

            switch (opcion) {
                case 1:
                    danio = jugador.atacar(rival, elegirHechizo(jugador.hechizosAtaque));
                    break;
                case 2:
                    danio = jugador.atacar(rival, elegirHechizo(jugador.hechizosAtaque), 1.5);
                    break;
                case 3:
                    danio = jugador.ataqueEspecial(rival);
                    break;
                case 4:
                    jugador.defender(elegirHechizo(jugador.hechizosDefensa));
                    break;
                case 5:
                    jugador.recuperarse();
                    break;
            }
            break;
        }
        return danio;
    }

    //Turno del villano manejado aleatorio
    static int turnoDelVillano(Villano rival, SuperHeroe jugador) {
        rival.defendiendo = false;
        System.out.println("Turno de " + rival.nombre );

        int hechizo = Personaje.numeroAleatorio(0, rival.hechizosAtaque.length - 1);
        int dado = Personaje.numeroAleatorio(1, 100);
        int danio = 0;

        if (rival.vidaHp < rival.vidaMax * 0.3 && dado <= 40) {
            rival.recuperarse();
        } else if (dado <= 20 && rival.usosTrampa > 0) {
            danio = rival.hacerTrampa(jugador, hechizo);
        } else if (dado <= 35) {
            rival.defender(Personaje.numeroAleatorio(0, rival.hechizosDefensa.length - 1));
        } else if (dado <= 50) {
            danio = rival.atacar(jugador, hechizo, 1.5);
        } else {
            danio = rival.atacar(jugador, hechizo);
        }
        return danio;
    }

    //Muestra los hechizos disponibles para el heroe
    static int elegirHechizo(String[] hechizos) {
        System.out.println("Elige el hechizo:");
        for (int i = 0; i < hechizos.length; i++) {
            System.out.println("  " + (i + 1) + ". " + hechizos[i]);
        }
        return leerOpcion(1, hechizos.length) - 1;
    }

    static int leerOpcion(int min, int max) {
        int opcion;
        do {
            System.out.print("> ");
            opcion = sc.nextInt();
            if (opcion < min || opcion > max) {
                System.out.println("Opcion invalida, escribe un numero entre " + min + " y " + max );
            }
        } while (opcion < min || opcion > max);
        return opcion;
    }

    //Creacion de los personajes
    static SuperHeroe[] crearHeroes() {
        SuperHeroe[] heroes = new SuperHeroe[3];
        heroes[0] = new SuperHeroe("Harry Potter", 18, 16, 120, new String[]{"Sectumsempra", "Desmaius", "Hechizo basico"}, new double[]{1.4, 1.6, 1.0}, new String[]{"Protego", "Escudo del Capitan America"}, "Expelliarmus");
        heroes[1] = new SuperHeroe("Hermione Granger", 15, 18, 110, new String[]{"Petrificus Totalus", "Confundo", "Hechizo basico"}, new double[]{1.7, 1.4, 1.0}, new String[]{"Protego", "Escudo del Capitan America"}, "Bombarda Maxima");
        heroes[2] = new SuperHeroe("Ron Weasley", 16, 14, 130, new String[]{"Imperio", "Slugulus Erecto", "Hechizo basico"}, new double[]{1.6, 1.3, 1.0}, new String[]{"Protego", "Escudo del Capitan America"}, "Jaque Mate");
        return heroes;
    }

    static Villano[] crearVillanos() {
        Villano[] villanos = new Villano[3];
        villanos[0] = new Villano("Lord Voldemort", 20, 15, 130, new String[]{"Avada Kedavra", "Parsel", "Sclopetum"}, new double[]{1.8, 1.2, 1.4}, new String[]{"Protego avanzado", "Creacion de inferi"});
        villanos[1] = new Villano("Bellatrix Lestrange", 17, 18, 115, new String[]{"Crucio", "Stupefy", "Confringo"}, new double[]{1.7, 1.3, 1.5}, new String[]{"Protego", "Morsmordre"});
        villanos[2] = new Villano("Gellert Grindelwald", 19, 16, 125, new String[]{"Whip-like spell", "Ripple spell", "Nigromancia"}, new double[]{1.4, 1.5, 1.7}, new String[]{"Protego Diabolica", "Aparicion"});
        return villanos;
    }
}