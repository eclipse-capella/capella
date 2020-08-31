/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.wizards;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistryFactory;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.sections.IAbstractSection;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

/**
 */
public class EditCapellaCustomPropertyWizardPage extends WizardPage implements ITabbedPropertySheetPageContributor {

  private FormToolkit toolkit;

  private HelpListener helpListener = null;
  private Composite comp = null;

  private Collection<ISection> sections = null;

  private IWorkbenchPart part;

  /**
   * Constructor.
   * 
   * @param pageName
   * @param element
   * @param metaclassLabel
   */
  public EditCapellaCustomPropertyWizardPage(IWorkbenchPart part, String pageName, EObject element,
      String metaclassLabel) {
    super(pageName);
    // Configure page title and description.
    setTitle(NamingHelper.getTitleLabel(element));
    setDescription(NLS.bind(Messages.editCapellaCustomPropertyWizardPage_Description, metaclassLabel));

    ImageDescriptor imageDescriptor = CapellaUIResourcesPlugin.getDefault().getPNGImage(element);
    if (imageDescriptor != null) {
      setImageDescriptor(imageDescriptor);
    }
    this.part = part;
  }

  /**
   * Create a default composite to host page widgets.
   * 
   * @param parent
   * @param numColumns
   * @return
   */
  private Composite createComposite(Composite parent, FormToolkit toolkit) {
    ScrolledForm scrolledContainer = toolkit.createScrolledForm(parent);
    setControl(scrolledContainer);
    // Get the content of this scrolled form.
    Composite content = scrolledContainer.getBody();
    content.setLayoutData(new GridData(GridData.FILL_BOTH));
    content.setLayout(new GridLayout());
    return content;
  }

  protected boolean sectionDisplayedOnWizard(ISectionDescriptor section) {
    // Expert view is not synchronized with other sections, so if the user change a value in it.
    // it is not reflected on other tabs.
    return !CapellaUIPropertiesPlugin.CAPELLA_EXPERT_SECTION.equals(section.getId());
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent) {
    TabbedPropertyRegistry registry = TabbedPropertyRegistryFactory.getInstance().createRegistry(this);
    IStructuredSelection selection = new StructuredSelection(getEObject());

    sections = new ArrayList<ISection>();
    toolkit = new FormToolkit(parent.getDisplay());
    toolkit.setBackground(parent.getBackground());
    // Create parent composite.
    Composite content = createComposite(parent, toolkit);
    // Initialize dialog units.
    initializeDialogUnits(content);

    // Get the edited model element.
    EObject object = getEObject();

    // Create a Tab folder to store each section per tab.
    CTabFolder tabFolder = new CTabFolder(content, SWT.BORDER);
    tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
    tabFolder.setSimple(false);

    tabFolder.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        Object data = tabFolder.getSelection().getData();
        if (data instanceof ISection) {
          ((ISection) data).refresh();
        }
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }
    });

    for (ITabDescriptor descriptor : registry.getTabDescriptors(part, selection)) {
      Composite tabItemContent = new Composite(tabFolder, SWT.NONE);
      tabItemContent.setLayout(new GridLayout());
      tabItemContent.setLayoutData(new GridData(GridData.FILL_BOTH));

      List<ISectionDescriptor> descriptors = ((List<Object>) descriptor.getSectionDescriptors()).stream()
          .map(ISectionDescriptor.class::cast).filter(x -> sectionDisplayedOnWizard(x)).collect(Collectors.toList());

      if (!descriptors.isEmpty()) {
        CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
        tabItem.setText(descriptor.getLabel());
        tabItem.setControl(tabItemContent);

        for (ISectionDescriptor sd : descriptors) {
          ISectionDescriptor sectionDescriptor = (ISectionDescriptor) sd;
          ISection section = sectionDescriptor.getSectionClass();
          if (section != null) {
              Composite sectionComposite = new Composite(tabItemContent, SWT.NONE);
              sectionComposite.setLayout(new FillLayout());
              int style = (section.shouldUseExtraSpace()) ? GridData.FILL_BOTH : GridData.FILL_HORIZONTAL;
              GridData data = new GridData(style);
              data.heightHint = section.getMinimumHeight();
              sectionComposite.setLayoutData(data);
              section.createControls(sectionComposite, null);
              section.setInput(part, new StructuredSelection(object));
              tabItem.setData(section);
              sections.add(section);
            }
          }
        }

    }

    FormHelper.adaptBackgroundColor((Control) content, content.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND),
        false);

    //
    // Help on wizards
    //
    final IWorkbenchHelpSystem helpSystem = PlatformUI.getWorkbench().getHelpSystem();
    final EClass eclass = object.eClass();

    if ((null != comp) && (null != helpListener)) {
      comp.removeHelpListener(helpListener);
    }

    helpListener = new HelpListener() {
      private boolean isHelpAvailable() {
        return checkIfHelpIsAvailable(eclass);
      }

      protected boolean checkIfHelpIsAvailable(EClass eclass) {
        try (InputStream inputStream = HelpSystem.getHelpContent(generateHTMLLink(eclass))) {
          return ((null != eclass) && (null != inputStream));
        } catch (Exception e) {
          // Fail silently
        }
        return false;
      }

      public void helpRequested(HelpEvent e) {
        IContext c = new IContext() {
          @SuppressWarnings("synthetic-access")
          public String getText() {
            if (!isHelpAvailable()) {
              return NLS.bind(Messages.editCapellaCustomPropertyWizardPage_MainDescriptionKO, eclass.getName());
            }
            return null;
          }

          @SuppressWarnings("synthetic-access")
          public IHelpResource[] getRelatedTopics() {
            IHelpResource hr = null;
            if (isHelpAvailable()) {
              hr = new IHelpResource() {
                public String getLabel() {
                  return NLS.bind(Messages.editCapellaCustomPropertyWizardPage_RLLinkName, eclass.getName());
                }

                public String getHref() {
                  return generateHTMLLink(eclass);
                }
              };
            }
            return (null != hr ? new IHelpResource[] { hr } : null);
          }

        };

        helpSystem.displayContext(c, 0, 0);
      }

      protected String generateHTMLLink(EClass eclass) {
        return "/org.polarsys.capella.core.doc.user/html/editors/" + //$NON-NLS-1$
        eclass.getEPackage().getName() + ICommonConstants.SLASH_CHARACTER + eclass.getName()
            + ICommonConstants.POINT_CHARACTER + "html"; //$NON-NLS-1$
      }
    };

    comp = parent;
    comp.addHelpListener(helpListener);
  }

  /**
   * @see org.eclipse.jface.dialogs.DialogPage#dispose()
   */
  @Override
  public void dispose() {
    if (null != toolkit) {
      toolkit.dispose();
      toolkit = null;
    }
    if ((null != comp) && (null != helpListener)) {
      comp.removeHelpListener(helpListener);
      helpListener = null;
      comp.dispose();
      comp = null;
    }

    // memory leaks, sections weren't disposed
    if (null != sections) {
      for (ISection section : sections) {
        section.dispose();
      }
      sections = null;
    }

    TabbedPropertyRegistryFactory.getInstance().disposeRegistry(this);

  }

  /**
   * Get the model element that the wizard is open for.
   * 
   * @return a not <code>null</code> element.
   */
  protected EObject getEObject() {
    return ((EditCapellaCustomPropertyWizard) getWizard()).getEObject();
  }

  public void performFinish() {
    // Propagate the Finish action to sections
    sections.stream().filter(IAbstractSection.class::isInstance).map(IAbstractSection.class::cast)
        .forEach(IAbstractSection::performFinish);
  }

  public Collection<ISection> getSections() {
    return sections;
  }

  @Override
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }
  
}
