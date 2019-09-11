package other;

import java.util.StringTokenizer;


public class TextureCoordinate {
    private double u, v, w;
    private boolean invalid = false;

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }

    public double getW() {
        return w;
    }

    public void setU(double u) {
        this.u = u;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setW(double w) {
        this.w = w;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean isInvalid) {
        this.invalid = isInvalid;
    }
    
    public static TextureCoordinate parse(String line) throws Exception {
        StringTokenizer tk = new StringTokenizer(line, " ");
        tk.nextToken(); // skip label
        int coordinateCount = tk.countTokens();
        if(coordinateCount < 2 || coordinateCount > 3){
            throw new Exception("Wrong number of coordinates for uv. Expected 2 or 3, found " + coordinateCount + ".");
        }
        TextureCoordinate uv = new TextureCoordinate();
        uv.setU(Double.parseDouble(tk.nextToken()));
        uv.setV(Double.parseDouble(tk.nextToken()));
        if(coordinateCount == 3) uv.setW(Double.parseDouble(tk.nextToken()));
        return uv;
    }
}
