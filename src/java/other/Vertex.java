package other;

import java.util.StringTokenizer;


public class Vertex {
    private double x, y, z, w;
    private boolean invalid = false;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setW(double w) {
        this.w = w;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }
    
    public static Vertex parse(String line) throws Exception {
        StringTokenizer tk = new StringTokenizer(line, " ");
        tk.nextToken(); // skip label
        int coordinateCount = tk.countTokens();
        if(coordinateCount < 3 || coordinateCount > 4){
            throw new Exception("Wrong number of coordinates for vertex. Expected 3 or 4, found " + coordinateCount + ".");
        }
        Vertex v = new Vertex();
        v.setX(Double.parseDouble(tk.nextToken()));
        v.setY(Double.parseDouble(tk.nextToken()));
        v.setZ(Double.parseDouble(tk.nextToken()));
        if(coordinateCount == 4) v.setW(Double.parseDouble(tk.nextToken()));
        return v;
    }
}
