package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {

        // Ouverture du fichier de configuration pour récupérer les noms des classes
        Scanner scanner = new Scanner(new File("config.txt"));

        // Récupération de la première ligne : nom complet de la classe DAO
        String daoClassName = scanner.nextLine();

        // Chargement dynamique de la classe DAO grâce à la réflexion
        Class<?> cDao = Class.forName(daoClassName);

        // Création d'une instance de cette classe (objet DAO)
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        // Lecture de la deuxième ligne du fichier : nom de la classe Métier
        String metierClassName = scanner.nextLine();

        // Chargement de la classe métier à partir de son nom
        Class<?> cMetier = Class.forName(metierClassName);

        // Instanciation de la classe métier
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        // Recherche de la méthode setDao dans la classe métier
        // Cette méthode permettra d'injecter la dépendance DAO dans la couche métier
        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);

        // Appel de la méthode setDao pour effectuer l'injection de dépendance
        setDaoMethod.invoke(metier, dao);

        // Exécution de la méthode calcul() du service métier
        // puis affichage du résultat dans la console
        System.out.println("Résultats = " + metier.calcul());

        // Fermeture du scanner pour libérer la ressource
        scanner.close();
    }
}