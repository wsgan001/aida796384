package aida;

import java.util.List;

import prefixspan.MainTestPrefixSpan_saveToFile;

public class Main {

	public static void main(String[] args) throws Exception {
		 
		Manager m = new Manager();
		m.parseCSVtoTXT("C:\\Users\\Matteo\\Dropbox\\UNI\\TESI RELACS\\MatteoSimoni\\java_prove\\csv\\log_new.csv",
				"C:\\Users\\Matteo\\Dropbox\\UNI\\TESI RELACS\\MatteoSimoni\\java_prove\\csv\\inputPrefixSpan.txt",
				"SELECT * FROM sensors");	 
		
		// Launching PrefixSpan Algorithm..
		System.out.println("Launching PrefixSpan Algorithm..\n");
		String[] arg = new String[2];
		arg[0]="C:\\Users\\Matteo\\Dropbox\\UNI\\TESI RELACS\\MatteoSimoni\\java_prove\\csv\\inputPrefixSpan.txt";
		arg[1]="C:\\Users\\Matteo\\Dropbox\\UNI\\TESI RELACS\\MatteoSimoni\\java_prove\\csv\\outputPrefixSpan.txt";
		MainTestPrefixSpan_saveToFile.main(arg);
		System.out.println("Sequential pattern search done.\n");
		
		// Parsing PrefixSPan Output
		/* Now I have to parse outputPrefixSpan.txt in order to retrieve the frequent sequential patter that I need (the ones that
		contains the time-expensive query) and use them in order to calculate the "duration" of a pattern.. */
		List<SequentialPattern> sp = m.parseSP("C:\\Users\\Matteo\\Dropbox\\UNI\\TESI RELACS\\MatteoSimoni\\java_prove\\csv\\outputPrefixSpan.txt");
		
		//Finding Sequential Pattern in the chuncks of log
		m.findSP(sp);
	  }
}