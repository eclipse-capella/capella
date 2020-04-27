/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.tiger.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.Bundle;
import org.polarsys.capella.core.tiger.Activator;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRule;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.TransfoException;

/**
 * 
 */
public class TransfoRuleBase implements ITransfoRuleBase {

  private List<TransfoRule> _rules = new ArrayList<TransfoRule>();
  private Map<EClass, List<TransfoRule>> _rulesMapping = new HashMap<EClass, List<TransfoRule>>();

  /**
   * 
   */
  public TransfoRuleBase() {
  }

  /**
   * @return 
   */
  public Iterator<TransfoRule> iterator() {
    return _rules.iterator();
  }
  
  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#addRule(org.polarsys.capella.core.bridges.transfo.impl.TransfoRule)
   */
  public void addRule(TransfoRule rule) {
    EClass sourceType = rule.getSourceType();
    List<TransfoRule> candidates = _rulesMapping.get(sourceType);
    if (candidates == null) {
      candidates = new ArrayList<TransfoRule>();
    }
    candidates.add(rule);

    _rulesMapping.put(rule.getSourceType(), candidates);
    _rules.add(rule);
  }

  /**
   * @throws TransfoException 
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#findMatchingRule(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public ITransfoRule findMatchingRule(EObject element, ITransfo transfo) throws TransfoException {
    EClass sourceType = element.eClass();    
    
    // Actual class
    List<TransfoRule> candidates = _rulesMapping.get(sourceType);
    if(candidates!=null) {
      for (ITransfoRule rule : candidates) {
        if(rule.isActive()
            && rule.when(element, transfo)) {
          return rule;
        }
      }
    }
    
    // Sub-typing
    candidates = new ArrayList<TransfoRule>();
    for (EClass eClass : _rulesMapping.keySet()) {
      if (eClass.isSuperTypeOf(sourceType)) {
        candidates.addAll(_rulesMapping.get(eClass));
      }
    }
    
    for (ITransfoRule rule : candidates) {
      if(rule.isActive()
          && rule.when(element, transfo)) {
        return rule;
      }
    }
    
    return null;
  }

  @SuppressWarnings("rawtypes")
  public static Class[] getClasses(String packageName) throws ClassNotFoundException {
    ArrayList<Class> classes = new ArrayList<Class>();
    //  Get a File object for the package
    File directory = null;
    try {
      ClassLoader cld = Thread.currentThread().getContextClassLoader();
      if (cld == null) {
        throw new ClassNotFoundException("Can't get class loader."); //$NON-NLS-1$
      }
      String path = packageName.replace('.', '/');
      URL resource = cld.getResource(path);
      if (resource == null) {
        throw new ClassNotFoundException("No resource for " + path); //$NON-NLS-1$
      }
      directory = new File(resource.getFile());
    } catch (NullPointerException x) {
      throw new ClassNotFoundException(packageName + " (" + directory + ") does not appear to be a valid package"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (directory.exists()) {
      // Get the list of the files contained in the package
      String[] files = directory.list();
      if (files != null) {
        for (int i = 0; i < files.length; i++) {
          // we are only interested in .class files
          if (files[i].endsWith(".class")) { //$NON-NLS-1$
            // removes the .class extension
            classes.add(Class.forName(packageName + '.' + files[i].substring(0, files[i].length() - 6))); //$NON-NLS-1$
          }
        }
      }
    } else {
      throw new ClassNotFoundException(packageName + " does not appear to be a valid package"); //$NON-NLS-1$
    }
    
    Class[] foundedClasses = new Class[classes.size()];
    classes.toArray(foundedClasses);
    return foundedClasses;
  }


  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#loadRules(java.lang.String)
   */
  public void loadRules(String rulePkgName) throws ClassNotFoundException {
    
    Class<?>[] classes = getClasses(rulePkgName);
    for (Class<?> class_ : classes) {
      loadRule(class_);
    }      

  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#loadRule(java.lang.Class)
   */
  public void loadRule(Class<?> ruleClass) {
    try {
      Object obj = ruleClass.newInstance();
      addRule((TransfoRule) obj);
    } catch (InstantiationException exception) {
      exception.printStackTrace();

    } catch (IllegalAccessException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#loadRules(java.lang.String, java.lang.String[])
   */
  public void loadRules(String rulePkgName, String[] classNames) 
    throws ClassNotFoundException {
	 Bundle bundle =  Activator.getDefault().getBundle();
    for (String className : classNames) {
    	Class<?> class_ = bundle.loadClass(rulePkgName + "." + className); //$NON-NLS-1$
      loadRule(class_);
    }
  }

  private static String CR = System.getProperty("line.separator"); //$NON-NLS-1$
  
  public String toHtml(boolean standalone) {
    StringBuilder htmlDoc = new StringBuilder();
    if(standalone) {
      htmlDoc.append("<html>").append(CR); //$NON-NLS-1$
      htmlDoc.append("<head>").append(CR); //$NON-NLS-1$
      htmlDoc.append("<title> Rule Base Documentation </title>").append(CR); //$NON-NLS-1$
      htmlDoc.append("</head>").append(CR); //$NON-NLS-1$
      htmlDoc.append("<body>").append(CR); //$NON-NLS-1$
    }
    htmlDoc.append("<table border=2>").append(CR); //$NON-NLS-1$
    htmlDoc.append("<tr>").append(CR); //$NON-NLS-1$
    htmlDoc.append("<th> Name").append(CR); //$NON-NLS-1$
    htmlDoc.append("</th>").append(CR); //$NON-NLS-1$
    htmlDoc.append("<th> Inherits").append(CR); //$NON-NLS-1$
    htmlDoc.append("</th>").append(CR); //$NON-NLS-1$
    htmlDoc.append("<th colspan='4'> Type").append(CR); //$NON-NLS-1$
    htmlDoc.append("</th>").append(CR); //$NON-NLS-1$
    htmlDoc.append("<th> Updated Attributes").append(CR); //$NON-NLS-1$
    htmlDoc.append("</th>").append(CR); //$NON-NLS-1$
    htmlDoc.append("<th> Description").append(CR); //$NON-NLS-1$
    htmlDoc.append("</th>").append(CR); //$NON-NLS-1$
    htmlDoc.append("</tr>").append(CR); //$NON-NLS-1$
    
    Collections.sort(_rules);
    int size = _rules.size();
    for (int i=0; i<size; i++) {
      htmlDoc.append("<tr>").append(CR); //$NON-NLS-1$
      htmlDoc.append(_rules.get(i).toHtml(false));
      htmlDoc.append("</tr>").append(CR); //$NON-NLS-1$
    }
    htmlDoc.append("</table>").append(CR); //$NON-NLS-1$
    if(standalone) {
      htmlDoc.append("</body>").append(CR); //$NON-NLS-1$
      htmlDoc.append("</html>").append(CR); //$NON-NLS-1$
    }
    return htmlDoc.toString();
  }
  
  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    
    builder.append("TransfoRuleBase: ").append(CR); //$NON-NLS-1$
    builder.append(" + Applicable rules").append(CR); //$NON-NLS-1$
    for (ITransfoRule rule : _rules) {
      builder.append("   - Rule " + rule.getName() //$NON-NLS-1$
                     + " [Active=" + rule.isActive() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
      builder.append(CR);      
    }

    builder.append(" + Rules Mapping").append(CR); //$NON-NLS-1$
    for (EClass eclass : _rulesMapping.keySet()) {
      builder.append("   - ").append(eclass.getName()); //$NON-NLS-1$
      builder.append(" : "); //$NON-NLS-1$
      List<TransfoRule> candidates = _rulesMapping.get(eclass);
      for (ITransfoRule candidate : candidates) {
        if(!candidate.isActive()) {
          builder.append("["); //$NON-NLS-1$
        }
        builder.append(candidate.getShortName());
        if(!candidate.isActive()) {
          builder.append("]"); //$NON-NLS-1$
        }        
        builder.append(" "); //$NON-NLS-1$
      }
      
      builder.append(CR);      
    }
    
    return builder.toString();
  }
 
}
