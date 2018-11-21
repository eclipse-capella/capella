/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package ms.design;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.diagram.business.api.refresh.RefreshExtensionService;
import org.eclipse.sirius.diagram.business.internal.helper.refresh.RefreshExtensionProviderDescriptor;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;

import ms.configuration.services.cs.CsConfigurationServices;
import ms.configuration.services.cs.DiagramConstants;

public class MsRefreshExtensionProvider implements IRefreshExtensionProvider {

  private CSSRefreshExtension css;
  private AbstractMsRefreshExtension scenarioRefresh = new ScenarioMsRefreshExtension(getCSSRefreshExtension());
  private AbstractMsRefreshExtension interfaceRefresh = new InterfaceDiagramMsRefreshExtension(getCSSRefreshExtension());
  private AbstractMsRefreshExtension defaultRefresh = new DefaultMsRefreshExtension(getCSSRefreshExtension());

  @Override
  public boolean provides(DDiagram diagram) {
    return CsConfigurationServices.isConfigurationsLayerActive(diagram);
  }

  @Override
  public IRefreshExtension getRefreshExtension(DDiagram diagram) {
    IRefreshExtension refresh = defaultRefresh;
    if (DiagramDescriptionConstants.INTERFACE_SCENARIO.equals(diagram.getDescription().getName())
        || DiagramConstants.EXCHANGE_SCENARIO.equals(diagram.getDescription().getName())) {
      refresh = scenarioRefresh;
    } else if (DiagramConstants.CDI_NAME.equals(diagram.getDescription().getName())) { 
      refresh = interfaceRefresh;
    }
    return refresh;
  }

  private CSSRefreshExtension getCSSRefreshExtension() {
    if (css == null) {
      for (RefreshExtensionProviderDescriptor d : RefreshExtensionService.getInstance().getProviders()) {
        if (CSSRefreshExtension.class.getName().equals(d.getProviderClassName())){
          css = (CSSRefreshExtension) d.getProviderInstance();
        }
      }
    }
    return css;
  }

}
