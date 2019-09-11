package other;

import java.util.StringTokenizer;


public class Face {
    private boolean containsTextureCoordinates;
    private boolean containsNormals;
    private int v1, v2, v3;
    private int t1, t2, t3;
    private int n1, n2, n3;
    private boolean invalid = false;
    private int lineNumber;

    public boolean isContainsTextureCoordinates() {
        return containsTextureCoordinates;
    }

    public void setContainsTextureCoordinates(boolean containsTextureCoordinates) {
        this.containsTextureCoordinates = containsTextureCoordinates;
    }

    public boolean isContainsNormals() {
        return containsNormals;
    }

    public void setContainsNormals(boolean containsNormals) {
        this.containsNormals = containsNormals;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean isInvalid) {
        this.invalid = isInvalid;
    }

    public int getV1() {
        return v1;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public int getV2() {
        return v2;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public int getV3() {
        return v3;
    }

    public void setV3(int v3) {
        this.v3 = v3;
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getN3() {
        return n3;
    }

    public void setN3(int n3) {
        this.n3 = n3;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    public void validate(Vertex[] vertices, TextureCoordinate[] uvs, Normal[] normals){
        if(v1 < 0 || v1 >= vertices.length) invalid = true;
        if(v2 < 0 || v2 >= vertices.length) invalid = true;
        if(v3 < 0 || v3 >= vertices.length) invalid = true;
        if(containsTextureCoordinates){
            if(t1 < 0 || t1 >= uvs.length) invalid = true;
            if(t2 < 0 || t2 >= uvs.length) invalid = true;
            if(t3 < 0 || t3 >= uvs.length) invalid = true;
        }
        if(containsNormals){
            if(n1 < 0 || n1 >= normals.length) invalid = true;
            if(n2 < 0 || n2 >= normals.length) invalid = true;
            if(n3 < 0 || n3 >= normals.length) invalid = true;
        }
    }
    
    public void validateElements(Vertex[] vertices, TextureCoordinate[] uvs, Normal[] normals){
        if(vertices[v1].isInvalid()) invalid = true;
        if(vertices[v2].isInvalid()) invalid = true;
        if(vertices[v3].isInvalid()) invalid = true;
        if(containsTextureCoordinates){
            if(uvs[t1].isInvalid()) invalid = true;
            if(uvs[t2].isInvalid()) invalid = true;
            if(uvs[t3].isInvalid()) invalid = true;
        }
        if(containsNormals){
            if(normals[n1].isInvalid()) invalid = true;
            if(normals[n2].isInvalid()) invalid = true;
            if(normals[n3].isInvalid()) invalid = true;
        }
    }
    
    public static Face parse(String line) throws Exception {
        StringTokenizer tk = new StringTokenizer(line, " /");
        tk.nextToken(); // skip label
        Face f = new Face();
        if(line.matches("f[ ]+[0-9]+[ ]+[0-9]+[ ]+[0-9]+[ ]*")){
            f.setContainsTextureCoordinates(false);
            f.setContainsNormals(false);
            f.setV1(Integer.parseInt(tk.nextToken())-1);
            f.setV2(Integer.parseInt(tk.nextToken())-1);
            f.setV3(Integer.parseInt(tk.nextToken())-1);
        } else if(line.matches("f[ ]+[0-9]+/[0-9]+[ ]+[0-9]+/[0-9]+[ ]+[0-9]+/[0-9]+[ ]*")){
            f.setContainsTextureCoordinates(true);
            f.setContainsNormals(false);
            f.setV1(Integer.parseInt(tk.nextToken())-1);
            f.setT1(Integer.parseInt(tk.nextToken())-1);
            f.setV2(Integer.parseInt(tk.nextToken())-1);
            f.setT2(Integer.parseInt(tk.nextToken())-1);
            f.setV3(Integer.parseInt(tk.nextToken())-1);
            f.setT3(Integer.parseInt(tk.nextToken())-1);
        } else if(line.matches("f[ ]+[0-9]+//[0-9]+[ ]+[0-9]+//[0-9]+[ ]+[0-9]+//[0-9]+[ ]*")) {
            f.setContainsTextureCoordinates(false);
            f.setContainsNormals(true);
            f.setV1(Integer.parseInt(tk.nextToken())-1);
            f.setN1(Integer.parseInt(tk.nextToken())-1);
            f.setV2(Integer.parseInt(tk.nextToken())-1);
            f.setN2(Integer.parseInt(tk.nextToken())-1);
            f.setV3(Integer.parseInt(tk.nextToken())-1);
            f.setN3(Integer.parseInt(tk.nextToken())-1);
        } else if(line.matches("f[ ]+[0-9]+/[0-9]+/[0-9]+[ ]+[0-9]+/[0-9]+/[0-9]+[ ]+[0-9]+/[0-9]+/[0-9]+[ ]*")){
            f.setContainsTextureCoordinates(true);
            f.setContainsNormals(true);
            f.setV1(Integer.parseInt(tk.nextToken())-1);
            f.setT1(Integer.parseInt(tk.nextToken())-1);
            f.setN1(Integer.parseInt(tk.nextToken())-1);
            f.setV2(Integer.parseInt(tk.nextToken())-1);
            f.setT2(Integer.parseInt(tk.nextToken())-1);
            f.setN2(Integer.parseInt(tk.nextToken())-1);
            f.setV3(Integer.parseInt(tk.nextToken())-1);
            f.setT3(Integer.parseInt(tk.nextToken())-1);
            f.setN3(Integer.parseInt(tk.nextToken())-1);
        } else {
            throw new Exception("Bad face format.");
        }
        return f;
    }
}
