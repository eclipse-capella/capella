package org.polarsys.capella.core.business.queries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.IQuery;
import org.polarsys.capella.common.queries.QuerySchema;

class GeneratePluginHelper {
  public static void main(String[] args) {

    System.out.println("init");
    for (Class clazz : getClasses("org.polarsys.capella.core.business.queries", IBusinessQuery.class)) {
      System.out.println("<MDEBusinessQueries class=\"" + clazz.getName() + "\" />");
    }

    for (Class clazz : getClasses("org.polarsys.capella.core.business.queries.queries", IQuery.class)) {
      String extendedQueryIdentifier = "";
      ExtendingQuery annotation = (ExtendingQuery) clazz.getAnnotation(ExtendingQuery.class);
      if (annotation != null) {
        extendedQueryIdentifier = " extendedQueryIdentifier=\"" + QuerySchema.getQueryIdentifier(annotation.extendingQuery()) + "\"";
      }
      System.out.println("    <querySpecification queryIdentifier=\"" + QuerySchema.getQueryIdentifier(clazz) + "\""+extendedQueryIdentifier+">\r\n"
          + "      <queryAlgorithm algorithm=\"" + clazz.getName() + "\" />\r\n" + "    </querySpecification>");
    }

    System.out.println();

  }

  public static Collection<Class> getClasses(String packageName, Class instance) {
    Collection<Class> queries = new ArrayList<>();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String path = packageName.replace('.', '/');
    try {
      File folder = new File(classLoader.getResources(path).nextElement().getFile());
      for (File s : folder.listFiles()) {
        if (s.isDirectory()) {
          for (File s2 : s.listFiles()) {
            if (s2.getName().endsWith(".class")) {
              String sss = s2.getAbsolutePath().substring(s2.getAbsolutePath().indexOf("bin\\") + 4)
                  .replaceAll("\\\\", ".").replace(".class", "");
              try {
                Class query = classLoader.loadClass(sss);
                if (instance.isAssignableFrom(query)) {
                  queries.add(query);
                }
              } catch (ClassNotFoundException e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return queries;
  }
}
