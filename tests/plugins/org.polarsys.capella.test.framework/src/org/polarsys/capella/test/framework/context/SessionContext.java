/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 *
 */
public class SessionContext {

  /**
   * Current session for semantic resource and diagram resource.
   */
  protected Session session;

  /** Map with needed semantic Object, useful */
  private Map<String, EObject> semanticObjectMap;

  /**
   * @param session
   */
  public SessionContext(Session session) {
    this.session = session;
  }

  public Session getSession() {
    return session;
  }

  public Map<String, EObject> getSemanticObjectMap() {

    if (null == semanticObjectMap) {
      semanticObjectMap = new HashMap<String, EObject>();
    }
    return semanticObjectMap;
  }

  /**
   * Get the Capella Execution manager.
   * 
   * @return a not <code>null</code> execution manager.
   */
  public ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(session);
  }

  @SuppressWarnings("unchecked")
  public <T extends EObject> Collection<T> getSemanticElements(String... objectIdentifiers) {

    Collection<T> result = new ArrayList<T>();
    for (String value : objectIdentifiers) {
      result.add((T) getSemanticElement(value));
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public <T extends EObject> T getSemanticElement(String objectIdentifier) {

    Map<String, EObject> map = getSemanticObjectMap();
    if (!map.containsKey(objectIdentifier)) {

      EObject object = IdManager.getInstance().getEObject(objectIdentifier, new IScope() {

        @Override
        public List<Resource> getResources() {
          return session.getSemanticResources().stream().filter(CapellaResourceHelper::isCapellaResource).collect(Collectors.toList());
        }
        
      });
      putSemanticElement(objectIdentifier, object);
    }
    return (T) map.get(objectIdentifier);
  }

  public <T extends EObject> Collection<T> getSemanticElements(Collection<String> objectIdentifiers) {
    String[] identifiers = objectIdentifiers.toArray(new String[0]);
    return getSemanticElements(identifiers);
  }

  public void removeSemanticElement(String objectIdentifier) {
    getSemanticObjectMap().remove(objectIdentifier);
  }

  public void putSemanticElement(String objectIdentifier, EObject object) {
    getSemanticObjectMap().put(objectIdentifier, object);
  }

  public void setPreference(String key, boolean value) {
    AbstractPreferencesInitializer.preferencesManager.setValue(key, value);
  }

  public void setReusableComponents(String id) {
    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EObject obj = getSemanticElement(id);
        if (obj instanceof KeyValue) {
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
      @Override
      public void run() {
        EObject obj = getSemanticElement(id);
        if (obj instanceof KeyValue) {
          KeyValue kv = (KeyValue) obj;
          kv.setValue("SingletonComponents");
        }
      }
    };

    // Let's perform the job
    getExecutionManager().execute(cmd);
  }
}
