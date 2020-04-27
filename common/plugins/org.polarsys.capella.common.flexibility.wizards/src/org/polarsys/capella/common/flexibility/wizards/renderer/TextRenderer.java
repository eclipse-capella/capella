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

package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.constants.ICommonConstants;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.util.StatusLabelHelper;
import org.polarsys.capella.common.ui.services.swt.events.AbstractKeyAdapter;

/**
 */
public class TextRenderer extends AbstractRenderer {

  protected Composite rootTextControl;
  protected Composite rootControl;
  protected Text textControl;
  protected Label imageControl;
  protected Label validateControl;
  Label label;

  Color errorColor;
  Color warningColor;
  Color defaultColor;
  Color disabledColor;

  public Label createPartLabel(Composite parent, String text, boolean required) {
    label = new Label(parent, 0);
    label.setText(text);

    if (required) {
      label.setFont(JFaceResources.getFontRegistry().getBold("org.eclipse.jface.defaultfont")); //$NON-NLS-1$
    }
    return label;
  }

  /**
   * @return
   */
  protected Color getDefaultColor(Display display) {
    return display.getSystemColor(SWT.COLOR_WHITE);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.wizards.schema.IRenderer#render(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void performRender(Composite parent, IRendererContext context) {
    Display display = parent.getShell().getDisplay();

    if (isColoredOnValidation()) {
      defaultColor = getDefaultColor(display);
      errorColor = defaultColor;
      warningColor = defaultColor;
      disabledColor = defaultColor;
    }

    if (isDescription() && !Boolean.FALSE.equals(context.getParameter(ICommonConstants.PARAMETER_RENDER_LABEL))) {
      createPartLabel(parent, context.getProperty(this).getName() + ICommonConstants.COLON_CHARACTER, false);
    }

    rootControl = new Composite(parent, SWT.NONE);

    rootTextControl = new Composite(rootControl, getRootStyle()) {

      /**
       * {@inheritDoc}
       */
      @Override
      public void setBackground(Color color) {
        // Avoid default background color to be set
        if ((color != null) && !color.equals(getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND))) {
          super.setBackground(color);
        }
      }

    };

    if (parent.getLayout() instanceof GridLayout) {
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      GridLayout layout = new GridLayout();
      layout.numColumns = getNbColumn();
      layout.makeColumnsEqualWidth = false;
      layout.marginWidth = 0;
      layout.marginHeight = 0;
      rootControl.setLayoutData(data);
      rootControl.setLayout(layout);
    } else {
      rootControl.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    if (parent.getLayout() instanceof GridLayout) {
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      GridLayout layout = new GridLayout();
      layout.numColumns = getNbTextColumn();
      layout.makeColumnsEqualWidth = false;
      layout.marginWidth = 4;
      layout.marginHeight = 2;
      rootTextControl.setLayoutData(data);
      rootTextControl.setLayout(layout);
    } else {
      rootTextControl.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    initializeControls(rootControl, context);
  }

  /**
   * @return
   */
  protected int getRootStyle() {
    return SWT.BORDER;
  }

  protected boolean isValidationEnd() {
    return true;
  }

  /**
   * @param parent
   * @param context
   */
  protected void initializeControls(Composite parent, final IRendererContext context) {

    if (isImage()) {
      imageControl = createImageControl(rootTextControl);
    }

    if (!isValidationEnd()) {
      validateControl = createImageControl(rootTextControl);
    }
    textControl = createTextControl(rootTextControl);

    if (parent.getLayout() instanceof GridLayout) {
      textControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }
    textControl.addKeyListener(new AbstractKeyAdapter() {
      /**
       * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
       */
      @Override
      public void keyPressed(KeyEvent event) {
        if ((event.character == SWT.CR)) {
          if (handle(event, IKeyLookup.CR_NAME)) {
            handleCRKeyStoke(context);
          }
        }
      }
    });

    textControl.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent event) {
        textControl.forceFocus();
        if (isDisposed()) {
          return;
        }
        if (isEditable(context.getProperty(TextRenderer.this), context)) {
          textControl.setText(getEditableText(context));
        }
      }

      public void focusLost(FocusEvent event) {
        if (isDisposed()) {
          return;
        }
        textChanged(context);
      }

    });

    if (isValidationEnd()) {
      validateControl = createImageControl(rootTextControl);
    }
    StatusLabelHelper.updateImage(Status.OK_STATUS, validateControl);
  }

  protected void handleCRKeyStoke(IRendererContext context) {
    textChanged(context);
  }

  /**
   * 
   */
  protected void textChanged(IRendererContext context) {
    IProperty property = context.getProperty(TextRenderer.this);
    if (isEditable(property, context)) {
      String newValue = textControl.getText();
      if (!newValue.equals(context.getPropertyContext().getCurrentValue(property))) {
        changeValue(property, context, newValue);
        updatedValue(property, context, newValue);
      }
    }
  }

  /**
   * @return
   */
  protected boolean isImage() {
    return false;
  }

  /**
   * @return
   */
  protected boolean isDescription() {
    return true;
  }

  /**
   * @return
   */
  protected int getNbColumn() {
    return 1;
  }

  /**
   * @return
   */
  protected int getNbTextColumn() {
    return 1 + (isImage() ? 1 : 0) + 1;
  }

  /**
   * @return
   */
  protected String getEditableText(IRendererContext context) {
    return textControl.getText();
  }

  /**
   * @param parent
   * @return
   */
  private Label createImageControl(Composite parent) {
    return new Label(parent, SWT.NONE);
  }

  /**
   * @param parent
   */
  protected Text createTextControl(Composite parent) {
    return new Text(parent, SWT.NONE);
  }

  public void initialize(IProperty property, IRendererContext context) {
    setBackgroundTextControl(defaultColor);
    Object value = context.getPropertyContext().getDefaultValue(property);
    updatedValue(property, context, value);
  }

  /**
   * @param property
   * @return
   */
  protected boolean isEditable(IProperty property, IRendererContext context) {
    return (property instanceof IEditableProperty) && property.isEnabled(context.getPropertyContext());
  }

  @Override
  public void updatedValue(IProperty property, IRendererContext rendererContext, Object newValue) {

    if (isDisposed()) {
      return;
    }

    if (property.equals(rendererContext.getProperty(this))) {
      Object value = property.toType(newValue, rendererContext.getPropertyContext());
      IStatus diag = property.validate(value, rendererContext.getPropertyContext());

      StatusLabelHelper.updateTooltip(diag, validateControl, true);
      String text = getLabelProvider(rendererContext).getText(value);

      if (isImage() && !imageControl.isDisposed()) {
        Image image = getLabelProvider(rendererContext).getImage(value);
        if ((image != null) && !image.equals(imageControl.getImage())) {
          imageControl.setImage(image);
        }
      }

      if (text == null) {
        text = "";
      }
      if ((label != null) && !label.isDisposed()) {
        label.setEnabled(property.isEnabled(rendererContext.getPropertyContext()));
      }

      if (!textControl.isDisposed()) {
        textControl.setEditable(isEditable(property, rendererContext));
        textControl.setText(text);

        if (isColoredOnValidation()) {
          StatusLabelHelper.updateImage(diag, validateControl);

          if (diag != null) {
            if (diag.isOK()) {
              if (!defaultColor.equals(rootTextControl.getBackground())) {
                setBackgroundTextControl(defaultColor);
              }
              if (!isEditable(property, rendererContext)) {
                if (!disabledColor.equals(rootTextControl.getBackground())) {
                  setBackgroundTextControl(disabledColor);
                }
              }
            } else if (diag.matches(IStatus.INFO)) {
              if (!defaultColor.equals(rootTextControl.getBackground())) {
                setBackgroundTextControl(defaultColor);
              }
            } else if (diag.matches(IStatus.WARNING)) {
              if (!warningColor.equals(rootTextControl.getBackground())) {
                setBackgroundTextControl(warningColor);
              }
            } else if (diag.matches(IStatus.ERROR)) {
              if (!errorColor.equals(rootTextControl.getBackground())) {
                setBackgroundTextControl(errorColor);
              }
            }
          }
        }
      }
    }
  }

  /**
   * @param color
   */
  protected void setBackgroundTextControl(Color color) {
    textControl.setBackground(color);
    rootTextControl.setBackground(color);
    validateControl.setBackground(color);

    if (isImage()) {
      imageControl.setBackground(color);
    }

  }

  protected boolean isColoredOnValidation() {
    return true;
  }

  @Override
  public void dispose(IRendererContext context) {
    super.dispose(context);
    if (textControl != null) {
      textControl.dispose();
    }
    if (imageControl != null) {
      imageControl.dispose();
    }
    if (defaultColor != null) {
      defaultColor.dispose();
    }
    if (errorColor != null) {
      errorColor.dispose();
    }
    if (warningColor != null) {
      warningColor.dispose();
    }
    if (disabledColor != null) {
      disabledColor.dispose();
    }
  }

}
