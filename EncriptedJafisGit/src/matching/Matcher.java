package matching;

import model.Fingerprint;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matcher {

	public static final EuclideanDistance EUCLIDEAN_DISTANCE = new EuclideanDistance();
	static String pathToCFile = "/home/tutzauer/Desktop/Ba/Cpp/main";
	static String newFingerprint;
	static String fPrintFromDatabaseAtPositionI;
	static String thresh;
	static int lambda;
	static boolean flag = true;
	static int y;

	private static Fingerprint getLambda(List<Fingerprint> fpBase, Fingerprint fingerprint, double threshold) throws IOException {
/* 
 * beim ersten vorkommen eines fingerprints mit einem wert kleiner threshold wird dieser akzeptiert
 * hier muss eine weitere schleife eingebunden werden, dass die jenigen die kleiner sind
 * nochmals nach c geschickt werden, solange bis das kleinste gefunden wurde
 * dieser ist dann der gültige
 */
		Fingerprint nearest = null;
		
		thresh = String.valueOf(Math.round(threshold));
		//Fingercode an Stelle 1 von double in String wandeln
//		newFingerprint = String.valueOf(Math.round((fingerprint.getFeatureValues()[0])*10));	
		newFingerprint = String.valueOf(Math.round((fingerprint.getFeatureValues()[0])));	
		
		//Db mit allen Fngercodes durchlaufen und an c schicken
		Map<Fingerprint,Double> distances = new HashMap<>();
		for (Fingerprint fpFromBase : fpBase) 
		{		
			//Fingerprint aus Database an Position i nehmen zu einem String machen 
//			fPrintFromDatabaseAtPositionI = String.valueOf(Math.round(fpFromBase.getFeatureValues()[0])*10);
			fPrintFromDatabaseAtPositionI = String.valueOf(Math.round(fpFromBase.getFeatureValues()[0]));
		
			System.out.println("Fingercode des eingelesenen fprints: " +  newFingerprint);
			System.out.println("Fingercode von der Datenbank an position i: " + fPrintFromDatabaseAtPositionI);
			
			
			//Distanz-Berechnung und das vergleichen wird an c++ übergeben 						
			Process p = new ProcessBuilder(pathToCFile, newFingerprint ,fPrintFromDatabaseAtPositionI, thresh).start();			
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
//			System.out.printf("Output of running %s is:", Arrays.toString(args))

		
			while ((line = br.readLine()) != null) 
			{
				 System.out.println(line);
				 try {
					y = Integer.parseInt(line);
					 if(y == 0)
					 {
						System.out.println("lambda: " + y);
						nearest = fpFromBase;	
						isr.close();
						br.close();
						return nearest;
					 }
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}	
		}	
		return nearest;
	}
	
	

	public static Fingerprint match(FingerprintsDatabase fpBase, Fingerprint fingerprint, double threshold) throws IOException
	{
		Fingerprint nearest = getLambda(fpBase.getFingerprints(), fingerprint, threshold);			
		return nearest;
	}
	
}