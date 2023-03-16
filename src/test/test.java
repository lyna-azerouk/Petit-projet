package test;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
	FileWriter writer_result = new FileWriter(result, true);
	FileWriter writer_csv = new FileWriter("Histogramme.csv");
//	writer_csv.write("type, time_nanosec\n");
	long totale_welzl=0 , totale_naif=0 ; 
	for (int i = 2; i <= 1631; i++) {
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
			totale_naif =totale_naif+temps1;
			
			temps2 = System.nanoTime();
			welzl.calculCercleMin(points);
			temps2 =System.nanoTime() - temps2;
			totale_welzl= temps2+totale_welzl;
			
			//affichage du resultat en nanosecond 
			String contents = "test" + i + " Naif: " +( temps1) + " Welzl: " + (temps2) + "\n";
			writer_result.write(contents);
			writer_result.flush();
			reader.close();
		} catch (IOException e) {
		}
	}
	writer_result.close();
	writer_csv.write("Wzel "+ totale_welzl/1630+"\n" );
	
	writer_csv.write("Naif "+ totale_naif/1630 );
	writer_csv.close(); 

}




}
