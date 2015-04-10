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
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 *
 */
public class DiagramCreationExecutionContext extends BasicExecutionContext {

  /**
   * The semantic element.
   */
  protected EObject _semanticElement;

  /**
   * The {@link RepresentationDescription} to create
   */
  protected RepresentationDescription _representationDescription;

  /**
   * The name of the {@link RepresentationDescription} to create
   */
  protected String _representationName;

  /**
   * @param _session
   */
  public DiagramCreationExecutionContext(Session session, EObject semanticElement, String representationName, RepresentationDescription representationDescription) {
    super(session);
    _semanticElement = semanticElement;
    _representationName = representationName;
    _representationDescription = representationDescription;
  }

  public EObject getSemanticElement() {
    return _semanticElement;
  }

  public RepresentationDescription getRepresentationDescription() {
    return _representationDescription;
  }
}
