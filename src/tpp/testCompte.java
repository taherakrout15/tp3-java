package tpp;

import java.util.Scanner;

public class testCompte {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le nombre de comptes à créer : ");
        int n = sc.nextInt();
        Compte[] comptes = new Compte[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n Création du compte n°" + (i + 1));
            System.out.print(" \n Nom du titulaire : ");
            String nom = sc.next();
            System.out.print(" \n Prénom du titulaire : ");
            String prenom = sc.next();
            System.out.print("\n Adresse du titulaire : ");
            String adresse = sc.next();
            Personne titulaire = new Personne(nom, prenom, adresse);
            comptes[i] = new Compte(titulaire);
        }

        for (Compte compte : comptes) {
            System.out.println(compte);
        }

        System.out.println("\n Effectuer un virement entre deux comptes.");
        System.out.print("\n Numéro du compte source : ");
        int sourceIndex = sc.nextInt() - 1;
        System.out.print("\n Numéro du compte destinataire : ");
        int destIndex = sc.nextInt() - 1;
        System.out.print("\n Montant du virement : ");
        double montant = sc.nextDouble();
        Compte.virement(comptes[sourceIndex], comptes[destIndex], montant);

        Compte compteMax = comptes[0];
        for (Compte compte : comptes) {
            if (compte.getSolde() > compteMax.getSolde()) {
                compteMax = compte;
            }
        }
        System.out.println("\n Le compte avec le solde le plus élevé est :");
        System.out.println(compteMax);
        
        sc.close();
    }
}
