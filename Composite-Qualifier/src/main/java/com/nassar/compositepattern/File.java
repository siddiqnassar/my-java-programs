package com.nassar.compositepattern;

public class File implements FileSystem{

	private String fileName;
	
	public File(String name) {
		this.fileName = name;
	}

	@Override
	public void ls() {
		System.out.println("File name is "+ fileName);
	}

	
	
}
