package accefa.guice;

import com.google.inject.AbstractModule;

/**
 * Factory um ein Modul zu erzeugen.
 */
public class FotoShootModuleFactory {

   private final String fqdnModuleClassName;

   /**
    * Erzeugt eine neue Factory.
    *
    * @param fqdnModuleClassName
    *           FQDN der Module Klasse.
    */
   public FotoShootModuleFactory(final String fqdnModuleClassName) {
      this.fqdnModuleClassName = fqdnModuleClassName;
   }

   /**
    * Erzeugt das konkrete Module.
    *
    * @return Modul Instanz.
    */
   public AbstractModule create() {
      try {
         final Class<?> moduleClass = FotoShootModuleFactory.class.getClassLoader().loadClass(fqdnModuleClassName);
         return (AbstractModule) moduleClass.newInstance();
      } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }

}
