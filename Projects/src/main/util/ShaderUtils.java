package main.util;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

public class ShaderUtils {
	//Die class isch da, zum shaders is LWJGL z lade

	private ShaderUtils() {
	}
	//shaders
	public static int TextureShaderId = ShaderUtils.load("Shaders/Texture.vert", "Shaders/Texture.frag");
	
	
	public static int load(String vertPath, String fragPath) {
		String vert = loadAsString(vertPath);
		String frag = loadAsString(fragPath);
		return create(vert, frag);

	}

	private static int create(String vert, String frag) {
		//Vertex Shader: pünkt 
		//Fragment Shader: flächi zwüsched pünkt
		int program = glCreateProgram();
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		//Shader Vom source Lade und compile
		glShaderSource(vertID, vert);
		glShaderSource(fragID, frag);
		glCompileShader(vertID);
		if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile vertex shader!");
			System.err.println(glGetShaderInfoLog(vertID, 2048));
		}
		glCompileShader(fragID);
		if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Failed to compile fragment shader!");
			System.err.println(glGetShaderInfoLog(fragID, 2048));
		}
		//Vertex und Fragemt Shader zu eim zemebaue
		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		
		
		//KA, was das Macht, warschindlich zum d Textur em shader zuewise
		// Position information will be attribute 0
		GL20.glBindAttribLocation(program, 0, "in_Position");
		// Color information will be attribute 1
		GL20.glBindAttribLocation(program, 1, "in_Color");
		// Textute information will be attribute 2
		GL20.glBindAttribLocation(program, 2, "in_TextureCoord");
		
		
		
		//ShaderProgram LWJGL überwiessen und Validieren
		glLinkProgram(program);
		glValidateProgram(program);
		return program;
	}

	private static String loadAsString(String file) {
		//Text file in String Laden
		String result = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result += buffer + "\n";
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		

		return result;
	}

}