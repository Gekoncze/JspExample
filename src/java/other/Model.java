package other;

import java.util.LinkedList;
import java.util.StringTokenizer;


public class Model {
    private Vertex[] vertices;
    private TextureCoordinate[] textureCoordinates;
    private Normal[] normals;
    private Face[] faces;
    private double minX, maxX;
    private double minY, maxY;
    private double minZ, maxZ;

    public Model(Vertex[] vertices, TextureCoordinate[] textureCoordinates, Normal[] normals, Face[] faces) {
        this.vertices = vertices;
        this.textureCoordinates = textureCoordinates;
        this.normals = normals;
        this.faces = faces;
        minX = Double.POSITIVE_INFINITY;
        maxX = Double.NEGATIVE_INFINITY;
        minY = Double.POSITIVE_INFINITY;
        maxY = Double.NEGATIVE_INFINITY;
        minZ = Double.POSITIVE_INFINITY;
        maxZ = Double.NEGATIVE_INFINITY;
        for(Vertex v : vertices){
            if(v.isInvalid()) continue;
            minX = Math.min(minX, v.getX());
            minY = Math.min(minY, v.getY());
            minZ = Math.min(minZ, v.getZ());
            maxX = Math.max(maxX, v.getX());
            maxY = Math.max(maxY, v.getY());
            maxZ = Math.max(maxZ, v.getZ());
        }
    }
   
    public Vertex[] getVertices() {
        return vertices;
    }

    public TextureCoordinate[] getTextureCoordinates() {
        return textureCoordinates;
    }

    public Normal[] getNormals() {
        return normals;
    }

    public Face[] getFaces() {
        return faces;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinZ() {
        return minZ;
    }

    public double getMaxZ() {
        return maxZ;
    }
    
    public static Model parse(String code, LinkedList<String> errorLog) {
        LinkedList<Vertex> vertices = new LinkedList<>();
        LinkedList<TextureCoordinate> uvs = new LinkedList<>();
        LinkedList<Normal> normals = new LinkedList<>();
        LinkedList<Face> faces = new LinkedList<>();
        
        int lineNumber = 0;
        
        StringTokenizer fileTokenizer = new StringTokenizer(code, "\r\n");
        while(fileTokenizer.hasMoreTokens()){
            String line = fileTokenizer.nextToken();
            lineNumber++;
            if(line.length() <= 0) continue;
            
            StringTokenizer lineTokenizer = new StringTokenizer(line, " ");
            String token = lineTokenizer.nextToken();
            if(token == null) {
                errorLog.add("Error at line " + lineNumber + ": Could not read element character.");
                continue;
            }
            
            if(token.trim().startsWith("#")) continue;
            if(token.trim().startsWith("//") || token.trim().startsWith("/*")) {
                errorLog.add("Error at line " + lineNumber + ": Cannot use slash comments in obj files.");
                continue;
            }
            
            try {
                switch(token) {
                    case "v":
                        try {
                            vertices.add(Vertex.parse(line));
                        } catch(Exception e){
                            vertices.add(new Vertex());
                            vertices.getLast().setInvalid(true);
                            throw e;
                        }
                        break;
                    case "vt":
                        try {
                            uvs.add(TextureCoordinate.parse(line));
                        } catch(Exception e){
                            uvs.add(new TextureCoordinate());
                            uvs.getLast().setInvalid(true);
                            throw e;
                        }
                        break;
                    case "vn":
                        try {
                            normals.add(Normal.parse(line));
                        } catch (Exception e) {
                            normals.add(new Normal());
                            normals.getLast().setInvalid(true);
                            throw e;
                        }
                        break;
                    case "f":
                        try {
                            Face f = Face.parse(line);
                            f.setLineNumber(lineNumber);
                            faces.add(f);
                        } catch(Exception e){
                            faces.add(new Face());
                            faces.getLast().setInvalid(true);
                            throw e;
                        }
                        break;
                    case "o": break;
                    case "g": break;
                    case "s": break;
                    case "mtllib": break;
                    case "usemtl": break;
                    default: errorLog.add("Error at line " + lineNumber + ": Unknown element character '" + token + "'.");
                }
            } catch(Exception e){
                errorLog.add("Error at line " + lineNumber + ": " + e.getMessage() + ".");
            }
        }
        
        Vertex[] verticesArray = vertices.toArray(new Vertex[0]);
        TextureCoordinate[] uvsArray = uvs.toArray(new TextureCoordinate[0]);
        Normal[] normalsArray = normals.toArray(new Normal[0]);
        Face[] facesArray = faces.toArray(new Face[0]);
        
        for(Face f : faces){
            if(f.isInvalid()) continue;
            f.validate(verticesArray, uvsArray, normalsArray);
            if(f.isInvalid()) errorLog.add("Error at line " + f.getLineNumber() + ": Index out of bounds.");
            if(f.isInvalid()) continue;
            f.validateElements(verticesArray, uvsArray, normalsArray);
        }
        
        return new Model(verticesArray, uvsArray, normalsArray, facesArray);
    }
}
