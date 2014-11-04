package com.dacin.schoolproject.main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.dacin.schoolproject.main.model.Face;
import com.dacin.schoolproject.main.model.Model;

public class ModelUtils {

	public static Model loadModel(String path){
		Model m = new Model();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = "";
			while((line = reader.readLine()) != null){
				//System.out.println(line);
				//parse
				if(line.startsWith("v ")){
					float x = Float.valueOf(line.split(" ")[1]);
					float y = Float.valueOf(line.split(" ")[2]);
					float z = Float.valueOf(line.split(" ")[3]);
					m.vertices.add(new Vector3f(x,y,z));
					
				}else if(line.startsWith("vn ")){
					float x = Float.valueOf(line.split(" ")[1]);
					float y = Float.valueOf(line.split(" ")[2]);
					float z = Float.valueOf(line.split(" ")[3]);
					m.hasNormals = true;
					m.nomals.add(new Vector3f(x,y,z));
				} else if(line.startsWith("f ")){
					Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[0]),
							Float.valueOf(line.split(" ")[2].split("/")[0]),
							Float.valueOf(line.split(" ")[3].split("/")[0]));
					Vector3f normalIndices;
					if(line.contains("//")){
					normalIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[2]),
							Float.valueOf(line.split(" ")[2].split("/")[2]),
							Float.valueOf(line.split(" ")[3].split("/")[2]));
					} else {
						normalIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[1]),
								Float.valueOf(line.split(" ")[2].split("/")[1]),
								Float.valueOf(line.split(" ")[3].split("/")[1]));
					}
					m.faces.add(new Face(vertexIndices, normalIndices));
				}else if(line.startsWith("vt")){
					m.hasTexture = true;
					float v = Float.valueOf(line.split(" ")[1]);
					float t = Float.valueOf(line.split(" ")[2]);
					m.textureCords.add(new Vector2f(v,t));
				}
			}
		} catch(IOException e){
			System.err.println("File probably not found at: " + path);
			e.printStackTrace();
		} 
		return m;
		
	}
}
