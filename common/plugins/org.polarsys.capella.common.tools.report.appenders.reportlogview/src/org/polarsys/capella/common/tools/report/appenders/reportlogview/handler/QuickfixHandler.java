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
package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerHelpRegistry;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.Messages;

/**
 */
public class QuickfixHandler extends AbstractDynamicContributionItem {

  public static final String PL_MARKER_RESOLUTION = "org.eclipse.ui.ide.markerResolution"; //$NON-NLS-1$

  private final String menuId = "org.polarsys.capella.core.validation.quickfix"; //$NON-NLS-1$
  private final String menuImage = "quickfix.gif"; //$NON-NLS-1$

  public static IMarkerHelpRegistry getMarkerRegistry() {
    return IDE.getMarkerHelpRegistry();
  }

  @Override
  protected boolean hasContributionItems() {
    return getContributionItems().length > 0;
  }

  @Override
  public boolean isEnabled() {
    return super.isEnabled();
  }

  /**
   * @see org.eclipse.ui.actions.CompoundContributionItem#getContributionItems()
   */
  @Override
  protected IContributionItem[] getContributionItems() {

    List<IMarker> selection = getSelectedMarkers();

    if (selection.isEmpty()) {
      return NO_CONTRIBUTION_ITEM;
    }

    Collection<IContributionItem> items = new ArrayList<IContributionItem>();

    for (IMarkerResolution res : getMarkerRegistry().getResolutions(selection.get(0))) {
      Collection<IMarker> markers = new HashSet<IMarker>();
      markers.add(selection.get(0));

      if (res instanceof WorkbenchMarkerResolution) {

        IMarker[] otherMarkers = ((WorkbenchMarkerResolution) res).findOtherMarkers(selection.toArray(new IMarker[0]));
        for (IMarker otherMarker : otherMarkers) {
          markers.add(otherMarker);
        }
      }

      // only create a menu item if the resolution can handle all markers
      // in the selection
      if (markers.containsAll(selection) && isCompatible(res, markers)) {
        items.add(createContributionItem(markers, res));
      }
    }

    if (items.size() > 0) {
      ImageDescriptor image = MarkerViewPlugin.getDefault().getImageDescriptor(menuImage);
      String text = Messages.MarkerView_Quickfix_Command_Name;
      MenuManager manager = new MenuManager(text, image, menuId);
      for (IContributionItem item : items) {
        manager.add(item);
      }
      return new IContributionItem[] { manager };
    }

    return NO_CONTRIBUTION_ITEM;
  }

  /**
   * 
   * @param res
   * @param markers
   * @return true if a resolution should be displayed for a set of markers
   */
  protected boolean isCompatible(IMarkerResolution res, Collection<IMarker> markers) {
    if (res instanceof ReportMarkerResolution) {
      return ((ReportMarkerResolution) res).enabled(markers);
    }
    return true;
  }

  /**
   * 
   * @param res
   * @param markers
   * @return true if a resolution should be displayed in Quickfix All Similar
   */
  protected boolean isInQuickFixAllSimilar(IMarkerResolution res, Collection<IMarker> markers) {
    if (res instanceof ReportMarkerResolution) {
      return ((ReportMarkerResolution) res).quickFixAllSimilarEnabled(markers);
    }
    return true;
  }

  /**
   * @param markers
   * @param resolution
   * @return
   */
  protected IContributionItem createContributionItem(final Collection<IMarker> markers,
      final IMarkerResolution resolution) {
    return new ActionContributionItem(new Action() {

      /**
       * {@inheritDoc}
       */
      @Override
      public String getText() {
        return resolution.getLabel();
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public ImageDescriptor getImageDescriptor() {
        ImageDescriptor imgDesc = null;
        if (resolution instanceof IMarkerResolution2) {
          Image img = ((IMarkerResolution2) resolution).getImage();
          if (img != null) {
            imgDesc = ImageDescriptor.createFromImage(img);
          } else {
            imgDesc = super.getImageDescriptor();
          }
        } else {
          imgDesc = super.getImageDescriptor();
        }
        return imgDesc;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public String getDescription() {
        String desc = null;
        if (resolution instanceof IMarkerResolution2) {
          desc = ((IMarkerResolution2) resolution).getDescription();
        } else {
          desc = super.getDescription();
        }
        return desc;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void run() {

        IRunnableWithProgress resolutionsRunnable = new IRunnableWithProgress() {
          @Override
          public void run(IProgressMonitor monitor) {
            if (resolution instanceof WorkbenchMarkerResolution) {
              ((WorkbenchMarkerResolution) resolution).run(markers.toArray(new IMarker[0]), monitor);
            } else {
              for (IMarker marker : markers) {
                resolution.run(marker);
              }
            }
          }
        };
        IRunnableContext context = new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
            .getShell());
        try {
          PlatformUI.getWorkbench().getProgressService().runInUI(context, resolutionsRunnable, null);
        } catch (InvocationTargetException exception) {
          StatusManager.getManager().handle(
              new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), exception.getMessage(), exception));
        } catch (InterruptedException exception) {
          StatusManager.getManager().handle(
              new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), exception.getMessage(), exception));
        }
      }
    });
  }
}
