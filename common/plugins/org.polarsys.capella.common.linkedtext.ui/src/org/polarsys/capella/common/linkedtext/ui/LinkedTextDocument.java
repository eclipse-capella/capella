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

package org.polarsys.capella.common.linkedtext.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;


public class LinkedTextDocument extends Document implements ILabelProviderListener {
  
  private static final Logger logger = Logger.getLogger(LinkedTextDocument.class.getName());
  
  /**
   * A parameter interface that describes input from which to derive linked text documents.
   */
  public static interface Input {

    /**
     * Returns the raw linked text
     */
    public String getText();

    /**
     * Set the raw linked text
     */
    public void setText(String linkedText);

    /**
     * @return the base object used for hyperlink resolution.
     */
    public Object getDocumentBase();

    /**
     * @return the label provider used to obtain hyperlink target text representations
     */
    public ILabelProvider getLabelProvider();

    /**
     * @return the resolver used to resolve and deresolve hyperlink references
     */
    public Resolver getResolver();
  }
  
  /**
   * A resolver is responsible to find (resolve) hyperlink targets given a base object and a hyperlink 
   * reference, and also to produce a hyperlink reference from a target object and a base object.
   */
  public static interface Resolver {
    
    /**
     * Given a hyperlink reference and a base object compute and return the target object.
     * @param base the base object
     * @param href the hyperlink reference
     * @return the referenced target object or null if the reference cannot be resolved.
     */
    public Object getTarget(Object base, String href);
    
    /**
     * Given a base and a target object, computes a hyperlink reference
     * @param base the base object
     * @param target the target object
     * @return
     */
    public String getHref(Object base, Object target);
  }

  private static final Pattern pattern = Pattern.compile("<a href=\"([^\"]*)\"/>");
  
  /* The base object. All hrefs are resolved against this object */
  private final Object documentBase;
  
  /* The label provider used to obtain text for hyperlink targets*/
  private final ILabelProvider labelProvider;

  /* The resolver */
  private final Resolver resolver;
  
  /**
   * Create a new instance using a document base and a label provider. The document base
   * is used as a reference element when resolving hyperlink references. The label provider
   * is used to obtain a text presentation for resolved target objects.
   * 
   * @param documentBase the document base
   * @param labelProvider the label provider
   */
  public LinkedTextDocument(Object documentBase, ILabelProvider labelProvider, Resolver resolver){
    this.labelProvider = labelProvider;
    this.documentBase = documentBase;
    this.resolver = resolver;
    if(labelProvider != null) {
      this.labelProvider.addListener(this);      
    }
  }
 
  /**
   * @return the document base object
   */
  public final Object getDocumentBase(){
    return documentBase;
  }
  
  /**
   * @return the document label provider used to obtain text presentation for hyperlink targets
   */
  public final ILabelProvider getLabelProvider(){
    return labelProvider;
  }
  
  /**
   * @return the resolver
   */
  public final Resolver getResolver(){
    return resolver;
  }

  @Override
  protected void completeInitialization(){
    super.completeInitialization();
    addPositionCategory(LinkedTextCategories.HYPERLINK.name());
    addPositionUpdater(new DefaultPositionUpdater(LinkedTextCategories.HYPERLINK.name()));
  }

  /**
   * Load a LinkedTextDocument from an Input.
   * 
   * @param input the input in the given language
   * @return the loaded document
   */
  public static LinkedTextDocument load(Input input) {

    LinkedTextDocument doc = new LinkedTextDocument(input.getDocumentBase(), input.getLabelProvider(),
        input.getResolver());
    List<LinkedTextHyperlink> links = new ArrayList<>();

    if (input.getText() != null) {
      Matcher m = pattern.matcher(input.getText());
      StringBuilder builder = new StringBuilder();
      int offset = 0;
      while (m.find()) {
        int start = m.start();
        if (start > offset) {
          builder.append(StringEscapeUtils.unescapeHtml(input.getText().substring(offset, start)));
        }
        final int labelStart = builder.length();

        Object target = doc.getResolver().getTarget(doc.getDocumentBase(), m.group(1));
        String text = null;
        if (target != null) {
          text = doc.getLabelProvider().getText(target);
        } else {
          text = m.group(1);
        }
        LinkedTextHyperlink hl = LinkedTextHyperlink.create(labelStart, text.length(), target);
        builder.append(text);
        links.add(hl);
        offset = m.end();
      }
      builder.append(StringEscapeUtils.unescapeHtml(input.getText().substring(offset, input.getText().length())));
      doc.set(builder.toString());
      for (Position pos : links) {
        try {
          doc.addPosition(LinkedTextCategories.HYPERLINK.name(), pos);
        } catch (BadLocationException exception) {
          logger.error(exception.getMessage(), exception);
        } catch (BadPositionCategoryException exception) {
          /* there's always a hyperlink category */
        }
      }
    }
    return doc;
  }

  public void insertHyperlink(LinkedTextHyperlink hl) throws BadLocationException {
    stopListenerNotification();
    replace(hl.getOffset(), 0, labelProvider.getText(hl.getTarget()));
    try {
      addPosition(LinkedTextCategories.HYPERLINK.name(), hl);
    } catch (BadPositionCategoryException exception) {
      /* there's always a hyperlink category */
    }
    resumeListenerNotification();
  }

  /**
   * @return
   */
  public Collection<LinkedTextHyperlink> getHyperlinks() {
    Collection<LinkedTextHyperlink> result = new ArrayList<>();
    try {
      for (Position pos : getPositions(LinkedTextCategories.HYPERLINK.name())) {
        if (!pos.isDeleted()) {
          result.add((LinkedTextHyperlink) pos);
        }
      }
    } catch (BadPositionCategoryException exception) {
      /* there's always a hyperlink category */
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void labelProviderChanged(LabelProviderChangedEvent event) {
    for (LinkedTextHyperlink hl : getHyperlinks()){
      String newText = null;
      if (event.getElements() == null && labelProvider != null) {
        newText = labelProvider.getText(hl.getTarget());
      } else {
        for (Object element : event.getElements()){
          if (element == hl.getTarget() && labelProvider != null){
            newText = labelProvider.getText(hl.getTarget());
            break;
          }
        }
      }
      if (newText != null){
        try {
          replace(hl.getOffset(), hl.getLength(), newText);
        } catch (BadLocationException exception) {
          logger.error(exception.getMessage(), exception);
        }
      }
    }
  }

  /**
   * Disposes the document. This unregisters the documents label provider listener from the hyperlink label provider
   */
  public void dispose(){
    labelProvider.removeListener(this);
  }

  /**
   * Saves this document back into raw text representation.
   */
  public String saveToRaw() {

    StringBuilder builder = new StringBuilder();

    int offset = 0;
    for (Iterator<LinkedTextHyperlink> it = getHyperlinks().iterator(); it.hasNext();) {
      LinkedTextHyperlink current = it.next();
      try {
        String segment = get(offset, current.getOffset() - offset);
        builder.append(StringEscapeUtils.escapeHtml(segment));
      } catch (BadLocationException exception) {
        logger.error(exception.getMessage(), exception);
      }

      String href = null;
      if (current.getTarget() == null) {
        try {
          href = get(current.getOffset(), current.getLength());
        } catch (BadLocationException exception) {
          logger.error(exception.getMessage(), exception);
        }
      } else {
        href = getResolver().getHref(getDocumentBase(), current.getTarget());
      }

      builder.append("<a href=\"") //$NON-NLS-1$
          .append(href).append("\"/>"); //$NON-NLS-1$
      offset = current.getOffset() + current.getLength();
    }

    try {
      String segment = get(offset, getLength() - offset);
      builder.append(StringEscapeUtils.escapeHtml(segment));
    } catch (BadLocationException exception) {
      logger.error(exception.getMessage(), exception);
    }

    return builder.toString();
  }
}
