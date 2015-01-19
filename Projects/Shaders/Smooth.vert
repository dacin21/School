#version 150
uniform sampler2D texture1; 

smooth out vec4 VertexColor;

void main()
{
  gl_TexCoord[0] = gl_MultiTexCoord0;
  gl_Position = ftransform();
  VertexColor = texture2D(texture1, gl_TexCoord[0].st);
}