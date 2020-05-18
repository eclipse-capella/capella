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
package org.polarsys.capella.core.transition.system.topdown.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.core.flexibility.wizards.ui.CapellaPropertyPreferencePage;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;

/**
 */
public class TopDownPreferencePage extends CapellaPropertyPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.transition.preferences.ProjectionPreferencePage"; //$NON-NLS-1$

  protected IProperties properties = null;
  protected IPropertyContext context = null;
  protected IRenderers renderers = null;

  public TopDownPreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  public IProperties getProperties() {
    if (properties == null) {
      properties = new PropertiesLoader().getProperties(ITopDownConstants.OPTIONS_SCOPE__PREFERENCES);
    }

    return properties;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyContext getContext() {
    if (context == null) {
      context = new PropertyContext(getProperties());
    }
    return context;
  }

  /**
   * {@inheritDoc}
   */
  public IRenderers getRenderers() {
    if (renderers == null) {
      renderers = new RenderersLoader().getRenderers(getContext().getProperties());
    }
    return renderers;
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPageTitle() {
    return "Transitions/Generation";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPageDescription() {
    return "Transition Preferences";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IRendererContext getRendererContext() {
    return new RendererContext(getRenderers(), getContext());
  }
}
