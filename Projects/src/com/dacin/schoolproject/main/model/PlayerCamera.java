package com.dacin.schoolproject.main.model;

import static com.dacin.schoolproject.main.util.LoggingUtils.debug;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

public class PlayerCamera {
	private static EulerCamera camera;

	private float fov = 70;
	private float zNear = -100;
	private float zFar = 100;
	
	public PlayerCamera(){
		this.init();
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
	}
	public void applyRotationAndTranslation(){
		 camera.applyTranslations();
		 camera.applyPerspectiveMatrix();
	}

}
