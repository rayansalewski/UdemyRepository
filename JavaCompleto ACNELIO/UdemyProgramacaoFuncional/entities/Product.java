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
	
	@Override
	public String toString() {
		return "Produto: " + this.name + ", " + "Valor: " + getValue();
	}
	
}
