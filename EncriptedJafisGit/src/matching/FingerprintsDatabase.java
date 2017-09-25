package matching;

import model.Finger;
import model.Fingerprint;

import java.io.*;
import java.util.*;

public class FingerprintsDatabase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8588930080462566138L;

	public static FingerprintsDatabase loadExistent(String path) throws Exception {

		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
			FingerprintsDatabase fingerprintsDatabase = (FingerprintsDatabase) objectInputStream.readObject();
			objectInputStream.close();
			return fingerprintsDatabase;
		} catch (Exception e) {
			return new FingerprintsDatabase();
		}
	}
	
//	import java.sql.*;
//
//	public class Anfrage {
//
//	   public static void main(String[] args) {
//
//	      try {
//
//	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//
//	            Connection con = java.sql.DriverManager.getConnection(
//
//	                                               "jdbc:odbc:weine", "", "");
//
//	            Statement s = con.createStatement();
//
//	            ResultSet res;
//
//	res = s.executeQuery("select * from rotweine where Preis<20");
//
//	            while (res.next()) {
//
//	                  System.out.print(res.getString("Name") + "\t");
//
//	                  System.out.println(res.getDouble("Preis"));
//
//	            }
//
//	            con.close();
//
//	      }
//
//	      catch (Exception e) { System.out.println(""+e.getMessage());}
//
//	   }
//
//	}

	private List<Fingerprint> fingerprints;
	private Map<Finger,List<Fingerprint>> fingerDb;

	public FingerprintsDatabase() {

		fingerprints = new ArrayList<>();
		fingerDb = new HashMap<>();
	}

	public void addFinger(Finger finger) {
		fingerDb.put(finger, new LinkedList<Fingerprint>());
	}

	public void addFingerprintToFinger(Finger finger, Fingerprint fingerprint) {

		//Hier könnte die verschlüsselung stattfinden und anschließnd der verschlüsselte fb in die db geschrieben werden
		fingerDb.get(finger).add(fingerprint);
		fingerprints.add(fingerprint);
	}

	public void remove(Finger finger) {

		for (Fingerprint fingerprint : fingerDb.get(finger)) {
			fingerprints.remove(fingerprint);
		}
		fingerDb.remove(finger);
	}

	public void remove(Finger finger, Fingerprint fingerprint) {

		fingerprints.remove(fingerprint);
		fingerDb.get(finger).remove(fingerprint);
	}

	public List<Fingerprint> getFingerprints(Finger finger) {
		return fingerDb.get(finger);
	}

	public List<Fingerprint> getFingerprints() {
		return fingerprints;
	}

	public Map<Finger, List<Fingerprint>> getFingerDb() {
		return fingerDb;
	}

	public Finger getFinger(Fingerprint fingerprint) {

		for (Map.Entry<Finger,List<Fingerprint>> entry : fingerDb.entrySet()) {

			if (entry.getValue().contains(fingerprint)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public void saveDB(String path) throws Exception {

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
		objectOutputStream.writeObject(this);
		objectOutputStream.close();
	}
}