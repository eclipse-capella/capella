/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.handlers.merge;

import org.eclipse.swt.graphics.Image;

public class CategorySet implements ICategorySet {

  private String id;
  private String text; 
  private String description;
  private Image image;
  
  public CategorySet(String id, String text, String description) {
    super();
    this.id = id;
    this.text = text;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public Image getImage() {
    return image;
  }

}
