#version 110

uniform sampler2D texture1; //Remember back to my first tutorial (if you read it). Samplers are data types used to access textures. //To use textures from your main program, this must be uniform. 

void main() {
    gl_FragColor = texture2D(texture1, gl_TexCoord[0].st); //And that is all we need.
}