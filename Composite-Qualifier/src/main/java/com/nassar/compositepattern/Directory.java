package com.nassar.compositepattern;

import java.util.ArrayList;

public class Directory implements FileSystem {

	private String directoryName;
	ArrayList<FileSystem> directoryList;
	
	public Directory(String directoryName) {
		this.directoryName = directoryName;
		this.directoryList = new ArrayList<>();
	}


	public void addFileDir(FileSystem fileDir) {
		directoryList.add(fileDir);
	}
	
	@Override
	public void ls() {
		System.out.println("Directory name is "+ directoryName);
		for (FileSystem fileObj : directoryList) {
			fileObj.ls();
		}
	}

	
}
