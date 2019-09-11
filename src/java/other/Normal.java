package other;

import java.util.StringTokenizer;


public class Normal {
    private double x, y, z;
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
    
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean isInvalid) {
        this.invalid = isInvalid;
    }
    
    public static Normal parse(String line) throws Exception {
        StringTokenizer tk = new StringTokenizer(line, " ");
        tk.nextToken(); // skip label
        int coordinateCount = tk.countTokens();
        if(coordinateCount != 3){
            throw new Exception("Wrong number of coordinates for normal. Expected 3, found " + coordinateCount + ".");
        }
        Normal n = new Normal();
        n.setX(Double.parseDouble(tk.nextToken()));
        n.setY(Double.parseDouble(tk.nextToken()));
        n.setZ(Double.parseDouble(tk.nextToken()));
        return n;
    }
}
