package com.dacin.schoolproject.main.objects.world;

import org.lwjgl.opengl.GL11;

import com.dacin.schoolproject.main.model.Model;
import com.dacin.schoolproject.main.util.ModelUtils;
import com.dacin.schoolproject.main.util.Texture;

public class SphereObject {
	private float x, y, z;
	private int frameCounter;
	private Model model;
	
	public SphereObject(float x, float y, float z){
		model = ModelUtils.loadModel("Sphere.obj");
		model.setTexture(new Texture("Sphere.png"));
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void render(){
		frameCounter = (frameCounter+1)%360;
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef((float) (frameCounter), 0.0f, 1.0f, 0.0f);
		model.render();
		GL11.glPopMatrix();
		

		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScalef(0.1f, 0.1f, 0.1f);
		GL11.glRotatef((float) (-frameCounter), 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(100.0f, 0.0f, 0.0f);
		GL11.glRotatef((float) (5*frameCounter), 0.0f, 1.0f, 0.0f);
		model.render();
		GL11.glPopMatrix();
	}
	

}
