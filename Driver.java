package com.aspera.lcw;

/**
 * @author Ram date: 04/19/2016
 */
public class Driver {

	public static void main(String[] args) throws Exception {
		if (args.length == 0)
			throw new Exception("Invalid file path");
		Processor processor = new Processor();
		processor.processFile(args[0]);
	}
}
