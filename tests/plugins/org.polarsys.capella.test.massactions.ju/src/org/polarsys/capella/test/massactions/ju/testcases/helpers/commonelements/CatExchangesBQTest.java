/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.testcases.helpers.commonelements;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.CommonElementsHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.test.massactions.ju.model.AbstractCapellaMATestCase;

/**
 * 
 * @author Sandu Postaru
 *
 */
public class CatExchangesBQTest extends AbstractCapellaMATestCase {

  private static final String FEATURE_NAME = "exchanges";

  @Override
  public void performTest() throws Exception {

    EObject cat = getObject(CAT_DISPLAYED_IMPOSED_VIDEO_DATA);
    EStructuralFeature feature = getFeatureByName(cat, FEATURE_NAME).get();

    IBusinessQuery query = CommonElementsHelper.getBusinessQuery(feature);
    assertNotNull(query);

    Set<EObject> availableElements = new HashSet<>(query.getAvailableElements(cat));
    assertFalse(availableElements.isEmpty());

    Set<EObject> currentElements = new HashSet<>(query.getCurrentElements(cat, false));
    assertFalse(currentElements.isEmpty());
  }

}
