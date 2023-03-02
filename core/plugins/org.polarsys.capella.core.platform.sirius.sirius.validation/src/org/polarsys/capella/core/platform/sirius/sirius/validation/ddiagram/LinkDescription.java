package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import org.eclipse.emf.ecore.EObject;
import org.xml.sax.Attributes;

public class LinkDescription {
  private final String name;
  private final String href;
  private EObject targetElement;
  private Attributes attributes;

  public LinkDescription(String name, String href, EObject targetElement, Attributes attributes) {
    this.name = name;
    this.href = href;
    this.targetElement = targetElement;
    this.attributes = attributes;
  }

  public String getName() {
    return name;
  }

  public String getHref() {
    return href;
  }

  public EObject getTargetElement() {
    return targetElement;
  }

  public Attributes getAttributes() {
    return attributes;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    return prime + ((href == null) ? 0 : href.hashCode());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || (getClass() != obj.getClass()))
      return false;
    LinkDescription other = (LinkDescription) obj;
    if (href.replace("hlink://", "").equals(other.getHref().replace("hlink://", "")))
      return true;
    return false;
  }
}
