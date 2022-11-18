package com.nassar.compositepattern;

import org.springframework.stereotype.Service;

@Service
public class FileSystemService {
	
	public String printFileSystem() {
		try {
			Directory directory = new Directory("root");
			File onelevel = new File("nassar");
			directory.addFileDir(onelevel);
			
			Directory dir = new Directory("siddiq");
			//FileSystem twolevelleft = new File("masthan");
			FileSystem twolevelright = new File("shah");
			//dir.addFileDir(twolevelleft);
			dir.addFileDir(twolevelright);
			
			directory.addFileDir(dir);
			
			directory.ls();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return "printed in console.";
	}
}
