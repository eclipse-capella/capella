/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.context;

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
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.data.capellacore.KeyValue;
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

  public Map<String, EObject> getSemanticObjectMap() {
    if (null == _semanticObjectMap) {
      _semanticObjectMap = new HashMap<String, EObject>();
    }
    return _semanticObjectMap;
  }

  public <T extends EObject> Collection<T> getSemanticElements(String... objectIdentifiers) {
    Collection<T> result = new ArrayList<T>();
    for (String value : objectIdentifiers) {
      result.add((T) getSemanticElement(value));
    }
    return result;
  }

  public void removeSemanticElement(String objectIdentifier) {
    getSemanticObjectMap().remove(objectIdentifier);
  }
  
  public void putSemanticElement(String objectIdentifier, EObject object) {
    getSemanticObjectMap().put(objectIdentifier, object);
  }

  public <T extends EObject> T getSemanticElement(String objectIdentifier) {
    Map<String, EObject> map = getSemanticObjectMap();
    if (!map.containsKey(objectIdentifier)) {
      EObject object = IdManager.getInstance().getEObject(objectIdentifier, new IScope() {

        @Override
        public List<Resource> getResources() {
          Resource semanticResource = TestHelper.getSemanticResource(_session);
          return Collections.singletonList(semanticResource);
        }
      });
      putSemanticElement(objectIdentifier, object);
    }
    return (T) map.get(objectIdentifier);
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
   * 
   * @return a not <code>null</code> execution manager.
   */
  public ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_session);
  }

  public void setPreference(String key, boolean value) {
    AbstractPreferencesInitializer.preferencesManager.setValue(key, value);
  }
  
  public void setReusableComponents(String id) {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        EObject obj = getSemanticElement(id);
        if(obj instanceof KeyValue) {
          KeyValue kv = (KeyValue) obj;
          kv.setValue("ReusableComponents");
        }
      }
    };

    // Let's perform the job
    getExecutionManager().execute(cmd);
  }
  
  public void setSingletonComponents(String id) {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        EObject obj = getSemanticElement(id);
        if(obj instanceof KeyValue) {
          KeyValue kv = (KeyValue) obj;
          kv.setValue("SingletonComponents");
        }
      }
    };

    // Let's perform the job
    getExecutionManager().execute(cmd);
  }
}
