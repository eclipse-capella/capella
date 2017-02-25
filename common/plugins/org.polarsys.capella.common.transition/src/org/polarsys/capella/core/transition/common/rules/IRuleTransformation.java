/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.rules;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public interface IRuleTransformation {

  /**
   * Returns for the given element whether the transposer apply method should be called
   */
  IStatus applyRequired(EObject element, IContext context);

  /**
   * Returns for the given element whether a transformation is required (already transformed or not according the given context)
   */
  IStatus transformRequired(EObject source, IContext context);

  EClass getTargetType(EObject element, IContext context);

}
