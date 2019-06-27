/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.navigator.ju;

import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.platform.sirius.ui.navigator.filters.FilterManager;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.toolkit.helpers.UserKnowledgeHelper;
import org.polarsys.capella.test.navigator.ju.model.NavigatorEmptyProject;

/**
 * This class instanciates all filters on Capella Project Explorer to detect if some filters classes have been deleted
 * without removing the filter
 */
public class PartIcon extends NavigatorEmptyProject {

  String [] initialFilters = null;
  
  @Override
  public void test() throws Exception {
    // Try to instantiate filters using a class
    boolean exist = false;
    for (IConfigurationElement element : Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.eclipse.ui.navigator.navigatorContent")) {
      if ("commonFilter".equals(element.getName())) {
        if (FilterManager.PART_FILTER.equals(element.getAttribute("id"))) {
          exist = true;
          break;
        }
      }
    }
    CommonNavigator navigator = getNavigator();
    assertTrue(NLS.bind("Filter {0} has been deleted.", FilterManager.PART_FILTER), exist);

    Part part = ComponentExt.getRepresentingParts(LA_LOGICAL_SYSTEM).iterator().next();

    initialFilters = Arrays.asList(navigator.getNavigatorContentService().getFilterService().getVisibleFilterDescriptors()).stream().map(x -> x.getId()).collect(Collectors.toList()).toArray(new String[0]);
    
    assertTrue("'Part' is filtered by default",
        navigator.getNavigatorContentService().getFilterService().isActive(FilterManager.PART_FILTER));

    updateApproach(ProjectApproach.SingletonComponents, part);
    showParts(navigator);
    assertTrue("'Part' is displayed, so on understood", UserKnowledgeHelper.isHandlingParts(part));
    assertTrue("Part icon is decorated Component icon", hasDecoratedComponentIcon(part));

    hideParts(navigator);
    assertTrue("'Part' is hidden, so on not understood", !UserKnowledgeHelper.isHandlingParts(part));
    assertTrue("Part icon is Component icon", hasComponentIcon(part));
    
    updateApproach(ProjectApproach.ReusableComponents, part);
    assertTrue("Multipart enabled, so on understood", UserKnowledgeHelper.isHandlingParts(part));
    assertTrue("Part icon is decorated Component icon, whether hidden or not", hasDecoratedComponentIcon(part));
    
  }
  
  private CommonNavigator getNavigator() {
    return (CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .findView(CapellaCommonNavigator.ID);
    
  }
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    
    if (initialFilters != null) {
      CommonNavigator navigator = getNavigator();
      navigator.getNavigatorContentService().getFilterService().activateFilterIdsAndUpdateViewer(initialFilters);
      navigator.getNavigatorContentService().getFilterService().persistFilterActivationState();
    }
  }
  private boolean hasDecoratedComponentIcon(Part part) {
    AdapterFactory adapterFactory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
    IItemLabelProvider partProvider = (IItemLabelProvider)adapterFactory.adapt(part, IItemLabelProvider.class);
    IItemLabelProvider cptProvider = (IItemLabelProvider)adapterFactory.adapt(part.getAbstractType(), IItemLabelProvider.class);
    ComposedImage ipart = (ComposedImage) partProvider.getImage(part);
    URL icpt = (URL)cptProvider.getImage(part.getAbstractType());
    return ((URL)ipart.getImages().get(0)).equals(icpt);
  }
  
  private boolean hasComponentIcon(Part part) {
    AdapterFactory adapterFactory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
    IItemLabelProvider partProvider = (IItemLabelProvider)adapterFactory.adapt(part, IItemLabelProvider.class);
    IItemLabelProvider cptProvider = (IItemLabelProvider)adapterFactory.adapt(part.getAbstractType(), IItemLabelProvider.class);
    URL ipart = (URL) partProvider.getImage(part);
    URL icpt = (URL)cptProvider.getImage(part.getAbstractType());
    return ipart.equals(icpt);
  }
  
  private void showParts(CommonNavigator navigator) {
    navigator.getNavigatorContentService().getFilterService().activateFilterIdsAndUpdateViewer(new String[] {});
    navigator.getNavigatorContentService().getFilterService().persistFilterActivationState();
  }
  
  private void hideParts(CommonNavigator navigator) {
    navigator.getNavigatorContentService().getFilterService().activateFilterIdsAndUpdateViewer(new String[] {FilterManager.PART_FILTER});
    navigator.getNavigatorContentService().getFilterService().persistFilterActivationState();
  }
  
  private void updateApproach(ProjectApproach approach, EObject source) {
    TransactionHelper.getExecutionManager(source).execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        CapellaProjectHelper.setProjectWithApproach(ProjectExt.getProject(source), approach);

      }
    });
  }
}
