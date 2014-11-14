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
package org.polarsys.capella.core.transition.common.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface IRuleAttachment {

  public EObject _getBestContainer(EObject element_p, EObject result_p, IContext context_p);

  public EObject _getDefaultContainer(EObject element_p, EObject result_p, IContext context_p);

  public EStructuralFeature _getSourceContainementFeature(EObject element_p, EObject result_p, IContext context_p);

  public EStructuralFeature _getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p);

}
