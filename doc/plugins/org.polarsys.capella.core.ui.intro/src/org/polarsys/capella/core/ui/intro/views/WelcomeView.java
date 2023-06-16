package org.polarsys.capella.core.ui.intro.views;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.PerformanceStats;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.HyperlinkGroup;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.internal.intro.impl.IIntroConstants;
import org.eclipse.ui.internal.intro.impl.IntroPlugin;
import org.eclipse.ui.internal.intro.impl.model.loader.ContentProviderManager;
import org.eclipse.ui.internal.intro.impl.util.Log;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.part.IntroPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class WelcomeView extends IntroPart{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.polarsys.capella.core.ui.intro.views.WelcomeView";
	public static final String EXTENSION_POINT_ID = "org.polarsys.capella.core.ui.intro";
	public static final String EXTENSION_POINT_ID2 = "org.eclipse.ui.intro.configExtension";	
	
	Image banner_icon = null;
	Image compass_icon = null;
	Font titleTextFont = null;
	Font introTextFont = null;
		
	public WelcomeView() {
		contributions = new LinkedHashSet<WelcomePageContribution>();
	}
	
	@Override
	public void init(IIntroSite site, IMemento memento)
			throws PartInitException {
		super.init(site, memento);
		IntroPlugin.getDefault().closeLaunchBar();
		// load the correct model based in the current Intro Part id. Set the
		// IntroPartId in the manager class.
	}
	

	@Inject IWorkbench workbench;
	
	private Set<WelcomePageContribution> contributions;
	

	@Override
	public void createPartControl(Composite parent) {
				
		
		GridLayout parentGridLayout = new GridLayout();
		parentGridLayout.numColumns = 1;
		parent.setLayout(parentGridLayout);
		
		final Canvas viewCanvas = new Canvas (parent, SWT.FILL );
		GridData viewCanvasLayoutData = new GridData();
		viewCanvasLayoutData.horizontalAlignment = SWT.FILL;
		viewCanvasLayoutData.heightHint = 180 ;
		viewCanvasLayoutData.grabExcessHorizontalSpace = true;
		viewCanvas.setLayoutData(viewCanvasLayoutData);
		
		ImageDescriptor imgDesc = AbstractUIPlugin.imageDescriptorFromPlugin(EXTENSION_POINT_ID, "icons/ov_banner.jpg");		
		try {
	    banner_icon =  imgDesc.createImage();
		}catch(Exception e ) {
			
		}
		final Image finalIcon = banner_icon;
		
		ImageDescriptor imgDesc2 = AbstractUIPlugin.imageDescriptorFromPlugin(EXTENSION_POINT_ID, "icons/firststeps-select.png");
		
		try {
			compass_icon =  imgDesc2.createImage();
		}catch(Exception e ) {
			
		}
		
		final Image finalIcon2 = compass_icon;
		
		viewCanvas.addListener (SWT.Paint, e -> {
			GC gc = e.gc;			
			
            Rectangle rect = finalIcon.getBounds();
			Rectangle client = viewCanvas.getClientArea ();
            

			gc.drawImage(finalIcon, rect.x, rect.y, rect.width, rect.height, client.x, client.y, client.width, rect.height );
			gc.drawImage(finalIcon2, 10, 20);
			gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
			gc.fillRectangle(rect.x, rect.height, client.width, client.height - rect.height);   
			//gc.setAlpha(0);
			titleTextFont = new Font(e.display, "Verdana", 21, SWT.NORMAL);

			
			gc.setFont(titleTextFont);
			gc.drawText("First Steps", 75, 90, true);
			
			introTextFont = new Font(e.display, "Verdana", 10, SWT.NORMAL);
			
			gc.setFont(introTextFont);
			gc.drawText("Getting started with Capella ", 75, 130, true);
			
		});
		
		viewCanvas.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Composite itemsComposite = new Composite(parent, SWT.FILL);	
		itemsComposite.setBackgroundMode(SWT.INHERIT_FORCE);
		itemsComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		GridData itemsCompositeLayoutData = new GridData();
		itemsComposite.setLayoutData(itemsCompositeLayoutData);
		
		GridLayout itemsLayout = new GridLayout();
		itemsComposite.setLayout(itemsLayout);		
		itemsLayout.numColumns = 2;
		
		initializeContributions();
		HyperlinkGroup group = new HyperlinkGroup(itemsComposite.getDisplay());
		for(WelcomePageContribution contrib : contributions) {
			Composite contribComposite = new Composite(itemsComposite, SWT.FILL);
			
			
			
			GridLayout contribLayout = new GridLayout();
			contribComposite.setLayout(contribLayout);		
			contribLayout.numColumns = 1;
			
			ImageHyperlink hyperLink = new ImageHyperlink(contribComposite, SWT.None);
			group.add(hyperLink);
			hyperLink.setText(contrib.getTitle());
			hyperLink.setHref(contrib.getLink());
			hyperLink.setImage(contrib.getIcon());
			
			hyperLink.addHyperlinkListener(contrib.getHandler());
			
			
			Label descriptionLabel = new Label(contribComposite, SWT.NONE);
			descriptionLabel.setText(contrib.getDescription());
		}		
	}
		
	private void initializeContributions() {
		contributions = new LinkedHashSet<WelcomePageContribution>();
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for(IConfigurationElement aConfigurationElement : configurationElements){
			for(IConfigurationElement welcomePageContribution : aConfigurationElement.getChildren("welcome_contribution")) {
				String label = welcomePageContribution.getAttribute("label"); 
				String description = welcomePageContribution.getAttribute("desc"); 
				String link = welcomePageContribution.getAttribute("link");
				String id = welcomePageContribution.getAttribute("id");
				String iconKey = welcomePageContribution.getAttribute("icon");
			
				IConfigurationElement[] children =  welcomePageContribution.getChildren("handler");
				
				
				HyperLinkHandler handler = null;
				
				if(children.length != 0) {
					IConfigurationElement handlerExt = children[0];
					String clazz = handlerExt.getAttribute("class");
					if(clazz != null && !clazz.isBlank()) {
						try {
							handler = (HyperLinkHandler) handlerExt.createExecutableExtension("class");
						} catch (CoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}					
				}
				
				ImageDescriptor imgDesc = AbstractUIPlugin.imageDescriptorFromPlugin(EXTENSION_POINT_ID, iconKey);
				Image icon = null;
				try {
			    icon =  imgDesc.createImage();
				}catch(Exception e ) {
					
				}
				WelcomePageContribution contribution = new WelcomePageContribution(label, description, id, link, icon, handler);
				contributions.add(contribution);					
			}
			
		}
		
	}

	/*
	 * Handled state changes. Recreates the standby part if workbench was shut
	 * down with one.
	 *
	 * @see org.eclipse.ui.IIntroPart#standbyStateChanged(boolean)
	 */
	@Override
	public void standbyStateChanged(boolean standby) {

	}


	@Override
	public void setFocus() {
	}

	@Override
	public void dispose() {
		if(compass_icon != null) compass_icon.dispose();
		if(banner_icon != null) banner_icon.dispose();
		if(introTextFont != null) introTextFont.dispose();
		if(titleTextFont != null) titleTextFont.dispose();

		for(WelcomePageContribution contrib : contributions) {
			Image icon = contrib.getIcon();
			if(icon != null) icon.dispose();
		}
		super.dispose();
		// clear all loaded models since we are disposing of the Intro Part.
		IntroPlugin.getDefault().getExtensionPointManager().clear();
		ContentProviderManager.getInst().clear();
	}



	@Override
	public void saveState(IMemento memento) {
	}

}
