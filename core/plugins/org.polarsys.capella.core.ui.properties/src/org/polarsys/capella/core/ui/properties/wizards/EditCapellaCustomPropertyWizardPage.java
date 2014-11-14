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
package org.polarsys.capella.core.ui.properties.wizards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.eclipse.ui.views.properties.tabbed.ISection;

import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.sections.IAbstractSection;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

/**
 */
public class EditCapellaCustomPropertyWizardPage extends WizardPage {
  private ScrolledForm _scrolledContainer;
  private FormToolkit _toolkit;

  private HelpListener _helpListener = null;
  private Composite _comp = null;

  private Collection<ISection> _sections = null;

  /**
   * Constructor.
   * @param pageName_p
   * @param element_p
   * @param metaclassLabel_p
   */
  public EditCapellaCustomPropertyWizardPage(String pageName_p, EObject element_p, String metaclassLabel_p) {
    super(pageName_p);
    // Configure page title and description.
    setTitle(metaclassLabel_p);
    setDescription(Messages.EditCapellaCustomPropertyWizardPage_Description + metaclassLabel_p);

    ImageDescriptor imageDescriptor = CapellaUIResourcesPlugin.getDefault().getPNGImage(element_p);
    if (imageDescriptor != null) {
      setImageDescriptor(imageDescriptor);
    }
  }

  /**
   * Create a default composite to host page widgets.
   * @param parent_p
   * @param numColumns_p
   * @return
   */
  private Composite createComposite(Composite parent_p, FormToolkit toolkit_p) {
    _scrolledContainer = toolkit_p.createScrolledForm(parent_p);
    setControl(_scrolledContainer);
    // Get the content of this scrolled form.
    Composite content = _scrolledContainer.getBody();
    content.setLayoutData(new GridData(GridData.FILL_BOTH));
    content.setLayout(new GridLayout());
    return content;
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent_p) {
    _toolkit = new FormToolkit(parent_p.getDisplay());
    _toolkit.setBackground(parent_p.getBackground());
    // Create parent composite.
    Composite content = createComposite(parent_p, _toolkit);
    // Initialize dialog units.
    initializeDialogUnits(content);
    // Create a Tab folder to store each section per tab.
    CTabFolder tabFolder = new CTabFolder(content, SWT.BORDER);
    tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
    tabFolder.setSimple(false);
    // Get the edited model element.
    EObject object = getEObject();
    // Load the Custom section according to given element.
    Map<String, IAbstractSection> sections = CustomPropertyHelper.getCustomPropertySection(object, CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR, false);
    Iterator<Entry<String, IAbstractSection>> entries = sections.entrySet().iterator();
    while (entries.hasNext()) {
      // Get the current entry.
      Entry<String, IAbstractSection> entry = entries.next();
      // Get the section itself.
      IAbstractSection section = entry.getValue();
      // Check if a filter is applied to this properties section for the given modelElement
      if (section.select(object)) {
        if (_sections == null) {
          _sections = new ArrayList<ISection>();
        }
        _sections.add(section);
        // Create a new composite hosted by the new entry section tab item.
        Composite tabItemContent = new Composite(tabFolder, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        tabItemContent.setLayout(layout);
        // Create a tab item for the current entry section.
        CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
        tabItem.setControl(tabItemContent);
        // Set tab item name with value got from section extension.
        tabItem.setText(entry.getKey());
        // Set appropriate background color.
        section.setParentBackgroundColor(parent_p.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        section.createControls(tabItemContent, null);
        section.loadData(object);
      }
    }
    FormHelper.adaptBackgroundColor(content, content.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND), false);

    //
    // Help on wizards
    //
    final IWorkbenchHelpSystem helpSystem = PlatformUI.getWorkbench().getHelpSystem();
    final EClass eclass = object.eClass();

    if ((null != _comp) && (null != _helpListener)) {
      _comp.removeHelpListener(_helpListener);
    }

    _helpListener = new HelpListener() {
      private boolean _isHelpAvailable() {
        return checkIfHelpIsAvailable(eclass);
      }

      protected boolean checkIfHelpIsAvailable(EClass eclass_p) {
        return ((null != eclass_p) && (null != HelpSystem.getHelpContent(generateHTMLLink(eclass_p))));
      }

      public void helpRequested(HelpEvent e_p) {
        IContext c = new IContext() {
          @SuppressWarnings("synthetic-access")
          public String getText() {
            if (!_isHelpAvailable()) {
              return NLS.bind(Messages.editCapellaCustomPropertyWizardPage_MainDescriptionKO, eclass.getName());
            }
            return null;
          }

          @SuppressWarnings("synthetic-access")
          public IHelpResource[] getRelatedTopics() {
            IHelpResource hr = null;
            if (_isHelpAvailable()) {
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

      protected String generateHTMLLink(EClass eclass_p) {
        String result = "/org.polarsys.capella.core.doc.user/html/editors/" + //$NON-NLS-1$
                        eclass_p.getEPackage().getName() + ICommonConstants.SLASH_CHARACTER + eclass_p.getName() + ICommonConstants.POINT_CHARACTER + "html" //$NON-NLS-1$
        ;
        return result;
      }
    };

    _comp = parent_p;
    _comp.addHelpListener(_helpListener);
  }

  /**
   * @see org.eclipse.jface.dialogs.DialogPage#dispose()
   */
  @Override
  public void dispose() {
    if (null != _toolkit) {
      _toolkit.dispose();
      _toolkit = null;
    }
    if ((null != _comp) && (null != _helpListener)) {
      _comp.removeHelpListener(_helpListener);
      _helpListener = null;
      _comp.dispose();
      _comp = null;
    }

    //memory leaks, sections weren't disposed
    if (null != _sections) {
      for (ISection section : _sections) {
        section.dispose();
      }
      _sections = null;
    }

    return;
  }

  /**
   * Get the model element that the wizard is open for.
   * @return a not <code>null</code> element.
   */
  protected EObject getEObject() {
    return ((EditCapellaCustomPropertyWizard) getWizard()).getEObject();
  }

}
