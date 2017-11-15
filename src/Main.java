import java.util.Scanner;

public class Main {

    static int pozitie = 0;

    //creeaza un array de persons
    static Person[] contacte = new Person[6];     //un pointer spre obiecte; cand instantiezi arrayu aloca memorie numa ot pointeri, nu si pt obiecte

    public static void main(String[] args) {

        //creeaza contacte si asigneaza valori contactelor
        contacte[0] = new Person();         //instantiaza obiectul, aloca memorie practic, pentru a putea da valori
        contacte[0].name = "Ana";
        contacte[0].phone = "024566542";

        contacte[1] = new Person();
        contacte[1].name = "Maria M";
        contacte[1].phone = "07678234";

        contacte[2] = new Person();
        contacte[2].name = "Maria";
        contacte[2].phone = "07623234";

        contacte[3] = new Person();
        contacte[3].name = "Ioana";
        contacte[3].phone = "04678234";

        contacte[4] = new Person();
        contacte[4].name = "Laura";
        contacte[4].phone = "076278234";

        int optiune = -1;
        do {
            afisareMeniu();
            Scanner sc = new Scanner(System.in);
            optiune = sc.nextInt();
            switch (optiune) {
                case 1: {
                    afisareAgenda(contacte);
                    break;
                }

                case 2: {
                    adaugareAgenda();
                    break;
                }

                case 3: {
                    stergere();
                    break;
                }

                case 4: {
                    cautareSimpla();
                    break;
                }

                case 5: {
                    modificare();
                    break;
                }
            }
        }
        while (optiune != 0);
    }


    public static void afisareMeniu() {
        System.out.println("1> Afisare");
        System.out.println("2> Adaugare");
        System.out.println("3> Stergere");
        System.out.println("4> Cautare simpla");
        System.out.println("5> Modificare");
        System.out.println("0> Exit");

    }

    public static void afisareAgenda(Person[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                System.out.println(a[i].name + " \n" + a[i].phone);
                System.out.println();
            }

        }
    }

    public static String citireNume(String label) {
        System.out.print(label);
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        return name;
    }

    public static void adaugareAgenda() {
        boolean a = false;

        for (int i = 0; i < contacte.length; i++) {
            if (contacte[i] == null) {
                // System.out.println(contacte[i].name);
                contacte[i] = new Person();
                String nume = citireNume("Introduceti numele de adaugat:");
                contacte[i].name = nume;


                String telefon = citireNume("Introduceti numarul de telefon:");
                contacte[i].phone = telefon;
                a = true;
                break;

            }
        }
        if (a == false) {
            System.out.println("Agenda plina, ia alta mai mare! ");
        }

        System.out.println();
    }

    public static int cautareSimpla() {

        String cautat = citireNume("Introduceti numele cautat:");         //Se poate introduce nume sau numar de telefon pentru cautare
        int a = 0;
        for (int i = 0; i < contacte.length && contacte[i] != null; i++) {

            if (contacte[i].name.toLowerCase().contains(cautat.toLowerCase()) || contacte[i].phone.equals(cautat)) {            //nume.equalsIgnoreCase(agendaDeNume[i])) {
                a++;

                System.out.println(contacte[i].name + " \n" + contacte[i].phone);
                System.out.println();
            }
        }
        if (a == 0) {
            System.out.println("Nu aveti acesta persoana in agenda.");
        }

        return a;
    }

    public static void stergere() {
        int a = cautareSimpla();

        //se poate alege din mai multe persoane cu acelasi nume pentru a sterge una anume
        //nu am gasit o varianta cum sa fac sa nu fie nevoie sa mai introduca persoana in cazul in care s-a gasit doar una dupa cautare, fara sa fie nevoie sa repet cod
        if (a > 0) {
            String numeDeSters = citireNume("Introduceti numele exact ca in Agenda pentru stergere:");
            for (int i = 0; i < contacte.length && contacte[i] != null; i++) {
                if (numeDeSters.equals(contacte[i].name)) {
                    System.out.println(contacte[i].name + " a fost eliminata din agenda.");
                    contacte[i] = null;
                    a = 0;
                    break;
                }
            }

            if (a != 0) {
                System.out.println("Nu aveti acesta persoana in agenda.");
            }
        }

    }

    public static void modificare() {
        int a = cautareSimpla();
        boolean found = true;

        //se poate alege din mai multe persoane cu acelasi nume pentru a modifica una anume
        if (a > 0) {
            String numeDeModificat = citireNume("Introduceti numele exact ca in Agenda pentru modificare: ");
            for (int i = 0; i < contacte.length && contacte[i] != null; i++) {
                if (numeDeModificat.equals(contacte[i].name)) {

                    String b = citireNume("Tastati 1 - modificare nume sau 2 - modificare numar de telefon ");

                    if (b.equals("1")) {
                        String numeNou = citireNume("Introduceti numele nou: ");
                        contacte[i].name = numeNou;
                    }

                    if (b.equals("2")) {
                        String numarNou = citireNume("Introduceti numarul de telefon nou: ");
                        contacte[i].phone = numarNou;
                    }
                    found = true;
                    break;

                } else {
                    found = false;
                }
            }

            if (found == false) {
                System.out.println("Numele introdus nu se gaseste in agenda.");
            }
        }
    }

}