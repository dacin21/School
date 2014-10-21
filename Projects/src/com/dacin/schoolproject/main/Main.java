package com.dacin.schoolproject.main;

import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import com.dacin.schoolproject.main.model.EulerCamera;
import com.dacin.schoolproject.main.model.Model;
import com.dacin.schoolproject.main.util.ModelUtils;
import static org.lwjgl.opengl.GL11.*;

public class Main implements Runnable{
	
	private int width=1280;
	private int height = 720;
	private String title = "Test";
	private float fov = 70;
	private float zNear = -1000;
	private float zFar = 1000;
	
	private boolean running = false;
	private Thread thread;
	private static EulerCamera camera;
	
	private Model testModel;
	
	public void start(){
		running = true;
		thread =  new Thread(this, "Display");
		thread.start();
	}
	
	public void run(){
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(fov, (float) Display.getWidth() / (float)Display.getHeight(), zNear, zFar);
		 camera = new EulerCamera.Builder().setAspectRatio((float) Display.getWidth() / Display.getHeight())
				 .setRotation(-1.12f, 0.16f, 0f).setPosition(-1.38f, 1.36f, 7.95f).setFieldOfView(60).build();
				 camera.applyOptimalStates();
				 camera.applyPerspectiveMatrix();
				 
		testModel = ModelUtils.loadModel("bunny.obj");
		
		while(running){
			
			Display.update();
			Display.sync(50);
			render();
			

				camera.processMouse(1, 80, -80);
				camera.processKeyboard(16, 1, 1, 1);
			
			if(Display.isCloseRequested()) running = false;
			
		}
		Display.destroy();
	}

	public static void main(String[] args) {
		new Main().start();

	}
	
	private void render(){
		 glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		 glLoadIdentity();
		 //camera.applyTranslations();
		 glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			testModel.render();
	}
	

}
