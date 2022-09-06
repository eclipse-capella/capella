/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.activator;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.refresh.RepresentationTimeStampInformationSupplierRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.ui.RefreshHelper;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.core.sirius.analysis.helpers.DDiagramHelper;
import org.polarsys.capella.core.sirius.analysis.preferences.DiagramProcessChainPathPreferenceInitializer;
import org.polarsys.capella.core.sirius.analysis.preferences.TitleBlockPreferencesInitializer;
import org.polarsys.capella.core.sirius.analysis.titleblock.TitleBlockRepresentationTimeStampInfoProvider;
import org.polarsys.capella.core.sirius.analysis.tool.ActivityEditorUpdater;

public class SiriusViewActivator extends AbstractUIPlugin {

  public static final String ID = "org.polarsys.capella.core.sirius.analysis"; //$NON-NLS-1$

  private static SiriusViewActivator instance;

  private Set<Viewpoint> viewpoints;

  private Predicate<Notification> considerCollapseStateForAutomaticRefreshPredicate;

  public SiriusViewActivator() {
    //
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    instance = this;
    viewpoints = new HashSet<>();
    viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/common.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
    viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/oa.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
    viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/context.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
    viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/logical.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
    viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/physical.odesign")); //$NON-NLS-1$ //$NON-NLS-2$
    viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin("/" + ID + "/description/EPBS.odesign")); //$NON-NLS-1$ //$NON-NLS-2$

    // Modify palette tool name with a custom end user label
    Messages.ArrangeBorderNodesAction_actionText = CapellaMessages.ArrangeBorderNodesAction_actionText;
    Messages.ArrangeBorderNodesAction_commandLabel = CapellaMessages.ArrangeBorderNodesAction_commandLabel;
    Messages.ArrangeBorderNodesAction_toolbarActionText = CapellaMessages.ArrangeBorderNodesAction_toolbarActionText;

    // Initialize preference values
    new DiagramProcessChainPathPreferenceInitializer();
    new TitleBlockPreferencesInitializer();

    // Register a predicate to consider Collapse/Uncollapse changes as impacting in "Functional Chain Description"
    // diagram.
    considerCollapseStateForAutomaticRefreshPredicate = notification -> {
      if (notification != null && NotationPackage.eINSTANCE.getDrawerStyle_Collapsed().equals(notification.getFeature())
          && notification.getNotifier() instanceof EObject) {
        Option<DDiagram> optionalDDiagram = new EObjectQuery((EObject) notification.getNotifier()).getParentDiagram();
        if (optionalDDiagram.some()) {
          DDiagram diagram = optionalDDiagram.get();
          return DDiagramHelper.isFCD(diagram) || DDiagramHelper.isOPD(diagram);
        }
      }
      return false;
    };
    RefreshHelper.registerImpactingNotification(considerCollapseStateForAutomaticRefreshPredicate);
    PlatformUI.getWorkbench().getActivitySupport().getActivityManager()
        .addActivityManagerListener(new ActivityEditorUpdater());

    RepresentationTimeStampInformationSupplierRegistry.INSTANCE
        .add(new TitleBlockRepresentationTimeStampInfoProvider());
  }

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    if (viewpoints != null) {
      for (Viewpoint viewpoint : viewpoints) {
        ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
      }
      viewpoints.clear();
    }
    if (considerCollapseStateForAutomaticRefreshPredicate != null) {
      RefreshHelper.unregisterImpactingNotification(considerCollapseStateForAutomaticRefreshPredicate);
      considerCollapseStateForAutomaticRefreshPredicate = null;
    }
  }

  /**
   * @generated
   */
  public static SiriusViewActivator getInstance() {
    return instance;
  }
}
