package com.example.demo4;

import com.example.demo4.entities.Client;
import com.example.demo4.entities.Commande;
import com.example.demo4.entities.LigneCommande;
import com.example.demo4.entities.Produit;
import com.example.demo4.service.ClientRepository;
import com.example.demo4.service.CommandeRepository;
import com.example.demo4.service.LigneCommandeRepository;
import com.example.demo4.service.ProduitRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestConnection {
    /*public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Produit p1 = new Produit(0L, "pRep", 10.1f);

        ProduitRepository pREP= new ProduitRepository();
        p1=pREP.saveProduit(p1);

        Client c1 = new Client(0L,"salah Eddine Rossi","Tangier");
        ClientRepository cREP= new ClientRepository();
        c1=cREP.saveClient(c1);

        Commande commande1 = new Commande(0L,c1);
        CommandeRepository commandeRepo= new CommandeRepository();
        commande1=commandeRepo.saveCommande(commande1);

        LigneCommande ligneCommande = new LigneCommande(0L,2,commande1,p1);
        LigneCommandeRepository ligneCommandeRepository = new LigneCommandeRepository();
        ligneCommandeRepository.saveLigneCommande(ligneCommande);

        c1.setNom("rossi");
        cREP.updateClient(c1);
        ProduitRepository pREP2= new ProduitRepository();
        CommandeRepository commandeRepository = new CommandeRepository();
        LigneCommandeRepository ligneCommandeRepository = new LigneCommandeRepository();



        Produit p2 = pREP2.getOneProduit(10L);

        System.out.println(p2);

        for(Produit tmp:pREP2.getAllProduits()){
            System.out.println(tmp);
        }

        for(Commande tmp : commandeRepository.getAllCommandes()){
            System.out.println(tmp);
        }

        for(LigneCommande tmp:ligneCommandeRepository.findLignesCommandeByCommande(commandeRepository.getOneCommande(7L))){
            System.out.println(tmp);
        };



    }*/

}
