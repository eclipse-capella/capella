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
package org.polarsys.capella.test.diagram.common.ju.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.EObjectHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 *
 */
public abstract class AbstractDiagramTestCase extends BasicTestCase {

  /** Map with needed semantic Object, useful */
  private Map<String, EObject> _semanticObjectMap;

  public Map<String, EObject> getSemanticObjectMap() {
    if (null == _semanticObjectMap) {
      _semanticObjectMap = new HashMap<String, EObject>();
    }
    return _semanticObjectMap;
  }


  public void loadSemanticObjects(Session session, Collection<String> objectIds) {
    Resource semanticResource = TestHelper.getSemanticResource(session);
    Map<String, EObject> map =
        EObjectHelper.getMatchingEObject(semanticResource.getContents().get(0), ModellingcorePackage.Literals.MODEL_ELEMENT,
            ModellingcorePackage.Literals.MODEL_ELEMENT__ID, objectIds);

    getSemanticObjectMap().putAll(map);
  }
}
