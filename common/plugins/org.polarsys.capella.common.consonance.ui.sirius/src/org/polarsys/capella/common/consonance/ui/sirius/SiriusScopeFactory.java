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
package org.polarsys.capella.common.consonance.ui.sirius;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.helper.SiriusUtil;



/**
 * A factory for comparison scopes within Viewpoint models.
 */
public class SiriusScopeFactory extends GMFScopeDefinitionFactory {
  
  /** The file extension for AIRD fragments */
  public static final String SESSION_RESOURCE_FRAGMENT_EXTENSION = "airdfragment"; //$NON-NLS-1$
  
  /** The set of supported file extensions */
  public static final Collection<String> VIEWPOINT_FILE_EXTENSIONS = Arrays.asList(
      SiriusUtil.SESSION_RESOURCE_EXTENSION,
      SESSION_RESOURCE_FRAGMENT_EXTENSION);
  
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory#createScopeDefinitionFromURI(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
   */
  @Override
  protected IModelScopeDefinition createScopeDefinitionFromURI(URI uri, String label,
      boolean editable) {
    return new URIScopeDefinition(uri, label, editable) {
      /**
       * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#createScopeOnEditingDomain(org.eclipse.emf.edit.domain.EditingDomain)
       */
      @Override
      protected IEditableModelScope createScopeOnEditingDomain(EditingDomain editingDomain) {
        return new SiriusScope(getEntrypoint(), editingDomain, !isEditable());
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(GMFScopeDefinitionFactory.class);
  }
  
}
