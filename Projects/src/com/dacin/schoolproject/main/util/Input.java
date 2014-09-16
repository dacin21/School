package com.dacin.schoolproject.main.util;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	public int mouseX = 0;
	public int mouseY = 0;
	public boolean up = false;
	public boolean down = false;
	public boolean right = false;
	public boolean left = false;
	public boolean q,w,e,r,t,z,u,i,o,p,a,s,d,f,g,h,j,k,l,y,x,c,v,b,n,m ;

	public Input() {
		q=w=e=r=t=z=u=i=o=p=a=s=d=f=g=h=j=k=l=y=x=c=v=b=n=m=false;
	}

	public void getKeys() {
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
				if (Keyboard.getEventKeyState()) {
					up = true;
				} else {
					up = false;
				}
				continue;
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
				if (Keyboard.getEventKeyState()) {
					down = true;
				} else {
					down = false;
				}
				continue;
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
				if (Keyboard.getEventKeyState()) {
					left = true;
				} else {
					left = false;
				}
				continue;
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
				if (Keyboard.getEventKeyState()) {
					right = true;
				} else {
					right = false;
				}
				continue;
			}
			//TODO: ADD key Events
		}
	}
}
