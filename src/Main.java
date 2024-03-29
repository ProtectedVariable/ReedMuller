import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import ch.hepia.IL.ReedMuller.ReedMullerOne;
import ch.hepia.IL.ReedMuller.files.PGMReader;
import ch.hepia.IL.ReedMuller.files.PGMWriter;
import ch.hepia.IL.ReedMuller.searches.QuickSearch;

public class Main {

	public static void main(String[] args) throws IOException {
		// permet de prendre les entrées pour le menu
		// soit du clavier, d'un fichier ou de la ligne de commande
		Scanner in;
		switch (args.length) {
			case 0:
				in = new Scanner(System.in);
				break;
			case 1:
				in = new Scanner(new File(args[0]));
				break;
			default:
				String source = args[0];
				for (int i = 1; i < args.length; i++)
					source += " " + args[i];
				in = new Scanner(source);
		}

		// les impressions des menus sont envoyées sur le canal d'erreur
		// pour les différencier des sorties de l'application
		// lesquelles sont envoyées sur la sortie standard

		// choix des paramètres
		System.err.println("Mot en clair de (r+1)-bits à encoder sur (2^r)-bits.");
		System.err.println("Choisir la valeur de r: ");
		int r = in.nextInt();
		System.err.println("\nLe seuil de bruit est la probabilité d'inverser un bit.");
		System.err.println("Choisir un seuil de bruit (nombre entre 0.0 et 1.0): ");
		double seuil = in.nextDouble();

		// traiter un mot ou une image
		System.err.println("\nMenu initial");
		System.err.println("0: Quitter");
		System.err.println("1: Traiter un mot");
		System.err.println("2: Traiter une image");
		int mode = in.nextInt();

		// opération à effectuer sur le mot ou l'image
		String menu = "\nMenu opérations\n" + "0: Quitter\n" + "1: Encoder\n" + "2: Décoder\n" + "3: Bruiter\n" + "4: Débruiter\n" + "5: Réinitialiser\n" + "Opération choisie:";
		ReedMullerOne rmo = new ReedMullerOne(r);
		int choix = 5;
		if (mode == 1) {
			BigInteger mot = null;
			do {
				switch (choix) {
					case 1:
						// vos opérations pour l'encodage du mot courant,
						// ne rien afficher sur la sortie standard
						mot = rmo.encode(mot);
						break;
					case 2:
						// vos opérations pour le décodage du mot courant,
						// ne rien afficher sur la sortie standard
						mot = rmo.decode(mot);
						break;
					case 3:
						// vos opérations pour le bruitage du mot courant,
						// ne rien afficher sur la sortie standard
						for (int i = 0; i < (1 << rmo.getR()); i++) {
							if (Math.random() <= seuil) {
								mot = mot.flipBit(i);
							}
						}
						break;
					case 4:
						// vos opérations pour le débruitage du mot courant,
						// ne rien afficher sur la sortie standard
						mot = rmo.encode(QuickSearch.getInstance().nearestWord(mot, rmo));
						break;
					case 5:
						System.err.println("\nEntrer un mot (en décimal)");
						mot = new BigInteger(in.next());
						break;
				}
				if (choix != 5) {
					System.err.println("Valeur du mot courant (en décimal):");
					// imprimer la valeur du mot courant en décimal
					// sur la sortie standard
					System.out.println(mot);
				}
				System.err.println(menu);
				choix = in.nextInt();
			} while (choix != 0);
		} else if (mode == 2) {
			BigInteger file[][] = null;
			do {
				String fileName;
				switch (choix) {
					case 1:
						// vos opérations pour l'encodage de l'image courante,
						// ne rien afficher sur la sortie standard
						for (int i = 0; i < file.length; i++) {
							for (int j = 0; j < file[0].length; j++) {
								file[i][j] = rmo.encode(file[i][j]);
							}
						}
						break;
					case 2:
						// vos opérations pour le décodage de l'image courante,
						// ne rien afficher sur la sortie standard
						for (int i = 0; i < file.length; i++) {
							for (int j = 0; j < file[0].length; j++) {
								file[i][j] = rmo.decode(file[i][j]);
							}
						}
						break;
					case 3:
						// vos opérations pour le bruitage de l'image courante,
						// ne rien afficher sur la sortie standard
						for (int i = 0; i < file.length; i++) {
							for (int j = 0; j < file[0].length; j++) {
								for (int k = 0; k < (1 << rmo.getR()); k++) {
									if (Math.random() <= seuil) {
										file[i][j] = file[i][j].flipBit(k);
									}
								}
							}
						}
						break;
					case 4:
						// vos opérations pour le débruitage de l'image
						// courante,
						// ne rien afficher sur la sortie standard
						for (int i = 0; i < file.length; i++) {
							for (int j = 0; j < file[0].length; j++) {
								file[i][j] = rmo.encode(QuickSearch.getInstance().nearestWord(file[i][j], rmo));
							}
						}
						break;
					case 5:
						System.err.println("Nom du fichier de l'image à charger (format png):");
						fileName = in.next();
						// lire le fichier contenant l'image pgm
						file = PGMReader.parse(fileName);
						break;
				}
				if (choix != 5) {
					System.err.println("Nom du fichier où sauver l'image courante (format png):");
					fileName = in.next();
					// sauver l'image courante au format pgm
					PGMWriter.writeFile(file, fileName);
				}
				System.err.println(menu);
				choix = in.nextInt();
			} while (choix != 0);
			in.close();
		}
	}
}

// Encoder => Décoder ou Bruiter ou Réinitialiser
// Décoder => Encoder ou Réinitialiser
// Bruiter => Débruiter ou Bruiter ou Réinitialiser
// Débruiter => Bruiter ou Décoder ou Réinitialiser
