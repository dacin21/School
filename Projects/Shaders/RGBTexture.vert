#version 110

varying vec4 vColor;

void main() {
    vColor = gl_Color;
    gl_Position = ftransform(); //Transform the vertex position
    gl_TexCoord[0] = gl_MultiTexCoord0;
}