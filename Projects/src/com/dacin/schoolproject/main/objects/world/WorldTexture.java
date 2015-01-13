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

import com.dacin.schoolproject.main.util.BufferUtils;
import com.dacin.schoolproject.main.util.Texture;

public class WorldTexture {

	
	private final int length = WorldFloor.maxX;
	private final int width = WorldFloor.maxY;
	private final float maxBlueColor = 0.2f;
	private final float maxRedColor = 0.5f;
	
	private int pixels[];

	private Texture worldTexture;
	
	private int worldTextureId;

	public void createWorldTexture() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; i++) {
		worldTexture.bind();	
			}
		}
		worldTextureId = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, worldTextureId); //zu bearbeitendi Textur uf d id Setze
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, length, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(pixels));
		glBindTexture(GL_TEXTURE_2D, 0); // zu bearbeitendi Textur Freigeh
	}

	public int getWorldTextureId() {
		return worldTextureId;
	}
}
