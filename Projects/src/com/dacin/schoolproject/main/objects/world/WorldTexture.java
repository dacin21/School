package com.dacin.schoolproject.main.objects.world;

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

import java.util.Random;

import com.dacin.schoolproject.main.util.BufferUtils;
import com.dacin.schoolproject.main.util.Texture;

public class WorldTexture extends Texture {

	private final int length = WorldFloor.maxX;
	private final int width = WorldFloor.maxZ;
	private final int maxBlueColor = 20;// 100 ist das Maximum
	private final int maxRedColor = 50;// 100 ist das Maximum
	private final int resolution = 10;// pixels per Einheit length/width

	private int pixels[] = new int[length * resolution * width * resolution];

	private Random random = new Random();

	public WorldTexture() {
		/*
		 * super.width = this.width; super.height = this.length;
		 */
		this.createWorldTexture();
	}

	public void createWorldTexture() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				pixels[i * resolution * length + j * resolution] = random.nextInt(maxRedColor) / 100 << 24 + (random.nextInt(50) + 50) / 100 << 16 + random.nextInt(maxBlueColor) / 100 << 8;// 00000000x0
			}
		}
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				for (int k = 1; k < resolution; k++) {
					pixels[i * resolution * length + j * resolution + k * length] = Math.abs(pixels[i * resolution * length + j * resolution] - pixels[(i + 1) * resolution * length + j * resolution]) / resolution * k;
				}
			}
		}
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				for (int k = 0; k < resolution - 1; k++) {
					for (int l = 0; l < resolution; l++) {
						pixels[i * resolution * length + j * resolution + k * length + l] = Math.abs(pixels[i * resolution * length + j * resolution + k * length] - pixels[(i + 1) * resolution * length + (j + 1) * resolution + k * length]) / resolution;
					}
				}
			}
		}
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID); // zu bearbeitendi Textur
													// uf d id Setze
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, length, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(pixels));
		glBindTexture(GL_TEXTURE_2D, 0); // zu bearbeitendi Textur Freigeh
	}

	@Deprecated
	public int getWorldTextureId() {
		return getID();
	}
}
