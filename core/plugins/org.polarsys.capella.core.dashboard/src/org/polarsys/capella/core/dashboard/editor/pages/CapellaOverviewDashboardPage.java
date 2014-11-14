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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.actions.util.FormTextPageLinkAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.ui.editor.CapellaDashboardEditorInput;

/**
 * Capella Overview page that behaves as a dashboard. That displays navigable links to other pages.
 */
public class CapellaOverviewDashboardPage extends AbstractCapellaDashboardPage {
  class OverviewPageLinkAdapter extends FormTextPageLinkAdapter {
    /**
     * @param editor_p
     */
    public OverviewPageLinkAdapter(FormEditor editor_p) {
      super(editor_p);
    }

    /**
     * @see org.polarsys.capella.core.dashboard.actions.util.PageLinkAdapter#linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent)
     */
    @SuppressWarnings("synthetic-access")
    @Override
    public void linkEntered(HyperlinkEvent e_p) {
      super.linkEntered(e_p);
      // Replace the original image with the one used in hover mode.
      updateImage(e_p, __hoverHrefImageMappings);
    }

    /**
     * @see org.polarsys.capella.core.dashboard.actions.util.FormTextPageLinkAdapter#linkExited(org.eclipse.ui.forms.events.HyperlinkEvent)
     */
    @SuppressWarnings("synthetic-access")
    @Override
    public void linkExited(HyperlinkEvent e_p) {
      super.linkExited(e_p);
      // Restore original image.
      updateImage(e_p, __hrefImageMappings);
    }

    private void updateImage(HyperlinkEvent e_p, Map<String, String> imageMappings_p) {
      String pageId = (String) e_p.getHref();
      // Replace image with the one got from specified map.
      String image = imageMappings_p.get(pageId);
      if (null != image) {
        ((FormText) e_p.widget).setImage(pageId, CapellaDashboardActivator.getDefault().getImage(image));
      }
    }
  }

  /**
   * Normal HREF image mappings.
   */
  private static Map<String, String> __hrefImageMappings = new HashMap<String, String>();

  /**
   * Hover HREF image mappings.
   */
  private static Map<String, String> __hoverHrefImageMappings = new HashMap<String, String>();

  static {
    __hrefImageMappings.put(OperationalAnalysisDashboardPage.PAGE_ID, IImageKeys.IMG_OPERATIONAL_ANALYSIS_OVERVIEW_PAGE);
    __hrefImageMappings.put(SystemAnalysisDashboardPage.PAGE_ID, IImageKeys.IMG_SYSTEM_ANALYSIS_OVERVIEW_PAGE);
    __hrefImageMappings.put(LogicalArchitectureDashboardPage.PAGE_ID, IImageKeys.IMG_LOGICAL_ARCHITECTURE_OVERVIEW_PAGE);
    __hrefImageMappings.put(PhysicalArchitectureDashboardPage.PAGE_ID, IImageKeys.IMG_PHYSICAL_ARCHITECTURE_OVERVIEW_PAGE);
    __hrefImageMappings.put(EpbsArchitectureDashboardPage.PAGE_ID, IImageKeys.IMG_EPBS_ARCHITECTURE_OVERVIEW_PAGE);

    __hoverHrefImageMappings.put(OperationalAnalysisDashboardPage.PAGE_ID, IImageKeys.IMG_OPERATIONAL_ANALYSIS_OVERVIEW_HOVER_PAGE);
    __hoverHrefImageMappings.put(SystemAnalysisDashboardPage.PAGE_ID, IImageKeys.IMG_SYSTEM_ANALYSIS_OVERVIEW_HOVER_PAGE);
    __hoverHrefImageMappings.put(LogicalArchitectureDashboardPage.PAGE_ID, IImageKeys.IMG_LOGICAL_ARCHITECTURE_OVERVIEW_HOVER_PAGE);
    __hoverHrefImageMappings.put(PhysicalArchitectureDashboardPage.PAGE_ID, IImageKeys.IMG_PHYSICAL_ARCHITECTURE_OVERVIEW_HOVER_PAGE);
    __hoverHrefImageMappings.put(EpbsArchitectureDashboardPage.PAGE_ID, IImageKeys.IMG_EPBS_ARCHITECTURE_OVERVIEW_HOVER_PAGE);
  }

  /**
   * Constructor.
   * @param editor_p
   * @param id_p
   * @param title_p
   */
  public CapellaOverviewDashboardPage(FormEditor editor_p) {
    super(editor_p, CapellaOverviewDashboardPage.class.getName(), Messages.AbstractCapellaArchitectureDashboardPage_OverviewSection_Title);
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaDashboardPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void createFormContent(IManagedForm managedForm_p) {
    super.createFormContent(managedForm_p);
    CapellaDashboardEditorInput editorInput = (CapellaDashboardEditorInput) getEditorInput();
    // Set the header title.
    ScrolledForm form = managedForm_p.getForm();
    form.setText(Messages.CapellaOverviewDashboardPage_Heading_Word + editorInput.getName());
    // Install a default layout.
    TableWrapLayout layout = new TableWrapLayout();
    layout.leftMargin = 0;
    layout.rightMargin = 0;
    layout.topMargin = 0;
    layout.bottomMargin = 0;
    layout.horizontalSpacing = 0;
    layout.verticalSpacing = 0;
    Composite body = form.getBody();
    body.setLayout(layout);
    CapellaDashboardActivator activator = CapellaDashboardActivator.getDefault();
    // Create the overview content.
    StringBuilder overviewContent = new StringBuilder(FileHelper.readFile(activator.getPluginId() + "/xml/introduction/overviewHeader.overview")); //$NON-NLS-1$
    Project capellaProject = editorInput.getCapellaProject();
    // Check if Operational Analysis content is needed.
    if (null != ModelQueryHelper.getOperationalAnalysis(capellaProject)) {
      overviewContent.append(FileHelper.readFile(activator.getPluginId() + "/xml/introduction/overviewOperationalAnalysis.overview")); //$NON-NLS-1$
    }
    // Add common content.
    overviewContent.append(FileHelper.readFile(activator.getPluginId() + "/xml/introduction/overviewCommonArchitectures.overview")); //$NON-NLS-1$
    // Check if EPBS Architecture content is needed.
    if (null != ModelQueryHelper.getEPBSArchitecture(capellaProject)) {
      overviewContent.append(FileHelper.readFile(activator.getPluginId() + "/xml/introduction/overviewEpbsArchitecture.overview")); //$NON-NLS-1$
    }
    // Add footer.
    overviewContent.append(FileHelper.readFile(activator.getPluginId() + "/xml/introduction/overviewFooter.overview")); //$NON-NLS-1$

    // Create the rich text that host the Capella overview.
    FormText richText = FormHelper.createRichText(managedForm_p.getToolkit(), body, overviewContent.toString(), new OverviewPageLinkAdapter(getEditor()));
    richText.setHyperlinkSettings(managedForm_p.getToolkit().getHyperlinkGroup());

    // Set image HREF.
    richText.setImage(OperationalAnalysisDashboardPage.PAGE_ID, activator.getImage(__hrefImageMappings.get(OperationalAnalysisDashboardPage.PAGE_ID)));
    richText.setImage("oa_text", activator.getImage(IImageKeys.IMG_OPERATIONAL_ANALYSIS_OVERVIEW_TEXT_PAGE)); //$NON-NLS-1$

    richText.setImage(SystemAnalysisDashboardPage.PAGE_ID, activator.getImage(__hrefImageMappings.get(SystemAnalysisDashboardPage.PAGE_ID)));
    richText.setImage("sa_text", activator.getImage(IImageKeys.IMG_SYSTEM_ANALYSIS_OVERVIEW_TEXT_PAGE)); //$NON-NLS-1$

    richText.setImage(LogicalArchitectureDashboardPage.PAGE_ID, activator.getImage(__hrefImageMappings.get(LogicalArchitectureDashboardPage.PAGE_ID)));
    richText.setImage("la_text", activator.getImage(IImageKeys.IMG_LOGICAL_ARCHITECTURE_OVERVIEW_TEXT_PAGE)); //$NON-NLS-1$

    richText.setImage(PhysicalArchitectureDashboardPage.PAGE_ID, activator.getImage(__hrefImageMappings.get(PhysicalArchitectureDashboardPage.PAGE_ID)));
    richText.setImage("pa_text", activator.getImage(IImageKeys.IMG_PHYSICAL_ARCHITECTURE_OVERVIEW_TEXT_PAGE)); //$NON-NLS-1$

    richText.setImage(EpbsArchitectureDashboardPage.PAGE_ID, activator.getImage(__hrefImageMappings.get(EpbsArchitectureDashboardPage.PAGE_ID)));
    richText.setImage("epbs_text", activator.getImage(IImageKeys.IMG_EPBS_ARCHITECTURE_OVERVIEW_TEXT_PAGE)); //$NON-NLS-1$

    // Set Layout data.
    richText.marginHeight = 0;
    richText.marginWidth = 0;
    TableWrapData layoutData = new TableWrapData();
    layoutData.align = TableWrapData.CENTER;
    layoutData.valign = TableWrapData.MIDDLE;
    richText.setLayoutData(layoutData);
  }
}
