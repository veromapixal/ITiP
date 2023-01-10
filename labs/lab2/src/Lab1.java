

public class Lab1 {
    public static void main(String[] args) {
//        Point3d firstPoint = new Point3d(0, 0, 0);
//        Point3d secondPoint = new Point3d(1, 2, 3);
//        Point3d thirdPoint = new Point3d(1, 5, 3);

        Point3d firstPoint = new Point3d(0, 0, 0);
        Point3d secondPoint = new Point3d(1, 2, 3);
        Point3d thirdPoint = new Point3d(1, 5, 3);

        System.out.println(secondPoint.distanceTo(thirdPoint));
        System.out.println("площадь первого треугольника " + computeArea(firstPoint, secondPoint, thirdPoint));
    }

    public static double computeArea(Point3d firstPoint, Point3d secondPoint, Point3d thirdPoint) {

        double a = firstPoint.distanceTo(secondPoint);
        System.out.println(a);
        double b = firstPoint.distanceTo(thirdPoint);
        System.out.println("b " + b);
        double c = thirdPoint.distanceTo(secondPoint);
        System.out.println(c);

        double p = (a + b + c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        String result = String.format("%.2f", s);
        return Double.parseDouble(result);


    }
}
