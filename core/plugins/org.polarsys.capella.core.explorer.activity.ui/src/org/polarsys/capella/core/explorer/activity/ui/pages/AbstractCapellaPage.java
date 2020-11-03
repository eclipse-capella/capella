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
package org.polarsys.capella.core.explorer.activity.ui.pages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.input.ActivityExplorerEditorInput;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.BasicSessionActivityExplorerPage;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.viewers.AbstractActivityExplorerViewer;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.explorer.activity.ui.viewer.CapellaDiagramViewer;

public abstract class AbstractCapellaPage extends BasicSessionActivityExplorerPage {

  protected Set<String> handledViewpoint = new HashSet<String>();

  @SuppressWarnings("unchecked")
  @Override
  protected Class<? extends AbstractActivityExplorerViewer>[] addViewersTypeInPage() {
    return new Class[] { CapellaDiagramViewer.class };
  }

  public Optional<CapellaDiagramViewer> getCapellaDiagramViewer() {
    return Arrays.stream(getListeners())//
        .filter(CapellaDiagramViewer.class::isInstance) //
        .map(CapellaDiagramViewer.class::cast) //
        .findFirst();
  }

  @Override
  protected String getHeaderTitle() {
    ActivityExplorerEditorInput editorInput = getEditorInput();
    String richTitle = super.getHeaderTitle();
    if (editorInput != null) {
      richTitle = richTitle + " of " + editorInput.getName();
    }
    return richTitle;
  }

  /**
   * Get the EClass this architecture page is handling.<br>
   * This EClass is used to filter out UI from other levels in Common viewpoint part.<br>
   * Example: in SA page we only display Common representations hold by CtxPackage.Literals.SYSTEM_ANALYSIS.
   * 
   * @return
   */
  public abstract EClass getFilteringMetaClassForCommonViewpoint();

}
