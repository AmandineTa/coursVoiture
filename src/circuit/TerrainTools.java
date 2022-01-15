package circuit;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;

public class TerrainTools {
	
	public static Terrain terrainFromChar(char c)
			throws TerrainException {
		Terrain[] values = Terrain.values();
		for(int i= 0; i<values.length; i++){
			if(c == Terrain.conversion[i])
				return values[i];}
		throw new TerrainException("Terrain inconnu : "+c);
	}
	
	public static char charFromTerrain(Terrain c){
		return Terrain.conversion[c.ordinal()];
	}
	public static Color terrainToRGB(Terrain c){
		return Terrain.convColor[c.ordinal()];
	}
	
	public static boolean isRunnable(Terrain t){
		Terrain[] values = Terrain.values();
		int k = 0;
		for (int i = 0; i< values.length ; i++){
			if(t== Terrain.BandeBlanche || t == Terrain.BandeRouge){
				k=k+1 ;
			}
			if(t == Terrain.StartPoint||t == Terrain.EndLine ){
				k= k+1;
			}
			if(t == Terrain.Boue|| t == Terrain.Route){
				k= k+1;
			}
		}
		return k!=0;
		}
	
	public static Terrain[][] buldTerrain(String filename) 
			throws FileNotFoundException{
		InputStream file = new FileInputStream(filename);
		try {
			//variable
			// ouverture 
			InputStreamReader fr = new InputStreamReader(file); 
			// fonction suppl�mentaire
			BufferedReader in = new BufferedReader(fr);
			String buf = in.readLine();
			//�criture des variables
			int x = Integer.parseInt(buf);
			buf = in.readLine();
			int y = Integer.parseInt(buf);
			Terrain t[][] = new Terrain[y][x] ;
			buf = in.readLine();
			int j = 0;
			while(buf != null){
				
				
				for(int i = 0; i<buf.length();i++){
					t[j][i] = terrainFromChar(buf.charAt(i));
				}	
				buf = in.readLine();
				j++;
			}
			in.close(); 
			return t;
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Format : " + file 
					+ "... Loading aborted");
		   return null;
		 }
	}
	
	public static BufferedImage imageFromCircuit(Circuit track){
		int nbLignes = track.getHeight();
		int nbColonnes = track.getWidth();
		BufferedImage im = // objet image en mémoire
                new BufferedImage(nbColonnes, nbLignes, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i<nbLignes;i++){
			for (int j = 0; j<nbColonnes; j++ ){
				int color = terrainToRGB(track.getTerrain(i, j)).getRGB();
				im.setRGB(j, i, color );
			}
		}		
//		try {
//            File outputfile = new File("saved.png");
//            ImageIO.write(im, "png", outputfile);
//         } catch (IOException e) {
//            System.out.println("Erreur lors de la sauvegarde");
//         }
		return im;
	}
}
