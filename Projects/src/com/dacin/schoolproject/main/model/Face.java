package com.dacin.schoolproject.main.model;


import org.lwjgl.util.vector.Vector3f;
public class Face {
	
	public Vector3f vertex; // 3 indicies
	public Vector3f normal;
	public Vector3f tex;
	
	public Face(Vector3f vertex,  Vector3f normal, Vector3f tex){
		this.vertex = vertex;
		this.normal = normal;
		this.tex = tex;
	}

}
