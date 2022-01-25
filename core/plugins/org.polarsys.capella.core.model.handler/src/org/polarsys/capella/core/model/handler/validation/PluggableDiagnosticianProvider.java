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
package org.polarsys.capella.core.model.handler.validation;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.util.Diagnostician;

import org.polarsys.capella.core.model.handler.ModelHandlerPlugin;

/**
 * Provides diagnosticians by searching the extension registry for DiagnosticianProviders 
 * registered via the 'diagnosticianProvider' extension point of this plug-in. The current 
 * notion is, that if there's exactly one extension that contributes to this extension point, 
 * its providers Diagnostician will be used. Otherwise we fall back to using the defaule
 * CapellaDiagnosticion. Future implementations might provide choosing a diagnostician, 
 * e.g. via preferences or some sort of priority system...
 */
public class PluggableDiagnosticianProvider extends AbstractDiagnosticianProvider {

  public static final String DIAGNOSTICIAN_PROVIDER_EXTENSION = ModelHandlerPlugin.PLUGIN_ID + ".diagnosticianProviders"; //$NON-NLS-1$

  @Override
  public Diagnostician getDiagnostician(AdapterFactory adapterFactory_p, IProgressMonitor progressMonitor_p) {
    Diagnostician result = null;
    IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(DIAGNOSTICIAN_PROVIDER_EXTENSION);
    if (null != elems && elems.length == 1) {
      try {
        AbstractDiagnosticianProvider provider = (AbstractDiagnosticianProvider) elems[0].createExecutableExtension("class"); //$NON-NLS-1$
        result = provider.getDiagnostician(adapterFactory_p, progressMonitor_p);
      } catch (CoreException e1) {
        Platform.getLog(ModelHandlerPlugin.class).log(new Status(IStatus.ERROR, ModelHandlerPlugin.PLUGIN_ID, e1.getMessage(), e1));
      }
    }
    if (null == result) {
      result = new CapellaDiagnostician(adapterFactory_p, progressMonitor_p);
    }
    return result;
  }
}
