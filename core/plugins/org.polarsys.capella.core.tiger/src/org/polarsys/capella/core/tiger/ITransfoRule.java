/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
  String getName();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#isActive()
   */
  boolean isActive();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#setLongName(java.lang.String)
   */
  void setLongName(String name);

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getShortName()
   */
  String getShortName();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#setShortName(java.lang.String)
   */
  void setShortName(String shortName);

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getSourceType()
   */
  EClass getSourceType();

  /**
   * @see org.polarsys.capella.common.tiger.ITransfoRule#getTargetType()
   */
  EClass getTargetType();

  EClass getSpecificLinkKind();

  /**
   * @return the abstract
   */
  boolean isAbstract();

  /**
   * @param isAbstract the abstract to set
   */
  void setAbstract(boolean isAbstract);

  /**
   * @return the deprecated
   */
  boolean isDeprecated();

  /**
   * @param deprecated the deprecated to set
   */
  void setDeprecated(boolean deprecated);

  /**
   * @param active the active to set
   */
  void setActive(boolean active);

  abstract boolean when(EObject element, ITransfo transfo);

  abstract List<EObject> retrieveRelatedElements(EObject element, ITransfo transfo);

  /**
   * Returns whether the element should be transformed by the rule or if it has been already transformed
   * @param element
   * @param transfo
   * @return
   */
  abstract boolean requireTransformation(EObject element, ITransfo transfo);

  abstract Object transform(EObject element, ITransfo transfo);

  abstract void update(EObject element, ITransfo transfo);

  abstract void attach(EObject element, ITransfo transfo) throws TransfoException;

  abstract String getDescription();

  abstract String toHtml(boolean standalone);

  /**
   * @see java.lang.Object#toString()
   */
  abstract String toString();
}
