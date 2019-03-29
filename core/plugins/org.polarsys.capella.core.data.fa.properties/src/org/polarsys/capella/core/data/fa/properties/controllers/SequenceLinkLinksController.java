/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

public class SequenceLinkLinksController extends AbstractMultipleSemanticFieldController {

  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {

    BusinessQueriesProvider provider = BusinessQueriesProvider.getInstance();
    EReference linksReference = FaPackage.Literals.SEQUENCE_LINK__LINKS;

    IBusinessQuery contribution = provider.getContribution(semanticElement.eClass(), linksReference);

    return contribution;
  }
}
