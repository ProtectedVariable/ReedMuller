package ch.hepia.IL.ReedMuller.files;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class PGMWriter {

	public static void writeFile(BigInteger[][] values, String filename) {
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.println("P2");
		    writer.print(values.length+" "+values[0].length+"\n");
		    writer.println("63");
		    
		    for (int i = 0; i < values.length; i++) {
				for (int j = 0; j < values[0].length; j++) {
					writer.println(values[i][j]);
				}
			}
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
