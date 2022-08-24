package org.polarsys.capella.core.commands.preferences.ui.sirius;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;

/**
 * This preference page is used to enable navigation towards related diagrams on double click
 * 
 * @author etraisnel
 */
public class CapellaDiagramPreferencePage extends AbstractDefaultPreferencePage {
	public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.sirius.analysis.preferences.CapellaDiagramPreferencePage";
	public static final String PROPERTY_PAGE_TILE = "Diagram";
	public static final String GROUP_LABEL = "Scenario/Process/Chain/Path";
	public static final String NAME_PREF_DISPLAY_NAVIGATE_ON_DOUBLECLICK = "org.polarsys.capella.core.commands.preferences.ui.sirius.diagrams.navigate.doubleclick";
	public static final String LABEL_PREF_DISPLAY_NAVIGATE_ON_DOUBLECLICK = "Navigate to related diagrams on double click";
	public static final String TOOLTIP_PREF_DISPLAY_NAVIGATE_ON_DOUBLECLICK = "If only a diagram exists, directly navigate to the diagram \n"
			+ "If multiple diagrams exist, let the user decide towards which diagram navigation is made \n"
			+ "If no diagram exist , and only one kind of diagram can be created, display the diagram creation wizard \n"
			+ "If no diagram exist, but several kinds of diagram can be created, let the user decie which one to create";

	public CapellaDiagramPreferencePage() {
		super(PROPERTY_PAGE_ID);
	}


	@Override
	protected String getPageTitle() {
		return PROPERTY_PAGE_TILE;
	}

	@Override
	protected String getPageDescription() {
		return null;
	}

	@Override
	protected void createFieldEditors() {
		final Composite fieldEditorParent = getFieldEditorParent();

		 Group treeContentGroup = createGroup(GROUP_LABEL, GROUP_LABEL, fieldEditorParent);
		 
		PreferenceField fieldEditorOP1 = new PreferenceField(NAME_PREF_DISPLAY_NAVIGATE_ON_DOUBLECLICK,
				LABEL_PREF_DISPLAY_NAVIGATE_ON_DOUBLECLICK, treeContentGroup);

		addField(fieldEditorOP1, UserProfileModeEnum.User, treeContentGroup, ProjectScope.class);

		fieldEditorOP1.getChangeControl(treeContentGroup)
		.setToolTipText(TOOLTIP_PREF_DISPLAY_NAVIGATE_ON_DOUBLECLICK);
	}

}
