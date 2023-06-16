package org.polarsys.capella.core.ui.intro.views;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkEvent;

public class HelpHyperLinkHandler extends HyperLinkHandler{
	
	public HelpHyperLinkHandler() {
	}

	@Override
	public void linkActivated(HyperlinkEvent ev) {
		String localHRef = PlatformUI.getWorkbench().getHelpSystem().resolve(href, true).toExternalForm();
		PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(localHRef);
	}

}
