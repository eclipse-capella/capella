/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.compare;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.ui.sirius.SiriusScopeFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory;


/**
 * A factory for comparison scopes within Capella models.
 */
public class CapellaScopeFactory extends SiriusScopeFactory {
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusScopeFactory#createScopeDefinitionFromURI(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
   */
  @Override
  protected IModelScopeDefinition createScopeDefinitionFromURI(URI uri, String label,
      boolean editable) {
    return new URIScopeDefinition(uri, label, editable) {
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
       * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#getDefaultContext()
       */
      @Override
      protected Object getDefaultContext() {
        SemanticEditingDomainFactory factory = new SemanticEditingDomainFactory();
        EditingDomain result = factory.createEditingDomain();
        return result;
      }
    };
  }
  
  /**
   * @see org.polarsys.capella.common.consonance.ui.sirius.SiriusScopeFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(SiriusScopeFactory.class);
  }
  
}
