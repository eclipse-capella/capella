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
package org.polarsys.capella.core.model.handler.crossreferencer;


import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ICrossReferencerProvider;

/**
 * A cross referencer provider specific to Capella M2.<br>
 * This result in an {@link ECrossReferenceAdapter} customized for Capella needs.
 */
public class CapellaCrossReferencerProvider implements ICrossReferencerProvider {

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ICrossReferencerProvider#getCrossReferencer()
   */
  public ECrossReferenceAdapter getCrossReferencer(EditingDomain editingDomain) {
    return new CapellaECrossReferenceAdapter(editingDomain);
  }

  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.ICrossReferencerProvider#getDerivedCrossReferencer()
   */
  public ECrossReferenceAdapter getDerivedCrossReferencer(EditingDomain editingDomain) {
    return new CapellaDerivedECrossReferenceAdapter(editingDomain);
  }

}
