package entities;

public class Product {

	public String name;
	public double value;
	
	public Product(String name, double value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public static boolean staticProductPredicate(Product p) {
		return p.getValue() >= 100;
	}
	
	public static void priceUpdate(Product p) {
		p.setValue(p.getValue() * 1.1);
	}
	
	public void priceUpdateWithFactor(Product p, double factor) {
		p.setValue(p.getValue() * factor);
	}
	
	public static String upperCaseListProduct(Product p) {
		return p.getName().toUpperCase();
	}
	
	@Override
	public String toString() {
		return String.format("Produto: %s ,Valor: %.2f" , this.name, getValue());
	}
	
}
