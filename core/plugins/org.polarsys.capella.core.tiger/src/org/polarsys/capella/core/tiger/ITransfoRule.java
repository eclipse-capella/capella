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
  public void setLongName(String name_p);

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getShortName()
   */
  public String getShortName();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#setShortName(java.lang.String)
   */
  public void setShortName(String shortName_p);

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
   * @param abstract_p the abstract to set
   */
  public void setAbstract(boolean abstract_p);

  /**
   * @return the deprecated
   */
  public boolean isDeprecated();

  /**
   * @param deprecated_p the deprecated to set
   */
  public void setDeprecated(boolean deprecated_p);

  /**
   * @param active_p the active to set
   */
  public void setActive(boolean active_p);

  public abstract boolean when(EObject element_p, ITransfo transfo_p);

  public abstract List<EObject> retrieveRelatedElements(EObject element_p, ITransfo transfo_p);

  /**
   * Returns whether the element should be transformed by the rule or if it has been already transformed
   * @param element_p
   * @param transfo_p
   * @return
   */
  public abstract boolean requireTransformation(EObject element_p, ITransfo transfo_p);

  public abstract Object transform(EObject element_p, ITransfo transfo_p);

  public abstract void update(EObject element_p, ITransfo transfo_p);

  public abstract void attach(EObject element_p, ITransfo transfo_p) throws TransfoException;

  public abstract String getDescription();

  public abstract String toHtml(boolean standalone_p);

  /**
   * @see java.lang.Object#toString()
   */
  public abstract String toString();
}
