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
package org.apache.abdera2.ext.serializer.impl;

import org.apache.abdera2.common.Constants;
import org.apache.abdera2.ext.serializer.Conventions;
import org.apache.abdera2.ext.serializer.ObjectContext;
import org.apache.abdera2.ext.serializer.SerializationContext;
import org.apache.abdera2.ext.serializer.annotation.Accept;
import org.apache.abdera2.ext.serializer.annotation.Categories;
import org.apache.abdera2.ext.serializer.annotation.Title;

public class CollectionSerializer extends ElementSerializer {

    public CollectionSerializer() {
        super(Constants.COLLECTION);
    }

    protected void process(Object source,
                           ObjectContext objectContext,
                           SerializationContext context,
                           Conventions conventions) {
        writeAttributes(source, objectContext, context, conventions);
        writeElement(Title.class, new TextSerializer(Constants.TITLE), source, objectContext, context, conventions);
        writeElements(Accept.class,
                      new SimpleElementSerializer(Constants.ACCEPT),
                      source,
                      objectContext,
                      context,
                      conventions);
        writeElements(Categories.class, new CategoriesSerializer(), source, objectContext, context, conventions);
        writeExtensions(source, objectContext, context, conventions);
    }
}
