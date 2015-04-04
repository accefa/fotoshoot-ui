package accefa.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Command Line Parser der Applikation. Stellt auch Default-Werte zur Verfügung.
 */
public class ApplicationCliParser {

   private String fqdnModule;

   /**
    * Erzeugt einen neuen Cli Parser.
    *
    * @param args
    *           Programmargumente.
    */
   public ApplicationCliParser(final String[] args) {
      final Options options = new Options();
      final Option optionModule = new Option("m", "module", true, "FQDN von Module");
      options.addOption(optionModule);
      final CommandLineParser parser = new GnuParser();
      try {
         final CommandLine cmd = parser.parse(options, args);
         if (cmd.hasOption(optionModule.getOpt())) {
            fqdnModule = cmd.getOptionValue(optionModule.getOpt());
         }
      } catch (final ParseException e) {
         fqdnModule = null;
      }
   }

   /**
    * Gibt den FQDN des Moduls zurück. Falls kein Wert gesetzt ist, wird der
    * Default Wert zurückgegeben.
    *
    * @param defaultValue
    *           Default Wert für diesen Moduls.
    * @return FQDN Module.
    */
   public String getFqdnModule(final String defaultValue) {
      if (fqdnModule == null) {
         return defaultValue;
      }
      return fqdnModule;
   }

}
