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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 *
 */
public class SessionContext {

  /**
   * Current session for semantic resource and diagram resource.
   */
  protected Session _session;

  /** Map with needed semantic Object, useful */
  private Map<String, EObject> _semanticObjectMap;

  protected Map<String, EObject> getSemanticObjectMap() {
    if (null == _semanticObjectMap) {
      _semanticObjectMap = new HashMap<String, EObject>();
    }
    return _semanticObjectMap;
  }

  public <T extends EObject> Collection<T> getSemanticElements(String... objectIdentifiers_p) {
    Collection<T> result = new ArrayList<T>();
    for (String value : objectIdentifiers_p) {
      result.add((T) getSemanticElement(value));
    }
    return result;
  }

  public void putSemanticElement(String objectIdentifier_p, EObject object_p) {
    getSemanticObjectMap().put(objectIdentifier_p, object_p);
  }

  public <T extends EObject> T getSemanticElement(String objectIdentifier_p) {
    Map<String, EObject> map = getSemanticObjectMap();
    if (!map.containsKey(objectIdentifier_p)) {
      EObject object = IdManager.getInstance().getEObject(objectIdentifier_p, new IScope() {

        @Override
        public List<Resource> getResources() {
          Resource semanticResource = TestHelper.getSemanticResource(_session);
          return Collections.singletonList(semanticResource);
        }
      });
      map.put(objectIdentifier_p, object);
    }
    return (T) map.get(objectIdentifier_p);
  }

  /**
   * @param _session
   */
  public SessionContext(Session session) {
    _session = session;
  }

  public Session getSession() {
    return _session;
  }

  /**
   * Get the Capella Execution manager.
   * @return a not <code>null</code> execution manager.
   */
  public ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_session);
  }
}
