import java.io.File;
import java.io.FileOutputStream;

public class Builder {
  protected static String directory = null;
  protected static String language  = null;
  protected static String greeting  = null;

  public static void main(String[] args) {
    // Arg1: Path to language classes
    // Arg2: Language
    // Arg3: Text
    if(args.length != 3) {
      System.out.println("You must provide the path to the language files, a language, and a string.");
      System.exit(1);
    }

    directory = args[0];
    language  = args[1];
    greeting  = args[2];

    try {
      File f = new File(directory, language + ".java");
      FileOutputStream fop = new FileOutputStream(f);

      if(f.exists()) {
        StringBuilder b = new StringBuilder();
        b.append("public class ").append(language).append(" {\n");
        b.append("  public String getLanguage() {\n");
        b.append("    return \"").append(language).append("\";\n");
        b.append("  }\n");
        b.append("\n");
        b.append("  public String getGreeting() {\n");
        b.append("    return \"").append(greeting).append("\";\n");
        b.append("  }\n");
        b.append("}\n");

        fop.write(b.toString().getBytes());
        fop.flush();
        fop.close();
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
