#version 150

smooth in vec4 VertexColor;


out vec4 Out_Color;
void main()
{
  Out_Color = VertexColor;
}