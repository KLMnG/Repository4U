package General;

public class LuggageData {

    private int code;
    private int Weight;
    private int Height;
    private int Width;

    public LuggageData(int code, int weight, int height, int width) {
        this.code = code;
        this.Weight = weight;
        this.Height = height;
        this.Width = width;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }
}
