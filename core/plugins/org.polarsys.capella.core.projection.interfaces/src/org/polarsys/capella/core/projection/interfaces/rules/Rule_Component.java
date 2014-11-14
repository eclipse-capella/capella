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
package org.polarsys.capella.core.projection.interfaces.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.projection.common.AbstractRule;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;

/**
 * A rule to load COMPONENT_ALLOCATION transfo link
 */
public class Rule_Component extends AbstractRule {

  /**
   * @param sourceType_p
   * @param targetType_p
   * @param specificLinkKind_p
   */
  public Rule_Component() {
    super(CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT_ALLOCATION);
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractRule#doGoDeep(org.eclipse.emf.ecore.EObject, java.util.List)
   */
  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
    return new ArrayList<EObject>();
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    //Nothing to do
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public Object transform_(EObject element_p, ITransfo transfo_p) {
    return null;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractRule#doGoDeep(org.eclipse.emf.ecore.EObject, java.util.List)
   */
  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {
    //Nothing to do
  }
  
}
