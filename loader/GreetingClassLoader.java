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

  public Class loadClass(String className, boolean resolve) throws ClassNotFoundException {
    Class cls = findLoadedClass(className);
    if(cls == null) {
      try {
        ClassLoader parent = getParent();
        if(parent == null) {
          cls = getSystemClassLoader().loadClass(className);
        } else {
          cls = parent.loadClass(className);
        }
      } catch(ClassNotFoundException e) {
        cls = findClass(className);
      }
    }

    try {
      if(resolve) {
        resolveClass(cls);
      }
    } catch(Error e) {
        e.printStackTrace();
        throw e;
    }
    return cls;
  }
}
