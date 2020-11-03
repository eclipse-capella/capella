/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.preferences;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;

public class DiagramProcessChainPathPreferencePage extends AbstractDefaultPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.sirius.analysis.preferences.DiagramsPreferencePage"; //$NON-NLS-1$

  public static final String LABEL_GROUP_FUNCTIONAL_CHAIN = "Functional Chain";
  public static final String NAME_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL = "org.polarsys.capella.core.sirius.analysis.preferences.diagrams.functionalchain.label.incomplete";
  public static final String LABEL_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL = "Show 'Incomplete' in the chain's label";
  public static final String TOOLTIP_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL = "If one of the exchanges on the chain is not visible";
  public static final String NAME_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL = "org.polarsys.capella.core.sirius.analysis.preferences.diagrams.functionalchain.label.invalid";
  public static final String LABEL_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL = "Show 'Invalid' in the chain's label";
  public static final String TOOLTIP_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL = "If the chain is not valid";

  public static final String LABEL_GROUP_PHYSICAL_PATH = "Physical Path";
  public static final String NAME_PREF_DISPLAY_INCOMPLETE_IN_PHYSICAL_PATH_LABEL = "org.polarsys.capella.core.sirius.analysis.preferences.diagrams.physicalpath.label.incomplete";
  public static final String LABEL_PREF_DISPLAY_INCOMPLETE_IN_PHYSICAL_PATH_LABEL = "Show 'Incomplete' in the path's label";
  public static final String TOOLTIP_PREF_DISPLAY_INCOMPLETE_IN_PHYSICAL_PATH_LABEL = "If one of the links on the path is not visible";
  public static final String NAME_PREF_DISPLAY_INVALID_IN_PHYSICAL_PATH_LABEL = "org.polarsys.capella.core.sirius.analysis.preferences.diagrams.physicalpath.label.invalid";
  public static final String LABEL_PREF_DISPLAY_INVALID_IN_PHYSICAL_PATH_LABEL = "Show 'Invalid' in the path's label";
  public static final String TOOLTIP_PREF_DISPLAY_INVALID_IN_PHYSICAL_PATH_LABEL = "If the path is not valid";
  
  public static final String LABEL_GROUP_OPERATIONAL_PROCESS = "Operational Process";
  public static final String NAME_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL = "org.polarsys.capella.core.sirius.analysis.preferences.diagrams.operationalprocess.label.incomplete";
  public static final String LABEL_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL = "Show 'Incomplete' in the process's label";
  public static final String TOOLTIP_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL = "If one of the interactions on the process is not visible";
  public static final String NAME_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL = "org.polarsys.capella.core.sirius.analysis.preferences.diagrams.operationalprocess.label.invalid";
  public static final String LABEL_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL = "Show 'Invalid' in the process's label";
  public static final String TOOLTIP_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL = "If the process is not valid";

  public DiagramProcessChainPathPreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  @Override
  protected String getPageTitle() {
    return "Diagrams";
  }

  @Override
  protected String getPageDescription() {
    return "Preferences for displaying diagrams";
  }

  @Override
  protected void createFieldEditors() {

    final Composite fieldEditorParent = getFieldEditorParent();
    
    // Preference for Operational Process
    Group groupOperationalProcess = createGroup(LABEL_GROUP_OPERATIONAL_PROCESS, LABEL_GROUP_OPERATIONAL_PROCESS,
        fieldEditorParent);

    PreferenceField fieldEditorOP1 = new PreferenceField(NAME_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL,
        LABEL_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL, groupOperationalProcess);
    addField(fieldEditorOP1, UserProfileModeEnum.Expert, groupOperationalProcess, ProjectScope.class);
    fieldEditorOP1.getChangeControl(groupOperationalProcess)
        .setToolTipText(TOOLTIP_PREF_DISPLAY_INCOMPLETE_IN_OPERATIONAL_PROCESS_LABEL);

    PreferenceField fieldEditorOP2 = new PreferenceField(NAME_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL,
        LABEL_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL, groupOperationalProcess);
    addField(fieldEditorOP2, UserProfileModeEnum.Expert, groupOperationalProcess, ProjectScope.class);
    fieldEditorOP2.getChangeControl(groupOperationalProcess)
        .setToolTipText(TOOLTIP_PREF_DISPLAY_INVALID_IN_OPERATIONAL_PROCESS_LABEL);

    // Preference for Functional Chain
    Group groupFunctionalChain = createGroup(LABEL_GROUP_FUNCTIONAL_CHAIN, LABEL_GROUP_FUNCTIONAL_CHAIN,
        fieldEditorParent);

    PreferenceField fieldEditorFC1 = new PreferenceField(NAME_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL,
        LABEL_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL, groupFunctionalChain);
    addField(fieldEditorFC1, UserProfileModeEnum.Expert, groupFunctionalChain, ProjectScope.class);
    fieldEditorFC1.getChangeControl(groupFunctionalChain)
        .setToolTipText(TOOLTIP_PREF_DISPLAY_INCOMPLETE_IN_FUNCTIONAL_CHAIN_LABEL);

    PreferenceField fieldEditorFC2 = new PreferenceField(NAME_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL,
        LABEL_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL, groupFunctionalChain);
    addField(fieldEditorFC2, UserProfileModeEnum.Expert, groupFunctionalChain, ProjectScope.class);
    fieldEditorFC2.getChangeControl(groupFunctionalChain)
        .setToolTipText(TOOLTIP_PREF_DISPLAY_INVALID_IN_FUNCTIONAL_CHAIN_LABEL);

    // Preference for Physical Path
    Group groupPhysicalPath = createGroup(LABEL_GROUP_PHYSICAL_PATH, LABEL_GROUP_PHYSICAL_PATH, fieldEditorParent);

    PreferenceField fieldEditorPP1 = new PreferenceField(NAME_PREF_DISPLAY_INCOMPLETE_IN_PHYSICAL_PATH_LABEL,
        LABEL_PREF_DISPLAY_INCOMPLETE_IN_PHYSICAL_PATH_LABEL, groupPhysicalPath);
    addField(fieldEditorPP1, UserProfileModeEnum.Expert, groupPhysicalPath, ProjectScope.class);
    fieldEditorPP1.getChangeControl(groupPhysicalPath)
        .setToolTipText(TOOLTIP_PREF_DISPLAY_INCOMPLETE_IN_PHYSICAL_PATH_LABEL);

    PreferenceField fieldEditorPP2 = new PreferenceField(NAME_PREF_DISPLAY_INVALID_IN_PHYSICAL_PATH_LABEL,
        LABEL_PREF_DISPLAY_INVALID_IN_PHYSICAL_PATH_LABEL, groupPhysicalPath);
    addField(fieldEditorPP2, UserProfileModeEnum.Expert, groupPhysicalPath, ProjectScope.class);
    fieldEditorPP2.getChangeControl(groupPhysicalPath)
        .setToolTipText(TOOLTIP_PREF_DISPLAY_INVALID_IN_PHYSICAL_PATH_LABEL);
  }

}
