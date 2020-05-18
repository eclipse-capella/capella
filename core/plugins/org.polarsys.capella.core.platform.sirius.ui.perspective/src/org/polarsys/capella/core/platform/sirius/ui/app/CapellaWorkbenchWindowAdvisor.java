/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.app;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.internal.ide.application.IDEWorkbenchAdvisor;
import org.eclipse.ui.internal.ide.application.IDEWorkbenchWindowAdvisor;
import org.eclipse.ui.internal.intro.impl.IntroPlugin;
import org.eclipse.ui.internal.intro.impl.model.IntroModelRoot;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.config.CustomizableIntroPart;
import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.platform.sirius.ui.PerspectivePreferences;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Extends the {@link IDEWorkbenchWindowAdvisor} to enable Capella features.
 */
public class CapellaWorkbenchWindowAdvisor extends IDEWorkbenchWindowAdvisor {
  /**
   * Constructor.
   * @param wbAdvisor
   * @param configurer
   */
  public CapellaWorkbenchWindowAdvisor(IDEWorkbenchAdvisor wbAdvisor, IWorkbenchWindowConfigurer configurer) {
    super(wbAdvisor, configurer);
  }

  /**
   * @see org.eclipse.ui.internal.ide.application.IDEWorkbenchWindowAdvisor#openIntro()
   */
  @Override
  public void openIntro() {
    // Since org.eclipse.ui.intro/INTRO_START_PAGE is not implemented in 3.3, we workaround the openIntro to force to open on first steps page rather than Home
    // one.
    boolean showIntro = PrefUtil.getAPIPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.SHOW_INTRO);
    // Classic code.
    super.openIntro();
    // Show First steps page.
    IWorkbenchConfigurer workbenchConfig = getWindowConfigurer().getWorkbenchConfigurer();
    IIntroManager introManager = workbenchConfig.getWorkbench().getIntroManager();
    boolean hasIntro = introManager.hasIntro();
    // If an intro is provided and showed, let's change the current page.
    if (showIntro && hasIntro) {
      showPage("firststeps"); //$NON-NLS-1$
    }
  }

  /**
   * Display an Intro Page.
   * <p>
   * INTRO: revisit picking first page.
   */
  boolean showPage(String pageId) {
    // set the current page id in the model. This will trigger appropriate
    // listener event to the UI. If setting the page in the model fails (ie:
    // the page was not found in the current model, look for it in loaded
    // models. return false if failed.
    // avoid flicker.
    CustomizableIntroPart currentIntroPart = (CustomizableIntroPart) IntroPlugin.getIntro();
    currentIntroPart.getControl().setRedraw(false);

    IntroModelRoot modelRoot = IntroPlugin.getDefault().getIntroModelRoot();
    boolean success = modelRoot.setCurrentPageId(pageId);
    // we turned drawing off. Turn it on again.
    currentIntroPart.getControl().setRedraw(true);

    if (success) {
      // found page. Set the history
      modelRoot.getPresentation().updateHistory(modelRoot.getCurrentPage());
      // ran action successfully. Now set intro  standby if needed.
      return true;
    }
    // could not find referenced page.
    return false;
  }

  /**
   * @see org.eclipse.ui.internal.ide.application.IDEWorkbenchWindowAdvisor#postWindowRestore()
   */
  @Override
  public void postWindowRestore() throws WorkbenchException {
    super.postWindowRestore();
    initCapellaPerspective();
  }

  @Override
  public void postWindowCreate() {
    super.postWindowCreate();
    initCapellaPerspective();
  }
  
  private void initCapellaPerspective() {
    IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    IWorkbenchWindow window = configurer.getWindow();
    // The active page was set in the super call.
    final IWorkbenchPage activePage = window.getActivePage();
    window.getShell().getDisplay().asyncExec(new Runnable() {
      public void run() {
        // Hide action sets to clean useless menus and toolbars for end-users.
        activePage.hideActionSet("org.eclipse.ui.edit.text.actionSet.navigation"); //$NON-NLS-1$
        activePage.hideActionSet("org.eclipse.ui.externaltools.ExternalToolsSet"); //$NON-NLS-1$
        activePage.hideActionSet("org.eclipse.ui.edit.text.actionSet.annotationNavigation"); //$NON-NLS-1$
        activePage.hideActionSet("org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo"); //$NON-NLS-1$
        activePage.hideActionSet("org.eclipse.ui.actionSet.openFiles"); //$NON-NLS-1$
      }
    });
  }

  /**
   * Update the capella version persisted data.
   * @return <code>true</code> if the version was updated.<br>
   *         Caller can use it to reset the perspective for instance.
   */
  public boolean updateCapellaVersion() {
    boolean updated = false;
    // Get the current capella version (computed from feature version).
    String currentCapellaVersion = FeatureHelper.getCapellaVersion(true);
    IPreferenceStore store = Activator.getDefault().getPreferenceStore();
    String capellaRegisteredVersion = store.getString(PerspectivePreferences.PREFS_PRODUCT_VERSION);
    if ((ICommonConstants.EMPTY_STRING.equals(capellaRegisteredVersion)) || (!capellaRegisteredVersion.equals(currentCapellaVersion))) {
      store.putValue(PerspectivePreferences.PREFS_PRODUCT_VERSION, currentCapellaVersion);
      updated = true;
    }
    return updated;
  }
}
