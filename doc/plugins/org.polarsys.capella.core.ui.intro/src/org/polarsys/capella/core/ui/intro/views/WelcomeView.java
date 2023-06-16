/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.intro.views;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.HyperlinkGroup;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.internal.intro.impl.IntroPlugin;
import org.eclipse.ui.internal.intro.impl.model.loader.ContentProviderManager;
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
	Font hyperLinkFont = null;

	public WelcomeView() {
	}

	@Override
	public void init(IIntroSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		IntroPlugin.getDefault().closeLaunchBar();
		// load the correct model based in the current Intro Part id. Set the
		// IntroPartId in the manager class.
	}

	private Set<WelcomePageContribution> internalContributions;
	private Set<WelcomePageContribution> externalContributions;

	@Override
	public void createPartControl(Composite parent) {

		ScrolledComposite mainComposite = new ScrolledComposite(parent, SWT.FILL | SWT.H_SCROLL | SWT.V_SCROLL);
		mainComposite.setExpandHorizontal(true);
		mainComposite.setExpandVertical(true);
		mainComposite.setMinHeight(1000);
		mainComposite.setMinWidth(900);

		Composite intermediaryComposite = new Composite(mainComposite, SWT.FILL);
		mainComposite.setContent(intermediaryComposite);

		parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		intermediaryComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		GridLayout mainCompositeGridLayout = GridLayoutFactory.fillDefaults().create();
		mainCompositeGridLayout.numColumns = 1;
		intermediaryComposite.setLayout(mainCompositeGridLayout);
		GridData mainCompositeGridLayoutData = new GridData(GridData.FILL_BOTH);
		mainCompositeGridLayoutData.grabExcessHorizontalSpace = true;
		mainCompositeGridLayoutData.grabExcessVerticalSpace = true;
		intermediaryComposite.setLayoutData(mainCompositeGridLayoutData);

		final Canvas viewCanvas = new Canvas(intermediaryComposite, SWT.FILL);
		GridData viewCanvasLayoutData = new GridData();
		viewCanvasLayoutData.horizontalAlignment = SWT.FILL;
		viewCanvasLayoutData.heightHint = 180;
		viewCanvasLayoutData.grabExcessHorizontalSpace = true;
		viewCanvas.setLayoutData(viewCanvasLayoutData);

		ImageDescriptor imgDesc = AbstractUIPlugin.imageDescriptorFromPlugin(EXTENSION_POINT_ID, "icons/ov_banner.jpg");
		try {
			banner_icon = imgDesc.createImage();
		} catch (Exception e) {

		}
		final Image finalIcon = banner_icon;
		ImageDescriptor imgDesc2 = AbstractUIPlugin.imageDescriptorFromPlugin(EXTENSION_POINT_ID,
				"icons/firststeps-select.png");

		try {
			compass_icon = imgDesc2.createImage();
		} catch (Exception e) {

		}

		final Image finalIcon2 = compass_icon;

		viewCanvas.addListener(SWT.Paint, e -> {
			GC gc = e.gc;

			Rectangle rect = finalIcon.getBounds();
			Rectangle client = viewCanvas.getClientArea();

			gc.drawImage(finalIcon, rect.x, rect.y, rect.width, rect.height, client.x, client.y, client.width,
					rect.height);
			gc.drawImage(finalIcon2, 10, 20);
			gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
			gc.fillRectangle(rect.x, rect.height, client.width, client.height - rect.height);
			// gc.setAlpha(0);
			titleTextFont = new Font(e.display, "Verdana", 21, SWT.NORMAL);

			gc.setFont(titleTextFont);
			gc.drawText("First Steps", 75, 90, true);

			introTextFont = new Font(e.display, "Verdana", 10, SWT.NORMAL);

			gc.setFont(introTextFont);
			gc.drawText("Getting started with Capella ", 75, 130, true);

		});

		viewCanvas.setBackgroundMode(SWT.INHERIT_FORCE);

		Composite itemsComposite = new Composite(intermediaryComposite, SWT.FILL);
		itemsComposite.setBackgroundMode(SWT.INHERIT_FORCE);
		itemsComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		GridData itemsCompositeLayoutData = new GridData();
		itemsCompositeLayoutData.horizontalAlignment = SWT.FILL;
		itemsCompositeLayoutData.verticalAlignment = SWT.FILL;
		itemsComposite.setLayoutData(itemsCompositeLayoutData);

		GridLayout itemsLayout = GridLayoutFactory.fillDefaults().spacing(new Point(30, 0)).margins(new Point(30, 0))
				.create();

		itemsComposite.setLayout(itemsLayout);
		itemsLayout.numColumns = 2;

		Composite topLeftPanel = new Composite(itemsComposite, SWT.FILL);
		topLeftPanel.setBackgroundMode(SWT.INHERIT_FORCE);
		topLeftPanel.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout leftLayout = GridLayoutFactory.fillDefaults().spacing(new Point(30, 30)).margins(new Point(30, 30))
				.create();
		topLeftPanel.setLayout(leftLayout);
		topLeftPanel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		leftLayout.numColumns = 1;

		Composite topRightPanel = new Composite(itemsComposite, SWT.FILL);
		topRightPanel.setBackgroundMode(SWT.INHERIT_FORCE);
		topRightPanel.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout rightLayout = GridLayoutFactory.fillDefaults().spacing(new Point(30, 30)).margins(new Point(30, 30))
				.create();
		topRightPanel.setLayout(rightLayout);
		topRightPanel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		rightLayout.numColumns = 1;

		Composite bottomLeftPanel = new Composite(itemsComposite, SWT.FILL);
		bottomLeftPanel.setBackgroundMode(SWT.INHERIT_FORCE);
		bottomLeftPanel.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		bottomLeftPanel.setLayout(leftLayout);
		bottomLeftPanel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		leftLayout.numColumns = 1;

		Composite bottomRightPanel = new Composite(itemsComposite, SWT.FILL);
		bottomRightPanel.setBackgroundMode(SWT.INHERIT_FORCE);
		bottomRightPanel.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		bottomRightPanel.setLayout(rightLayout);
		bottomRightPanel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		rightLayout.numColumns = 1;

		internalContributions = new LinkedHashSet<WelcomePageContribution>();
		externalContributions = new LinkedHashSet<WelcomePageContribution>();

		initializeInternalContributions();
		initializeContributions();

		HyperlinkGroup group = new HyperlinkGroup(itemsComposite.getDisplay());

		int count = 0;
		for (WelcomePageContribution contrib : internalContributions) {
			// Create a Wrapper composite to contain the ImageHyperLink and its Label
			Composite parentComposite = null;
			if (count <= internalContributions.size() / 2) {
					parentComposite = topLeftPanel;
			} else {
				parentComposite = topRightPanel;
			}
			ImageHyperlink hyperlink = createHyperlinkFromContribution(contrib, parentComposite);
			// Add the HyperLink to the HyperlinkGroup
			group.add(hyperlink);

			count++;
		}

		count = 0;
		for (WelcomePageContribution contrib : externalContributions) {
			// Create a Wrapper composite to contain the ImageHyperLink and its Label
			Composite parentComposite = null;
			if (count <= externalContributions.size() / 2) {
					parentComposite = bottomLeftPanel;
			} else {
					parentComposite = bottomRightPanel;
			}
			ImageHyperlink hyperlink = createHyperlinkFromContribution(contrib, parentComposite);
			// Add the HyperLink to the HyperlinkGroup
			group.add(hyperlink);

			count++;
		}
	}

	private void initializeContributions() {
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_POINT_ID);
		List<IConfigurationElement> externalConfigurationElements = Stream.of(configurationElements)
				.filter(configurationElement -> configurationElement.getContributor() != null
						&& !configurationElement.getContributor().getName().equals(EXTENSION_POINT_ID))
				.collect(Collectors.toList());

		createContributions(externalConfigurationElements, externalContributions);
	}

	private void initializeInternalContributions() {
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_POINT_ID);


		List<IConfigurationElement> internalConfigurationElements = Stream.of(configurationElements)
				.filter(configurationElement -> configurationElement.getContributor() != null
						&& configurationElement.getContributor().getName().equals(EXTENSION_POINT_ID))
				.collect(Collectors.toList());

		createContributions(internalConfigurationElements, internalContributions);

	}

	private ImageHyperlink createHyperlinkFromContribution(WelcomePageContribution contrib, Composite parentComposite) {
		Composite contribComposite = new Composite(parentComposite, SWT.FILL);

		FillLayout contribLayout = new FillLayout(SWT.VERTICAL);
		// contribLayout.numColumns = 1;

		GridData contribLayoutData = new GridData();
		contribLayoutData.widthHint = 400;
		contribComposite.setLayout(contribLayout);
		contribComposite.setLayoutData(contribLayoutData);

		// Create a ImageHyperlink
		ImageHyperlink hyperLink = new ImageHyperlink(contribComposite, SWT.WRAP);
		hyperLink.setText(contrib.getTitle());
		hyperLink.setHref(contrib.getLink());
		hyperLink.setImage(contrib.getIcon());
		hyperLink.addHyperlinkListener(contrib.getHandler());

		if (hyperLinkFont == null) {
			// Custom BOLD Font
			Font formerFont = hyperLink.getFont();
			FontData[] formerFontData = formerFont.getFontData();
			formerFontData[0].setHeight(11);
			formerFontData[0].setStyle(formerFontData[0].getStyle() | SWT.BOLD);
			hyperLinkFont = new Font(parentComposite.getDisplay(), formerFontData[0]);

		}
		hyperLink.setFont(hyperLinkFont);

		// Create the label to display the link description
		Composite labelComposite = new Composite(contribComposite, SWT.FILL);
		GridLayout a = GridLayoutFactory.fillDefaults().extendedMargins(54, 0, 0, 0).create();
		labelComposite.setLayout(a);
		Label descriptionLabel = new Label(labelComposite, SWT.WRAP);
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		descriptionLabel.setText(contrib.getDescription());

		return hyperLink;
	}

	private void createContributions(List<IConfigurationElement> configurationElements,
			Set<WelcomePageContribution> contributions) {
		for (IConfigurationElement aConfigurationElement : configurationElements) {
			aConfigurationElement.getContributor();
			for (IConfigurationElement welcomePageContribution : aConfigurationElement
					.getChildren("welcome_contribution")) {
				String label = welcomePageContribution.getAttribute("label");
				String description = welcomePageContribution.getAttribute("desc");
				String link = welcomePageContribution.getAttribute("link");
				String id = welcomePageContribution.getAttribute("id");
				String iconKey = welcomePageContribution.getAttribute("icon");

				IConfigurationElement[] children = welcomePageContribution.getChildren("handler");

				HyperLinkHandler handler = null;

				if (children.length != 0) {
					IConfigurationElement handlerExt = children[0];
					String clazz = handlerExt.getAttribute("class");
					if (clazz != null && !clazz.isBlank()) {
						try {
							handler = (HyperLinkHandler) handlerExt.createExecutableExtension("class");
						} catch (CoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				ImageDescriptor imgDesc = AbstractUIPlugin
						.imageDescriptorFromPlugin(welcomePageContribution.getContributor().getName(), iconKey);
				Image icon = null;
				try {
					icon = imgDesc.createImage();
				} catch (Exception e) {

				}
				WelcomePageContribution contribution = new WelcomePageContribution(label, description, id, link, icon,
						handler);
				contributions.add(contribution);
			}
		}
	}

	/*
	 * Handled state changes. Recreates the standby part if workbench was shut down
	 * with one.
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
		if (compass_icon != null)
			compass_icon.dispose();
		if (banner_icon != null)
			banner_icon.dispose();
		if (introTextFont != null)
			introTextFont.dispose();
		if (titleTextFont != null)
			titleTextFont.dispose();
		if (hyperLinkFont != null)
			hyperLinkFont.dispose();
		for (WelcomePageContribution contrib : internalContributions) {
			Image icon = contrib.getIcon();
			if (icon != null)
				icon.dispose();
		}
		for (WelcomePageContribution contrib : externalContributions) {
			Image icon = contrib.getIcon();
			if (icon != null)
				icon.dispose();
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
