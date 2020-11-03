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


package org.polarsys.capella.common.ui.services;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * 
 */
@Deprecated
public class UIUtil {
  private static IDiagramServices diagramServices = null;

  private static UIUtil instance = null;
  private static ISelectorInPackageExplorer selectorInPackageExplorer = null;
  private static boolean isDialogOpen = false;

  // Log4j reference logger.
  private Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /**
   * Close the corresponding diagram view.
   * @param element a model element
   */
  public void closeDiagram(ModelElement element) {
    if (element != null) {
      IDiagramServices selector = getDiagramServices();
      if (selector != null) {
        selector.closeDiagram(element);
      }
    }
  }

  private IDiagramServices getDiagramServices() {
    if (diagramServices == null) {
      try {
        IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(IPlugin.ID, IPlugin.EXT_PT_DIAGRAM_SERVICES);
        for (IConfigurationElement configurationElement : configurationElements) {
          diagramServices = (IDiagramServices) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        }
      } catch (Exception ex) {
        logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }
    }
    return diagramServices;
  }

  private ISelectorInPackageExplorer getSelectorInPackageExplorer() {
    if (selectorInPackageExplorer == null) {
      try {
        IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(IPlugin.ID, IPlugin.EXT_PT_SELECT_IN_PACKAGE_EXPLORER);
        for (IConfigurationElement configurationElement : configurationElements) {
          selectorInPackageExplorer = (ISelectorInPackageExplorer) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        }
      } catch (Exception ex) {
        logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }
    }
    return selectorInPackageExplorer;
  }

  /**
   * Open the corresponding diagram view.
   * @param element a model element
   */
  public void openDiagram(ModelElement element) {
    if (element != null) {
      IDiagramServices selector = getDiagramServices();
      if (selector != null) {
        selector.openDiagram(element);
      }
    }
  }

  /**
   * Refresh the corresponding diagram view.
   * @param diagram a diagram
   */
  public void refreshActiveDiagram(EObject diagram) {
    IDiagramServices selector = getDiagramServices();
    if (selector != null) {
      selector.refreshActiveDiagram(diagram);
    }
  }

  /**
   * Set the focus on the corresponding view element in the package explorer.
   * @param element a model element
   */
  public void selectInPackageExplorer(EObject element) {
    if (element != null) {
      ISelectorInPackageExplorer selector = getSelectorInPackageExplorer();
      if (selector != null) {
        selector.selectInPackageExplorer(element);
      }
    }
  }

  /**
   * Gets the unique instance of the UI util.
   * @return The UI util instance.
   */
  public static UIUtil getInstance() {
    if (instance == null) {
      instance = new UIUtil();
    }
    return instance;
  }

  /**
   * @return the isDialogOpen
   */
  public static boolean isDialogOpen() {
    return isDialogOpen;
  }

  /**
   * @param isDialogOpen the isDialogOpen to set
   */
  public static void setDialogOpen(boolean isOpen) {
    isDialogOpen = isOpen;
  }
}
