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
package org.polarsys.capella.core.data.migration;

import java.util.List;
import java.util.Optional;

import org.eclipse.sirius.business.api.migration.IMigrationParticipant;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.ui.business.internal.migration.WorkspaceImageGMFBoundsMigrationParticipant;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.polarsys.capella.core.data.migration.participant.CapellaWorkspaceImageGMFBoundsMigrationParticipant;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction")
public class Activator extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.polarsys.capella.core.data.migration"; //$NON-NLS-1$

  // The shared instance
  private static Activator plugin;

  /**
   * The constructor
   */
  public Activator() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    // since https://bugs.eclipse.org/bugs/show_bug.cgi?id=576423, Sirius introduced a migration participant that
    // might cause wrong size migration for collapsed border nodes. We replace this participant by a new one
    // containing the fix.
    installCapellaWorkspaceImageGMFBoundsMigrationParticipant();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }
  @SuppressWarnings({ "unchecked" })
  private void installCapellaWorkspaceImageGMFBoundsMigrationParticipant() {
      RepresentationsFileMigrationService representationsFileMigrationService = RepresentationsFileMigrationService.getInstance();
      Optional<List<?>> optionalValue = ReflectionHelper.getFieldValueWithoutException(representationsFileMigrationService, "delegatesParticipants", RepresentationsFileMigrationService.class)
              .filter(List.class::isInstance).map(List.class::cast);
      if (optionalValue.isPresent()) {
          try {
              List<IMigrationParticipant> migrationParticipants = (List<IMigrationParticipant>) optionalValue.get();
              Optional<WorkspaceImageGMFBoundsMigrationParticipant> optionalWorkspaceImageGMFBoundsMigrationParticipant = migrationParticipants.stream()
                      .filter(WorkspaceImageGMFBoundsMigrationParticipant.class::isInstance).map(WorkspaceImageGMFBoundsMigrationParticipant.class::cast).findFirst();
              if (optionalWorkspaceImageGMFBoundsMigrationParticipant.isPresent()) {
                  migrationParticipants.remove(optionalWorkspaceImageGMFBoundsMigrationParticipant.get());
                  migrationParticipants.add(new CapellaWorkspaceImageGMFBoundsMigrationParticipant());
                  getLog().info("Capella: Sirius WorkspaceImageGMFBoundsMigrationParticipant replaced by CapellaWorkspaceImageGMFBoundsMigrationParticipant");//$NON-NLS-1$
              }
          } catch (ClassCastException e) {
              // We do nothing, the return type is not intended to be different from List<IMigrationParticipant>
          }
      }

  }

}
