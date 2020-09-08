package projetJavaS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author thief
 *
 */
public class VirtualCard implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = -6299954673899501391L;
	/**
	 * 
	 */
	private String nom;
	/**
	 * 
	 */
	private String prenom;
	/**
	 * 
	 */
	private String numero;
	/**
	 * 
	 */
	private String mail;
	/**
	 * 
	 */
	private String entreprise;
	
	/**
	 * 
	 */
	public VirtualCard() {
		nom = null;
		prenom = null;
		numero = null;
		mail = null;
		entreprise = null;
	}
	
	/**
	 * 
	 * @param ln
	 * @param fn
	 * @param num
	 * @param m
	 * @param t
	 */
	public VirtualCard(String ln, String fn, String num, String m, String t) {
		nom = ln;
		prenom = fn;
		numero = num;
		mail = m;
		entreprise= t;
	}
	
	/**
	 * 
	 * @param f
	 */
	public VirtualCard (File f) {
		BufferedReader br;
		String currentLine;
		try {
			br = new BufferedReader(new FileReader(f));
			while ((currentLine = br.readLine()) != null) {
				String [] ligne = currentLine.split(":");
				if (ligne[0].equals("N")) {
					String n = ligne[1];
					String [] last = n.split(";");
					this.nom = last[0];
					this.prenom = last[1];
				}
				if (ligne[0].equals("ORG")) {
					this.entreprise = ligne[1];
				}
				if (ligne[0].equals("EMAIL")) {
					this.mail = ligne[1];
				}
				if (ligne[0].equals("TEL;TYPE=WORK,VOICE")) {
					this.numero = ligne[1];
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * 
	 * @param brvc
	 */
	public VirtualCard (BufferedReader brvc) {
		String currentLine;
		try {
			while ((currentLine = brvc.readLine()) != null) {
				String [] ligne = currentLine.split(":");
				if (ligne[0].equals("N")) {
					String n = ligne[1];
					String [] last = n.split(";");
					this.nom = last[0];
					this.prenom = last[1];
				}
				if (ligne[0].equals("ORG")) {
					this.entreprise = ligne[1];
				}
				if (ligne[0].equals("EMAIL")) {
					this.mail = ligne[1];
				}
				if (ligne[0].equals("TEL;TYPE=WORK,VOICE")) {
					this.numero = ligne[1];
				}
				if (currentLine.startsWith("END:VCARD")) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void fragHtmlCons () {
		System.out.println("\t\t\tVirtualCard : " + this.getNom() + " " + this.getPrenom() + "<br>\r\n" +
				"\t\t\t\tNom : " + this.getNom() + "<br>\r\n" +
				"\t\t\t\tPrénom : " + this.getPrenom() + "<br>\r\n" +
				"\t\t\t\tNuméro : " + this.getNumero() + "<br>\r\n" +
				"\t\t\t\tEmail : " + this.getMail() + "<br>\r\n" +
				"\t\t\t\tEntreprise : " + this.getEntreprise() + "<br>\r\n");
	}
	
	/**
	 * 
	 * @param f
	 * @param bw
	 */
	public void fragFileHtmlVCF (File f, BufferedWriter bw) {
		String str = new String ("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VirtualCard : " + this.getNom() + " " + this.getPrenom() + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nom : " + this.getNom() + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prénom : " + this.getPrenom() + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numéro : " + this.getNumero() + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email : " + this.getMail() + "<br>\r\n" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Entreprise : " + this.getEntreprise() + "<br>\r\n");
		try {
			bw.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param f
	 */
	public void fileHtmlVCF (File f) {
		BufferedWriter bw;
		String str = new String ("<!doctype html>\r\n" +
				"<html lang =\"fr\">\r\n" +
				"<head>\r\n" +
				"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" +
				"  <title>" + this.getNom() + " " + this.getPrenom() + "</title>\r\n" +
				"  <link rel=\"stylesheet\" href=\"style.css\">\r\n" +
				"  <script src=\"script.js\"></script>\r\n" +
				"</head>\r\n" +
				"<body>\r\n" +
				"Nom :" + this.getNom() + "<br>\n" +
				"Prénom :" + this.getPrenom() + "<br>\n" +
				"Numéro :" + this.getNumero() + "<br>\n" +
				"Email :" + this.getMail() + "<br>\n" +
				"Entreprise :" + this.getEntreprise() + "<br>\n" +
				"</body>\r\n" + 
				"</html>");
		try {
			bw = new BufferedWriter (new FileWriter(f));
			bw.write(str);
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param f
	 */
	public void serialize(File f) {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.getStackTrace();
		}	
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	public ArrayList<VirtualCard> deserialize (File f) {
		ArrayList <VirtualCard> vc = new ArrayList<VirtualCard>();
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			vc.add((VirtualCard) ois.readObject());
			ois.close();
			return vc;
		} catch (IOException e) {
			e.getStackTrace();
			System.out.println("Le fichier n'existe pas !");
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		}
		return null; 
	}
	
	/**
	 * 
	 * @Override
	 */
	public String toString() {
		return "VirtualCard [nom=" + nom + ", prenom=" + prenom + ", numero=" + numero + ", mail=" + mail + ", entreprise="
				+ entreprise + "]";
	}
	
	/**
	 * 
	 * @Override
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((entreprise == null) ? 0 : entreprise.hashCode());
		return result;
	}
	
	/**
	 * 
	 * @Override
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VirtualCard other = (VirtualCard) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (entreprise == null) {
			if (other.entreprise != null)
				return false;
		} else if (!entreprise.equals(other.entreprise))
			return false;
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * 
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * 
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * 
	 * @param mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEntreprise() {
		return entreprise;
	}
	
	/**
	 * 
	 * @param entreprise
	 */
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
}
