package org.polarsys.capella.core.ui.intro.views;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ImportResourcesAction;
import org.eclipse.ui.forms.events.HyperlinkEvent;

public class ImportProjectHandler extends HyperLinkHandler {
@Override
public void linkActivated(HyperlinkEvent e) {
	ImportResourcesAction importAction = new ImportResourcesAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
    importAction.run();
    importAction.dispose();
}
}
