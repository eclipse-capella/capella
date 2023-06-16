package org.polarsys.capella.core.ui.intro.views;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;

public class HyperLinkHandler implements IHyperlinkListener{
	
	String href;
		
	public HyperLinkHandler() {
	}
	
	public void setHRef(String h) {
		href = h;
	}

	@Override
	public void linkActivated(HyperlinkEvent ev) {
		try {
			URL url = new URL(href);
			IWorkbenchBrowserSupport support = PlatformUI.getWorkbench()
					.getBrowserSupport();
			support.getExternalBrowser().openURL(url);
		} catch (PartInitException e) {
		} catch (MalformedURLException e) {
		}
	}

	@Override
	public void linkEntered(HyperlinkEvent e) {
		
	}

	@Override
	public void linkExited(HyperlinkEvent e) {
	}
}
