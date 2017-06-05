package ch.hepia.IL.ReedMuller.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class PGMReader {

	public static BigInteger[][] parse(String filename) {

		BigInteger[][] values = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			int maxGrey = 0;
			int x = 0, y = 0;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if(line.startsWith("#")) continue;
				if(line.isEmpty()) continue;
				if(maxGrey == -1) {
					maxGrey = Integer.parseInt(line);
					continue;
				}
				if(values == null) {
					String[] infs = line.split(" +");
					if(infs.length == 2) {
						values = new BigInteger[Integer.parseInt(infs[0])][Integer.parseInt(infs[1])];
						maxGrey = -1;
					}
				} else {
					if(y >= values.length) {
						throw new RuntimeException("Too many numbers");
					}
					values[y][x] = new BigInteger(line);
					x++;
					if(x >= values[0].length) {
						x = 0;
						y++;
					}
					
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}

}
