package hoang.example;

interface Drawable {
    void draw();
}

class Circle implements Drawable {
    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
