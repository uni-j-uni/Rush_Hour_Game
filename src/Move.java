import java.awt.Rectangle;

class Move {
    public static final int MAP_SIZE = 6;
    public static final int BLOCK_SIZE = 100;

    private Car[] cars;
    public int cnt = 0;
    
    public Move() {
        cars = new Car[8];
        new Sound();
    }

    public boolean canMove(Car Car, int newX, int newY) {
        if (Car.carWidth() > 1) {
            if (newX < 0 || newX + Car.carWidth() > MAP_SIZE) { return false; }
        }
        if (Car.carHeight() > 1) {
            if (newY < 0 || newY + Car.carHeight() > MAP_SIZE) { return false; }
        }
        if (Car.carWidth() > 1 && newY != Car.getY()) { return false; }
        if (Car.carHeight() > 1 && newX != Car.getX()) { return false; }

        Rectangle Rect = new Rectangle(newX * BLOCK_SIZE, newY * BLOCK_SIZE, Car.carWidth() * BLOCK_SIZE, Car.carHeight() * BLOCK_SIZE);

        for (Car otherCar : cars) {
            if (otherCar != Car && otherCar.makeRect().intersects(Rect)) { return false; }
        }
        return true;
    }

    public Car getLocation(int x, int y) {
        for (Car Car : cars) {
            Rectangle rect = Car.makeRect();
            if (rect.contains(x, y)) { return Car; }
        }
        return null;
    }
    public Car[] makeCars() { return cars; }
    public void moveCar(Car car, int newX, int newY) {
        if (canMove(car, newX, newY)) { car.move(newX, newY); }
    }
    public boolean isGoal() { return cars[0].userLocation(); }
}
