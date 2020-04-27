/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.preferences.configuration.project.ui.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE.SharedImages;
import org.polarsys.capella.core.preferences.configuration.project.ConfigurationProject;
import org.polarsys.capella.core.preferences.configuration.project.nature.ConfigurationProjectNature;
import org.polarsys.capella.core.preferences.project.configuration.project.ConfigurationPlugin;

/**
 * A class which to show swt widgets with available viewpoints.
 */
public final class ReferecedConfigurationProjectSelectionPage extends WizardPage {

  /*
	 * 
	 */
  private static final String[] COLUMNS = { " ", "icon", "project name" };

  /*
	 * 
	 */
  final TableViewerAwareCellModifier cellModifier;

  final IBaseLabelProvider labelProvider;

  private Set<ConfigurationProject> configurationProjects;

  /**
   * @param pageName_p
   */
  public ReferecedConfigurationProjectSelectionPage(String pageName_p, String[] projectsNaturesIds) {
    this(pageName_p, "Referenced Capella Configuration Project", ConfigurationPlugin
        .getImageDescriptor(ConfigurationPlugin.PROJECT_WIZARD_CONFIGURATION_FOLDER_IMG), projectsNaturesIds);
    initilizeSelectedProject(projectsNaturesIds);
  }

  /**
   * @param pageName_p
   */
  public ReferecedConfigurationProjectSelectionPage(String pageName_p, String title, ImageDescriptor imageDescriptor_p, String[] all) {
    super(pageName_p, title, imageDescriptor_p);
    initilizeSelectedProject(all);
    this.labelProvider = new WizardTableLabelProvider();
    this.cellModifier = new WizardPageTableCellModifier(configurationProjects, this.getContainer());
  }

  /**
   * @param projectsNaturesIds_p
   */
  private void initilizeSelectedProject(String[] projectsNaturesIds_p) {

    configurationProjects = new HashSet<ConfigurationProject>();
    IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (IProject project : projects) {
      if (isProjectAutorized(project, projectsNaturesIds_p)) {
        configurationProjects.add(new ConfigurationProject(project, Boolean.FALSE));
      }
    }
  }

  /**
   * 
   */
  public List<IProject> getSelectedConfigurationsProjects() {
    List<IProject> projects = new ArrayList<IProject>();
    if ((configurationProjects != null) && !configurationProjects.isEmpty()) {
      for (ConfigurationProject configurationProject : configurationProjects) {
        if (configurationProject.getIsSelected().booleanValue() && !projects.contains(configurationProject.getProject())) {
          projects.add(configurationProject.getProject());
        }
      }
    }
    return projects;
  }

  /**
   * @param iProject_p
   * @param projectsNaturesIds_p
   * @return
   */
  private boolean isProjectAutorized(IProject iProject_p, String[] projectsNaturesIds_p) {
    IProjectNature nature = null;
    for (String element : projectsNaturesIds_p) {

      try {
        nature = iProject_p.getNature(element);
      } catch (CoreException exception_p) {
      }
      if (nature != null) {
        return true;
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  public void createControl(Composite composite) {
    final Composite control = new Composite(composite, SWT.None);

    final GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 3;
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    gridLayout.makeColumnsEqualWidth = false;
    control.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
    control.setLayout(gridLayout);

    final TableViewer tableViewer = new TableViewer(control, SWT.BORDER | SWT.FULL_SELECTION);
    // TODOMCH do something great with html
    ColumnViewerToolTipSupport.enableFor(tableViewer, ToolTip.NO_RECREATE);

    final Table table = tableViewer.getTable();

    table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    final TableColumn tc0 = new TableColumn(table, SWT.CENTER, 0);
    tc0.setWidth(30);

    final TableColumn tc1 = new TableColumn(table, SWT.CENTER, 1);
    tc1.setWidth(30);

    final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.LEFT, 2);
    column.getColumn().setWidth(450);

    table.setSize(new Point(table.getSize().x, 510));

    // Can only changes the first column - the visible column
    final CellEditor[] editors = new CellEditor[3];
    editors[0] = new CheckboxCellEditor(table);
    for (int i = 1; i < 3; i++) {
      editors[i] = null;
    }

    tableViewer.setColumnProperties(COLUMNS);

    tableViewer.setCellEditors(editors);
    cellModifier.setViewer(tableViewer);
    tableViewer.setCellModifier(cellModifier);
    tableViewer.setContentProvider(new WizardTableContentProvider());

    tableViewer.setLabelProvider(labelProvider);
    tableViewer.setComparator(new ViewerComparator());

    tableViewer.setInput(configurationProjects);

    /* Lines and headers are not visible */
    table.setLinesVisible(false);
    table.setHeaderVisible(false);

    this.setControl(control);
  }

  @Override
  public boolean isPageComplete() {
    return super.isPageComplete();
  }

  /**
   * An common interface which adds a set viewer method.
   */
  private interface TableViewerAwareCellModifier extends ICellModifier {
    /**
     * Set the table viewer to update.
     * @param viewer the viewer to update
     */
    void setViewer(final TableViewer viewer);
  }

  /**
   * An common abstract class for cell modifiers.
   */
  private abstract static class AbstractWizardTableCellModifier implements TableViewerAwareCellModifier {

    protected TableViewer tableViewer;

    /** ll viewpoints and there selection state. */
    protected final Set<ConfigurationProject> configurationProjects;

    /**
     * Cosntructor.
     * @param configurationProjects All viewpoints and there selection state.
     */
    public AbstractWizardTableCellModifier(final Set<ConfigurationProject> configurationProjects) {
      this.configurationProjects = configurationProjects;
    }

    /**
     * {@inheritDoc}
     */
    public void setViewer(final TableViewer viewer) {
      this.tableViewer = viewer;
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(final Object element, final String property) {

      if (property.equals(COLUMNS[0])) {
        /* first column */
        return true;
      }
      return false;
    }

  }

  /**
   * The content provider.
   */
  private static final class WizardTableContentProvider implements IStructuredContentProvider {

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
      if (inputElement instanceof Set<?>) {
        return ((Set<?>) inputElement).toArray();

      }
      return Collections.EMPTY_LIST.toArray();
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
      // nothing to do
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
      // nothing to do
    }
  }

  /**
   * The label provider
   */
  private static final class WizardTableLabelProvider extends ColumnLabelProvider {

    private int columnIndex;

    /**
     * Constructor.
     * @param viewpoints the viewpoints
     */
    public WizardTableLabelProvider() {
      super();

    }

    private Image getOtherProjectIcon() {
      IWorkbench workbench = PlatformUI.getWorkbench();
      ISharedImages sharedImages = workbench.getSharedImages();
      Image image = sharedImages.getImage(SharedImages.IMG_OBJ_PROJECT);
      image = ConfigurationPlugin.getOverlayedDescriptor(image, "capella_8x8.png").createImage();
      return image;
    }

    private Image getConfigurationProjectIcon() {
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      Image image = sharedImages.getImage(SharedImages.IMG_OBJ_PROJECT);
      image = ConfigurationPlugin.getOverlayedDescriptor(image, "config_8x8.gif").createImage();
      return image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(final Object element) {
      Image image = null;

      switch (columnIndex) {
        case 0:
          if (element instanceof ConfigurationProject) {
            final ConfigurationProject vp = (ConfigurationProject) element;
            image = ConfigurationPlugin.getImageDescriptor("disabled_checkbox.gif").createImage();
            if (vp.getIsSelected()) {
              image = ConfigurationPlugin.getImageDescriptor("enabled_checkbox.gif").createImage();
            }
          }
        break;
        case 1:
          if (element instanceof ConfigurationProject) {
            final ConfigurationProject vp = (ConfigurationProject) element;
            image = getProjectIcon(vp.getProject());
          }
        break;
        case 2:
        break;
        default:
        break;
      }
      return image;
    }

    /**
     * @param project_p
     * @return
     */
    private Image getProjectIcon(IProject project_p) {
      IProjectNature nature = ConfigurationProjectNature.hasConfigurationProject(project_p);
      if (nature != null) {
        return getConfigurationProjectIcon();
      }
      return getOtherProjectIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(final Object element) {
      switch (columnIndex) {
        case 2:
          if (element instanceof ConfigurationProject) {
            return ((ConfigurationProject) element).getProject().getName();
          }
        break;
        default:
        break;
      }
      return null;
    }

    @Override
    public String getToolTipText(final Object element) {
      String toolTip = "";
      if ((columnIndex == 2) && (element instanceof ConfigurationProject)) {
        ConfigurationProject viewpoint = (ConfigurationProject) element;

        try {
          toolTip += viewpoint.getProject().getDescription().getName();
        } catch (CoreException exception_p) {
        	// catch silently the exception
        }

      }
      return toolTip;
    }

    @Override
    public Point getToolTipShift(final Object object) {
      return new Point(5, 5);
    }

    @Override
    public int getToolTipDisplayDelayTime(final Object object) {
      return 200;
    }

    @Override
    public void update(final ViewerCell cell) {
      columnIndex = cell.getColumnIndex();
      super.update(cell);
    }

    @Override
    public int getToolTipStyle(final Object object) {
      return SWT.SHADOW_OUT;
    }

  }

  /**
   * A cell modifier which applies to a wizard page.
   */
  private static class WizardPageTableCellModifier extends ConfigurationProjectTableLazyCellModifier {

    private final IWizardContainer wizardContainer;

    /**
     * .
     */
    public WizardPageTableCellModifier(final Set<ConfigurationProject> configurationProjects, final IWizardContainer wizardContainer) {
      super(configurationProjects);
      this.wizardContainer = wizardContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modify(final Object element, final String property, final Object value) {
      super.modify(element, property, value);

      if (property.equals(COLUMNS[0]) && (this.wizardContainer != null)) {
        this.wizardContainer.updateButtons();
      }
    }
  }

  /**
   * The cell modifier which only modifies the input map.
   */
  private static class ConfigurationProjectTableLazyCellModifier extends AbstractWizardTableCellModifier {

    /**
     * Constructor.
     * @param configurationProjects All viewpoints and there selection state.
     */
    public ConfigurationProjectTableLazyCellModifier(final Set<ConfigurationProject> configurationProjects) {
      super(configurationProjects);
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final Object element, final String property) {

      final ConfigurationProject tableProjectConfiguration = (ConfigurationProject) element;
      Object result = Boolean.FALSE;

      if (property.equals(COLUMNS[0])) {

        if (!tableProjectConfiguration.getProject().isOpen() || !tableProjectConfiguration.getProject().isAccessible()) {
          MessageDialog.openError(Display.getCurrent().getActiveShell(), "Closed Configuration Project", "The selected Project must be opened and accessible");

        } else {

          for (ConfigurationProject configurationProject : configurationProjects) {
            ConfigurationProject currentProject = configurationProject;
            if (tableProjectConfiguration.getProject().getName().equals(currentProject.getProject().getName()) && currentProject.getProject().isOpen()
                && currentProject.getProject().isAccessible() && tableProjectConfiguration.getIsSelected()) {
              result = Boolean.TRUE;
              break;
            }
          }
        }

      } else if (property.equals(COLUMNS[1])) {
        /* second column */
        // do nothing as there is only an image
      } else {
        /* third column */
        result = tableProjectConfiguration.getProject().getName();
      }
      return result;
    }

    /**
     * {@inheritDoc}
     */
    public void modify(final Object element, final String property, final Object value) {

      Object objElement;

      if (element instanceof Item) {

        objElement = ((Item) element).getData();
      } else {
        objElement = element;
      }

      if (property.equals(COLUMNS[0])) {
        final ConfigurationProject vp = (ConfigurationProject) objElement;

        // Convert Object to Boolean without instanceof
        final Boolean result = Boolean.valueOf(Boolean.TRUE.equals(value));
        vp.setIsSelected(result);

        /* update the label provider */
        this.tableViewer.update(vp, null);
      }
    }
  }

}
