/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.services.helper;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import org.polarsys.capella.common.mdsofa.common.misc.Couple;

/**
 * UI Forms helper.<br>
 * Allows creation of Composites, Layouts and Forms widgets.
 */
public class FormHelper {
  /**
   * Layout usable types.
   */
  public static enum LayoutType {
    GRID_LAYOUT, TABLEWRAP_LAYOUT
  }

  /**
   * Create a new composite and set the layout using
   * {@link #updateCompositeLayoutWithLayoutType(Composite, org.polarsys.capella.common.mdsofa.common.ui.helper.FormHelper.LayoutType, int)} method.
   * @param toolkit
   * @param parent
   * @param numColumns
   * @return
   */
  public static Composite createCompositeWithLayoutType(FormToolkit toolkit, Composite parent, LayoutType layoutType, int numColumns,
      boolean equalWidth) {
    Composite result = toolkit.createComposite(parent);
    updateCompositeLayoutWithLayoutType(result, layoutType, numColumns, equalWidth);
    return result;
  }

  /**
   * Create a user text widget with preceding label.<br>
   * Requires at least a two columns layout so that both the label and the text are displayed on the same line.
   * @param toolkit
   * @param parent
   * @param labelMessage
   * @param initialText
   * @param editable
   * @return
   */
  public static Couple<Label, Text> createLabelAndText(FormToolkit toolkit, Composite parent, String labelMessage, String initialText,
      boolean editable) {
    // Create label.
    Label label = toolkit.createLabel(parent, labelMessage, SWT.WRAP);
    label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
    // Create text.
    Text text = new Text(parent, SWT.BORDER);
    text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    // Set existing value.
    if (null != initialText) {
      text.setText(initialText);
    }
    // Set editable state.
    text.setEditable(editable);
    return new Couple<Label, Text>(label, text);
  }

  /**
   * Create a user text widget with preceding label and following button.<br>
   * Requires at least a three columns layout so that the label, the text and the button are displayed on the same line.
   * @param toolkit
   * @param parent
   * @param labelMessage
   * @param buttonLabel
   * @param listener
   * @return
   */
  public static Couple<Text, Button> createLabelTextAndButton(FormToolkit toolkit, Composite parent, String labelMessage, String buttonLabel,
      SelectionListener listener) {
    // Create label and text.
    Couple<Label, Text> labelAndText = createLabelAndText(toolkit, parent, labelMessage, null, true);
    // Create button.
    Button button = toolkit.createButton(parent, buttonLabel, SWT.PUSH);
    // Add button listener.
    if (null != listener) {
      button.addSelectionListener(listener);
    }
    return new Couple<Text, Button>(labelAndText.getValue(), button);
  }

  /**
   * Create a link with a label description.<br>
   * Requires a two columns layout so that both the link and the label are displayed on the same line.
   * @param toolkit
   * @param parent
   * @param icon
   * @param linkText
   * @param linkRef
   * @param linkDescription can be <code>null</code>, if not necessary. Description is rendered as a tooltip.
   * @param listener
   */
  public static void createLinkWithDescription(FormToolkit toolkit, Composite parent, Image icon, String linkText, Object linkRef,
      String linkDescription, IHyperlinkListener listener) {
    ImageHyperlink specificationLink = toolkit.createImageHyperlink(parent, SWT.WRAP);
    specificationLink.setText(linkText);
    if (null != linkDescription) {
      specificationLink.setToolTipText(linkDescription);
    }
    specificationLink.setImage(icon);
    specificationLink.setHref(linkRef);
    specificationLink.addHyperlinkListener(listener);
  }

  /**
   * Create a rich {@link FormText} with specified parameters.
   * @param toolkit
   * @param parent
   * @param content
   * @param listener
   */
  public static FormText createRichText(FormToolkit toolkit, Composite parent, String content, IHyperlinkListener listener) {
    // Create the form text.
    FormText text = toolkit.createFormText(parent, false);
    if (null != content) {
      try {
        // Set its pseudo HTML content.
        text.setText(content, true, false);
      } catch (SWTException e) {
        text.setText(e.getMessage(), false, false);
      }
    }
    if (null != listener) {
      text.addHyperlinkListener(listener);
    }
    return text;
  }

  /**
   * Create a section with a composite child using given child layout type.<br>
   * Created section layouts in filling and grabbing in both directions.
   * @param toolkit
   * @param parent
   * @param sectionStyle
   * @param layoutType
   * @param childNumColumns
   * @param equalWidth
   * @return
   */
  public static Couple<Section, Composite> createSectionWithChildComposite(FormToolkit toolkit, Composite parent, int sectionStyle,
      LayoutType layoutType, int childNumColumns, boolean equalWidth) {
    Section resultingSection = toolkit.createSection(parent, sectionStyle);
    updateControlLayoutDataWithLayoutTypeData(resultingSection, layoutType);
    Composite childComposite = createCompositeWithLayoutType(toolkit, resultingSection, layoutType, childNumColumns, equalWidth);
    resultingSection.setClient(childComposite);
    return new Couple<Section, Composite>(resultingSection, childComposite);
  }

  /**
   * Create a section with specified title and description.<br>
   * The created section layouts its content according to the {@link TableWrapLayout}.
   * @param toolkit
   * @param parent
   * @param title
   * @param description can be <code>null</code>
   * @return a {@link Couple} containing the child composite and the created section.
   */
  public static Couple<Section, Composite> createSectionWithDescription(FormToolkit toolkit, Composite parent, String title, String description) {
    // Create the section style.
    int sectionStyle = ExpandableComposite.TITLE_BAR;
    if (null != description) {
      // Add description style.
      sectionStyle |= Section.DESCRIPTION;
    }
    // Create the section.
    Couple<Section, Composite> createdSectionWithChildComposite =
        createSectionWithChildComposite(toolkit, parent, sectionStyle, LayoutType.GRID_LAYOUT, 1, true);
    // Get created section.
    Section section = createdSectionWithChildComposite.getKey();
    // Set it its title.
    section.setText(title);
    // Set it its description if necessary.
    if (null != description) {
      section.setDescription(description);
    }
    return createdSectionWithChildComposite;
  }

  /**
   * Create a section with specified title and description.<br>
   * The created section layouts its content according to the {@link TableWrapLayout}.
   * @param managedForm
   * @param parent
   * @param title
   * @param description can be <code>null</code>
   * @return a {@link Couple} containing the child composite and the created section.
   */
  public static Couple<Section, Composite> createTwistieSectionWithDescription(final IManagedForm managedForm, Composite parent, String title,
      String description) {
    // Create the section style.
    int sectionStyle = ExpandableComposite.TWISTIE | ExpandableComposite.TITLE_BAR;
    if (null != description) {
      // Add description style.
      sectionStyle |= Section.DESCRIPTION;
    }
    // Create the section.
    Couple<Section, Composite> createdSectionWithChildComposite =
        createSectionWithChildComposite(managedForm.getToolkit(), parent, sectionStyle, LayoutType.TABLEWRAP_LAYOUT, 1, true);
    // Get created section.
    Section section = createdSectionWithChildComposite.getKey();
    section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
    // Set it its title.
    section.setText(title);
    // Set it its description if necessary.
    if (null != description) {
      section.setDescription(description);
    }
    // Handle expansion.
    section.addExpansionListener(new ExpansionAdapter() {
      @Override
      public void expansionStateChanged(ExpansionEvent e) {
        managedForm.reflow(false);
      }
    });
    return createdSectionWithChildComposite;
  }

  /**
   * Create a twistie section with a title, a description and a toolbar.
   * @param sectionContainer
   * @param managedForm
   * @param title
   * @param description can be <code>null</code>
   * @param isExpanded Is the section expanded at startup.
   * @param toolbarActions can be <code>null</code>
   * @return
   */
  public static Couple<Section, Composite> createTwistieSectionWithToolbar(Composite sectionContainer, IManagedForm managedForm, String title,
      String description, boolean isExpanded, List<? extends IAction> toolbarActions) {
    // Create the section.
    Couple<Section, Composite> createdSection = FormHelper.createTwistieSectionWithDescription(managedForm, sectionContainer, title, description);
    // Make this section expanded at construction time.
    Section section = createdSection.getKey();
    section.setExpanded(isExpanded);
    // Create the toolbar for the section.
    if (null != toolbarActions) {
      createSectionToolbar(section, toolbarActions);
    }
    return createdSection;
  }

  /**
   * Force control size.
   * @param control
   * @param widthInChars The expected width, in number of chars to display.
   * @param heightInChars The expected height, in number of chars to display.
   */
  public static void forceControlSize(Control control, int widthInChars, int heightInChars) {
    // Preconditions.
    if ((null == control) || (0 >= widthInChars) || (0 >= heightInChars)) {
      return;
    }
    // Get font metrics.
    GC gc = new GC(control);
    FontMetrics fontMetrics = gc.getFontMetrics();
    gc.dispose();
    // Get layout data.
    Object layoutData = control.getLayoutData();
    if (layoutData instanceof GridData) {
      GridData data = (GridData) layoutData;
      data.widthHint = Dialog.convertWidthInCharsToPixels(fontMetrics, widthInChars);
      data.heightHint = Dialog.convertHeightInCharsToPixels(fontMetrics, heightInChars);
    } else if (layoutData instanceof TableWrapData) {
      TableWrapData data = (TableWrapData) layoutData;
      data.heightHint = Dialog.convertHeightInCharsToPixels(fontMetrics, heightInChars);
    }
  }

  /**
   * Update given composite with given layout type and given number of columns (if it makes any sense).<br>
   * Also set the layout data to
   * {@link #updateControlLayoutDataWithLayoutTypeData(Composite, org.polarsys.capella.common.mdsofa.common.ui.helper.FormHelper.LayoutType)}.
   * @param composite
   * @param layoutType
   * @param numColumns
   */
  public static Object updateCompositeLayoutWithLayoutType(Composite composite, LayoutType layoutType, int numColumns, boolean equalWidth) {
    Layout selectedLayout = null;
    if (LayoutType.GRID_LAYOUT.equals(layoutType)) {
      GridLayout layout = new GridLayout();
      layout.numColumns = numColumns;
      layout.makeColumnsEqualWidth = equalWidth;
      selectedLayout = layout;
    } else if (LayoutType.TABLEWRAP_LAYOUT.equals(layoutType)) {
      TableWrapLayout layout = new TableWrapLayout();
      layout.numColumns = numColumns;
      layout.makeColumnsEqualWidth = equalWidth;
      selectedLayout = layout;
    }
    // Do not set neither layout if layout could not be created.
    if (null != selectedLayout) {
      composite.setLayout(selectedLayout);
    }
    return selectedLayout;
  }

  /**
   * Update given control layout data depending on given layout type.<br>
   * Replace layout data is set to fill/grab in both directions, if it makes any sense.
   * @param control
   */
  public static Object updateControlLayoutDataWithLayoutTypeData(Control control, LayoutType layoutType) {
    Object layoutData = null;
    if (LayoutType.GRID_LAYOUT.equals(layoutType)) {
      layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
    } else if (LayoutType.TABLEWRAP_LAYOUT.equals(layoutType)) {
      layoutData = new TableWrapData(TableWrapData.FILL_GRAB);
    }
    // Do not set layout data if it could not be created.
    if (null != layoutData) {
      control.setLayoutData(layoutData);
    }
    return layoutData;
  }

  /**
   * Create a section toolbar in given section filled in with provided actions.
   * @param section
   * @param actions
   */
  public static void createSectionToolbar(Section section, List<? extends IAction> actions) {
    // Preconditions.
    if ((null == actions) || actions.isEmpty() || (null == section)) {
      return;
    }
    // Create a toolbar manager.
    ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
    // Create the widget against the section widget.
    ToolBar toolbar = toolBarManager.createControl(section);
    // Create cursor to provide the end-user with an UI feedback.
    final Cursor handCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_HAND);
    toolbar.setCursor(handCursor);
    // Cursor needs to be explicitly disposed
    toolbar.addDisposeListener(new DisposeListener() {
      public void widgetDisposed(DisposeEvent e) {
        if ((null != handCursor) && !handCursor.isDisposed()) {
          handCursor.dispose();
        }
      }
    });
    // Loop over provided actions.
    for (IAction action : actions) {
      toolBarManager.add(action);
    }
    toolBarManager.update(true);
    section.setTextClient(toolbar);
  }

  /**
   * Adapt specified composite to {@link FormToolkit} recursively.
   * @param toolkit
   * @param composite
   */
  public static void adaptRecursively(FormToolkit toolkit, Composite composite) {
    // Preconditions.
    if ((null == composite) || (null == toolkit)) {
      return;
    }
    toolkit.adapt(composite, true, true);
    Control[] children = composite.getChildren();
    for (Control control : children) {
      if (null != control) {
        toolkit.adapt(control, true, true);
        if (control instanceof Composite) {
          adaptRecursively(toolkit, (Composite) control);
        }
      }
    }
  }

  /**
   * Adapt background color of specified composite and its children.
   * @param composite
   * @param color
   * @param ignoreEditableTextField <code>true</code> means editable text field won't be modified.
   */
  @Deprecated
  public static void adaptBackgroundColor(Composite composite, Color color, boolean ignoreEditableTextField) {
    adaptBackgroundColor((Control)composite, color, ignoreEditableTextField);
  }

  public static void adaptBackgroundColor(Control composite, Color color, boolean ignoreEditableTextField) {
    boolean applyColor = true;
    if ((composite instanceof Text) && !ignoreEditableTextField && ((Text) composite).getEditable()) {
      applyColor = false;
    }
    if ((composite instanceof StyledText && !ignoreEditableTextField && ((StyledText) composite).getEditable())) {
      applyColor = false;
    }
    if (composite instanceof Table && composite.isEnabled()) {
      applyColor = false;
    }
    if (composite instanceof Tree && composite.isEnabled()) {
      applyColor = false;
    }
    if ((composite instanceof CCombo)) {
      applyColor = false;
    }
    if (composite instanceof Label) {
      if (((Label) composite).getImage() != null) {
        applyColor = false;
      }
    }

    if (applyColor) {
      composite.setBackground(color);
    }

    if (composite instanceof Composite) {
      Control[] children = ((Composite) composite).getChildren();
      for (Control control : children) {
        adaptBackgroundColor(control, color, ignoreEditableTextField);
      }
    }
  }
}
