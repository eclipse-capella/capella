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
