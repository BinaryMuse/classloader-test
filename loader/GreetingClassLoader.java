import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class GreetingClassLoader extends ClassLoader {
  protected String dir;

  public GreetingClassLoader(String class_dir) {
    super();
    this.dir = class_dir;
  }

  protected Class findClass(String className) throws ClassNotFoundException {
    try {
      File classFile = new File(dir, className + ".class");
      InputStream input = new BufferedInputStream(new FileInputStream(classFile));
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      int i = 0;
      while((i = input.read()) >= 0) {
        output.write(i);
      }
      byte[] bytes = output.toByteArray();
      return defineClass(className, bytes, 0, bytes.length);
    } catch(Exception e) {
      throw new ClassNotFoundException("Could not load " + className + ".class.");
    }
  }
}
