package tpp;

public class Compte {
    private static int compteur = 0; 
    private int numeroCompte;
    private Personne titulaire;
    private double solde;
    private double decouvertMax;
    private double debitMax;

    public Compte(Personne titulaire) {
        this.numeroCompte = ++compteur;
        this.titulaire = titulaire;
        this.solde = 0;
        this.decouvertMax = 800;
        this.debitMax = 1000;
    }

    public Compte(Personne titulaire, double decouvertMax, double debitMax) {
        this.numeroCompte = ++compteur;
        this.titulaire = titulaire;
        this.solde = 0;
        this.decouvertMax = decouvertMax;
        this.debitMax = debitMax;
    }

    @Override
    public String toString() {
        return "Compte N°" + numeroCompte + " - Titulaire: " + titulaire +
               "\nSolde: " + solde + " DT, Découvert Max: " + decouvertMax + " DT, Débit Max: " + debitMax + " DT";
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }

    public double getSolde() {
        return solde;
    }

    public double getDecouvertMax() {
        return decouvertMax;
    }

    public void setDecouvertMax(double decouvertMax) {
        this.decouvertMax = decouvertMax;
    }

    public double getDebitMax() {
        return debitMax;
    }

    public void setDebitMax(double debitMax) {
        this.debitMax = debitMax;
    }

    public void crediter(double montant) {
        if (montant > 0) {
            solde += montant;
        } else {
            System.out.println("Le montant doit être positif.");
        }
    }

    public void debiter(double montant) {
        if (montant > 0 && solde - montant >= -decouvertMax) {
            solde -= montant;
        } else {
            System.out.println("Le montant dépasse le découvert maximal autorisé.");
        }
    }

   /* public void retrait(double montant) {
        if (montant > 0 && montant <= debitMax && solde - montant >= -decouvertMax) {
            solde -= montant;
        } else {
            System.out.println("Retrait impossible : dépasse le débit maximal ou découvert maximal.");
        }
    }*/

    public boolean estADecouvert() {
        return solde < 0;
    }

    public double montantDecouvert() {
        return Math.abs(solde);
    }

    public void virement(Compte autreCompte, double montant) {
        if (solde - montant >= -decouvertMax) {
            this.debiter(montant);
            autreCompte.crediter(montant);
        } else {
            System.out.println("Virement impossible : découvert maximal dépassé.");
        }
    }

    public static void virement(Compte compteSource, Compte compteDestinataire, double montant) {
        if (compteSource.solde - montant >= -compteSource.decouvertMax) {
            compteSource.debiter(montant);
            compteDestinataire.crediter(montant);
        } else {
            System.out.println("Virement impossible : découvert maximal dépassé.");
        }
    }
}

