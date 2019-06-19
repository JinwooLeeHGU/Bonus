package edu.handong.bonus;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.File;
import java.lang.String;
import java.nio.file.Paths;
import java.nio.file.Path;

public class ls {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ls runner = new ls();
		runner.run(args);
	}
	String path;
	boolean filePath;
	boolean all;
	boolean version;
	boolean folder;
	boolean list;
	boolean help;
	
	public void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
//				//String path = ls.class.getResource("").getPath();
//				System.out.println("Current relative path is: " + System.getProperty("user.dir"));
				return;
			}
			if(filePath) {
				Path currentRelativePath = Paths.get("");
				path = currentRelativePath.toAbsolutePath().toString();
				System.out.println("Current absolute path of project Directory is: " + path);
			}

			if(all) {							
				path = System.getProperty("user.dir");
				File f = new File(path);
				boolean checkDir = f.isDirectory();
				
				if(checkDir) {
					File []fileList=f.listFiles();
					for(File tempFile : fileList) {
						  if(tempFile.isFile()) {
	  						 	//String tempPath=tempFile.getParent();
	  							//String tempFileName=tempFile.getName();
	  							//System.out.println("Path="+tempPath);
							  System.out.println(tempFile.getName());
							  //System.out.println(tempFile.getAbsolutePath());
						  }
						  else {		
							  System.out.println(tempFile.getName());
							//System.out.println(tempFile.getAbsolutePath());
						   }
					}
				}
			}
			
			if(version) {
			System.out.println("ls (GNU coreutils) 1.1\r\n" + 
					"Copyright (C) 2019 ISEL, edu.\r\n" +  
					"This is free software: you are free to change and redistribute it.\r\n" + 
					"There is NO WARRANTY, to the extent permitted by law.\r\n" + 
					"\r\n" + 
					"Written by Jinwoo Lee.\r\n" + 
					"");
		}
		if(folder) {
			path = System.getProperty("user.dir");
			File f = new File(path);
			boolean checkDir = f.isDirectory();
			
			if(checkDir) {
				File []fileList=f.listFiles();
				for(File tempFile : fileList) {
					  if(tempFile.isFile()) {						
						  System.out.println(tempFile.getName() + " is a file");					  
					  }
					  else {		
						  System.out.println(tempFile.getName() + " is a folder");
					   }
				}
			}
		}
	}
	}
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
	
		try {
			
			CommandLine cmd = parser.parse(options, args);
			all = cmd.hasOption("a"); //
			filePath = cmd.hasOption("p");  //
			version = cmd.hasOption("v");//
			folder = cmd.hasOption("f");			
			help = cmd.hasOption("help");   //
						
		} catch (Exception e) {
			System.out.println("ls: unknown option -- e\r\n" + 
					"Try 'ls --help' for more information.\r\n" + 
					"");
			return false;
		}

		return true;
	}
		
	private Options createOptions() {
		Options options = new Options();
				
		options.addOption(Option.builder("a").longOpt("all")
				.desc("do not ignore entries starting with .")
				//.hasArg()
				.argName("List all files in directory")
				//.required()
				.build());		
		options.addOption(Option.builder("v").longOpt("version")
				.desc("output version information and exit")
				//.hasArg()
				.argName("shows version of program")
				//.required()
				.build());
		options.addOption(Option.builder("f").longOpt("folder")
				.desc("shows whether contents in current directory are files or folders")
				//.hasArg()
				.argName("folder or file")
				//.required()
				.build());
		options.addOption(Option.builder("p").longOpt("path")
				.desc("Shows absolute path of current Project directory")
				//.hasArg()
				.argName("Path name to display")
				//.required()
				.build());
		options.addOption(Option.builder().longOpt("help")
				.desc("Help")
		        .build()); 
		
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI ls implementation program";
		String footer ="";
		formatter.printHelp("CLI-ls-program", header, options, footer, true);
	}
}