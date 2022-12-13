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

package org.polarsys.capella.core.platform.sirius.ui.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.PerspectivePreferences;

/**
 * The Capella perspective factory.
 */
public class CapellaPerspective implements IPerspectiveFactory {
  /**
   * The Capella perspective identifier.
   */
  public static final String PERSPECTIVE_ID = "capella.sirius.perspective"; //$NON-NLS-1$
  /**
   * The Capella logger view identifier.
   */
  public static final String LOG_REPORT_ID = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview"; //$NON-NLS-1$
  /**
   * The outline view identifier.
   */
  public static final String OUTLINE_ID = IPageLayout.ID_OUTLINE;
  /**
   * The property view identifier.
   */
  public static final String PROPERTIES_ID = IPageLayout.ID_PROP_SHEET;
  /**
   * The Project explorer view identifier.
   */
  public static final String CAPELLA_PROJECT_EXPLORER_ID = "capella.project.explorer"; //$NON-NLS-1$
  /**
   * The Fast Linker view identifier.
   */
  public static final String FAST_LINKER_ID = "org.polarsys.capella.core.ui.fastlinker.view"; //$NON-NLS-1$
  /**
   * The center area place holder identifier.
   */
  public static final String CENTER_AREA = "center"; //$NON-NLS-1$

  /**
   * The top left area place holder identifier.
   */
  public static final String TOPLEFT_AREA = "topLeft"; //$NON-NLS-1$

  /**
   * The bottom left area place holder identifier.
   */
  public static final String BOTTOMLEFT_AREA = "bottomLeft"; //$NON-NLS-1$

  /**
   * The bottom area place holder.
   */
  public static final String BOTTOM_AREA = "bottom"; //$NON-NLS-1$

  /**
   * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
   */
  @Override
  public void createInitialLayout(IPageLayout layout_p) {
    // Allow editors.
    layout_p.setEditorAreaVisible(true);
    layout_p.createPlaceholderFolder(CENTER_AREA, IPageLayout.LEFT, 0.99f, IPageLayout.ID_EDITOR_AREA);

    IFolderLayout topLeft = layout_p.createFolder(TOPLEFT_AREA, IPageLayout.LEFT, (IPageLayout.DEFAULT_VIEW_RATIO / 2), IPageLayout.ID_EDITOR_AREA);
    topLeft.addView(CAPELLA_PROJECT_EXPLORER_ID);

    IFolderLayout bottomLeft = layout_p.createFolder(BOTTOMLEFT_AREA, IPageLayout.BOTTOM, (IPageLayout.DEFAULT_VIEW_RATIO / 0.7f), TOPLEFT_AREA);

    //If the outline isn't explicitly disabled through the preference , add the view in default perspective
    if (! AbstractPreferencesInitializer.getBoolean(PerspectivePreferences.PREFS_HIDE_OUTLINE_VIEW_ON_STARTUP, true)) {
      bottomLeft.addView(OUTLINE_ID);
    }

    IFolderLayout bottom = layout_p.createFolder(BOTTOM_AREA, IPageLayout.BOTTOM, (IPageLayout.DEFAULT_VIEW_RATIO / 0.7f), IPageLayout.ID_EDITOR_AREA);
    bottom.addView(PROPERTIES_ID);
    bottom.addView(LOG_REPORT_ID);

  }
}
