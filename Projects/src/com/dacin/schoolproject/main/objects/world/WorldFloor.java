package com.dacin.schoolproject.main.objects.world;

import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.dacin.schoolproject.main.model.Face;
import com.dacin.schoolproject.main.model.Model;
import com.dacin.schoolproject.main.util.Texture;

public class WorldFloor {
	public Model Floor;
	private Random random = new Random();
	public static final int maxX = 200;
	public static final int maxZ = 150;
	private static final int maxModifyRadius = 100;
	private static final int minModifyRadius = 15;
	private static final float maxModifyRadiusY = 2.0f;
	private static final float minModifyRadiusY = 1.0f;
	private WorldTexture tex;
	
	public WorldFloor(){
		tex = new WorldTexture();
		Floor = new Model();
		Floor.setTexture(tex);
		int i, j;
		
		// Flat floor
		for(i = 0; i < maxZ ; i++){
			for(j = 0; j < maxX; j++){
				Floor.vertices.add(new Vector3f((float)j, 0.0f, (float)i));
			}
		}
		//Landscape
		int genmax = random.nextInt(maxX*maxZ/25) + maxX * maxZ / 25;
		//genmax = 1;
		for(i = 0; i < genmax; i++){
			int range = random.nextInt(maxModifyRadius) + minModifyRadius;
			int range2 = random.nextInt(maxModifyRadius) + minModifyRadius;
			float rangeY = random.nextFloat() * maxModifyRadiusY + minModifyRadiusY;
			int xBase = random.nextInt(maxX);
			int zBase = random.nextInt(maxZ);
			float yModifier = random.nextFloat() - 0.5f;
			
			for(int x = -range; x < range; x++){
				for(int z = -range2; z < range2; z++){
					float a = range;
					float c = range2;
					float b = rangeY;
					float yOffset = b/(a*c) * (float)Math.sqrt(a*a*c*c - a*a*z*z - c*c*x*x);
					yOffset *= yModifier;
					if(a*a*c*c - a*a*z*z - c*c*x*x > 0){
						modifyVertex(xBase + x, yOffset, zBase + z);
						//modifyVertex(xBase + x, 1, zBase + z);
					}
				}
			}
			
		}
		
		
		//Faces + textures
		for(i = 0; i < maxZ - 1; i++){
			for(j = 0; j< maxX - 1; j++){
				Vector3f vec = new Vector3f(i*maxX+j + 1, i*maxX+j + 1 + 1, i*maxX+j + maxX + 1);
				Floor.faces.add(new Face(vec, vec, vec));
				vec = new Vector3f(i*maxX+j + maxX + 1 + 1, i*maxX+j + 1 + 1, i*maxX+j + maxX + 1);
				Floor.faces.add(new Face(vec, vec, vec));
				
			}
		}
		for(i = 0; i < maxZ; i++){
			for(j = 0; j< maxX; j++){
				Floor.textureCords.add(new Vector2f((float)j / (float)maxX, (float)i / (float)maxZ));
			}
		}
		Floor.hasTexture = true;
	}
	
	private void modifyVertex(int x, float yOffset, int z){
		if(x < 0 || x >= maxX) return;
		if(z < 0 || z >= maxZ) return;
		Vector3f vec = Floor.vertices.get(z*maxX + x);
		vec = new Vector3f(vec.x, vec.y + yOffset, vec.z);
		Floor.vertices.set(z*maxX+x, vec);
	}
	
	public void render(){
		this.Floor.render();
	}

}
