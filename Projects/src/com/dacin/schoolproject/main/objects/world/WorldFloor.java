package com.dacin.schoolproject.main.objects.world;

import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.dacin.schoolproject.main.model.Face;
import com.dacin.schoolproject.main.model.Model;
import com.dacin.schoolproject.main.util.Texture;

public class WorldFloor {
	private Model Floor;
	private Random random = new Random();
	private static final float range = 0.4f;
	private static final int maxX = 100;
	private static final int maxY = 50;
	
	public WorldFloor(Texture tex){
		Floor = new Model();
		Floor.setTexture(tex);
		int i, j;
		Floor.vertices.add(new Vector3f(0.0f, 0.0f, 0.0f));
		for(j = 1; j< maxX; j++){
			float oldy = Floor.vertices.get(j-1).y;
			float newy = random.nextFloat() * range * 2 - range + oldy;
			Floor.vertices.add(new Vector3f((float)j, newy, 0.0f));
		}
		
		for(i = 1; i < maxY ; i++){
			for(j = 0; j < maxX; j++){
				if(j!= 0){
					float oldy = Floor.vertices.get(i*maxX + j - maxX).y;
					float oldy2 = Floor.vertices.get(i*maxX + j - 1).y;
					if(Math.abs(oldy-oldy2) > 2 * range) {
						Floor.vertices.add(new Vector3f((float)j,(oldy + oldy2)/2,(float)i));
					} else{
						float newRange =  (Math.min(oldy, oldy2)+range - (Math.max(oldy, oldy2)-range));
						float newy = random.nextFloat() * newRange * 2 - newRange ;
						Floor.vertices.add(new Vector3f((float)j , newy,(float)i ));
					}
				} else {
					float oldy = Floor.vertices.get(i*maxX- maxX).y;
					float newy = random.nextFloat() * range * 2 - range + oldy;
					Floor.vertices.add(new Vector3f(0.0f, newy, (float)i));
				}
			}
		}
		for(i = 0; i < maxY - 1; i++){
			for(j = 0; j< maxX - 1; j++){
				Vector3f vec = new Vector3f(i*maxX+j + 1, i*maxX+j + 1 + 1, i*maxX+j + maxX + 1);
				Floor.faces.add(new Face(vec, vec, vec));
				vec = new Vector3f(i*maxX+j + maxX + 1 + 1, i*maxX+j + 1 + 1, i*maxX+j + maxX + 1);
				Floor.faces.add(new Face(vec, vec, vec));
				
			}
		}

		for(i = 0; i < maxY; i++){
			for(j = 0; j< maxX; j++){
				Floor.textureCords.add(new Vector2f((float)j / (float)maxX, (float)i / (float)maxY));
			}
		}
		Floor.hasTexture = true;
	}
	
	public void render(){
		this.Floor.render();
	}

}
