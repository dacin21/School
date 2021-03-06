package com.dacin.schoolproject.main.model;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.dacin.schoolproject.main.util.ShaderUtils;
import com.dacin.schoolproject.main.util.Texture;

public class Model {
	public final List<Vector3f> vertices = new ArrayList<Vector3f>();
	public final List<Vector2f> textureCords = new ArrayList<Vector2f>();
	public final List<Vector3f> nomals = new ArrayList<Vector3f>();
	public final List<Face> faces = new ArrayList<Face>(); 
	private Texture texture;
	public boolean hasNormals, hasTexture;
	
	
	public Model(){
		hasNormals = hasTexture = false;
	}
	public void setTexture(Texture tex){
		this.texture = tex;
	}
	
	
	/**
	 * Used for Shader rendering ?
	 * @author OskarVeerhoek
	 *
	 */
	public static class Material {
		@Override
		public String toString() {
		return "Material{" +
		"specularCoefficient=" + specularCoefficient +
		", ambientColour=" + ambientColour +
		", diffuseColour=" + diffuseColour +
		", specularColour=" + specularColour +
		'}';
		}
		/** Between 0 and 1000. */
		public float specularCoefficient = 100;
		public float[] ambientColour = {0.2f, 0.2f, 0.2f};
		public float[] diffuseColour = {0.3f, 1, 1};
		public float[] specularColour = {1, 1, 1};
		public Texture texture;
		}
	
	public void render(){
		if(hasTexture){
			ARBShaderObjects.glUseProgramObjectARB(ShaderUtils.TextureShaderId);
			texture.bind();
		}
		glBegin(GL11.GL_TRIANGLES);
		for(Face face : this.faces){
			
			Vector3f n,v;
			Vector2f t;
			if(hasNormals){
				n = this.nomals.get((int)face.normal.x-1);
				glNormal3f(n.x, n.y, n.z);
			} 
			if(hasTexture){
				t= this.textureCords.get((int)face.tex.x-1);
				glTexCoord2f(t.x, t.y);
			}
			v = this.vertices.get((int)face.vertex.x - 1);
			glVertex3f(v.x, v.y, v.z);
			
			
			if(hasNormals){
			n = this.nomals.get((int)face.normal.y-1);
			glNormal3f(n.x, n.y, n.z);
			} 
			if(hasTexture){
				t= this.textureCords.get((int)face.tex.y-1);
				glTexCoord2f(t.x, t.y);
			}
			v = this.vertices.get((int)face.vertex.y - 1);
			glVertex3f(v.x, v.y, v.z);
			
			
			if(hasTexture){
				t= this.textureCords.get((int)face.tex.z-1);
				glTexCoord2f(t.x, t.y);
			}
			if(hasNormals){
			n = this.nomals.get((int)face.normal.z-1);
			glNormal3f(n.x, n.y, n.z);
			}
			v = this.vertices.get((int)face.vertex.z - 1);
			glVertex3f(v.x, v.y, v.z);
		}
		glEnd();
		glBindTexture(GL_TEXTURE_2D,0);
		ARBShaderObjects.glUseProgramObjectARB(0);
	}

	
}
