/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.showhide;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;

/**
 *
 */
interface IShowHide {

  Collection<DSemanticDecorator> showWithResult(EObject semantic, DiagramContext context);

  void show(EObject semantic, DiagramContext context);

  void hide(EObject semantic, DiagramContext context);

}
