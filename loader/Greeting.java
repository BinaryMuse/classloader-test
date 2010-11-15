import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Greeting {
  // Get our classloader
  protected static GreetingClassLoader cl = null;

  public static void main(String[] args) {
    // Verify we were passed the directory to load classes from
    String class_dir = "";
    if(args.length == 1) {
      class_dir = args[0];
    } else {
      System.out.println("You must pass the directory to load classes from as the first parameter.");
      System.exit(1);
    }
  
    // Initialize the classloader
    cl = new GreetingClassLoader(class_dir);

    // Find each of the languages to load
    File search_dir = new File(class_dir);
    String[] languages = search_dir.list();

    // Iterate through the languages
    for(int i = 0; i < languages.length; i++) {
      String filename = languages[i];
      String[] parts = filename.split("\\.");
      if(parts.length == 2 && parts[1].equals("class")) {
        Greeting.print(parts[0]);
      }
    }
  }

  private static void print(String language) {
    // Load the class and output the greeting info
    try {
      Class<?> cls = cl.loadClass(language);
      Object obj   = cls.newInstance();
      Method m_lang  = cls.getMethod("getLanguage", (Class[]) null);
      Method m_greet = cls.getMethod("getGreeting", (Class[]) null);
      Object lang  = m_lang.invoke(obj, (Object[]) null);
      Object greet = m_greet.invoke(obj, (Object[]) null);
      System.out.println(lang.toString() + ": " + greet.toString());
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
