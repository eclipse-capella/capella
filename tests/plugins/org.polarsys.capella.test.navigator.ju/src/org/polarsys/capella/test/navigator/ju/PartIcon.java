/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.navigator.ju;

import java.net.URL;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;
import org.eclipse.ui.navigator.CommonNavigator;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.navigator.ju.model.NavigatorEmptyProject;

/**
 * This class instanciates all filters on Project Explorer to detect if some filters classes have been deleted
 * without removing the filter
 */
public class PartIcon extends NavigatorEmptyProject {

  @Override
  public void test() throws Exception {
    // Try to instantiate filters using a class

    Part part = ComponentExt.getRepresentingParts(LOGICAL_SYSTEM).iterator().next();
    
    updateApproach(ProjectApproach.SingletonComponents, part);
    assertTrue("Part icon is decorated Component icon", hasDecoratedComponentIcon(part));
    
    updateApproach(ProjectApproach.ReusableComponents, part);
    assertTrue("Part icon is decorated Component icon", hasDecoratedComponentIcon(part));
    
    NavigatorDecoratingLabelProvider b = ((NavigatorDecoratingLabelProvider)getNavigator().getCommonViewer().getLabelProvider());
    
    int style = b.getFont(part).getFontData()[0].getStyle();
    assertTrue("Part label is italic", style == SWT.ITALIC);
    
    removePartType(part);
    assertTrue("Part icon shall be the default one", hasPartIcon(part));
    
  }
  
  private CommonNavigator getNavigator() {
    return (CommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .findView(CapellaCommonNavigator.ID);
  }

  private boolean hasPartIcon(Part part) {
    AdapterFactory adapterFactory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
    IItemLabelProvider partProvider = (IItemLabelProvider)adapterFactory.adapt(part, IItemLabelProvider.class);
    return partProvider.getImage(part) instanceof URL;
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
  
  private void updateApproach(ProjectApproach approach, EObject source) {
    TransactionHelper.getExecutionManager(source).execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        CapellaProjectHelper.setProjectWithApproach(ProjectExt.getProject(source), approach);

      }
    });
  }
  
  private void removePartType(Part source) {
    TransactionHelper.getExecutionManager(source).execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        source.setAbstractType(null);
      }
    });
  }
}
