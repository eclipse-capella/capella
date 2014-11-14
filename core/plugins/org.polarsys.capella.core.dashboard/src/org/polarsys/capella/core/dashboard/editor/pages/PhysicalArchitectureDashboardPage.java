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
import org.polarsys.capella.core.dashboard.actions.pa.AllocatePhysicalFunctionToPhysicalComponentsSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.pa.AllocatePhysicalFunctionToPhysicalComponentsSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.pa.DefinePhysicalComponentsActorsDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.pa.DefinePhysicalComponentsActorsFilteringAction;
import org.polarsys.capella.core.dashboard.actions.pa.DelegateLogicalInterfacesCreatePhysicalInterfacesSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.pa.DelegateLogicalInterfacesCreatePhysicalInterfacesSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.pa.EnrichPhysicalScenarioSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.pa.EnrichPhysicalScenarioSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.pa.PhysicalFunctionsLogicalFunctionsAction;
import org.polarsys.capella.core.dashboard.actions.pa.RefinePhysicalFunctionsDescribeDataflowsSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.pa.RefinePhysicalFunctionsDescribeDataflowsSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.pa.TransitionFromLFSectionDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.pa.TransitionFromLFSectionFilteringAction;
import org.polarsys.capella.core.dashboard.actions.pa.TransverseModelingDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.pa.TransverseModelingFilteringAction;
import org.polarsys.capella.core.dashboard.actions.util.PopupMenuLinkAdapter;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPageContribution;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider;
import org.polarsys.capella.core.dashboard.editor.pages.preferences.IPhysicalArchitecturePreferences;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.la.PerformAutomatedCapabilitiesTransitionAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.ExecuteGlobalRefinementOfAllSaCapabilitiesAndScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewAllocationPcPfMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewBlankFunctionalDataflowDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewClassDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewComponentBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewExchangeScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewFunctionalBreakdownDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewFunctionalScenarioAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewInternalInterfaceDiagramOnPSAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewPhysicalArchitectureDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewPhysicalComponentLogicalComponentMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewScenarioDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewStateMachineDiagramAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.NewStateModeFunctionsMatrixAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.PerformAutomatedTransitionAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.PerformAutomatedTransitionOfLogicalActorsAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.pa.PerformAutomatedTransitionOfLogicalComponentsAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.sirius.analysis.IViewpointNameConstants;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

/**
 * Base class to implement the Physical Architecture dashboard page.
 */
public class PhysicalArchitectureDashboardPage extends AbstractCapellaArchitectureDashboardPage {
  /**
   * Page id. This id would be used to contribute sections to this page.
   */
  public static final String PAGE_ID = "org.polarsys.capella.core.dashboard.editor.pages.PhysicalArchitectureDashboardPage"; //$NON-NLS-1$
  /**
   * Create Transition from Logical Functions Section.
   */
  private Section _sectionTransitionFromLFSection;
  /**
   * Create Refine Physical functions, describe dataflows Section.
   */
  private Section _sectionRefinePhysicalFunctionsDescribeDataflows;
  /**
   * Create Define Physical Components and Actors Section.
   */
  private Section _sectionDefinePhysicalComponentsActors;
  /**
   * Create Allocate Physical functions to Physical components and manage deployments Section.
   */
  private Section _sectionAllocatePhysicalFunctionToPhysicalComponents;
  /**
   * Create Delegate Logical Interfaces and create Physical Interfaces Section.
   */
  private Section _sectionDelegateLogicalInterfacesCreatePhysicalInterfaces;
  /**
   * Enrich Physical scenario Section.
   */
  private Section _sectionEnrichPhysicalScenario;
  /**
   * Create Transverse modeling Section.
   */
  private Section _sectionTransverseModeling;

  /**
   * Constructor.
   * @param editor_p
   */
  public PhysicalArchitectureDashboardPage(FormEditor editor_p) {
    super(editor_p, PAGE_ID);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#adjustImageHRef(org.eclipse.ui.forms.widgets.FormText)
   */
  @Override
  protected void adjustImageHRef(FormText richText_p, CapellaDashboardActivator activator_p) {
    richText_p.setImage(LogicalArchitectureDashboardPage.PAGE_ID, activator_p.getImage("full/overview/pa/physicalarchitecture_overview_01.png")); //$NON-NLS-1$
    richText_p.setImage(PAGE_ID, activator_p.getImage("full/overview/pa/physicalarchitecture_overview_02.png")); //$NON-NLS-1$
    // If EPBS architecture exists, load its image.
    if (null != ModelQueryHelper.getEPBSArchitecture(getCapellaProject())) {
      richText_p.setImage(EpbsArchitectureDashboardPage.PAGE_ID, activator_p.getImage("full/overview/pa/physicalarchitecture_overview_03.png")); //$NON-NLS-1$
    }
  }

  /**
   * Create Allocate Physical functions to Physical components and manage deployments Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createAllocatePhysicalFunctionToPhysicalComponents(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new AllocatePhysicalFunctionToPhysicalComponentsSectionDescriptionAction(sectionContainer_p.getShell()),
                       new AllocatePhysicalFunctionToPhysicalComponentsSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.PhysicalArchitectureDashboardPage_AllocatePhysicalFunctionToPhysicalComponentsSection_Title, null, false,
            MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Physical Architecture diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator
        .getImage(IImageKeys.IMG_NEW_PHYSICAL_ARCHITECTURE_FUNCTIONS_DIAGRAM), Messages.PhysicalArchitectureDashboardPage_NewPhysicalArchitectureDiagram_Title,
        null, null, new NewPhysicalArchitectureDiagramAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Exchange Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_EXCHANGE_SCENARIO),
        Messages.PhysicalArchitectureDashboardPage_NewExchangeScenario_Title, null, null, new NewExchangeScenarioAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create an new allocation Physical Component / Physical Function matrix.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_ALLOCATIONS_MATRIX),
        Messages.PhysicalArchitectureDashboardPage_NewAllocationPcPfMatrix_Title, null, null, new NewAllocationPcPfMatrixAdapter(getCapellaProject(),
            getSession()));
    return section.getKey();
  }

  /**
   * Create Define Physical Components and Actors Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDefinePhysicalComponentsActors(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DefinePhysicalComponentsActorsDescriptionAction(sectionContainer_p.getShell()),
                       new DefinePhysicalComponentsActorsFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.PhysicalArchitectureDashboardPage_DefinePhysicalComponentsActorsSection_Title, null, false, MiscHelper.asList(toolbarActions));

    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Perform an automated transition of Logical Actors.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.PhysicalArchitectureDashboardPage_PerformAutomatedTransitionOfLogicalActors_Title, null,
        Messages.PhysicalArchitectureDashboardPage_PerformAutomatedTransitionOfLogicalActors_Description, new PerformAutomatedTransitionOfLogicalActorsAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Perform an automated transition of Logical Components.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.PhysicalArchitectureDashboardPage_PerformAutomatedTransitionOfLogicalComponents_Title, null,
        Messages.PhysicalArchitectureDashboardPage_PerformAutomatedTransitionOfLogicalComponents_Description,
        new PerformAutomatedTransitionOfLogicalComponentsAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Component Breakdown diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_PHYSICAL_BREAKDOWN_DIAGRAM),
        Messages.PhysicalArchitectureDashboardPage_NewComponentBreakdownDiagram_Title, null, null, new NewComponentBreakdownDiagramAdapter(getCapellaProject(),
            getSession()));
    // Create an hyper link for Create a new Physical Architecture diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_PHYSICAL_ARCHITECTURE_DIAGRAM),
        Messages.PhysicalArchitectureDashboardPage_NewPhysicalArchitectureDiagram_Title, null, null, new NewPhysicalArchitectureDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Physical Component / Logical Component Matrix.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_TRACEABILITY_MATRIX),
        Messages.PhysicalArchitectureDashboardPage_NewPhysicalComponentLogicalComponentMatrix_Title, null, null,
        new NewPhysicalComponentLogicalComponentMatrixAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Create Delegate Logical Interfaces and create Physical Interfaces Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createDelegateLogicalInterfacesCreatePhysicalInterfaces(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new DelegateLogicalInterfacesCreatePhysicalInterfacesSectionDescriptionAction(sectionContainer_p.getShell()),
                       new DelegateLogicalInterfacesCreatePhysicalInterfacesSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.PhysicalArchitectureDashboardPage_DelegateLogicalInterfacesCreatePhysicalInterfacesSection_Title, null, false,
            MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new internal interface diagram on the Physical System.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_INTERNAL_INTERFACE_DIAGRAM),
        Messages.PhysicalArchitectureDashboardPage_NewInternalInterfaceDiagramOnPS_Title, null, null, new NewInternalInterfaceDiagramOnPSAdapter(
            getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Enrich Physical scenario Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected Section createEnrichPhysicalScenario(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new EnrichPhysicalScenarioSectionDescriptionAction(sectionContainer_p.getShell()),
                       new EnrichPhysicalScenarioSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.PhysicalArchitectureDashboardPage_EnrichPhysicalScenarioSection_Title, null, false, MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();

    // Create an hyper link for Execute a transition of all Logical Architecture Capabilities.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.PhysicalArchitectureDashboardPage_PerformTransitionOfCapabilities_Title, null,
        Messages.PhysicalArchitectureDashboardPage_PerformTransitionOfCapabilities_Description, new PerformAutomatedCapabilitiesTransitionAdapter(
            getCapellaProject(), ModelQueryHelper.getLogicalArchitecture(getCapellaProject()), getSession()));

    // Create an hyper link for Execute a global refinement of all System Analysis Capabilities and Scenarios.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.PhysicalArchitectureDashboardPage_ExecuteGlobalRefinementOfAllSaCapabilitiesAndScenario_Title, null,
        Messages.PhysicalArchitectureDashboardPage_ExecuteGlobalRefinementOfAllSaCapabilitiesAndScenario_Description,
        new ExecuteGlobalRefinementOfAllSaCapabilitiesAndScenarioAdapter(getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Scenario.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_INTERFACE_SCENARIO),
        Messages.PhysicalArchitectureDashboardPage_NewScenario_Title, null, null, new NewScenarioDiagramAdapter(getCapellaProject(), getSession()));
    return section.getKey();
  }

  /**
   * Create Refine physical functions, describe functional exchanges Section.
   * @param sectionContainer_p
   * @param managedForm_p RefineSystemFunctionsDescribeDataflows
   * @return
   */
  protected Section createRefinePhysicalFunctionsDescribeFunctionalExchanges(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new RefinePhysicalFunctionsDescribeDataflowsSectionDescriptionAction(sectionContainer_p.getShell()),
                       new RefinePhysicalFunctionsDescribeDataflowsSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.PhysicalArchitectureDashboardPage_RefinePhysicalFunctionsDescribeFunctionalExchangesSection_Title, null, false,
            MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Create a new Functional Breakdown diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_FUNCTIONAL_BREAKDOWN_DIAGRAM),
        Messages.PhysicalArchitectureDashboardPage_NewFunctionalBreakdownDiagram_Title, null, null, new NewFunctionalBreakdownDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Blank Functional Dataflow diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_BLANK_FUNCTIONAL_DATAFLOW_DIAGRAM),
        Messages.PhysicalArchitectureDashboardPage_NewBlankFunctionalDataflowDiagram_Title, null, null, new NewBlankFunctionalDataflowDiagramAdapter(
            getCapellaProject(), getSession()));
    // Create an hyper link for Create a new Functional Scenario diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_FUNCTIONAL_SCENARIO_DIAGRAM),
        Messages.PhysicalArchitectureDashboardPage_NewFunctionalScenarioAction_Title, null, null, new NewFunctionalScenarioAdapter(getCapellaProject(),
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
    // Create Overview section.
    createOverviewSection(sectionContainer_p, managedForm_p);
    // Create Transition from Logical Functions Section.
    if (AbstractPreferencesInitializer.getBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_LF, true)) {
      _sectionTransitionFromLFSection = createTransitionFromLFSection(sectionContainer_p, managedForm_p);
    }
    // Create Refine Physical functions, describe dataflows Section.
    if (AbstractPreferencesInitializer.getBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_PHYSICAL_FUNCTIONS_DESCRIBE_FE, true)) {
      _sectionRefinePhysicalFunctionsDescribeDataflows = createRefinePhysicalFunctionsDescribeFunctionalExchanges(sectionContainer_p, managedForm_p);
    }
    // Create Define Physical Components and Actors Section.
    if (AbstractPreferencesInitializer.getBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_PHYSICAL_COMPONENTS_ACTORS, true)) {
      _sectionDefinePhysicalComponentsActors = createDefinePhysicalComponentsActors(sectionContainer_p, managedForm_p);
    }
    // Create Allocate Physical functions to Physical components and manage deployments Section.
    if (AbstractPreferencesInitializer.getBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_PHYSICAL_FUNCTION_TO_PHYSICAL_COMPONENTS, true)) {
      _sectionAllocatePhysicalFunctionToPhysicalComponents = createAllocatePhysicalFunctionToPhysicalComponents(sectionContainer_p, managedForm_p);
    }
    // Create Delegate Logical Interfaces and create Physical Interfaces Section.
    if (AbstractPreferencesInitializer.getBoolean(
        IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_LOGICAL_INTERFACES_AND_CREATE_PHYSICAL_INTERFACES, true)) {
      _sectionDelegateLogicalInterfacesCreatePhysicalInterfaces = createDelegateLogicalInterfacesCreatePhysicalInterfaces(sectionContainer_p, managedForm_p);
    }
    // Enrich Physical scenario Section.
    if (AbstractPreferencesInitializer.getBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_PHYSICAL_SCENARIO, true)) {
      _sectionEnrichPhysicalScenario = createEnrichPhysicalScenario(sectionContainer_p, managedForm_p);
    }
    // Create Transverse modeling Section.
    if (AbstractPreferencesInitializer.getBoolean(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING, true)) {
      _sectionTransverseModeling =
          createTransverseModeling(sectionContainer_p, managedForm_p, new TransverseModelingFilteringAction(this), new TransverseModelingDescriptionAction(
              sectionContainer_p.getShell()), new NewClassDiagramAdapter(getCapellaProject(), getSession()), new NewStateMachineDiagramAdapter(
              getCapellaProject(), getSession()), new NewStateModeFunctionsMatrixAdapter(getCapellaProject(), getSession()), null);
    }
  }

  /**
   * Create Transition from Logical Functions Section.
   * @param sectionContainer_p
   * @param managedForm_p
   */
  protected Section createTransitionFromLFSection(Composite sectionContainer_p, IManagedForm managedForm_p) {
    IAction[] toolbarActions =
        new IAction[] { new TransitionFromLFSectionDescriptionAction(sectionContainer_p.getShell()), new TransitionFromLFSectionFilteringAction(this) };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p, Messages.PhysicalArchitectureDashboardPage_TransitionFromLFSection_Title,
            null, false, MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Perform an automated transition of Logical Functions.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_PERFORM_AUTOMATED_TRANSITION),
        Messages.PhysicalArchitectureDashboardPage_PerformAutomatedTransition_Title, null,
        Messages.PhysicalArchitectureDashboardPage_PerformAutomatedTransition_Description, new PerformAutomatedTransitionAdapter(getCapellaProject(),
            getSession()));
    // Create an hyper link for Create Traceability Matrix.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_TRACEABILITY_MATRIX),
        Messages.PhysicalArchitectureDashboardPage_CreateTraceabilityMatrix, null, null, new PopupMenuLinkAdapter() {
          @Override
          protected void fillPopupMenu(IMenuManager menuManager_p) {
            Project capellaProject = getCapellaProject();
            // Add Physical Functions / System Functions action.
            menuManager_p.add(new PhysicalFunctionsLogicalFunctionsAction(capellaProject, getSession()));
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
    if ((property_p).endsWith(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSITION_FROM_LF)) {
      if (value_p) {
        _sectionTransitionFromLFSection = createTransitionFromLFSection(getSectionContainer(), getManagedForm());
        _sectionTransitionFromLFSection.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionTransitionFromLFSection.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_REFINE_PHYSICAL_FUNCTIONS_DESCRIBE_FE)) {
      if (value_p) {
        _sectionRefinePhysicalFunctionsDescribeDataflows = createRefinePhysicalFunctionsDescribeFunctionalExchanges(getSectionContainer(), getManagedForm());
        _sectionRefinePhysicalFunctionsDescribeDataflows.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionRefinePhysicalFunctionsDescribeDataflows.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DEFINE_PHYSICAL_COMPONENTS_ACTORS)) {
      if (value_p) {
        _sectionDefinePhysicalComponentsActors = createDefinePhysicalComponentsActors(getSectionContainer(), getManagedForm());
        _sectionDefinePhysicalComponentsActors.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionDefinePhysicalComponentsActors.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ALLOCATE_PHYSICAL_FUNCTION_TO_PHYSICAL_COMPONENTS)) {
      if (value_p) {
        _sectionAllocatePhysicalFunctionToPhysicalComponents = createAllocatePhysicalFunctionToPhysicalComponents(getSectionContainer(), getManagedForm());
        _sectionAllocatePhysicalFunctionToPhysicalComponents.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionAllocatePhysicalFunctionToPhysicalComponents.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_DELEGATE_LOGICAL_INTERFACES_AND_CREATE_PHYSICAL_INTERFACES)) {
      if (value_p) {
        _sectionDelegateLogicalInterfacesCreatePhysicalInterfaces =
            createDelegateLogicalInterfacesCreatePhysicalInterfaces(getSectionContainer(), getManagedForm());
        _sectionDelegateLogicalInterfacesCreatePhysicalInterfaces.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionDelegateLogicalInterfacesCreatePhysicalInterfaces.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_ENRICH_PHYSICAL_SCENARIO)) {
      if (value_p) {
        _sectionEnrichPhysicalScenario = createEnrichPhysicalScenario(getSectionContainer(), getManagedForm());
        _sectionEnrichPhysicalScenario.layout();// Force to have the toolbar correctly displayed.
      } else {
        _sectionEnrichPhysicalScenario.dispose();
      }
      refreshNeeded = true;
    } else if ((property_p).endsWith(IPhysicalArchitecturePreferences.PREFERENCE_SECTION_TRANSVERSE_MODELING)) {
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
    return IViewpointNameConstants.PHYSICAL_ARCHITECTURE_VIEWPOINT_NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getFilteringMetaClassForCommonViewpoint() {
    return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getOverviewFileName()
   */
  @Override
  protected String getOverviewFileName() {
    String fileName = "physical-architecture.xml"; //$NON-NLS-1$
    if (null == ModelQueryHelper.getEPBSArchitecture(getCapellaProject())) {
      fileName = "physical-architecture2.xml"; //$NON-NLS-1$
    }
    return fileName;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageDescriptionFileName()
   */
  @Override
  protected String getPageDescriptionFileName() {
    return "description/physical-architecture-description.xml"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#getPageTitle()
   */
  @Override
  protected String getPageTitle() {
    return Messages.PhysicalArchitectureDashboardPage_Title;
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage#handleContributedSectionsFor(org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider,
   *      org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void handleContributedSectionsFor(ICapellaDashboardPagesProvider contributor_p, Composite sectionContainer_p, IManagedForm managedForm_p) {
    ICapellaDashboardPageContribution physicalArchitectureContribution = contributor_p.getPhysicalArchitectureContribution();
    // If provided.
    if (null != physicalArchitectureContribution) {
      physicalArchitectureContribution.createSections(sectionContainer_p, this, managedForm_p);
    }
  }
}
