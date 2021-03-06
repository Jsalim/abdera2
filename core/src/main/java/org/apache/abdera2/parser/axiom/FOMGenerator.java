/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package org.apache.abdera2.parser.axiom;

import javax.xml.namespace.QName;

import org.apache.abdera2.common.Constants;
import org.apache.abdera2.common.iri.IRI;
import org.apache.abdera2.model.Element;
import org.apache.abdera2.model.Generator;
import org.apache.axiom.om.OMContainer;
import org.apache.axiom.om.OMException;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMXMLParserWrapper;

public class FOMGenerator extends FOMElement implements Generator {

    private static final long serialVersionUID = -8441971633807437976L;

    public FOMGenerator() {
        super(Constants.GENERATOR);
    }

    public FOMGenerator(String value, String version, String uri) {
        this();
        ((Element)this).setText(value);
        setVersion(version);
        setUri(uri);
    }

    public FOMGenerator(String name, OMNamespace namespace, OMContainer parent, OMFactory factory)
        throws OMException {
        super(name, namespace, parent, factory);
    }

    public FOMGenerator(QName qname, OMContainer parent, OMFactory factory) {
        super(qname, parent, factory);
    }

    public FOMGenerator(QName qname, OMContainer parent, OMFactory factory, OMXMLParserWrapper builder) {
        super(qname, parent, factory, builder);
    }

    public FOMGenerator(OMContainer parent, OMFactory factory) throws OMException {
        super(GENERATOR, parent, factory);
    }

    public FOMGenerator(OMContainer parent, OMFactory factory, OMXMLParserWrapper builder) throws OMException {
        super(GENERATOR, parent, factory, builder);
    }

    public IRI getUri() {
        String value = getAttributeValue(AURI);
        return (value != null) ? new IRI(value) : null;
    }

    public IRI getResolvedUri() {
        return _resolve(getResolvedBaseUri(), getUri());
    }

    public Generator setUri(String uri) {
      complete();
      return setAttributeValue(AURI, uri==null?null:(new IRI(uri)).toString());
    }

    public String getVersion() {
        return getAttributeValue(VERSION);
    }

    public Generator setVersion(String version) {
      complete();
       return setAttributeValue(VERSION,version);
    }

}
