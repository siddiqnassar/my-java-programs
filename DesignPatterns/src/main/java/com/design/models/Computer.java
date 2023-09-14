package com.design.models;

public class Computer {

	public static class ComputerBuilder {
		
		private String ram;
		private int cores;
		
		public ComputerBuilder() {
		}
		public Computer builder() {
			return new Computer(this);
		}
		public ComputerBuilder addRam(String ram) {
			this.ram = ram;
			return this;
		}
		public ComputerBuilder addCores(int cores) {
			this.cores = cores;
			return this;
		}
		
	}

	private String ram;
	private int cores;
	
	public String getRam() {
		return this.ram;
	}
	
	public int getCores() {
		return this.cores;
	}

	public Computer(ComputerBuilder computerBuilder) {
		this.ram = computerBuilder.ram;
		this.cores = computerBuilder.cores;
	}
}
