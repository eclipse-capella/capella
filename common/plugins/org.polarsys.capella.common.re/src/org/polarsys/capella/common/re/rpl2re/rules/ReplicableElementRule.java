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
package org.polarsys.capella.common.re.rpl2re.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReplicableElementRule extends org.polarsys.capella.common.re.rules.ReplicableElementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveCurrent(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveCurrent(source_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveRootElement(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveRootElement(source_p, result_p, context_p);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);


  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

}
