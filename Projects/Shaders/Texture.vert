#version 110

void main() {
    gl_Position = ftransform(); //Transform the vertex position
    gl_TexCoord[0] = gl_MultiTexCoord0;
    //glTexCoord is an openGL defined varying array of vec4. Different elements in the array can be used for multi-texturing with 
    //different textures, each requiring their own coordinates.
    //gl_MultiTexCoord0 is an openGl defined attribute vec4 containing the texture coordinates for unit 0 (I'll explain units soon) that
    //you give with calls to glTexCoord2f, glTexCoordPointer etc. gl_MultiTexCoord1 contains unit 1, gl_MultiTexCoord2  unit 2 etc.
}