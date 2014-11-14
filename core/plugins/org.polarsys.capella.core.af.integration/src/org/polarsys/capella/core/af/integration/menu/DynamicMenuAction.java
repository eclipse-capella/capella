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
package org.polarsys.capella.core.af.integration.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.polarsys.capella.core.af.integration.Activator;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Service;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Viewpoint;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.edit.provider.ServiceItemProvider;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.edit.provider.ViewpointItemProvider;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.edit.provider.ViewpointItemProviderAdapterFactory;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.tools.model.ViewpointElement;
import org.polarsys.kitalpha.ad.viewpoint.handlers.ModelManager;
import org.polarsys.kitalpha.ad.viewpoint.integration.services.Implementations;
import org.polarsys.kitalpha.ad.viewpoint.integration.services.ServiceImplementation;
import org.polarsys.kitalpha.ad.viewpoint.ui.provider.AFSelectionProvider;
import org.polarsys.kitalpha.ad.viewpoint.utils.ModelAccessor;
import org.polarsys.kitalpha.resourcereuse.model.Resource;

/**
 * 
 */
public class DynamicMenuAction implements IMenuCreator, IObjectActionDelegate {
  private static final String MGR = "mgr";
  private static final String ACTION = "action";
  private Shell shell;
  private IWorkbenchPartSite site;
  private AFSelectionProvider selectionProvider = AFSelectionProvider.INSTANCE.getSelectionProvider(AFSelectionProvider.DEFAULT_PROVIDER_ID);
  private SelectionListener listener = new SelectionListener() {

    public void widgetSelected(SelectionEvent ee) {
      try {
        MenuItem menuItem = (MenuItem) ee.getSource();
        Service action = (Service) menuItem.getData(ACTION);
        ModelManager vpMgr = (ModelManager) menuItem.getData(MGR);

        ServiceImplementation impl = Implementations.getInstance(action);
        if (impl == null) {
          MessageDialog.openError(shell, "Error with service", "An error occured while loading viewpoint service:" + action.getId());
        } else {
          impl.run(action, new ModelAccessor(vpMgr), selectionProvider.getSelection().toArray());
        }

      } catch (Exception e) {
        MessageDialog.openError(shell, "Error with service", "An error occured while loading viewpoint service");
        Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getSymbolicName(), "Error with service", e));
      }
    }

    public void widgetDefaultSelected(SelectionEvent e) {

    }
  };

  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    site = targetPart.getSite();

    shell = site.getShell();
    action.setMenuCreator(this);
  }

  public void dispose() {
  }

  public Menu getMenu(Control parent) {
    return null;
  }

  public Menu getMenu(Menu parent) {
    final Menu dynamicMenu = new Menu(parent);

    dynamicMenu.addListener(SWT.Show, new Listener() {

      public void handleEvent(Event event) {
        if (!event.widget.isDisposed()) {
          for (MenuItem item : dynamicMenu.getItems()) {
            item.dispose();
          }
          fillMenu(dynamicMenu);
        }
      }
    });
    return dynamicMenu;
  }

  private void fillMenu(Menu dynamicMenu) {

    Map<Viewpoint, ModelManager> vp2mgr = new LinkedHashMap<Viewpoint, ModelManager>();
    Map<Viewpoint, List<ViewpointElement>> vp2elt = new HashMap<Viewpoint, List<ViewpointElement>>();

    computeActiveServices(vp2mgr, vp2elt);

    ViewpointItemProviderAdapterFactory adapterFactory = new ViewpointItemProviderAdapterFactory();
    ViewpointItemProvider provider = (ViewpointItemProvider) adapterFactory.createViewpointAdapter();
    Object url = provider.getImage((Object) null);

    Image vpImage = ExtendedImageRegistry.getInstance().getImage(url);
    url = ((ServiceItemProvider) adapterFactory.createServiceAdapter()).getImage((Object) null);
    Image actionImage = ExtendedImageRegistry.getInstance().getImage(url);

    for (Viewpoint vp : vp2mgr.keySet()) {
      ModelManager vpMgr = vp2mgr.get(vp);
      if (!vp2elt.containsKey(vp)) {
        continue;
      }
      MenuItem mi = new MenuItem(dynamicMenu, SWT.CASCADE);
      mi.setImage(vpImage);
      mi.setText(vp.getName());
      mi.setData(vp);
      Menu subMenu = new Menu(dynamicMenu);
      mi.setMenu(subMenu);

      for (ViewpointElement elt : vp2elt.get(vp)) {
        MenuItem smi = new MenuItem(subMenu, SWT.PUSH);
        smi.setText(elt.getName() == null ? "no name" : elt.getName());
        smi.setData(ACTION, elt);
        smi.setData(MGR, vpMgr);
        smi.addSelectionListener(listener);
        smi.setImage(actionImage);
      }
    }

  }

  public Viewpoint[] getAvailableViewpoints() {
    List<Viewpoint> vps = new ArrayList<Viewpoint>();
    Resource[] resources = ViewpointManager.INSTANCE.getAvailableViewpoints();

    ResourceSet set = new ResourceSetImpl();
    for (Resource resource : resources) {
      String path2 = resource.getPath();
      if ((path2 == null) || "".equals(path2)) {
        continue;
      }
      URI uri = URI.createPlatformPluginURI(path2, false);
      Viewpoint vp = (Viewpoint) set.getEObject(uri, true);
      vps.add(vp);
    }

    Viewpoint[] array = vps.toArray(new Viewpoint[vps.size()]);
    return array;
  }

  private void computeActiveServices(Map<Viewpoint, ModelManager> vp2mgr, Map<Viewpoint, List<ViewpointElement>> vp2elt) {
    for (Viewpoint vp : sort(getAvailableViewpoints())) {
      if (vp.isAbstract() || !ViewpointManager.INSTANCE.isActive(vp.getId())) {
        continue;
      }
      ModelManager vpMgr = ModelManager.createViewpointManager(vp);
      vp2mgr.put(vp, vpMgr);
      for (ViewpointElement elt : sort(vpMgr.getServiceHandler().getElements())) {
        try {
          Service action = (Service) elt;
          ServiceImplementation impl = Implementations.getInstance(action);
          if ((impl != null) && impl.canRun(action, new ModelAccessor(vpMgr), selectionProvider.getSelection().toArray())) {
            List<ViewpointElement> list = vp2elt.get(vp);
            if (list == null) {
              list = new ArrayList<ViewpointElement>();
              vp2elt.put(vp, list);
            }
            list.add(elt);
          }
        } catch (Exception e1) {
          Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getSymbolicName(), "Error with service: " + ((Service) elt).getId(), e1));
        }
      }
    }
  }

  private Viewpoint[] sort(Viewpoint[] vps) {
    Arrays.sort(vps, new Comparator<Viewpoint>() {

      public int compare(Viewpoint arg0, Viewpoint arg1) {
        String name0 = arg0.getName() == null ? "" : arg0.getName();
        String name1 = arg1.getName() == null ? "" : arg1.getName();

        return name0.compareTo(name1);
      }
    });
    return vps;
  }

  private List<ViewpointElement> sort(List<ViewpointElement> elements) {
    Collections.sort(elements, new Comparator<ViewpointElement>() {

      public int compare(ViewpointElement arg0, ViewpointElement arg1) {
        String name0 = arg0.getName() == null ? "" : arg0.getName();
        String name1 = arg1.getName() == null ? "" : arg1.getName();

        return name0.compareTo(name1);
      }
    });
    return elements;
  }

  public void run(IAction action) {
  }

  public void selectionChanged(IAction action, ISelection selection) {
  }

  public DynamicMenuAction() {
    super();
  }

}
