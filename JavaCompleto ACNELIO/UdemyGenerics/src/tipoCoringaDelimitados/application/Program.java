package tipoCoringaDelimitados.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tipoCoringaDelimitados.entities.Circle;
import tipoCoringaDelimitados.entities.Rectangle;
import tipoCoringaDelimitados.entities.Shape;

public class Program {

	public static void main(String[] args) {
		List<Shape> myShapes = new ArrayList<>();	
		myShapes.add(new Rectangle(3.0, 2.0));
		myShapes.add(new Circle(2.0));
		
		List<Circle> myCircles = new ArrayList<Circle>();
		myCircles.add(new Circle(3.0));
		myCircles.add(new Circle(5.0));
		
		System.out.println("Total area: " + totalArea(myShapes));
		
		
		List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
		List<Double> myDoubles = Arrays.asList(3.14, 6.28);
		List<Object> myObjs = new ArrayList<Object>();
		copy(myInts, myObjs);
		copy(myDoubles, myObjs);
		
		printList(myObjs);
		
	}
	
	private static void printList(List<?> list) {
		for (Object object : list) {
			System.out.print(object + " ");
		}
	}

	private static void copy(List<? extends Number> sourceList, List<? super Number> targetList) {
		for (Number number: sourceList) {
			targetList.add(number);
		}
		
	}

	public static double totalArea(List<? extends Shape> list) {
		double sum = 0.0;
		
		for (Shape s : list) {
			sum+= s.area();
		}
		
		return sum;
	}
}
