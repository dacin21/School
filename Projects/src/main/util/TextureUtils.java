package main.util;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.util.BufferUtils;

public class TextureUtils {
	//Die class isch da, zum Texture vome BildFile in e LWJGL texture z lade und d ID zrugbecho

	private int width, height;
	private int texture;
	
	public TextureUtils(String path){
		texture = load(path);
	}
	
	
	private int load(String path){
		int[] pixels = null;
		//ImageFile in es BufferImage Lade
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		}  catch (IOException e) {
			e.printStackTrace();
		}
		//FarbFormat vo ARGB zu ABGR Šndere
		for(int i=0; i < width * height; i++){
			int a= (pixels[i] & 0xff000000)>> 24;
			int r= (pixels[i] & 0xff0000)>> 16;
			int g= (pixels[i] & 0xff00)>> 8;
			int b= (pixels[i] & 0xff);
			pixels[i] = a << 24 | b << 16 | g << 8 | r;
		}
		/*pixel[] zu Buffer mache
		 * Freie TexturID vo LWJGL becho
		 * Textur schŠrfe (wenn mer inezoom nšd verschmiere)
		 * Buffer i d Textur Lade
		 * Id vo de Textur returne
		 */
		int tex = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, tex); //zu bearbeitendi Textur uf d id Setze
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(pixels));
		glBindTexture(GL_TEXTURE_2D, 0); // zu bearbeitendi Textur Freigeh
		return tex;
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getID(){
		return texture;
	}
}


