package aida;

import java.util.List;

import forecast.Listener;
import forecast.Simulator;

import prefixspan.MainTestPrefixSpan_saveToFile;

public class Main {

	public static void main(String[] args) throws Exception {
		
		/* --- START of 1st part code --- */
		System.out.println("----------------------- 1st PART output -----------------------");
		
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
		/* --- END of 1st part code --- */
		
		/* --- START of 2nd part code --- */
		System.out.println("----------------------- 2nd PART output -----------------------");
		Simulator initiater = new Simulator(5);
        Listener r1 = new Listener(sp);

        initiater.addListener(r1);

        System.out.println("\nSTARTING EXECUTION!\n");
        
        for(int i=0; i<10; i++){
        	//Long pausing time in order to make some partial so invalid due to time constraint
        	if(i==6)	Thread.sleep(260000);
        	initiater.makeQuery();
        }
        
        System.out.println("\nSTOP EXECUTION!");
		
		/* --- END of 2nd part code --- */
	  }
}
