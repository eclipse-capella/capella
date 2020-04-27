/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.ui.sirius.SiriusScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;


/**
 * A factory for comparison scopes within Capella models.
 */
public class CapellaScopeFactory extends SiriusScopeDefinitionFactory {
  
  /**
   * A scope definition for Capella.
   */
  public static class CapellaScopeDefinition extends SiriusScopeDefinition {
    /**
     * Constructor
     * @param uri_p a non-null URI
     * @param label_p an optional label
     * @param editable_p whether the scope can be edited
     */
    public CapellaScopeDefinition(URI uri_p, String label_p, boolean editable_p) {
      super(uri_p, label_p, editable_p);
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#createScopeOnEditingDomain(org.eclipse.emf.edit.domain.EditingDomain)
     */
    @Override
    protected IEditableModelScope createScopeOnEditingDomain(EditingDomain domain) {
      return new CapellaScope(getEntrypoint(), domain, !isEditable());
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#createScopeOnResourceSet(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    @Override
    protected IEditableModelScope createScopeOnResourceSet(ResourceSet resourceSet) {
      return new CapellaScope(getEntrypoint(), resourceSet, !isEditable());
    }
    
    /**
     * Please note that it's up to the caller of this method to dispose the EditingDomain created when the session is
     * null.
     * 
     * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#getDefaultContext()
     */
    @Override
    protected Object getDefaultContext() {
      Object result;
      URI uri = getEntrypoint();
      Session session = SessionManager.INSTANCE.getExistingSession(uri);
      if (session != null) {
        result = super.getDefaultContext();
      } else {
        ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
        result = manager.getEditingDomain();
      }
      return result;
    }
  }
  
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusScopeDefinitionFactory#createScopeDefinitionFromURI(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
   */
  @Override
  protected IModelScopeDefinition createScopeDefinitionFromURI(URI uri, String label,
      boolean editable) {
    return new CapellaScopeDefinition(uri, label, editable);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusScopeDefinitionFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(SiriusScopeDefinitionFactory.class);
  }
  
}
