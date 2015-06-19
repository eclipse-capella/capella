/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 *
 */
public interface ITransfoRule {

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getName()
   */
  public String getName();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#isActive()
   */
  public boolean isActive();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#setLongName(java.lang.String)
   */
  public void setLongName(String name);

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getShortName()
   */
  public String getShortName();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#setShortName(java.lang.String)
   */
  public void setShortName(String shortName);

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getSourceType()
   */
  public EClass getSourceType();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getTargetType()
   */
  public EClass getTargetType();

  public EClass getSpecificLinkKind();

  /**
   * @return the abstract
   */
  public boolean isAbstract();

  /**
   * @param isAbstract the abstract to set
   */
  public void setAbstract(boolean isAbstract);

  /**
   * @return the deprecated
   */
  public boolean isDeprecated();

  /**
   * @param deprecated the deprecated to set
   */
  public void setDeprecated(boolean deprecated);

  /**
   * @param active the active to set
   */
  public void setActive(boolean active);

  public abstract boolean when(EObject element, ITransfo transfo);

  public abstract List<EObject> retrieveRelatedElements(EObject element, ITransfo transfo);

  /**
   * Returns whether the element should be transformed by the rule or if it has been already transformed
   * @param element
   * @param transfo
   * @return
   */
  public abstract boolean requireTransformation(EObject element, ITransfo transfo);

  public abstract Object transform(EObject element, ITransfo transfo);

  public abstract void update(EObject element, ITransfo transfo);

  public abstract void attach(EObject element, ITransfo transfo) throws TransfoException;

  public abstract String getDescription();

  public abstract String toHtml(boolean standalone);

  /**
   * @see java.lang.Object#toString()
   */
  public abstract String toString();
}
