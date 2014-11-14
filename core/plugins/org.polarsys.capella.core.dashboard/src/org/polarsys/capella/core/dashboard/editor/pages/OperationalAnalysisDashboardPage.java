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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.actions.oa.AllocateOperationalActivitiesToActorsEntitiesRolesSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.oa.AllocateOperationalActivitiesToActorsEntitiesRolesSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.oa.DefineActorsOperationalEntitiesCapabilitiesSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.oa.DefineActorsOperationalEntitiesCapabilitiesSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.oa.DefineOperationalActivitiesInteractionSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.oa.DefineOperationalActivitiesInteractionSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.oa.TransverseModelingDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.oa.TransverseModelingFilteringAction;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPageContribution;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider;
import org.polarsys.capella.core.dashboard.editor.pages.preferences.IOperationalAnalysisPreferences;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewActivityScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewClassDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewInteractionScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewOperationalActivityBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewOperationalCapabilityBlankDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewOperationalEntityBlankDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewOperationalEntityBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewOperationalInteractionBlankDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewRoleBlankDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewStateMachineDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.oa.NewStateModeFunctionsMatrixAdapter;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;

/**
 * Base class to implement the Operational Analysis dashboard page.
 */
public class OperationalAnalysisDashboardPage extends AbstractCapellaArchitectureDashboardPage {
  /**
   * Page id. This id would be used to contribute sections to this page.
   */
  public static final String PAGE_ID = "org.polarsys.capella.core.dashboard.editor.pages.OperationalAnalysisDashboardPage"; //$NON-NLS-1$
  /**
   * Create Define Actors, Operational Entities and Operational Capabilities Section.
   */
  private Section _sectionDefineActorsOperationalEntitiesCapabilities;
  /**
   * Create Define operational activities and describe interactions Section.
   */
  private Section _sectionDefineOperationalActivitiesInteraction;
  /**
   * Create Allocate operational activities to operational actors, entities or roles Section.
   */
  private Section _sectionAllocateOperationalActivitiesToActorsEntitiesRoles;
  /**
   * Create Transverse modeling.
   */
  private Section _sectionTransverseModeling;

  /**
   * Constructor.
   * @param editor_p
   */
  public OperationalAnalysisDashboardPage(FormEditor editor_p) {
    super(editor_p, PAGE_ID);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#adjustImageHRef(org.eclipse.ui.forms.widgets.FormText)
   */
  @Override
  protected void adjustImageHRef(FormText richText_p, CapellaDashboardActivator activator_p) {
    richText_p.setImage(PAGE_ID, activator_p.getImage("full/overview/oa/operationalanalysis_overview_01.png")); //$NON-NLS-1$
    richText_p.setImage(SystemAnalysisDashboardPage.PAGE_ID, activator_p.getImage("full/overview/oa/operationalanalysis_overview_02.png")); //$NON-NLS-1$
  }

  /**
   * Create Allocate operational activities to operational actors, entities or roles Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createAllocateOperationalActivitiesToActorsEntitiesRoles(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new AllocateOperationalActivitiesToActorsEntitiesRolesSectionDescriptionAction(sectionContainer_p.getShell()),
                       new AllocateOperationalActivitiesToActorsEntitiesRolesSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.OperationalAnalysisDashboardPage_AllocateOperationalActivitiesToActorsEntitiesRolesSection_Title, null, false,
            MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Operational Entity Blank diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_OPERATIONAL_ENTITY_BLANK_DIAGRAM),
        Messages.OperationalAnalysisDashboardPage_NewOperationalEntityBlankDiagramAction_Title, null, null, new NewOperationalEntityBlankDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Role Blank diagram.
    FormHelper
        .createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_ROLE_BLANK_DIAGRAM),
            Messages.OperationalAnalysisDashboardPage_NewRoleBlankDiagramAction_Title, null, null, new NewRoleBlankDiagramAdapter(getCapellaProject(),
                getSession()));
    return section.getKey();
  }

  /**
   * Create Define Operational Entities and Operational Capabilities Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDefineOperationalEntitiesCapabilities(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DefineActorsOperationalEntitiesCapabilitiesSectionDescriptionAction(sectionContainer_p.getShell()),
                       new DefineActorsOperationalEntitiesCapabilitiesSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.OperationalAnalysisDashboardPage_DefineActorsOperationalEntitiesCapabilitiesSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Operational Entity Breakdown diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_OPERATIONAL_ENTITY_BREAKDOWN_DIAGRAM),
        Messages.OperationalAnalysisDashboardPage_NewOperationalEntityBreakdownDiagramAction_Title, null, null,
        new NewOperationalEntityBreakdownDiagramAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Operational Capabilities diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_OPERATIONAL_CAPABILITY_BLANK_DIAGRAM),
        Messages.OperationalAnalysisDashboardPage_NewOperationalCapabilitiesDiagramAction_Title, null, null, new NewOperationalCapabilityBlankDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Entity Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_OA_DATAFLOW_SCENARIO),
        Messages.OperationalAnalysisDashboardPage_NewInteractionScenarioAction_Title, null, null, new NewInteractionScenarioAdapter(getCapellaProject(),
            getSession()));
    return section.getKey();
  }

  /**
   * Create Define operational activities and describe interactions Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDefineOperationalActivitiesInteraction(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DefineOperationalActivitiesInteractionSectionDescriptionAction(sectionContainer_p.getShell()),
                       new DefineOperationalActivitiesInteractionSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.OperationalAnalysisDashboardPage_DefineOperationalActivitiesInteractionSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Operational Entity Breakdown diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite,
        capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM),
        Messages.OperationalAnalysisDashboardPage_NewOperationalActivityBreakdownDiagramAction_Title, null, null,
        new NewOperationalActivityBreakdownDiagramAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Operational Interaction Blank diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite,
        capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_OPERATIONAL_INTERACTION_BLANK_DIAGRAM),
        Messages.OperationalAnalysisDashboardPage_NewOperationalInteractionBlankDiagramAction_Title, null, null,
        new NewOperationalInteractionBlankDiagramAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Activity Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_OA_ACTIVITY_SCENARIO),
        Messages.OperationalAnalysisDashboardPage_NewActivityInteractionScenarioAction_Title, null, null, new NewActivityScenarioAdapter(getCapellaProject(),
            getSession()));
    return section.getKey();
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#createSections(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void createSections(Composite sectionContainer_p, IManagedForm managedForm_p) {
    super.createSections(sectionContainer_p, managedForm_p);
    // Set the header title.
    IPreferenceStore preferenceStore = CapellaDashboardActivator.getDefault().getPreferenceStore();
    // Create Overview section.
    createOverviewSection(sectionContainer_p, managedForm_p);

    // Define Operational Entities and Capabilities Section.
    if (AbstractPreferencesInitializer.getBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ENTITIES_CAPABILITIES, true)) {
      _sectionDefineActorsOperationalEntitiesCapabilities = createDefineOperationalEntitiesCapabilities(sectionContainer_p, managedForm_p);
    }
    // Create Define operational activities and describe interactions Section.
    if (AbstractPreferencesInitializer.getBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ACTIVITIES_INTERACTION, true)) {
      _sectionDefineOperationalActivitiesInteraction = createDefineOperationalActivitiesInteraction(sectionContainer_p, managedForm_p);
    }
    // Create Allocate operational activities to operational actors, entities or roles Section.
    if (AbstractPreferencesInitializer.getBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_OPERATIONAL_ACTIVITIES_TO_ACTORS_ENTITIES_ROLES,
        true)) {
      _sectionAllocateOperationalActivitiesToActorsEntitiesRoles = createAllocateOperationalActivitiesToActorsEntitiesRoles(sectionContainer_p, managedForm_p);
    }
    // Create Transverse modeling Section.
    if (AbstractPreferencesInitializer.getBoolean(IOperationalAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true)) {
      _sectionTransverseModeling =
          createTransverseModeling(sectionContainer_p, managedForm_p, new TransverseModelingFilteringAction(this), new TransverseModelingDescriptionAction(
              sectionContainer_p.getShell()), new NewClassDiagramAdapter(getCapellaProject(), getSession()), new NewStateMachineDiagramAdapter(
              getCapellaProject(), getSession()), new NewStateModeFunctionsMatrixAdapter(getCapellaProject(), getSession()),
              Messages.OperationalAnalysisDashboardPage_NewStateModeOperationalActivitiesMatrix_Title);
    }
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#doPropertyChange(org.eclipse.jface.util.PropertyChangeEvent,
   *      boolean, java.lang.String)
   */
  @Override
  protected boolean doPropertyChange(PropertyChangeEvent event_p, boolean value_p, String property_p) {
    boolean refreshNeeded = false;
    if ((property_p).endsWith(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ENTITIES_CAPABILITIES)) {
      if (value_p) {
        _sectionDefineActorsOperationalEntitiesCapabilities = createDefineOperationalEntitiesCapabilities(getSectionContainer(), getManagedForm());
        _sectionDefineActorsOperationalEntitiesCapabilities.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionDefineActorsOperationalEntitiesCapabilities.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IOperationalAnalysisPreferences.PREFERENCE_SECTION_DEFINE_OPERATIONAL_ACTIVITIES_INTERACTION)) {
      if (value_p) {
        _sectionDefineOperationalActivitiesInteraction = createDefineOperationalActivitiesInteraction(getSectionContainer(), getManagedForm());
        _sectionDefineOperationalActivitiesInteraction.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionDefineOperationalActivitiesInteraction.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IOperationalAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_OPERATIONAL_ACTIVITIES_TO_ACTORS_ENTITIES_ROLES)) {
      if (value_p) {
        _sectionAllocateOperationalActivitiesToActorsEntitiesRoles =
            createAllocateOperationalActivitiesToActorsEntitiesRoles(getSectionContainer(), getManagedForm());
        _sectionAllocateOperationalActivitiesToActorsEntitiesRoles.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionAllocateOperationalActivitiesToActorsEntitiesRoles.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IOperationalAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING)) {
      if (value_p) {
        _sectionTransverseModeling =
            createTransverseModeling(getSectionContainer(), getManagedForm(), new TransverseModelingFilteringAction(this),
                new TransverseModelingDescriptionAction(PlatformUI.getWorkbench().getDisplay().getActiveShell()), new NewClassDiagramAdapter(
                    getCapellaProject(), getSession()), new NewStateMachineDiagramAdapter(getCapellaProject(), getSession()),
                new NewStateModeFunctionsMatrixAdapter(getCapellaProject(), getSession()),
                Messages.OperationalAnalysisDashboardPage_NewStateModeOperationalActivitiesMatrix_Title);
        _sectionTransverseModeling.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionTransverseModeling.dispose();
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
    return IViewpointNameConstants.OPERATIONAL_ANALYSIS_VIEWPOINT_NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getFilteringMetaClassForCommonViewpoint() {
    return OaPackage.Literals.OPERATIONAL_ANALYSIS;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getOverviewFileName()
   */
  @Override
  protected String getOverviewFileName() {
    return "operational-analysis.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageDescriptionFileName()
   */
  @Override
  protected String getPageDescriptionFileName() {
    return "description/operational-analysis-description.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.OperationalAnalysisDashboardPage_Title;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#handleContributedSectionsFor(org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider,
   *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void handleContributedSectionsFor(ICapellaDashboardPagesProvider contributor_p, Composite sectionContainer_p, IManagedForm managedForm_p) {
    ICapellaDashboardPageContribution operationalAnalysisContribution = contributor_p.getOperationalAnalysisContribution();
    // If provided.
    if (null != operationalAnalysisContribution) {
      operationalAnalysisContribution.createSections(sectionContainer_p, this, managedForm_p);
    }
  }
}
