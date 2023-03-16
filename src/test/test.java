package test;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import algorithms.DefaultTeam;
import algorithms.Naif;

public class test {
	protected static String samplesDirName = "../Samples";
	protected static File result = new File("result.txt");
	protected static String path = ".points";
	public static void main(String[] args) throws IOException {
		
	Naif naif = new Naif();
	DefaultTeam welzl = new DefaultTeam();
	long temps1, temps2;
	FileWriter writer = new FileWriter(result, true);

	for (int i = 10; i <= 1600; i++) {
		//List de points de chaque fichier
		ArrayList<Point> points = new ArrayList<Point>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./samples/test-" + i + ".points"));
			String line=reader.readLine();
			//Construction de la liste des points du fichier test numero i 
			while (line != null) {
				String[] coordone = line.split(" ");
				points.add(new Point(Integer.parseInt(coordone[0]), Integer.parseInt(coordone[1])));
				 line=reader.readLine();
			}
			temps1 = System.nanoTime();
			naif.calculCercleMin(points);
			temps1 =System.nanoTime() - temps1;

			temps2 = System.nanoTime();
			welzl.calculCercleMin(points);
			temps2 =System.nanoTime() - temps2;

			//affichage du resultat en nanosecond 
			String contents = "test" + i + " Naif: " +( temps1) + " Welzl: " + (temps2) + "\n";
			writer.write(contents);
			writer.flush();
			reader.close();
		} catch (IOException e) {
		}
	}
	writer.close();
}




}
