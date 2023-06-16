package org.polarsys.capella.core.ui.intro.views;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.core.platform.sirius.ui.project.NewProjectAction;

public class CreateProjectHandler extends HyperLinkHandler {
@Override
public void linkActivated(HyperlinkEvent e) {
	NewProjectAction newProjectAction = new NewProjectAction();
    newProjectAction.setSite(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart()
            .getSite());
    newProjectAction.run(null);
}
}
