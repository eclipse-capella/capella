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
package org.polarsys.capella.core.projection.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 */
@Deprecated
public abstract class AbstractRule extends TransfoRule {
  protected ITransfo _transfo;

  protected ITransfo getTransfo() {
    return _transfo;
  }

  /**
   * Constructor.
   */
  public AbstractRule(EClass sourceType_p, EClass targetType_p, EClass specificLinkKind_p) {
    super(sourceType_p, targetType_p, specificLinkKind_p);
  }

  /**
   * Constructor.
   */
  public AbstractRule(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#retrieveRelatedElements_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
    _transfo = transfo_p;
    List<EObject> result = new ArrayList<EObject>(0);
    // By default always add container.
    doAddContainer(element_p, result);
    // Get transformation nature.
    Object startEltTransfo = transfo_p.get(TransfoEngine.TRANSFO_SOURCE);
    if (startEltTransfo instanceof EObject) {
      if (!EcoreUtil2.isContainedBy((EObject) startEltTransfo, element_p)) {
        doGoDeep(element_p, result);
      }
    }
    return result;
  }

  /**
   * Go deep into the element tree from specified element.
   * @param element_p
   * @param result
   */
  protected abstract void doGoDeep(EObject element_p, List<EObject> result);

  /**
   * Default implementation adds element container to resulting list.
   * @param element_p
   * @param result_p
   */
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    result_p.add(element_p.eContainer());
  }
}
