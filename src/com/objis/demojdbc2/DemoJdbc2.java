package com.objis.demojdbc2;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DemoJdbc2 {

	public static void main(String[] args) {
		
		// Connexion
		String url = "jdbc:mysql://localhost/formation2";
		String user = "root";
		String pwd = "xxxxxxxx";

		// Chargement du driver
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			//Creer une connexion
			Connection cn = (Connection) DriverManager.getConnection(url, user, pwd);
			
			// Statement
			Statement st = (Statement) cn.createStatement();
			
			// Definition requete
			String sql = "SELECT  * FROM formation2.clients";
			
			
			// Executer requete
			ResultSet result = st.executeQuery(sql);
			
			
			int ncVar;
			String nomcVar;
			String villeVar;
			
			while (result.next()) {
				//recuperer le "nc"
				ncVar = result.getInt("nc");
				
				//recuperer le "nomc"
				nomcVar = result.getString("nomc");
				
				//recuperer le "ville"
				villeVar = result.getString("ville");
				
				System.out.println("Numero client : " + ncVar + ", Nom client: " + nomcVar + ", Ville : " + villeVar);
			}
			
			String sql2 = "SELECT np, nomp FROM formation2.produits WHERE coul = 'rouge' AND pds > 2000";
			
			ResultSet result2 = st.executeQuery(sql2);
			
			int numeroProduit;
			String nomProduit;
			//String couleur;
			//int poids;
			
			
			while (result2.next()) {
				numeroProduit = result2.getInt("np");
				nomProduit = result2.getString("nomp");
				//poids = result2.getInt("pds");
				//couleur = result2.getString("coul");
				
				System.out.println("\nLe produit rouge superieur à 2000g est le : " + numeroProduit + " " + nomProduit);
			}
			
			String sql3 = "SELECT representants.nomr, ventes.qi FROM formation2.representants INNER JOIN formation2.ventes ON representants.nr = ventes.qi WHERE qi >1";
			
			ResultSet result3 = st.executeQuery(sql3);
			
			int nombreVente;
			String nomRepresentant;
			
			while(result3.next()) {
				nombreVente = result3.getInt("qi");
				nomRepresentant = result3.getString("nomr");
				
				System.out.println(nomRepresentant + " a acheté plus d'un article");
			}
			
			String sql4 = "SELECT DISTINCT clients.nomc "
					+ "FROM formation2.clients "
					+ "INNER JOIN formation2.ventes "
					+ "ON clients.nc = ventes.nc "
					+ "WHERE clients.ville = 'Lyon' "
					+ "AND ventes.qi >180";
			
			
			ResultSet result4 = st.executeQuery(sql4);
			
			String nomClient;
			
			
			while(result4.next()){
				nomClient = result4.getString("nomc");
				
				System.out.println("\nLes clients residants Lyon et ayant achetés pour plus de 180 unites sont : " + nomClient);
				
			}
			String sql5 = "SELECT DISTINCT clients.nomc, representants.nomr FROM (((formation2.ventes\r\n" + 
					"\r\n" + 
					"INNER JOIN formation2.produits\r\n" + 
					"ON ventes.np = produits.np)\r\n" + 
					"\r\n" + 
					"INNER JOIN formation2.representants \r\n" + 
					"ON ventes.nr = representants.nr)\r\n" + 
					"\r\n" + 
					"INNER JOIN formation2.clients\r\n" + 
					"ON ventes.nc = clients.nc)\r\n" + 
					"\r\n" + 
					"WHERE produits.coul='Rouge'\r\n" + 
					"\r\n" + 
					"AND ventes.qi > 100";
			
			ResultSet result5 = st.executeQuery(sql5);
			
			//String nomClient5;
			String nomRepresentant5;
			String nomClient5;
			
			while(result5.next()) {
				nomClient5 = result5.getString("nomc");
				nomRepresentant5 = result5.getString("nomr");
				
				System.out.println("Le representant " + nomRepresentant5 + "a vendu à " + nomClient5);
			
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
