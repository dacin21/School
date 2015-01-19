package com.dacin.schoolproject.main;

import static com.dacin.schoolproject.main.util.LoggingUtils.debug;
import static com.dacin.schoolproject.main.util.LoggingUtils.error;
import static com.dacin.schoolproject.main.util.LoggingUtils.info;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.dacin.schoolproject.main.model.EulerCamera;
import com.dacin.schoolproject.main.model.Model;
import com.dacin.schoolproject.main.model.PlayerCamera;
import com.dacin.schoolproject.main.objects.world.WorldFloor;

public class Main implements Runnable {

	private int width = 1280;
	private int height = 720;
	private String title = "School 3d Project";
	private float fov = 70;
	private float zNear = -100;
	private float zFar = 100;

	private boolean running = false;
	private Thread thread;
	//private static EulerCamera camera;
	private static PlayerCamera camera;

	private Model testModel;
	public static WorldFloor floor;

	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void run() {
		debug("new Thread opened successfully");
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			error("Failed to create display");
			e.printStackTrace();
		}
		debug("Display Initialized successfully");
		glMatrixMode(GL_PROJECTION);
		glEnable(GL_DEPTH_TEST);
		glLoadIdentity();
		GLU.gluPerspective(fov,
				(float) Display.getWidth() / (float) Display.getHeight(),
				zNear, zFar);
		/*camera = new EulerCamera.Builder()
				.setAspectRatio((float) Display.getWidth() / Display.getHeight())
				.setRotation(-0.0f, 0.0f, 0.0f)
				.setPosition(-1.5f, 9.16f, 5.95f).setFieldOfView(60).build();
		camera.applyOptimalStates();
		camera.applyPerspectiveMatrix();*/
		camera = new PlayerCamera();
		debug("Camera loaded sucessfully");
		info("loading Models");
		//testModel = ModelUtils.loadModel("Sphere.obj");
		//testModel.setTexture(new Texture("Sphere.png"));
		floor= new WorldFloor();

		while (running) {

			Display.update();
			Display.sync(50);
			render();
			//System.out.println(camera.toString());

			/*camera.processMouse(10, 80, -80);
			camera.processKeyboard(16, 10, 10, 10.0f);*/
			camera.processMouseAndKeyboard();

			if (Display.isCloseRequested())
				running = false;

		}
		Display.destroy();
	}

	public static void main(String[] args) {
		debug("Game Started");
		new Main().start();

	}

	private void render(){
		 glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		 glLoadIdentity();
		 /*camera.applyTranslations();
		 camera.applyPerspectiveMatrix();*/
		 camera.applyRotationAndTranslation();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		 //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		 glColor3f(1.0f, 1.0f, 1.0f);
			//testModel.render();
		 floor.render();
	}

}
