/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.common.model.helpers.internal.IInternalModelConstants;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class DerivedFeaturesImplementation extends BasicTestCase {

  @Override
  public void test() throws Exception {

    // Retrieve a map <EClass.name, IHelper>
    HashMap<String, Object> helpers = (HashMap<String, Object>) getAllDelegatesHelpers(getRootHelpers()).stream()
        .collect(Collectors.toMap(x -> x.getClass().getSimpleName().replace("Helper", ""), x -> x));

    Collection<String> errors = new ArrayList<String>();
    for (EClass clazz : getAllCapellaEClasses()) {

      Collection<EClass> subtypes = getAllCapellaEClasses().stream().filter(x -> clazz.isSuperTypeOf(x))
          .collect(Collectors.toList());

      // For each derived feature, look in all subtypes if there is an helper implementing the corresponding feature.
      // If there is none, derived method may not be implemented
      // If there is several, the viatra expression shall be freeform with an 'or' inside.
      for (EReference reference : getDerived(clazz)) {
        boolean isFreefrom = false;
        int freefromAlternative = 0;
        EAnnotation annotation = reference
            .getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
        if (annotation != null) {
          String child = annotation.getDetails().get("viatra.expression");
          String variant = annotation.getDetails().get("viatra.variant");
          
          if ("freeform".equals(variant)) {
            System.out.println("Viatra Freeform pattern on " + reference.getName());
            isFreefrom = true;
            Matcher matcher = Pattern.compile(" or ").matcher(child);
            while (matcher.find()) freefromAlternative++;
          }
        }

        Collection<Method> methods = new ArrayList<Method>();

        //Retrieve all implementing methods in related helpers
        for (EClass subType : subtypes) {
          Object helper = helpers.get(subType.getName());
          if (helper != null) {
            Method method = Arrays.asList(helper.getClass().getDeclaredMethods()).stream()
                .filter(x -> getMethodNames(reference).contains(x.getName().toLowerCase())).findFirst().orElse(null);
            if (method != null) {
              methods.add(method);
            }
          }
        }

        if (methods.size() > 1) {
          String methodNames = methods.stream().map(x -> x.getDeclaringClass().getSimpleName() + " " + x.getName())
              .collect(Collectors.joining("; "));
          if (!isFreefrom) {
            errors.add(NLS.bind(
                "Viatra shall be 'freeform'. There is several implementations of the derived feature: {0}, {1}",
                reference.getName(), methodNames));
          }
          if (freefromAlternative != methods.size()) {
            errors.add(NLS.bind(
                "Viatra shall be 'freeform with an alternative'. There is {4} implementations of the derived feature but {3} alternatives in the pattern: {0}.{1}, {2}",
                new String[] { clazz.getName(), reference.getName(), methodNames, Integer.toString(freefromAlternative), Integer.toString(methods.size()) }));
          }

        } else if (methods.size() == 0) {
          errors.add(NLS.bind("No helper for: {0}.{1}", clazz.getName(), reference.getName()));
        }
      }
    }

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());
  }

  /**
   * Retrieve lowercase method names that shall implements the reference in the delegate helper.
   * Usually get<EReferenceName>
   */
  private Collection<String> getMethodNames(EReference reference) {
    Collection<String> result = new ArrayList<String>();
    result.add("get" + reference.getName().toLowerCase());
    return result;
  }

  /**
   * Retrieve all capella EClasses
   */
  public Collection<EClass> getAllCapellaEClasses() {
    Collection<EClass> classes = new ArrayList<EClass>();
    for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
      if (nsURI.startsWith("http://www.polarsys.org/capella")) {
        EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        classes.addAll(ePackage.getEClassifiers().stream().filter(x -> x instanceof EClass).map(x -> (EClass) x)
            .filter(x -> !getDerived(x).isEmpty()).collect(Collectors.toList()));
      }
    }
    return classes;
  }

  /**
   * Retrieve all IHelpers for the Capella packages
   */
  public Collection<IHelper> getRootHelpers() {
    Collection<IHelper> helpers = new ArrayList<IHelper>();
    for (IConfigurationElement element : ExtensionPointHelper.getConfigurationElements(
        IInternalModelConstants.CAPELLA_MODEL_PLUG_IN_ID, IInternalModelConstants.HELPER_EXTENSION_POINT_ID)) {
      try {
        helpers.add((IHelper) element.createExecutableExtension("class"));
      } catch (CoreException e) {
        e.printStackTrace();
      }
    }
    return helpers;
  }

  /**
   * Retrieve all delegates helpers for the given helpers
   */
  public Collection<Object> getAllDelegatesHelpers(Collection<IHelper> helpers) {
    return helpers.stream().flatMap(x -> getSubHelpers(x).stream()).distinct().collect(Collectors.toList());
  }

  /**
   * Retrieve all direct derived references for the class
   */
  public Collection<EReference> getDerived(EClass clazz) {
    return clazz.getEReferences().stream().filter(x -> x.isDerived()).collect(Collectors.toList());
  }

  /**
   * For an IHelper, retrieve all Helpers located in the sub package delegates aside to the IHelper
   */
  public Collection<Object> getSubHelpers(IHelper helper) {
    Collection<Object> result = new ArrayList<Object>();
    Bundle d = FrameworkUtil.getBundle(helper.getClass());
    BundleWiring bundleWiringOfBundleA = d.adapt(BundleWiring.class);
    String path = helper.getClass().getPackage().getName().replaceAll("\\.", "/");
    Collection<String> classes = bundleWiringOfBundleA.listResources(path, "*", BundleWiring.LISTRESOURCES_RECURSE);

    classes = classes.stream().map(x -> x.replaceAll("/", ".").replace(".class", ""))
        .filter(x -> x.contains("delegates") && x.endsWith("Helper")).collect(Collectors.toList());

    for (String classId : classes) {
      try {
        Class<?> clazz = d.loadClass(classId);
        try {
          Method m = clazz.getMethod("getInstance");
          Object ee = m.invoke(clazz);
          result.add(ee);
        } catch (NoSuchMethodException | SecurityException e) {
          e.printStackTrace();
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }

      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

}
