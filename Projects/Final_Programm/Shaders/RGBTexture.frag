#version 110

varying vec4 vColor;
uniform sampler2D texture1; //Remember back to my first tutorial (if you read it). Samplers are data types used to access textures. //To use textures from your main program, this must be uniform. 

void main() {
    vec4 texColor = texture2D(texture1, gl_TexCoord[0].st).rgba;
    //gl_FragColor = vec4(texColor.r*vColor.r,texColor.g*vColor.g,texColor.b*vColor.b,texColor.a*vColor.a).rgba;
    gl_FragColor = texColor * vColor;
}