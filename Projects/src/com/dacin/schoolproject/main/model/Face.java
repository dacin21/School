package com.dacin.schoolproject.main.model;


import org.lwjgl.util.vector.Vector3f;
public class Face {
	
	public Vector3f vertex; // 3 indicies
	public Vector3f normal;
	
	public Face(Vector3f vertex,  Vector3f normal){
		this.vertex = vertex;
		this.normal = normal;
	}

}
