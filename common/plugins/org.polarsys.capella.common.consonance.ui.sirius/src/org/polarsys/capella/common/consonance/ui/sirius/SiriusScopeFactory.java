/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.ui.gmf.GMFScopeFactory;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.emf.diffmerge.ui.specification.ext.FileScopeSpecification;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.helper.SiriusUtil;


/**
 * A factory for comparison scopes within Viewpoint models.
 */
public class SiriusScopeFactory extends GMFScopeFactory {
  
  /** The file extension for AIRD fragments */
  public static final String SESSION_RESOURCE_FRAGMENT_EXTENSION = "airdfragment"; //$NON-NLS-1$
  
  /** The set of supported file extensions */
  public static final Collection<String> VIEWPOINT_FILE_EXTENSIONS = Arrays.asList(
      SiriusUtil.SESSION_RESOURCE_EXTENSION,
      SESSION_RESOURCE_FRAGMENT_EXTENSION);
  
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.FileScopeSpecificationFactory#createScopeSpecificationFromUri(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
   */
  @Override
  protected IScopeSpecification createScopeSpecificationFromUri(URI uri_p, String label_p,
      boolean editable_p) {
    return new FileScopeSpecification(uri_p, label_p, editable_p) {
      /**
       * @see org.eclipse.emf.diffmerge.ui.specification.ext.FileScopeSpecification#createScope(org.eclipse.emf.edit.domain.EditingDomain)
       */
      @Override
      public IFeaturedModelScope createScope(EditingDomain domain_p) {
        return new SiriusScope(getEntrypointResource(domain_p));
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeSpecificationFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(GMFScopeFactory.class);
  }
  
}
