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
package org.polarsys.capella.core.tiger;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.tiger.impl.TransfoRuleBase;

/**
 *
 */
public interface ITransfo extends Map<String, Object> {

  public abstract String getUid();

  public abstract void setUid(String uid_p);

  /**
   * @param rule_p
   * @see org.polarsys.capella.core.tiger.impl.TransfoRuleBase#addRule(org.polarsys.capella.core.tiger.impl.TransfoRule)
   */
  public abstract void addRule(TransfoRule rule_p);

  /**
   * @param ruleClass_p
   * @see org.polarsys.capella.core.tiger.impl.TransfoRuleBase#loadRule(java.lang.Class)
   */
  public abstract void loadRule(Class<?> ruleClass_p);

  /**
   * @param rulePkgName_p
   * @param classNames_p
   * @throws ClassNotFoundException
   * @see org.polarsys.capella.core.tiger.impl.TransfoRuleBase#loadRules(java.lang.String, java.lang.String[])
   */
  public abstract void loadRules(String rulePkgName_p, String[] classNames_p) throws ClassNotFoundException;

  /**
   * @param rulePkgName_p
   * @throws ClassNotFoundException
   * @see org.polarsys.capella.core.tiger.impl.TransfoRuleBase#loadRules(java.lang.String)
   */
  public abstract void loadRules(String rulePkgName_p) throws ClassNotFoundException;

  /**
   * 
   * @param element_p
   * @param transfo_p
   * @return
   * @throws TransfoException 
   * @see TransfoRuleBase#findMatchingRule(EObject, Transfo)
   */
  public abstract ITransfoRule findMatchingRule(EObject element_p) throws TransfoException;

  /**
   * 
   * @param element_p
   * @return
   * @throws TransfoException 
   */
  public abstract ITransfoRule findCachedMatchingRule(EObject element_p) throws TransfoException;

  /**
   *
   */
  public abstract String toHtml();
  
  /**
   * @see java.util.AbstractMap#toString()
   */
  public abstract String toString();

  /**
   * @return
   */
  public abstract List<IFinalizer> getFinalizers();

  /**
   * @return defined resolvers
   */
  public abstract List<IResolver> getResolvers();

}
