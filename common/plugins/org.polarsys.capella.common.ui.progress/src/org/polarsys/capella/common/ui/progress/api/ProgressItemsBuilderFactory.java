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
package org.polarsys.capella.common.ui.progress.api;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.progress.Activator;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * This factory allows to build a platform specific progress items factory if the convenient platform plug-in is enabled.
 */
public class ProgressItemsBuilderFactory {
  /**
   * The single instance of the factory.
   */
  private static IProgressItemsBuilder BUILDER_FACTORY = null;

  private static Logger LOGGER = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /**
   * Gets the current platform specific builder factory if there is any.
   * @return an instance of a class implementing IProgressItemsBuilder if there is such class, null otherwise
   */
  public static IProgressItemsBuilder getProgressItemsBuilder() {
    if (BUILDER_FACTORY == null) {
      IConfigurationElement[] progressItemsBuilders =
          ExtensionPointHelper.getConfigurationElements(Activator.PLUGIN_ID, IProgressItemsBuilder.EXTENSION_POINT_ID);
      for (IConfigurationElement configurationElement : progressItemsBuilders) {
        String providerId = configurationElement.getAttribute(ExtensionPointHelper.ATT_ID);
        // Logs out a debug message about the deployed platform shell handler
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug(Messages.getString("ProgressItemsBuilderFactory.DealingWithProgressItemsBuilder") + providerId); //$NON-NLS-1$
        }
        IProgressItemsBuilder factory = (IProgressItemsBuilder) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (null != factory) {
          BUILDER_FACTORY = factory;
        }
      }
    }
    return BUILDER_FACTORY;
  }
}
