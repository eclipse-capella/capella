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
package org.polarsys.capella.core.dashboard.editor.pages;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.actions.epbs.DefineCiComponentsSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.epbs.DefineCiComponentsSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.epbs.PhysicalComponentEpbsComponentAction;
import org.polarsys.capella.core.dashboard.actions.epbs.RefineSystemCapabilityToEpbsArchitectureSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.epbs.RefineSystemCapabilityToEpbsArchitectureSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.util.PopupMenuLinkAdapter;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPageContribution;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider;
import org.polarsys.capella.core.dashboard.editor.pages.preferences.IEpbsArchitecturePreferences;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.epbs.ExecuteLogicalScenarioRefinementToCapabilityScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.epbs.NewComponentBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.epbs.NewEpbsArchitectureBlankAdapter;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;

/**
 * Base class to implement the EPBS Architecture dashboard page.
 */
public class EpbsArchitectureDashboardPage extends AbstractCapellaArchitectureDashboardPage {
  /**
   * Page id. This id would be used to contribute sections to this page.
   */
  public static final String PAGE_ID = "org.polarsys.capella.core.dashboard.editor.pages.EpbsArchitectureDashboardPage"; //$NON-NLS-1$
  /**
   * Create Define CI Components Section.
   */
  private Section _sectionDefineCiComponents;
  /**
   * Refine System Capability to EPBS Architecture Section.
   */
  private Section _sectionRefineSystemCapabilityToEpbsArchitecture;

  /**
   * Constructor.
   * @param editor_p
   */
  public EpbsArchitectureDashboardPage(FormEditor editor_p) {
    super(editor_p, PAGE_ID);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#adjustImageHRef(org.eclipse.ui.forms.widgets.FormText)
   */
  @Override
  protected void adjustImageHRef(FormText richText_p, CapellaDashboardActivator activator_p) {
    richText_p.setImage(PhysicalArchitectureDashboardPage.PAGE_ID, activator_p.getImage("full/overview/epbs/epbs_overview_01.png")); //$NON-NLS-1$
    richText_p.setImage(PAGE_ID, activator_p.getImage("full/overview/epbs/epbs_overview_02.png")); //$NON-NLS-1$
  }

  /**
   * Create Define CI Components Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDefineCiComponents(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DefineCiComponentsSectionDescriptionAction(sectionContainer_p.getShell()), new DefineCiComponentsSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p, Messages.EpbsArchitectureDashboardPage_DefineCiComponentsSection_Title,
            null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create an EPBS breakdown (i.e Configuration Items breakdown).
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_EPBS_BREAKDOWN_DIAGRAM),
        Messages.EpbsArchitectureDashboardPage_NewConfigurationItemsBreakdownAction_Title, null, null, new NewComponentBreakdownDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create an EPBS Architecture Blank.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_EPBS_ARCHITECTURE_BLANK_DIAGRAM),
        Messages.EpbsArchitectureDashboardPage_NewEpbsArchitectureBlankAction_Title, null, null, new NewEpbsArchitectureBlankAdapter(getCapellaProject(),
            getSession()));
    // Create an hyper link for Create Traceability Matrix.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_TRACEABILITY_MATRIX),
        Messages.EpbsArchitectureDashboardPage_CreateTraceabilityMatrixAction_Title, null, null, new PopupMenuLinkAdapter() {
          @Override
          protected void fillPopupMenu(IMenuManager menuManager_p) {
            Project capellaProject = getCapellaProject();
            // Physical Component / EPBS component action.
            menuManager_p.add(new PhysicalComponentEpbsComponentAction(capellaProject, getSession()));
          }
        });
    return section.getKey();
  }

  /**
   * Refine System Capability to EPBS Architecture Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createRefineSystemCapabilityToEpbsArchitecture(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new RefineSystemCapabilityToEpbsArchitectureSectionDescriptionAction(sectionContainer_p.getShell()),
                       new RefineSystemCapabilityToEpbsArchitectureSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.EpbsArchitectureDashboardPage_RefineSystemCapabilityToEpbsArchitectureSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Execute the Logical Scenario Refinement on the Scenarios of the Capability.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.EpbsArchitectureDashboardPage_ExecuteLogicalScenarioRefinementToCapabilityScenarioAction_Title, null, null,
        new ExecuteLogicalScenarioRefinementToCapabilityScenarioAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#createSections(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void createSections(Composite sectionContainer_p, IManagedForm managedForm_p) {
    super.createSections(sectionContainer_p, managedForm_p);
    // Override Header title.
    ScrolledForm form = managedForm_p.getForm();
    form.setText(getHeaderTitle() + Messages.EpbsArchitectureDashboardPage_Title_Suffix);
    // Create Overview section.
    createOverviewSection(sectionContainer_p, managedForm_p);

    // Create Define CI Components Section.
    if (AbstractPreferencesInitializer.getBoolean(IEpbsArchitecturePreferences.PREFERENCE_SECTION_DEFINE_CI_COMPONENTS, true)) {
      _sectionDefineCiComponents = createDefineCiComponents(sectionContainer_p, managedForm_p);
    }
    // Refine System Capability to EPBS Architecture Section.
    if (AbstractPreferencesInitializer.getBoolean(IEpbsArchitecturePreferences.PREFERENCE_SECTION_REFINE_SYSTEM_CAPABILITY_TO_EPBS_ARCHITECTURE, true)) {
      _sectionRefineSystemCapabilityToEpbsArchitecture = createRefineSystemCapabilityToEpbsArchitecture(sectionContainer_p, managedForm_p);
    }
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#doPropertyChange(org.eclipse.jface.util.PropertyChangeEvent,
   *      boolean, java.lang.String)
   */
  @Override
  protected boolean doPropertyChange(PropertyChangeEvent event_p, boolean value_p, String property_p) {
    boolean refreshNeeded = false;
    if ((property_p).endsWith(IEpbsArchitecturePreferences.PREFERENCE_SECTION_DEFINE_CI_COMPONENTS)) {
      if (value_p) {
        _sectionDefineCiComponents = createDefineCiComponents(getSectionContainer(), getManagedForm());
        _sectionDefineCiComponents.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionDefineCiComponents.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IEpbsArchitecturePreferences.PREFERENCE_SECTION_REFINE_SYSTEM_CAPABILITY_TO_EPBS_ARCHITECTURE)) {
      if (value_p) {
        _sectionRefineSystemCapabilityToEpbsArchitecture = createRefineSystemCapabilityToEpbsArchitecture(getSectionContainer(), getManagedForm());
        _sectionRefineSystemCapabilityToEpbsArchitecture.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionRefineSystemCapabilityToEpbsArchitecture.dispose();
      }
      refreshNeeded = true;
    }
    return refreshNeeded;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getHandledViewpoint()
   */
  @Override
  protected String getHandledViewpoint() {
    return IViewpointNameConstants.EPBS_ARCHITECTURE_VIEWPOINT_NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getFilteringMetaClassForCommonViewpoint() {
    return EpbsPackage.Literals.EPBS_ARCHITECTURE;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getOverviewFileName()
   */
  @Override
  protected String getOverviewFileName() {
    return "epbs-architecture.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageDescriptionFileName()
   */
  @Override
  protected String getPageDescriptionFileName() {
    return "description/epbs-architecture-description.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.EpbsArchitectureDashboardPage_Title;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#handleContributedSectionsFor(org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider,
   *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void handleContributedSectionsFor(ICapellaDashboardPagesProvider contributor_p, Composite sectionContainer_p, IManagedForm managedForm_p) {
    ICapellaDashboardPageContribution epbsArchitectureContribution = contributor_p.getEPBSArchitectureContribution();
    // If provided.
    if (null != epbsArchitectureContribution) {
      epbsArchitectureContribution.createSections(sectionContainer_p, this, managedForm_p);
    }
  }
}
