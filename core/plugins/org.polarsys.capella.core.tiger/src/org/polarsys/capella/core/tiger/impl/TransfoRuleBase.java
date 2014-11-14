/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  private List<TransfoRule> _rules 
    = new ArrayList<TransfoRule>();

  private HashMap<EClass, List<TransfoRule>> _rulesMapping 
    = new HashMap<EClass, List<TransfoRule>>();

  
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
  public void addRule(TransfoRule rule_p) {
    EClass sourceType = rule_p.getSourceType();
    List<TransfoRule> candidates = _rulesMapping.get(sourceType);
    if (candidates == null) {
      candidates = new ArrayList<TransfoRule>();
    }
    candidates.add(rule_p);

    _rulesMapping.put(rule_p.getSourceType(), candidates);
    _rules.add(rule_p);
  }

  /**
   * @throws TransfoException 
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#findMatchingRule(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.bridges.transfo.impl.Transfo)
   */
  public ITransfoRule findMatchingRule(EObject element_p, ITransfo transfo_p) 
    throws TransfoException {
    
    EClass sourceType = element_p.eClass();    
    
    // Actual class
    List<TransfoRule> candidates = _rulesMapping.get(sourceType);
    if(candidates!=null) {
      for (ITransfoRule rule : candidates) {
        if(rule.isActive()
            && rule.when(element_p, transfo_p)) {
          return rule;
        }
      }
    }
    
    // Sub-typing
    candidates = new ArrayList<TransfoRule>();
    for (EClass eClass : _rulesMapping.keySet()) {
      if(eClass.isSuperTypeOf(sourceType)) {
        candidates.addAll(_rulesMapping.get(eClass));
      }
    }
    
    for (ITransfoRule rule : candidates) {
      if(rule.isActive()
          && rule.when(element_p, transfo_p)) {
        return rule;
      }
    }
    
    return null;
  }

  
  @SuppressWarnings("rawtypes")
  public static Class[] getClasses(String packageName_p)
    throws ClassNotFoundException {
    ArrayList<Class> classes = new ArrayList<Class>();
    //  Get a File object for the package
    File directory = null;
    try {
      ClassLoader cld = Thread.currentThread().getContextClassLoader();
      if (cld == null) {
        throw new ClassNotFoundException("Can't get class loader.");
      }
      String path = packageName_p.replace('.', '/');
      URL resource = cld.getResource(path);
      if (resource == null) {
        throw new ClassNotFoundException("No resource for " + path);
      }
      directory = new File(resource.getFile());
    } catch (NullPointerException x) {
      throw new ClassNotFoundException(packageName_p + " (" + directory
                                       + ") does not appear to be a valid package");
    }
    if (directory.exists()) {
      // Get the list of the files contained in the package
      String[] files = directory.list();
      for (int i = 0; i < files.length; i++) {
        // we are only interested in .class files
        if (files[i].endsWith(".class")) {
          // removes the .class extension
          classes.add(Class.forName(packageName_p + '.'
                                    + files[i].substring(0, files[i].length() - 6)));
        }
      }
    } else {
      throw new ClassNotFoundException(packageName_p
                                       + " does not appear to be a valid package");
    }
    
    Class[] foundedClasses = new Class[classes.size()];
    classes.toArray(foundedClasses);
    return foundedClasses;
  }


  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#loadRules(java.lang.String)
   */
  public void loadRules(String rulePkgName_p) 
    throws ClassNotFoundException {
    
    Class<?>[] classes = getClasses(rulePkgName_p);
    for (Class<?> class_ : classes) {
      loadRule(class_);
    }      

  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#loadRule(java.lang.Class)
   */
  public void loadRule(Class<?> ruleClass_p) {
    try {
      Object obj = ruleClass_p.newInstance();
      addRule((TransfoRule) obj);
    } catch (InstantiationException exception_p) {
      exception_p.printStackTrace();

    } catch (IllegalAccessException exception_p) {
      exception_p.printStackTrace();
    }
  }

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#loadRules(java.lang.String, java.lang.String[])
   */
  public void loadRules(String rulePkgName_p, String[] classNames_p) 
    throws ClassNotFoundException {
	 Bundle bundle =  Activator.getDefault().getBundle();
    for (String className : classNames_p) {
    	Class<?> class_ = bundle.loadClass(rulePkgName_p + "." + className); //$NON-NLS-1$
      loadRule(class_);
    }
  }

  private static String __cr = System.getProperty("line.separator"); //$NON-NLS-1$  
  
  @SuppressWarnings("nls")
  public String toHtml(boolean standalone_p) {
    StringBuilder htmlDoc = new StringBuilder();
    if(standalone_p) {
      htmlDoc.append("<html>" + __cr);
      htmlDoc.append("<head>" + __cr);
      htmlDoc.append("<title> Rule Base Documentation </title>" + __cr);
      htmlDoc.append("</head>" + __cr);
      htmlDoc.append("<body>" + __cr);
    }
    htmlDoc.append("<table border=2>" + __cr);
    htmlDoc.append("<tr>" + __cr);      
    htmlDoc.append("<th> Name" + __cr);
    htmlDoc.append("</th>" + __cr);
    htmlDoc.append("<th> Inherits" + __cr);
    htmlDoc.append("</th>" + __cr);
    htmlDoc.append("<th colspan='4'> Type" + __cr);   
    htmlDoc.append("</th>" + __cr);
    htmlDoc.append("<th> Updated Attributes" + __cr);
    htmlDoc.append("</th>" + __cr);    
    htmlDoc.append("<th> Description" + __cr);
    htmlDoc.append("</th>" + __cr);
    htmlDoc.append("</tr>" + __cr);
    
    Collections.sort(_rules);
    int size = _rules.size();
    for (int i=0; i<size; i++) {
      htmlDoc.append("<tr>" + __cr);
      htmlDoc.append(_rules.get(i).toHtml(false));
      htmlDoc.append("</tr>" + __cr);
    }
    htmlDoc.append("</table>" + __cr);    
    if(standalone_p) {
      htmlDoc.append("</body>" + __cr);
      htmlDoc.append("</html>" + __cr);
    }
    return htmlDoc.toString();
  }
  
  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRuleBase#toString()
   */
  @Override
  @SuppressWarnings("nls")
  public String toString() {
    StringBuilder builder = new StringBuilder();
    
    builder.append("TransfoRuleBase: ");
    builder.append(__cr);    

    builder.append(" + Applicable rules");
    builder.append(__cr);
    for (ITransfoRule rule : _rules) {
      builder.append("   - Rule " + rule.getName() 
                     + " [Active=" + rule.isActive() + "]");
      builder.append(__cr);      
    }

    builder.append(" + Rules Mapping");
    builder.append(__cr);
    for (EClass eclass : _rulesMapping.keySet()) {
      builder.append("   - " + eclass.getName());
      builder.append(" : ");
      List<TransfoRule> candidates = _rulesMapping.get(eclass);
      for (ITransfoRule candidate : candidates) {
        if(!candidate.isActive()) {
          builder.append("[");
        }
        builder.append(candidate.getShortName());
        if(!candidate.isActive()) {
          builder.append("]");
        }        
        builder.append(" ");
      }
      
      builder.append(__cr);      
    }
    
    return builder.toString();
  }
 
}
