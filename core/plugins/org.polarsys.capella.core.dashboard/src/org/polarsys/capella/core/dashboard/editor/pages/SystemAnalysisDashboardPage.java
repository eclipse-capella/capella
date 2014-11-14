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
import org.polarsys.capella.core.dashboard.actions.sa.AllocateSystemFunctionToSystemAndActorsSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.sa.AllocateSystemFunctionToSystemAndActorsSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.sa.DefineActorsMissionsCapabilitiesSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.sa.DefineActorsMissionsCapabilitiesSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.sa.DefineInterfacesDescribeScenariosDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.sa.DefineInterfacesDescribeScenariosFilteringAction;
import org.polarsys.capella.core.dashboard.actions.sa.MissionBlankAction;
import org.polarsys.capella.core.dashboard.actions.sa.MissionCapabilitiesBlankAction;
import org.polarsys.capella.core.dashboard.actions.sa.PerformOpCapabilityToSystemCapabilityAction;
import org.polarsys.capella.core.dashboard.actions.sa.PerformOpCapabilityToSystemMissionAction;
import org.polarsys.capella.core.dashboard.actions.sa.RefineSystemFunctionsDescribeDataflowsSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.sa.RefineSystemFunctionsDescribeDataflowsSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.sa.TransitionFromOASectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.sa.TransitionFromOASectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.sa.TransverseModelingDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.sa.TransverseModelingFilteringAction;
import org.polarsys.capella.core.dashboard.actions.util.PopupMenuLinkAdapter;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPageContribution;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider;
import org.polarsys.capella.core.dashboard.editor.pages.preferences.ISystemAnalysisPreferences;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.PerformAutomatedCapabilitiesTransitionAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewActorsAllocationsDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewBlankFunctionalDataflowDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewClassDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewContextualSystemActorsDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewDetailedInterfaceDiagramOnSystemAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewExchangeScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewExternalInterfaceDiagramOnSystemAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewFunctionalBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewFunctionalScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewScenarioDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewStateMachineDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewStateModeFunctionsMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewSystemActorsOperationalEntitiesMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.NewSystemFunctionsOperationalActivitiesMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.PerformAutomatedSyncOfSystemInterfacesAndPortsAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.PerformAutomatedTransitionAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.PerformOpEntitiesAndActorsTransitionAdapter;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

/**
 * Base class to implement the System Analysis dashboard page.
 */
public class SystemAnalysisDashboardPage extends AbstractCapellaArchitectureDashboardPage {
  /**
   * Page id. This id would be used to contribute sections to this page.
   */
  public static final String PAGE_ID = "org.polarsys.capella.core.dashboard.editor.pages.SystemAnalysisDashboardPage"; //$NON-NLS-1$
  /**
   * Transition From Operational Analysis Section.
   */
  private Section _sectionTransitionFromOASection;
  /**
   * Define Actors, Missions and Capabilities Section.
   */
  private Section _sectionDefineActorsMissionsCapabilities;
  /**
   * Create Refine system functions, describe dataflows Section.
   */
  private Section _sectionRefineSystemFunctionsDescribeDataflows;
  /**
   * Create Allocate system function to system and actors Section.
   */
  private Section _sectionAllocateSystemFunctionToSystemAndActors;
  /**
   * Create Define Interfaces and describe scenarios.
   */
  private Section _sectionDefineInterfacesDescribeScenarios;
  /**
   * Create Transverse modeling.
   */
  private Section _sectionTransverseModeling;

  /**
   * Constructor.
   * @param editor_p
   */
  public SystemAnalysisDashboardPage(FormEditor editor_p) {
    super(editor_p, PAGE_ID);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#adjustImageHRef(org.eclipse.ui.forms.widgets.FormText)
   */
  @Override
  protected void adjustImageHRef(FormText richText_p, CapellaDashboardActivator activator_p) {
    // If Operational Analysis exists, load its image.
    if (null != ModelQueryHelper.getOperationalAnalysis(getCapellaProject())) {
      richText_p.setImage(OperationalAnalysisDashboardPage.PAGE_ID, activator_p.getImage("full/overview/sa/systemanalysis_overview_01.png")); //$NON-NLS-1$
    }
    richText_p.setImage(PAGE_ID, activator_p.getImage("full/overview/sa/systemanalysis_overview_02.png")); //$NON-NLS-1$
    richText_p.setImage(LogicalArchitectureDashboardPage.PAGE_ID, activator_p.getImage("full/overview/sa/systemanalysis_overview_03.png")); //$NON-NLS-1$
  }

  /**
   * Create Allocate system function to system and actors Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createAllocateSystemFunctionToSystemAndActors(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new AllocateSystemFunctionToSystemAndActorsSectionDescriptionAction(sectionContainer_p.getShell()),
                       new AllocateSystemFunctionToSystemAndActorsSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.SystemAnalysisDashboardPage_AllocateSystemFunctionToSystemAndActorsSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create Actors and manage allocations with a new System Architecture diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_AF_SYSTEM_ARCHITECTURE_DIAGRAM),
        Messages.SystemAnalysisDashboardPage_NewActorsAllocationsDiagram_Title, Messages.SystemAnalysisDashboardPage_NewActorsAllocationsDiagram_Description,
        null, new NewActorsAllocationsDiagramAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Exchange Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_EXCHANGE_SCENARIO),
        Messages.SystemAnalysisDashboardPage_NewExchangeScenario_Title, null, null, new NewExchangeScenarioAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Create Define Actors, Missions and Capabilities Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDefineActorsMissionsCapabilities(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DefineActorsMissionsCapabilitiesSectionDescriptionAction(sectionContainer_p.getShell()),
                       new DefineActorsMissionsCapabilitiesSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.SystemAnalysisDashboardPage_DefineActorsMissionsCapabilitiesSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();

    // Create an hyper link for Execute a transition of all System Analisys Capabilities to a Logical Architecture.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.SystemAnalysisDashboardPage_PerformTransitionOfCapabilities_Title, null,
        Messages.SystemAnalysisDashboardPage_PerformTransitionOfCapabilities_Description, new PerformAutomatedCapabilitiesTransitionAdapter(getCapellaProject(),
            ModelQueryHelper.getOperationalAnalysis(getCapellaProject()), getSession()));

    // Create an hyper link for Contextually create new System Actors from Operational Entities / Actors.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.SystemAnalysisDashboardPage_SystemAnalysisDashboardPage_NewSystemActorsFromOperationalEntitiesActors_Title, null,
        Messages.SystemAnalysisDashboardPage_SystemAnalysisDashboardPage_NewSystemActorsFromOperationalEntitiesActors_Description,
        new PerformOpEntitiesAndActorsTransitionAdapter(getCapellaProject(), getSession()));

    // Create an hyper link for Contextually create new System Actors from Operational Entities / Actors.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.SystemAnalysisDashboardPage_SystemAnalysisDashboardPage_NewSystemCapabilityOrMissionFromOperationalCapability_Title, null,
        Messages.SystemAnalysisDashboardPage_SystemAnalysisDashboardPage_NewSystemCapabilityOrMissionFromOperationalCapability_Description,
        new PopupMenuLinkAdapter() {
          @Override
          protected void fillPopupMenu(IMenuManager menuManager_p) {
            Project capellaProject = getCapellaProject();
            // Add Transition from Operational Capabilities to System Capability action.
            menuManager_p.add(new PerformOpCapabilityToSystemCapabilityAction(capellaProject, getSession()));
            // Add Transition from Operational Capabilities to System Mission action.
            menuManager_p.add(new PerformOpCapabilityToSystemMissionAction(capellaProject, getSession()));
          }
        });

    // Create an hyper link for Create a new Contextual System Actors diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_CONTEXTUAL_SYSTEM_ACTORS_DIAGRAM),
        Messages.SystemAnalysisDashboardPage_NewContextualSystemActorsDiagram_Title, null, null, new NewContextualSystemActorsDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new mission and / or capability diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_MISSION_AND_OR_CAPABILITY_DIAGRAM),
        Messages.SystemAnalysisDashboardPage_NewMissionCapabilityDiagram_Title, null, null, new PopupMenuLinkAdapter() {
          @Override
          protected void fillPopupMenu(IMenuManager menuManager_p) {
            // Add Mission Blank diagram action.
            Project capellaProject = getCapellaProject();
            menuManager_p.add(new MissionBlankAction(capellaProject, getSession()));
            // Add Mission Capabilities Blank diagram action.
            menuManager_p.add(new MissionCapabilitiesBlankAction(capellaProject, getSession()));
          }
        });
    // Create a System Actors / Operational Entities Traceability Matrix
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_TRACEABILITY_MATRIX),
        Messages.SystemAnalysisDashboardPage_NewSystemActorsOperationalEntitiesAction_Title, null, null, new NewSystemActorsOperationalEntitiesMatrixAdapter(
            getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Create Define Interfaces and describe scenarios Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDefineInterfacesDescribeScenarios(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DefineInterfacesDescribeScenariosDescriptionAction(sectionContainer_p.getShell()),
                       new DefineInterfacesDescribeScenariosFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.SystemAnalysisDashboardPage_DefineInterfacesDescribeScenariosSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Perform an automated synchronization of System interfaces and Ports.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.SystemAnalysisDashboardPage_PerformAutomatedTransitionOfSystem_Title, null,
        Messages.SystemAnalysisDashboardPage_PerformAutomatedTransitionOfSystem_Description, new PerformAutomatedSyncOfSystemInterfacesAndPortsAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new detailed interface diagram on the System.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_DETAILED_INTERFACE_DIAGRAM_ON_SYSTEM),
        Messages.SystemAnalysisDashboardPage_NewDetailedInterfaceDiagramOnSystem_Title, null, null, new NewDetailedInterfaceDiagramOnSystemAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new external interface diagram on the System.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_EXTERNAL_INTERFACE_DIAGRAM_ON_SYSTEM),
        Messages.SystemAnalysisDashboardPage_NewExternalInterfaceDiagramOnSystem_Title, null, null, new NewExternalInterfaceDiagramOnSystemAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_INTERFACE_SCENARIO),
        Messages.SystemAnalysisDashboardPage_NewScenario_Title, null, null, new NewScenarioDiagramAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Create Refine system functions, describe dataflows Section.
   * @param sectionContainer_p
   * @param managedForm_p RefineSystemFunctionsDescribeDataflows
   * @return
   */
  protected Section createRefineSystemFunctionsDescribeFunctionalExchanges(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new RefineSystemFunctionsDescribeDataflowsSectionDescriptionAction(sectionContainer_p.getShell()),
                       new RefineSystemFunctionsDescribeDataflowsSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.SystemAnalysisDashboardPage_RefineSystemFunctionsDescribeFunctionalExchangesSection_Title, null, false, MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Functional Breakdown diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_FUNCTIONAL_BREAKDOWN_DIAGRAM),
        Messages.SystemAnalysisDashboardPage_NewFunctionalBreakdownDiagram_Title, null, null, new NewFunctionalBreakdownDiagramAdapter(getCapellaProject(),
            getSession()));
    // Create an hyper link for Create a new Blank Functional Dataflow diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_BLANK_FUNCTIONAL_DATAFLOW_DIAGRAM),
        Messages.SystemAnalysisDashboardPage_NewBlankDataflowDiagram_Title, null, null, new NewBlankFunctionalDataflowDiagramAdapter(getCapellaProject(),
            getSession()));
    // Create an hyper link for Create a new Functional Scenario diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_FUNCTIONAL_SCENARIO_DIAGRAM),
        Messages.SystemAnalysisDashboardPage_NewFunctionalScenarioAction_Title, null, null, new NewFunctionalScenarioAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#createSections(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void createSections(Composite sectionContainer_p, IManagedForm managedForm_p) {
    super.createSections(sectionContainer_p, managedForm_p);
    // Create Overview section.
    createOverviewSection(sectionContainer_p, managedForm_p);
    // Create Transition From Operational Analysis Section.
    if (AbstractPreferencesInitializer.getBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSITION_FROM_OA, true)) {
      _sectionTransitionFromOASection = createTransitionFromOASection(sectionContainer_p, managedForm_p);
    }
    // Create Define Actors, Missions and Capabilities Section.
    if (AbstractPreferencesInitializer.getBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_ACTORS_MISSIONS_CAPABILITIES, true)) {
      _sectionDefineActorsMissionsCapabilities = createDefineActorsMissionsCapabilities(sectionContainer_p, managedForm_p);
    }
    // Create Refine system functions, describe dataflows Section.
    if (AbstractPreferencesInitializer.getBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_REFINE_SYSTEM_FUNCTIONS_DESCRIBE_FE, true)) {
      _sectionRefineSystemFunctionsDescribeDataflows = createRefineSystemFunctionsDescribeFunctionalExchanges(sectionContainer_p, managedForm_p);
    }
    // Create Allocate system function to system and actors Section.
    if (AbstractPreferencesInitializer.getBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_SYSTEM_FUNCTION_TO_SYSTEM_AND_ACTORS, true)) {
      _sectionAllocateSystemFunctionToSystemAndActors = createAllocateSystemFunctionToSystemAndActors(sectionContainer_p, managedForm_p);
    }
    // Create Define Interfaces and describe scenarios Section.
    if (AbstractPreferencesInitializer.getBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_INTERFACES_DESCRIBE_SCENARIOS, true)) {
      _sectionDefineInterfacesDescribeScenarios = createDefineInterfacesDescribeScenarios(sectionContainer_p, managedForm_p);
    }
    // Create Transverse modeling Section.
    if (AbstractPreferencesInitializer.getBoolean(ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true)) {
      _sectionTransverseModeling =
          createTransverseModeling(sectionContainer_p, managedForm_p, new TransverseModelingFilteringAction(this), new TransverseModelingDescriptionAction(
              sectionContainer_p.getShell()), new NewClassDiagramAdapter(getCapellaProject(), getSession()), new NewStateMachineDiagramAdapter(
              getCapellaProject(), getSession()), new NewStateModeFunctionsMatrixAdapter(getCapellaProject(), getSession()), null);
    }
  }

  /**
   * Create Transition From Operational Analysis Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createTransitionFromOASection(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new TransitionFromOASectionDescriptionAction(sectionContainer_p.getShell()), new TransitionFromOASectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p, Messages.SystemAnalysisDashboardPage_TransitionFromOASection_Title, null,
            false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Perform Activity to System Function Transition.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.SystemAnalysisDashboardPage_PerformAutomatedTransition_Title, null,
        Messages.SystemAnalysisDashboardPage_PerformAutomatedTransition_Description, new PerformAutomatedTransitionAdapter(getCapellaProject(), getSession()));
    // Create a System Functions / Operational Activities Traceability Matrix
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_TRACEABILITY_MATRIX),
        Messages.SystemAnalysisDashboardPage_NewSystemFunctionsOperationalActivitiesTraceabilityMatrix_Title, null, null,
        new NewSystemFunctionsOperationalActivitiesMatrixAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#doPropertyChange(org.eclipse.jface.util.PropertyChangeEvent,
   *      boolean, java.lang.String)
   */
  @Override
  protected boolean doPropertyChange(PropertyChangeEvent event_p, boolean value_p, String property_p) {
    boolean refreshNeeded = false;
    if (ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSITION_FROM_OA.equals(property_p)) {
      if (value_p) {
        _sectionTransitionFromOASection = createTransitionFromOASection(getSectionContainer(), getManagedForm());
        _sectionTransitionFromOASection.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionTransitionFromOASection.dispose();
      }
      refreshNeeded = true;
    } else if (ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_ACTORS_MISSIONS_CAPABILITIES.equals(property_p)) {
      if (value_p) {
        _sectionDefineActorsMissionsCapabilities = createDefineActorsMissionsCapabilities(getSectionContainer(), getManagedForm());
        _sectionDefineActorsMissionsCapabilities.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionDefineActorsMissionsCapabilities.dispose();
      }
      refreshNeeded = true;
    } else if (ISystemAnalysisPreferences.PREFERENCE_SECTION_REFINE_SYSTEM_FUNCTIONS_DESCRIBE_FE.equals(property_p)) {
      if (value_p) {
        _sectionRefineSystemFunctionsDescribeDataflows = createRefineSystemFunctionsDescribeFunctionalExchanges(getSectionContainer(), getManagedForm());
        _sectionRefineSystemFunctionsDescribeDataflows.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionRefineSystemFunctionsDescribeDataflows.dispose();
      }
      refreshNeeded = true;
    } else if (ISystemAnalysisPreferences.PREFERENCE_SECTION_ALLOCATE_SYSTEM_FUNCTION_TO_SYSTEM_AND_ACTORS.equals(property_p)) {
      if (value_p) {
        _sectionAllocateSystemFunctionToSystemAndActors = createAllocateSystemFunctionToSystemAndActors(getSectionContainer(), getManagedForm());
        _sectionAllocateSystemFunctionToSystemAndActors.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionAllocateSystemFunctionToSystemAndActors.dispose();
      }
      refreshNeeded = true;
    } else if (ISystemAnalysisPreferences.PREFERENCE_SECTION_DEFINE_INTERFACES_DESCRIBE_SCENARIOS.equals(property_p)) {
      if (value_p) {
        _sectionDefineInterfacesDescribeScenarios = createDefineInterfacesDescribeScenarios(getSectionContainer(), getManagedForm());
        _sectionDefineInterfacesDescribeScenarios.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionDefineInterfacesDescribeScenarios.dispose();
      }
      refreshNeeded = true;
    } else if (ISystemAnalysisPreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING.equals(property_p)) {
      if (value_p) {
        _sectionTransverseModeling =
            createTransverseModeling(getSectionContainer(), getManagedForm(), new TransverseModelingFilteringAction(this),
                new TransverseModelingDescriptionAction(PlatformUI.getWorkbench().getDisplay().getActiveShell()), new NewClassDiagramAdapter(
                    getCapellaProject(), getSession()), new NewStateMachineDiagramAdapter(getCapellaProject(), getSession()),
                new NewStateModeFunctionsMatrixAdapter(getCapellaProject(), getSession()), null);
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
    return IViewpointNameConstants.SYSTEM_ANALYSIS_VIEWPOINT_NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getFilteringMetaClassForCommonViewpoint() {
    return CtxPackage.Literals.SYSTEM_ANALYSIS;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getOverviewFileName()
   */
  @Override
  protected String getOverviewFileName() {
    String fileName = "system-analysis.xml"; //$NON-NLS-1$
    if (null == ModelQueryHelper.getOperationalAnalysis(getCapellaProject())) {
      // No Operational Analysis, let's take the appropriate file.
      fileName = "system-analysis2.xml"; //$NON-NLS-1$
    }
    return fileName;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageDescriptionFileName()
   */
  @Override
  protected String getPageDescriptionFileName() {
    return "description/system-analysis-description.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.SystemAnalysisDashboardPage_Title;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#handleContributedSectionsFor(org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider,
   *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void handleContributedSectionsFor(ICapellaDashboardPagesProvider contributor_p, Composite sectionContainer_p, IManagedForm managedForm_p) {
    ICapellaDashboardPageContribution systemAnalysisContribution = contributor_p.getSystemAnalysisContribution();
    // If provided.
    if (null != systemAnalysisContribution) {
      systemAnalysisContribution.createSections(sectionContainer_p, this, managedForm_p);
    }
  }
}
