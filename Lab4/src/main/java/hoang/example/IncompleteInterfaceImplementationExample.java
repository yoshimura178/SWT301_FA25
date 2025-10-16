package hoang.example;

import lombok.extern.slf4j.Slf4j;

interface Shape {
    void draw();
    void resize();
}

@Slf4j
class Square implements Shape {
    @Override
    public void draw() {
        log.info("Drawing square");
    }

    @Override
    public void resize() {
        // Demo: chưa có state để thay đổi, chỉ log hành động
        log.info("Resizing square");
    }
}
