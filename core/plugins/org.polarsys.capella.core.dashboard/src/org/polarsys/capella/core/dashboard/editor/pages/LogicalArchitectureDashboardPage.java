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
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.actions.la.AllocateLogicalFunctionToLogicalComponentsSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.la.AllocateLogicalFunctionToLogicalComponentsSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.la.DefineLogicalComponentsActorsDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.la.DefineLogicalComponentsActorsFilteringAction;
import org.polarsys.capella.core.dashboard.actions.la.DelegateSystemInterfacesCreateLogicalInterfacesSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.la.DelegateSystemInterfacesCreateLogicalInterfacesSectionFilterinAction;
import org.polarsys.capella.core.dashboard.actions.la.EnrichLogicalScenarioSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.la.EnrichLogicalScenarioSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.la.LogicalFunctionsSystemFunctionsAction;
import org.polarsys.capella.core.dashboard.actions.la.RefineLogicalFunctionsDescribeDataflowsSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.la.RefineLogicalFunctionsDescribeDataflowsSectionFilterinAction;
import org.polarsys.capella.core.dashboard.actions.la.TransitionFromSFSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.la.TransitionFromSFSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.la.TransverseModelingDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.la.TransverseModelingFilteringAction;
import org.polarsys.capella.core.dashboard.actions.util.PopupMenuLinkAdapter;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPageContribution;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider;
import org.polarsys.capella.core.dashboard.editor.pages.preferences.ILogicalArchitecturePreferences;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.ExecuteGlobalRefinementOfAllSaCapabilitiesAnsScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewAllocationLcLfMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewBlankFunctionalDataflowDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewClassDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewComponentBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewExchangeScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewFunctionalBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewFunctionalScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewInternalInterfaceDiagramOnLSAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewLogicalArchitectureDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewScenarioDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewStateMachineDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.NewStateModeFunctionsMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.PerformAutomatedCapabilitiesTransitionAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.PerformAutomatedTransitionAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.PerformAutomatedTransitionOfSystemActorsAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.PerformAutomatedTransitionOfSystemAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;

/**
 * Base class to implement the Logical Architecture dashboard page.
 */
public class LogicalArchitectureDashboardPage extends AbstractCapellaArchitectureDashboardPage {
  /**
   * Page id. This id would be used to contribute sections to this page.
   */
  public static final String PAGE_ID = "org.polarsys.capella.core.dashboard.editor.pages.LogicalArchitectureDashboardPage"; //$NON-NLS-1$
  /**
   * Transition from System Functions Section.
   */
  private Section _sectionTransitionFromSFSection;
  /**
   * Create Refine logical functions, describe dataflows Section.
   */
  private Section _sectionRefineLogicalFunctionsDescribeDataflows;
  /**
   * Create Define Logical Components and Actors Section.
   */
  private Section _sectionDefineLogicalComponentsActors;
  /**
   * Create Allocate logical function to logical components Section.
   */
  private Section _sectionAllocateLogicalFunctionToLogicalComponents;
  /**
   * Create Delegate System Interfaces and create Logical Interfaces Section.
   */
  private Section _sectionDelegateSystemInterfacesCreateLogicalInterfaces;
  /**
   * Enrich logical scenario Section.
   */
  private Section _sectionEnrichLogicalScenario;
  /**
   * Create Transverse modeling Section.
   */
  private Section _sectionTransverseModeling;

  /**
   * Constructor.
   * @param editor_p
   */
  public LogicalArchitectureDashboardPage(FormEditor editor_p) {
    super(editor_p, PAGE_ID);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#adjustImageHRef(org.eclipse.ui.forms.widgets.FormText)
   */
  @Override
  protected void adjustImageHRef(FormText richText_p, CapellaDashboardActivator activator_p) {
    richText_p.setImage(SystemAnalysisDashboardPage.PAGE_ID, activator_p.getImage("full/overview/la/logicalarchitecture_overview_01.png")); //$NON-NLS-1$
    richText_p.setImage(PAGE_ID, activator_p.getImage("full/overview/la/logicalarchitecture_overview_02.png")); //$NON-NLS-1$
    richText_p.setImage(PhysicalArchitectureDashboardPage.PAGE_ID, activator_p.getImage("full/overview/la/logicalarchitecture_overview_03.png")); //$NON-NLS-1$
  }

  /**
   * Create Allocate logical function to logical components Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createAllocateLogicalFunctionToLogicalComponents(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new AllocateLogicalFunctionToLogicalComponentsSectionDescriptionAction(sectionContainer_p.getShell()),
                       new AllocateLogicalFunctionToLogicalComponentsSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.LogicalArchitectureDashboardPage_AllocateLogicalFunctionToLogicalComponentsSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Logical Architecture diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_AF_LOGICAL_ARCHITECTURE_DIAGRAM),
        Messages.LogicalArchitectureDashboardPage_NewLogicalArchitectureDiagram_Title, null, null, new NewLogicalArchitectureDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Exchange Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_EXCHANGE_SCENARIO),
        Messages.LogicalArchitectureDashboardPage_NewExchangeScenario_Title, null, null, new NewExchangeScenarioAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create an new allocation Logical Component / Logical Function matrix.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_ALLOCATIONS_MATRIX),
        Messages.LogicalArchitectureDashboardPage_NewAllocationLcLfMatrix_Title, null, null, new NewAllocationLcLfMatrixAdapter(getSession()));
    return section.getKey();
  }

  /**
   * Create Define Logical Components and Actors Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDefineLogicalComponentsActors(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DefineLogicalComponentsActorsDescriptionAction(sectionContainer_p.getShell()),
                       new DefineLogicalComponentsActorsFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.LogicalArchitectureDashboardPage_DefineLogicalComponentsActorsSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Perform an automated transition of System Actors.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.LogicalArchitectureDashboardPage_PerformAutomatedTransitionOfSystemActors_Title, null,
        Messages.LogicalArchitectureDashboardPage_PerformAutomatedTransitionOfSystemActors_Description, new PerformAutomatedTransitionOfSystemActorsAdapter(
            getSession()));
    // Create an hyper link for Create a new Component Breakdown diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_LOGICAL_BREAKDOWN_DIAGRAM),
        Messages.LogicalArchitectureDashboardPage_NewComponentBreakdownDiagram_Title, null, null, new NewComponentBreakdownDiagramAdapter(getSession()));
    // Create an hyper link for Create a new Logical Architecture diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_LOGICAL_ARCHITECTURE_DIAGRAM),
        Messages.LogicalArchitectureDashboardPage_NewLogicalArchitectureDiagram_Title, null, null, new NewLogicalArchitectureDiagramAdapter(
            getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Create Delegate System Interfaces and create Logical Interfaces Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDelegateSystemInterfacesCreateLogicalInterfaces(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DelegateSystemInterfacesCreateLogicalInterfacesSectionDescriptionAction(sectionContainer_p.getShell()),
                       new DelegateSystemInterfacesCreateLogicalInterfacesSectionFilterinAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.LogicalArchitectureDashboardPage_DelegateSystemInterfacesCreateLogicalInterfacesSection_Title, null, false,
            MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Perform an automated transition of System Interfaces and Ports.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.LogicalArchitectureDashboardPage_PerformAutomatedTransitionOfSystem_Title, null,
        Messages.LogicalArchitectureDashboardPage_PerformAutomatedTransitionOfSystem_Description, new PerformAutomatedTransitionOfSystemAdapter(getSession()));
    // Create an hyper link for Create a new internal interface diagram on the Logical System.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_INTERNAL_INTERFACE_DIAGRAM),
        Messages.LogicalArchitectureDashboardPage_NewInternalInterfaceDiagramOnLS_Title, null, null, new NewInternalInterfaceDiagramOnLSAdapter(
            getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Enrich logical scenario Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createEnrichLogicalScenario(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new EnrichLogicalScenarioSectionDescriptionAction(sectionContainer_p.getShell()), new EnrichLogicalScenarioSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.LogicalArchitectureDashboardPage_EnrichLogicalScenarioSection_Title, null, false, MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();

    // Create an hyper link for Execute a transition of all System Analisys Capabilities to a Logical Architecture.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.LogicalArchitectureDashboardPage_PerformTransitionOfCapabilities_Title, null,
        Messages.LogicalArchitectureDashboardPage_PerformTransitionOfCapabilities_Description, new PerformAutomatedCapabilitiesTransitionAdapter(
            ModelQueryHelper.getSystemAnalysis(getCapellaProject()), getSession()));

    // Create an hyper link for Execute a global refinement of all System Analysis Capabilities and Scenarios.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.LogicalArchitectureDashboardPage_ExecuteGlobalRefinementOfAllSaCapabilitiesAndScenario_Title, null,
        Messages.LogicalArchitectureDashboardPage_ExecuteGlobalRefinementOfAllSaCapabilitiesAndScenario_Description,
        new ExecuteGlobalRefinementOfAllSaCapabilitiesAnsScenarioAdapter(getSession()));
    // Create an hyper link for Create a new Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_INTERFACE_SCENARIO),
        Messages.LogicalArchitectureDashboardPage_NewScenario_Title, null, null, new NewScenarioDiagramAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Create Refine logical functions, describe dataflows Section.
   * @param sectionContainer_p
   * @param managedForm_p RefineSystemFunctionsDescribeDataflows
   * @return
   */
  protected Section createRefineLogicalFunctionsDescribeFunctionalExchanges(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new RefineLogicalFunctionsDescribeDataflowsSectionDescriptionAction(sectionContainer_p.getShell()),
                       new RefineLogicalFunctionsDescribeDataflowsSectionFilterinAction(this) };

    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.LogicalArchitectureDashboardPage_RefineLogicalFunctionsDescribeFunctionalExchangesSection_Title, null, false,
            MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Functional Breakdown diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_FUNCTIONAL_BREAKDOWN_DIAGRAM),
        Messages.LogicalArchitectureDashboardPage_NewFunctionalBreakdownDiagram_Title, null, null, new NewFunctionalBreakdownDiagramAdapter(getSession()));
    // Create an hyper link for Create a new Blank Functional Dataflow diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_BLANK_FUNCTIONAL_DATAFLOW_DIAGRAM),
        Messages.LogicalArchitectureDashboardPage_NewBlankFunctionalDataflowDiagram_Title, null, null, new NewBlankFunctionalDataflowDiagramAdapter(
            getSession()));
    // Create an hyper link for Create a new Functional Scenario diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_FUNCTIONAL_SCENARIO_DIAGRAM),
        Messages.LogicalArchitectureDashboardPage_NewFunctionalScenarioAction_Title, null, null, new NewFunctionalScenarioAdapter(getSession()));
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
    // Create Transition from System Functions Section.
    if (AbstractPreferencesInitializer.getBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_SF, true)) {
      _sectionTransitionFromSFSection = createTransitionFromSFSection(sectionContainer_p, managedForm_p);
    }
    // Create Refine logical functions, describe dataflows Section.
    if (AbstractPreferencesInitializer.getBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_LOGICAL_FUNCTIONS_DESCRIBE_FE, true)) {
      _sectionRefineLogicalFunctionsDescribeDataflows = createRefineLogicalFunctionsDescribeFunctionalExchanges(sectionContainer_p, managedForm_p);
    }
    // Create Define Logical Components and Actors Section.
    if (AbstractPreferencesInitializer.getBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_LOGICAL_COMPONENTS_ACTORS, true)) {
      _sectionDefineLogicalComponentsActors = createDefineLogicalComponentsActors(sectionContainer_p, managedForm_p);
    }
    // Create Allocate logical function to logical components Section.
    if (AbstractPreferencesInitializer.getBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_LOGICAL_FUNCTION_TO_LOGICAL_COMPONENTS, true)) {
      _sectionAllocateLogicalFunctionToLogicalComponents = createAllocateLogicalFunctionToLogicalComponents(sectionContainer_p, managedForm_p);
    }
    // Create Delegate System Interfaces and create Logical Interfaces Section.
    if (AbstractPreferencesInitializer.getBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_SYSTEM_INTERFACES_AND_CREATE_LOGICAL_INTERFACES,
        true)) {
      _sectionDelegateSystemInterfacesCreateLogicalInterfaces = createDelegateSystemInterfacesCreateLogicalInterfaces(sectionContainer_p, managedForm_p);
    }
    // Enrich logical scenario Section.
    if (AbstractPreferencesInitializer.getBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_LOGICAL_SCENARIO, true)) {
      _sectionEnrichLogicalScenario = createEnrichLogicalScenario(sectionContainer_p, managedForm_p);
    }
    // Create Transverse modeling Section.
    if (AbstractPreferencesInitializer.getBoolean(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true)) {
      _sectionTransverseModeling =
          createTransverseModeling(sectionContainer_p, managedForm_p, new TransverseModelingFilteringAction(this), new TransverseModelingDescriptionAction(
              sectionContainer_p.getShell()), new NewClassDiagramAdapter(getSession()), new NewStateMachineDiagramAdapter(getCapellaProject(), getSession()),
              new NewStateModeFunctionsMatrixAdapter(getCapellaProject(), getSession()), null);
    }
  }

  /**
   * Create Transition from System Functions Section.
   * @param sectionContainer_p
   * @param managedForm_p
   */
  protected Section createTransitionFromSFSection(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new TransitionFromSFSectionDescriptionAction(sectionContainer_p.getShell()), new TransitionFromSFSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p, Messages.LogicalArchitectureDashboardPage_TransitionFromSFSection_Title,
            null, false, MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Perform an automated transition of System Functions.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.LogicalArchitectureDashboardPage_PerformAutomatedTransition_Title, null,
        Messages.LogicalArchitectureDashboardPage_PerformAutomatedTransition_Description, new PerformAutomatedTransitionAdapter(getSession()));
    // Create an hyper link for Create Traceability Matrix.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_TRACEABILITY_MATRIX),
        Messages.LogicalArchitectureDashboardPage_CreateTraceabilityMatrix_Title, null, null, new PopupMenuLinkAdapter() {
          @Override
          protected void fillPopupMenu(IMenuManager menuManager_p) {
            Project capellaProject = getCapellaProject();
            // Add Logical Functions / System Functions action.
            menuManager_p.add(new LogicalFunctionsSystemFunctionsAction(capellaProject, getSession()));
          }
        });
    return section.getKey();
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#doPropertyChange(org.eclipse.jface.util.PropertyChangeEvent,
   *      boolean, java.lang.String)
   */
  @Override
  protected boolean doPropertyChange(PropertyChangeEvent event_p, boolean value_p, String property_p) {
    boolean refreshNeeded = false;
    if ((property_p).endsWith(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_SF)) {
      if (value_p) {
        _sectionTransitionFromSFSection = createTransitionFromSFSection(getSectionContainer(), getManagedForm());
        _sectionTransitionFromSFSection.layout();// Force to have the toolbar correctly displayed.
      } else {
        if (_sectionTransitionFromSFSection != null) {
          _sectionTransitionFromSFSection.dispose();
        }
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(ILogicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_LOGICAL_FUNCTIONS_DESCRIBE_FE)) {
      if (value_p) {
        _sectionRefineLogicalFunctionsDescribeDataflows = createRefineLogicalFunctionsDescribeFunctionalExchanges(getSectionContainer(), getManagedForm());
        _sectionRefineLogicalFunctionsDescribeDataflows.layout();// Force to have the toolbar correctly displayed.
      } else {
        if (_sectionRefineLogicalFunctionsDescribeDataflows != null) {
          _sectionRefineLogicalFunctionsDescribeDataflows.dispose();
        }
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_LOGICAL_COMPONENTS_ACTORS)) {
      if (value_p) {
        _sectionDefineLogicalComponentsActors = createDefineLogicalComponentsActors(getSectionContainer(), getManagedForm());
        _sectionDefineLogicalComponentsActors.layout();// Force to have the toolbar correctly displayed.
      } else if (_sectionDefineLogicalComponentsActors != null) {
        _sectionDefineLogicalComponentsActors.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_LOGICAL_FUNCTION_TO_LOGICAL_COMPONENTS)) {
      if (value_p) {
        _sectionAllocateLogicalFunctionToLogicalComponents = createAllocateLogicalFunctionToLogicalComponents(getSectionContainer(), getManagedForm());
        _sectionAllocateLogicalFunctionToLogicalComponents.layout();// Force to have the toolbar correctly displayed.
      } else if (_sectionAllocateLogicalFunctionToLogicalComponents != null) {
        _sectionAllocateLogicalFunctionToLogicalComponents.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(ILogicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_SYSTEM_INTERFACES_AND_CREATE_LOGICAL_INTERFACES)) {
      if (value_p) {
        _sectionDelegateSystemInterfacesCreateLogicalInterfaces =
            createDelegateSystemInterfacesCreateLogicalInterfaces(getSectionContainer(), getManagedForm());
        _sectionDelegateSystemInterfacesCreateLogicalInterfaces.layout();// Force to have the toolbar correctly displayed.
      } else if (_sectionDelegateSystemInterfacesCreateLogicalInterfaces != null) {
        _sectionDelegateSystemInterfacesCreateLogicalInterfaces.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(ILogicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_LOGICAL_SCENARIO)) {
      if (value_p) {
        _sectionEnrichLogicalScenario = createEnrichLogicalScenario(getSectionContainer(), getManagedForm());
        _sectionEnrichLogicalScenario.layout();// Force to have the toolbar correctly displayed.
      } else if (_sectionEnrichLogicalScenario != null) {
        _sectionEnrichLogicalScenario.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(ILogicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING)) {
      if (value_p) {
        _sectionTransverseModeling =
            createTransverseModeling(getSectionContainer(), getManagedForm(), new TransverseModelingFilteringAction(this),
                new TransverseModelingDescriptionAction(PlatformUI.getWorkbench().getDisplay().getActiveShell()), new NewClassDiagramAdapter(getSession()),
                new NewStateMachineDiagramAdapter(getCapellaProject(), getSession()),
                new NewStateModeFunctionsMatrixAdapter(getCapellaProject(), getSession()), null);
        _sectionTransverseModeling.layout();// Force to have the toolbar correctly displayed.
      } else if (_sectionTransverseModeling != null) {
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
    return IViewpointNameConstants.LOGICAL_ARCHITECTURE_VIEWPOINT_NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getFilteringMetaClassForCommonViewpoint() {
    return LaPackage.Literals.LOGICAL_ARCHITECTURE;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getOverviewFileName()
   */
  @Override
  protected String getOverviewFileName() {
    return "logical-architecture.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageDescriptionFileName()
   */
  @Override
  protected String getPageDescriptionFileName() {
    return "description/logical-architecture-description.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.LogicalArchitectureDashboardPage_Title;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#handleContributedSectionsFor(org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider,
   *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void handleContributedSectionsFor(ICapellaDashboardPagesProvider contributor_p, Composite sectionContainer_p, IManagedForm managedForm_p) {
    ICapellaDashboardPageContribution logicalArchitectureContribution = contributor_p.getLogicalArchitectureContribution();
    // If provided.
    if (null != logicalArchitectureContribution) {
      logicalArchitectureContribution.createSections(sectionContainer_p, this, managedForm_p);
    }
  }
}
