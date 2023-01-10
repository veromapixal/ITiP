

public class Point3d extends Point2d {

    /** координата Z **/
    private double zCoord;

    //новый объект класса с тремя значениями с плавающей точкой
    public Point3d(double xCoord, double yCoord, double zCoord){
        super(xCoord, yCoord);
        this.zCoord = zCoord;
    }

    //новый объект класса с (0.0, 0.0, 0.0)
    public Point3d(){
        this(0.0, 0.0, 0.0);
    }
    //метод для сравнения двух объектов класса
    public boolean isCompare(Point3d newPoint){
        return newPoint.getxCoord() == this.getxCoord() & newPoint.getyCoord() == this.getyCoord() & newPoint.getzCoord() == this.getzCoord();
    }
    //метод для нахождения расстояния между двумя точками
    public double distanceTo(Point3d secondPoint){


        double distance = Math.sqrt(
                (secondPoint.getxCoord() - this.getxCoord()) * (secondPoint.getxCoord() - this.getxCoord()) +
                        (secondPoint.getyCoord() - this.getyCoord()) * (secondPoint.getyCoord() - this.getyCoord()) +
                        (secondPoint.getzCoord() - this.getzCoord()) * (secondPoint.getzCoord() - this.getzCoord())
        );


        //округление до 2х знаков после запятой
        String result = String.format("%.2f", distance);
        return Double.parseDouble(result);
        }

    public double getzCoord() {
        return zCoord;
    }

    public void setzCoord(double zCoord) {
        this.zCoord = zCoord;
    }
}
