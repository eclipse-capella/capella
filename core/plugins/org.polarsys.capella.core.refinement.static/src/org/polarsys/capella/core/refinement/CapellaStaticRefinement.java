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
package org.polarsys.capella.core.refinement;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.common.helpers.ExtensionPriorityComparator;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.scenarios.core.StaticRefinement;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;

/**
 */
public class CapellaStaticRefinement extends StaticRefinement {

  private static final String REFINEMENT_FRAMEWORK_PLUGIN_ID = "org.polarsys.capella.core.refinement.framework"; //$NON-NLS-1$
  private static final String STATIC_REFINEMENT_EXTENSION_ID = "staticRefinementExtension"; //$NON-NLS-1$
  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  public CapellaStaticRefinement(NamedElement context_p) {
    super();

    Comparator<IConfigurationElement> priorityComparator = new ExtensionPriorityComparator();

    List<IConfigurationElement> staticRefinementProvider =
        Arrays.asList(ExtensionPointHelper.getConfigurationElements(REFINEMENT_FRAMEWORK_PLUGIN_ID, STATIC_REFINEMENT_EXTENSION_ID));
    Collections.sort(staticRefinementProvider, priorityComparator);
    for (IConfigurationElement configurationElement : staticRefinementProvider) {
      /** logging */
      String loggedMsg = MessageFormat.format("new staticRefinementProvider : \"{0}\".", configurationElement.getAttribute(ExtensionPointHelper.ATT_ID)); //$NON-NLS-1$
      _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, context_p));
      /** */
      IProcessor processor = (IProcessor) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      if (processor != null) {
        processor.setContext(context_p);

        addPlug(processor);
      }
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getName()
   */
  public Object getName() {
    return null;
  }
}
