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
public class PropSeatBQTest extends AbstractCapellaMATestCase {

  private static final String FEATURE_NAME = "abstractType";

  @Override
  public void performTest() throws Exception {

    EObject prop = getObject(PROP_SEAT);
    EStructuralFeature feature = getFeatureByName(prop, FEATURE_NAME).get();

    IBusinessQuery query = CommonElementsHelper.getBusinessQuery(feature);
    assertNotNull(query);

    // TODO complete with actual expected results, for the moment the query is null and the test fails
  }

}
