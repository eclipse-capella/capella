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
public class UIUtil {
  private static IDiagramServices __diagramServices = null;

  private static UIUtil __instance = null;
  private static ISelectorInPackageExplorer __selectorInPackageExplorer = null;
  private static boolean _isDialogOpen = false;

  // Log4j reference logger.
  private Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  /**
   * Close the corresponding diagram view.
   * @param element_p a model element
   */
  public void closeDiagram(ModelElement element_p) {
    if (element_p != null) {
      IDiagramServices selector = getDiagramServices();
      if (selector != null) {
        selector.closeDiagram(element_p);
      }
    }
  }

  private IDiagramServices getDiagramServices() {
    if (__diagramServices == null) {
      try {
        IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(IPlugin.ID, IPlugin.EXT_PT_DIAGRAM_SERVICES);
        for (IConfigurationElement configurationElement : configurationElements) {
          __diagramServices = (IDiagramServices) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        }
      } catch (Throwable ex) {
        __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }
    }
    return __diagramServices;
  }

  private ISelectorInPackageExplorer getSelectorInPackageExplorer() {
    if (__selectorInPackageExplorer == null) {
      try {
        IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(IPlugin.ID, IPlugin.EXT_PT_SELECT_IN_PACKAGE_EXPLORER);
        for (IConfigurationElement configurationElement : configurationElements) {
          __selectorInPackageExplorer = (ISelectorInPackageExplorer) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        }
      } catch (Throwable ex) {
        __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
      }
    }
    return __selectorInPackageExplorer;
  }

  /**
   * Open the corresponding diagram view.
   * @param element_p a model element
   */
  public void openDiagram(ModelElement element_p) {
    if (element_p != null) {
      IDiagramServices selector = getDiagramServices();
      if (selector != null) {
        selector.openDiagram(element_p);
      }
    }
  }

  /**
   * Refresh the corresponding diagram view.
   * @param diagram_p a diagram
   */
  public void refreshActiveDiagram(EObject diagram_p) {
    IDiagramServices selector = getDiagramServices();
    if (selector != null) {
      selector.refreshActiveDiagram(diagram_p);
    }
  }

  /**
   * Set the focus on the corresponding view element in the package explorer.
   * @param element_p a model element
   */
  public void selectInPackageExplorer(EObject element_p) {
    if (element_p != null) {
      ISelectorInPackageExplorer selector = getSelectorInPackageExplorer();
      if (selector != null) {
        selector.selectInPackageExplorer(element_p);
      }
    }
  }

  /**
   * Gets the unique instance of the UI util.
   * @return The UI util instance.
   */
  public static UIUtil getInstance() {
    if (__instance == null) {
      __instance = new UIUtil();
    }
    return __instance;
  }

  /**
   * @return the isDialogOpen
   */
  public static boolean isDialogOpen() {
    return _isDialogOpen;
  }

  /**
   * @param isDialogOpen_p the isDialogOpen to set
   */
  public static void setDialogOpen(boolean isDialogOpen_p) {
    _isDialogOpen = isDialogOpen_p;
  }
}
