package com.dacin.schoolproject.main.model;

import static com.dacin.schoolproject.main.util.LoggingUtils.debug;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import com.dacin.schoolproject.main.Main;
import com.dacin.schoolproject.main.objects.world.WorldFloor;

public class PlayerCamera {
	private static EulerCamera camera;

	private final float fov = 70;
	private final float zNear = -100;
	private final float zFar = 100;
	public float yOffset;
	
	public PlayerCamera(){
		this.init();
		yOffset = 1.0f;
	}
	
	
	public void init(){
		GLU.gluPerspective(fov,
				(float) Display.getWidth() / (float) Display.getHeight(),
				zNear, zFar);
		camera = new EulerCamera.Builder()
				.setAspectRatio((float) Display.getWidth() / Display.getHeight())
				.setRotation(-0.0f, 0.0f, 0.0f)
				.setPosition(-1.5f, 9.16f, 5.95f).setFieldOfView(60).build();
		camera.applyOptimalStates();
		camera.applyPerspectiveMatrix();
		debug("Camera loaded sucessfully");
	}
	public void processMouseAndKeyboard(){
		camera.processMouse(1 * 10, 80, -80);
		camera.processKeyboard(16, 1 * 10, 1 * 10, 0.1f * 100);
		try{
		camera.setPosition(camera.x(),Main.floor.Floor.vertices.get((int)(camera.z()+0.5f)*WorldFloor.maxX + (int)(camera.x()+0.5f)).y+yOffset, camera.z());
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
	}
	public void applyRotationAndTranslation(){
		 camera.applyTranslations();
		 camera.applyPerspectiveMatrix();
	}

}
