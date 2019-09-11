package beans;

import java.util.LinkedList;
import other.Face;
import other.Model;
import other.Normal;
import other.TextureCoordinate;
import other.Vertex;


public class ModelBean {
    private String objCode = "";
    private LinkedList<String> errorLog = new LinkedList<>();
    private Model model = new Model(new Vertex[0], new TextureCoordinate[0], new Normal[0], new Face[0]);
    
    public void setObjCode(String code){
        objCode = code;
        errorLog.clear();
        model = Model.parse(code, errorLog);
    }

    public String getObjCode() {
        return objCode;
    }
    
    public int getVertexCount(){
        return model.getVertices().length;
    }
    
    public int getTextureCoordinateCount(){
        return model.getTextureCoordinates().length;
    }
    
    public int getNormalCount(){
        return model.getNormals().length;
    }
    
    public int getFaceCount(){
        return model.getFaces().length;
    }
    
    public double getMinX(){
        return model.getMinX();
    }
    
    public double getMaxX(){
        return model.getMaxX();
    }
    
    public double getMinY(){
        return model.getMinY();
    }
    
    public double getMaxY(){
        return model.getMaxY();
    }
    
    public double getMinZ(){
        return model.getMinZ();
    }
    
    public double getMaxZ(){
        return model.getMaxZ();
    }

    public Model getModel() {
        return model;
    }
    
    public LinkedList<String> getErrorLog() {
        return errorLog;
    }
}
