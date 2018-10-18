package distinctImsi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

public class DistinctImsiOperation {
	 public Set<String> imsiValueSet = new LinkedHashSet<>(); //list which does not have any duplication 
	 public Vector<String> filePath = new Vector<String>(); 
		
	 //find the files then export the imsi values to file
	 public void findDistinctValuesThenExport(String folderPath, String exportFileName, boolean operationMode){

		 //find the path then get file names to queue
		 getFileNameList(folderPath);
		 
		 //one by one skim all files 
		 
		 for (String fileName : filePath) {
			 if(!operationMode)
				 readThenAppendtoFile(fileName);
			 else
				 readThenAppendtoFileWParsed(fileName);
		}
		 
		 //we are ready to create file then write to file 
		 writeToFile(exportFileName);
		System.out.println("File succesfully created as: " + exportFileName);
		 
	 }
	 
	 public void getFileNameList(String folderPath){
		 //clear the list 
		 filePath.clear();
		 
		 //get folders from the given directory
		 File folder = new File(folderPath);
		 File[] listOfFiles = folder.listFiles();
		 
		 for (int i = 0; i < listOfFiles.length; i++) {
			 filePath.add(folderPath + "/" + listOfFiles[i].getName());
			 System.out.println("file path is added for detecting imsi: "+ folderPath+"/" +listOfFiles[i].getName());
		 }
	 }
	 
	 
	 public void readThenAppendtoFile(String filename){

	        try (InputStream fis = new FileInputStream(filename);
	                InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.ISO_8859_1);
	                BufferedReader br = new BufferedReader(isr)) {

	            br.lines().forEach(line -> imsiValueSet.add(line));
	            
	        }catch (Exception e) {
				System.out.println("File read error. At file: "+ filename);
				e.printStackTrace();
			}
	 
	 }
	 
	 
	 public void readThenAppendtoFileWParsed(String filename){

	        try (InputStream fis = new FileInputStream(filename);
	                InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.ISO_8859_1);
	                BufferedReader br = new BufferedReader(isr)) {

	            br.lines().forEach(line -> imsiValueSet.add(line.split(",")[1]));
	            
	        }catch (Exception e) {
				System.out.println("File read error. At file: "+ filename);
				e.printStackTrace();
			}
	 
	 }
	 
	 public void writeToFile(String exportFileName){
		 
		 Writer bufferedWriter = null;
		 
			try {
				
				//Creating a file
				Writer fileWriter = new FileWriter(exportFileName);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				// Writing the content
				for(String imsi : imsiValueSet) {
					bufferedWriter.write(imsi);
					bufferedWriter.write(System.getProperty("line.separator"));
				}
			} catch (IOException e) {
				System.out.println("Problem occurs when creating file " + filePath);
				e.printStackTrace();
			} finally {
				
				//Closing the file
				if (bufferedWriter != null) {
					try {
						bufferedWriter.close();
					} catch (IOException e) {
						System.out.println("Problem occurs when closing file !");
						e.printStackTrace();
					}
				}
			}
		 
	 }
		
		
		
}
