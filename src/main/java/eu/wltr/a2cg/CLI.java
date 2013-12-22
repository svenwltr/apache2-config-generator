package eu.wltr.a2cg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.xml.bind.JAXBException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class CLI {
	public static void main(String[] args) {
		Options options = new Options();

		options.addOption("h", "help", false, "Prints usage and exits.");

		CommandLineParser parser = new PosixParser();

		String cmdSyntax = ApacheConfigGenerator.class.getCanonicalName()
				+ " [options] infile outfile";

		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("help")) {
				new HelpFormatter().printHelp(cmdSyntax, options);
				System.exit(0);
			}

			if (line.getArgs().length != 2) {
				new HelpFormatter().printHelp(cmdSyntax, options);
				System.exit(1);
			}
			
			OutputStream out = ("-".equals(args[1])) ? System.out
					: new PrintStream(args[1]);
			InputStream in = new FileInputStream(args[0]);
			
			ApacheConfigGenerator a2cg = new ApacheConfigGenerator(in, out);
			a2cg.generate();

		} catch (ParseException e) {
			System.err.println(e.toString());
			new HelpFormatter().printHelp(cmdSyntax, options);
			System.exit(2);

		} catch (FileNotFoundException e) {
			System.err.println(e.toString());
			System.exit(3);

		} catch (JAXBException e) {
			System.err.println(e.toString());
			System.exit(4);

		}

	}

}
